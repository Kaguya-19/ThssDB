package cn.edu.thssdb.index;

import cn.edu.thssdb.schema.Row;
import cn.edu.thssdb.schema.Table;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BufferManager {
  public static final Integer BufferSize = 100;

  private final String tableName;
  private final String tableDir;

  ReentrantReadWriteLock tableLock;
  ArrayList<ArrayList<Row>> buffer = new ArrayList<>();
  ArrayList<Integer> bufferPageIndex = new ArrayList<>();

  List<Boolean> writeFlag = Collections.synchronizedList(new ArrayList<Boolean>());

  public BufferManager(Table table) {
    tableName = table.tableName;
    tableDir = table.tablePath;
    tableLock = table.lock;
  }

  private void writeIO(Integer index, ArrayList<Row> page) {
    File folder = new File(tableDir);
    if (!folder.exists()) {
      folder.mkdirs();
    }

    File pagePath = new File(getPagePath(index));
    if (!pagePath.exists()) {
      try {
        pagePath.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    try {
      FileOutputStream fileOutputStream = new FileOutputStream(pagePath);
      BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);

      for (Row row : page) {
        objectOutputStream.writeObject(row);
      }

      objectOutputStream.close();
      bufferedOutputStream.flush();
      bufferedOutputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void dropPage() {
    boolean found = false;
    for (int i = 0; i < buffer.size(); i++) {
      if (!writeFlag.get(i)) {
        //        writeIO(bufferPageIndex.get(i), buffer.get(i));
        buffer.remove(i);
        bufferPageIndex.remove(i);
        writeFlag.remove(i);
        found = true;
        if (buffer.size() <= BufferSize) break;
      }
    }
    assert buffer.size() == bufferPageIndex.size();
    assert buffer.size() == writeFlag.size();
    if (!found) System.out.println("No page to drop");
  }

  private ArrayList<Row> getPage(Integer pageIndex) {
    File folder = new File(tableDir);
    if (!folder.exists()) {
      folder.mkdirs();
    }

    File pagePath = new File(getPagePath(pageIndex));
    if (!pagePath.exists()) {
      try {
        pagePath.createNewFile();
        return new ArrayList<>();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    ArrayList<Row> res = new ArrayList<>();
    try {
      FileInputStream fileInputStream = new FileInputStream(pagePath);

      if (fileInputStream.available() <= 0) {
        return res;
      }

      BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
      ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);

      Object inputObject;
      while (fileInputStream.available() > 0) {
        inputObject = objectInputStream.readObject();
        res.add((Row) inputObject);
      }

      objectInputStream.close();
      bufferedInputStream.close();
      fileInputStream.close();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return res;
  }

  public void writePage(Integer pageIndex, ArrayList<Row> t_data) {
    ArrayList<Row> data = new ArrayList<>(t_data);
    if (bufferPageIndex.contains(pageIndex)) {
      buffer.set(bufferPageIndex.indexOf(pageIndex), data);
      writeFlag.set(bufferPageIndex.indexOf(pageIndex), true);
      assert buffer.size() == bufferPageIndex.size();
      assert buffer.size() == writeFlag.size();
      return;
    }
    buffer.add(data);
    bufferPageIndex.add(pageIndex);
    writeFlag.add(true);
    assert buffer.size() == bufferPageIndex.size();
    assert buffer.size() == writeFlag.size();
    // buffer full
    if (buffer.size() > BufferSize) {
      dropPage();
    }
  }

  public ArrayList<Row> readPage(Integer pageIndex) {
    synchronized (this) {
      if (bufferPageIndex.contains(pageIndex)) {
        assert buffer.size() == bufferPageIndex.size();
        assert buffer.size() == writeFlag.size();
        return new ArrayList<>(buffer.get(bufferPageIndex.indexOf(pageIndex)));
      }

      ArrayList<Row> page = getPage(pageIndex);

      buffer.add(page);
      bufferPageIndex.add(pageIndex);
      writeFlag.add(false);
      //    if (buffer.size() > BufferSize) dropPage();
      assert buffer.size() == bufferPageIndex.size();
      assert buffer.size() == writeFlag.size();
      return new ArrayList<>(page);
    }
  }

  public void writeAllDirty() {
    assert tableLock.isWriteLockedByCurrentThread();
    assert buffer.size() == bufferPageIndex.size();
    assert buffer.size() == writeFlag.size();
    for (int i = 0; i < buffer.size(); i++) {
      if (writeFlag.get(i)) {
        writeIO(bufferPageIndex.get(i), buffer.get(i));
        writeFlag.set(i, false);
      }
    }
  }

  public void deletePage(Integer index) {
    if (bufferPageIndex.contains(index)) {
      int indexInBuffer = bufferPageIndex.indexOf(index);
      bufferPageIndex.remove(indexInBuffer);
      buffer.remove(indexInBuffer);
      writeFlag.remove(indexInBuffer);
    }
  }

  private String getPagePath(Integer page) {
    return tableDir + File.separator + tableName + "_page_" + page.toString();
  }
}
