package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.plan.LogicalPlan;

public class ShowDatabasesPlan extends LogicalPlan {
  public ShowDatabasesPlan() {
    super(LogicalPlanType.SHOW_DB);
  }

  @Override
  public String toString() {
    return "ShowDatabasesPlan{}";
  }
}
