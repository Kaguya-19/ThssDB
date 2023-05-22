package cn.edu.thssdb.schema;

import cn.edu.thssdb.Global;
import cn.edu.thssdb.index.BPlusTree;
import cn.edu.thssdb.utils.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Table implements Iterable<Row> {
  ReentrantReadWriteLock lock;
  private String databaseName;
  public String tableName;
  public ArrayList<Column> columns;
  public BPlusTree<Entry, Row> index;
  private int primaryIndex = 0;

  private String tablePath;

  public Table(String databaseName, String tableName, Column[] columns, int primaryIndex) {
    this.databaseName = databaseName;
    this.tableName = tableName;
    this.columns = new ArrayList<>(Arrays.asList(columns));
    this.index = new BPlusTree<>();
    this.primaryIndex = primaryIndex;
    // TODO
    this.tablePath = Global.TABLE_DIR + databaseName + File.separator + tableName + ".table";
  }

  private void persist() throws IOException {
    // TODO
    serialize();
  }

  private void load() {
    // TODO
    try {
      ArrayList<Row> rows = deserialize();
      for (Row row : rows) {
        index.put(row.getEntries().get(primaryIndex), row);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void recover() {
    // TODO
    if (new File(tablePath).exists()) {
      load();
    }
  }

  public void insert(Row row) {
    // TODO
    // check primary key
    //    if (index.get(row.getEntries().get(primaryIndex)) != null) {
    //      throw new RuntimeException("Primary Key Error");
    //    }
    index.put(row.getEntries().get(primaryIndex), row);
  }

  public void delete(Row row) {
    // TODO
    index.remove(row.getEntries().get(primaryIndex));
  }

  public void update(Row oldRow, Row newRow) {
    // TODO
    delete(oldRow);
    insert(newRow);
  }

  private void serialize() throws IOException {
    // TODO
    File file = new File(tablePath);
    if (!file.exists()) {
      file.createNewFile();
    }
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
    Iterator<Row> iterator = iterator();
    while (iterator.hasNext()) {
      Row row = iterator.next();
      objectOutputStream.writeObject(row);
    }
    objectOutputStream.close();
    fileOutputStream.close();
  }

  private ArrayList<Row> deserialize() throws IOException {
    // TODO
    ArrayList<Row> rows = new ArrayList<>();
    FileInputStream fileInputStream = new FileInputStream(tablePath);
    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
    try {
      while (true) {
        Row row = (Row) objectInputStream.readObject();
        rows.add(row);
      }
    } catch (EOFException | ClassNotFoundException e) {
      objectInputStream.close();
      fileInputStream.close();
      return rows;
    }
  }

  public ArrayList<Column> getColumns() {
    return columns;
  }

  public ArrayList<String> getColumnNames() {
    ArrayList<String> names = new ArrayList<>();
    for (Column column : columns) {
      names.add(column.getName());
    }
    return names;
  }

  public Column getColumnByName(String name) {
    for (Column column : columns) {
      if (column.getName().equals(name)) {
        return column;
      }
    }
    return null;
  }

  private class TableIterator implements Iterator<Row> {
    private Iterator<Pair<Entry, Row>> iterator;

    TableIterator(Table table) {
      this.iterator = table.index.iterator();
    }

    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }

    @Override
    public Row next() {
      return iterator.next().right;
    }
  }

  @Override
  public Iterator<Row> iterator() {
    return new TableIterator(this);
  }

  @Override
  public String toString() {
    String buffer = "\t" + tableName;
    for (Column column : columns) {
      buffer = buffer.concat("\n\t\t" + column.toString());
    }
    return buffer;
  }
}
