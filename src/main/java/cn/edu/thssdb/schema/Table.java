package cn.edu.thssdb.schema;

import cn.edu.thssdb.index.BPlusTree;
import cn.edu.thssdb.utils.Pair;
import cn.edu.thssdb.index.BPlusTreeIterator;
import cn.edu.thssdb.exception.*;
import cn.edu.thssdb.type.ColumnType;
// import cn.edu.thssdb.type.ComparisonType;
import cn.edu.thssdb.utils.Global;
import java.io.*;
import javafx.util.Pair;
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
  private int primaryIndex;
  private PersistentStorage<Row> persistentStorageData; // 数据持久化
  private Meta tableMeta;
  private String folder;

  public Table(String databaseName, String tableName, Column[] columns) {
    this.databaseName = databaseName;
    this.tableName = tableName;
    this.columns = new ArrayList<>(Arrays.asList(columns));
    // TODO
    initData(databaseName, tableName);
    // this.lock = new ReentrantReadWriteLock();
    this.index = new BPlusTree<>();
    recoverMeta();
    recover(deserialize());

  }

    /**
   * [method] 恢复metadata
   * @exception MetaFileNotFoundException, CustomIOException
   */
  private void recoverMeta() {
    ArrayList<String []> meta_data = this.tableMeta.readFromFile();
    try {
      String [] database_name = meta_data.get(0);
      if (!database_name[0].equals(Global.DATABASE_NAME_META)) {
        throw new WrongMetaFormatException();
      }
      if (!this.databaseName.equals(database_name[1])) {
        throw new WrongMetaFormatException();
      }
    } catch (Exception e) {
      throw new WrongMetaFormatException();
    }

    try {
      String [] table_name = meta_data.get(1);
      if (!table_name[0].equals(Global.TABLE_NAME_META)) {
        throw new WrongMetaFormatException();

      }
      if (!this.tableName.equals(table_name[1])) {
        throw new WrongMetaFormatException();
      }
    } catch (Exception e) {
      throw new WrongMetaFormatException();
    }

    try {
      String [] primary_key = meta_data.get(2);
      if (!primary_key[0].equals(Global.PRIMARY_KEY_INDEX_META)) {
        throw new WrongMetaFormatException();
      }
      this.primaryIndex = Integer.parseInt(primary_key[1]);
    } catch (Exception e) {
      throw new WrongMetaFormatException();
    }
    for (int i = 3; i < meta_data.size(); i++) {
      String [] column_info = meta_data.get(i);
      try {
        String name = column_info[0];
        ColumnType type = ColumnType.string2ColumnType(column_info[1]);
        boolean primary = column_info[2].equals("true");
        boolean notNull = column_info[3].equals("true");
        int maxLength = Integer.parseInt(column_info[4]);
        this.columns.add(new Column(name, type, primary, notNull, maxLength));
      } catch (Exception e) {
        throw new WrongMetaFormatException();
      }
    }
  }

  /**
   * [method] 初始化元数据和数据存储相关
   * @param databaseName {String} 数据库名称
   * @param tableName {String} 表名称
   * @exception IllegalArgumentException
   */
  private void initData(String databaseName, String tableName) throws CustomIOException {
    this.databaseName = databaseName;
    this.tableName = tableName;
    folder = Paths.get(Global.DATA_ROOT_FOLDER, databaseName, tableName).toString();
    String meta_name = tableName + ".meta";
    String data_name = tableName + ".data";
    this.persistentStorageData = new PersistentStorage<>(folder, data_name);
    this.tableMeta = new Meta(folder, meta_name);
  }


  /**
   * [method] 恢复表
   * [note] 从持久化数据中恢复表
   */
  private synchronized void recover(ArrayList<Row> rows) {
    // TODO
    for(Row row:rows){
      index.put(row.getEntries().get(primaryIndex), row);
    }
  }

  /**
   * [method] 插入行
   * @param row {Row} 待插入行
   */
  public void insert(Row row) {
    // TODO
    index.put(row.getEntries().get(primaryIndex), row);
  }

  /**
   * [method] 删除所有行
   */
  public void delete() {
    index.clear();
    // TODO
    index = new BPlusTree<>();
  }

  /**
   * [method] 更新行
   */
  public void update(Row oldRow, Row newRow) {
    // TODO
    if(oldRow.getEntries().get(primaryIndex).compareTo(newRow.getEntries().get(primaryIndex))==0) {
        index.update(newRow.getEntries().get(primaryIndex), newRow);
    }
    else {
      try {
        delete(oldRow);
        insert(newRow);
      }
      catch (DuplicateKeyException e){
        throw e;
      }
    }
  }

  /**
   * [method] 序列化
   */
  private void serialize() {
    // TODO
    persistentStorageData.serialize(iterator());
  }

  /**
   * [method] 反序列化
   */
  private ArrayList<Row> deserialize() {
    // TODO
    return persistentStorageData.deserialize();
  }

  /**
   * [method] 删除table（包括表本身）
   */
  public void drop() {
    this.index.clear();
    this.tableMeta.deleteFile();
    this.persistentStorageData.deleteFile();
    new File(this.folder).delete();
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
    for(Column column: columns) {
      buffer = buffer.concat("\n\t\t" + column.toString());
    }
    return buffer;
  }
}
