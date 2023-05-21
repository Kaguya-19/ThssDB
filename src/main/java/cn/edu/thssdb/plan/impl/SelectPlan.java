package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.parser.ColumnFullName;
import cn.edu.thssdb.parser.Condition;
import cn.edu.thssdb.parser.TableQuery;
import cn.edu.thssdb.plan.LogicalPlan;

import java.util.ArrayList;

public class SelectPlan extends LogicalPlan {
  private ArrayList<ColumnFullName> columnFullNames;
  private ArrayList<TableQuery> tableQueries;
  private ArrayList<Condition> conditions;

  public SelectPlan(
      ArrayList<ColumnFullName> columnFullNames,
      ArrayList<TableQuery> tableQueries,
      ArrayList<Condition> conditions) {
    super(LogicalPlanType.SELECT);
    this.columnFullNames = columnFullNames;
    this.tableQueries = tableQueries;
    if (conditions == null) {
      this.conditions = new ArrayList<>();
    } else {
      this.conditions = conditions;
    }
  }

  //  public Table doSelect(Database db) {
  //    // TODO:JOIN & WHERE calculation
  //    // 1. Get Tables
  //    TableQuery tableName = tableQueries[0];
  ////    Table table = db.getTable(tableName);
  ////    QueryResult queryResult = new QueryResult(columnFullNames, table, conditions);
  //  }
}
