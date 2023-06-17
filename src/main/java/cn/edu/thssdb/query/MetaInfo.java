package cn.edu.thssdb.query;

import cn.edu.thssdb.schema.Column;

import java.util.ArrayList;
import java.util.List;

public class MetaInfo {

  private String tableName;
  private List<Column> columns;
  private int primaryIndex = -1;

  MetaInfo(String tableName, ArrayList<Column> columns, int primaryIndex) {
    this.tableName = tableName;
    this.columns = columns;
    this.primaryIndex = primaryIndex;
  }

  int columnFind(String name) {
    // TODO
    for (int i = 0; i < columns.size(); i++) {
      if (columns.get(i).getName().equals(name)) {
        return i;
      }
    }
    return 0;
  }

  public String getTableName() {
    return tableName;
  }

  public ArrayList<Column> getColumns() {
    return (ArrayList<Column>) columns;
  }

  public ArrayList<String> getColumnNames() {
    ArrayList<String> names = new ArrayList<>();
    for (Column column : columns) {
      names.add(column.getName());
    }
    return names;
  }
}
