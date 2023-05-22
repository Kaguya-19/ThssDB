package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.parser.ColumnFullName;
import cn.edu.thssdb.parser.MultipleConditions;
import cn.edu.thssdb.parser.TableQuery;
import cn.edu.thssdb.plan.LogicalPlan;

import java.util.ArrayList;

public class SelectPlan extends LogicalPlan {
  private ArrayList<ColumnFullName> columnFullNames;
  private ArrayList<TableQuery> tableQueries;
  private MultipleConditions conditions;

  public SelectPlan(
      ArrayList<ColumnFullName> columnFullNames,
      ArrayList<TableQuery> tableQueries,
      MultipleConditions conditions) {
    super(LogicalPlanType.SELECT);
    this.columnFullNames = columnFullNames;
    this.tableQueries = tableQueries;
    this.conditions = conditions;
  }
}
