package cn.edu.thssdb.parser;

import java.util.ArrayList;

public class TableQuery {
  String originTableName;

  ArrayList<String> joinTableNames;
  MultipleConditions conditions;

  public TableQuery(String tableName) {
    this.originTableName = tableName;
  }

  public TableQuery(
      String tableName, ArrayList<String> joinTableNames, MultipleConditions conditions) {
    this.originTableName = tableName;
    this.joinTableNames = joinTableNames;
    this.conditions = conditions;
  }

  public String getOriginTableName() {
    return originTableName;
  }

  public ArrayList<String> getJoinTableNames() {
    return joinTableNames;
  }

  public MultipleConditions getConditions() {
    return conditions;
  }
}
