package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.plan.LogicalPlan;

public class ShowTablePlan extends LogicalPlan {
  private String tableName;

  public ShowTablePlan(String tableName) {
    super(LogicalPlanType.SHOW_TABLE);
    this.tableName = tableName;
  }

  @Override
  public String toString() {
    return "ShowTablePlan{" + tableName + "}";
  }

  public String getTableName() {
    return tableName;
  }
}
