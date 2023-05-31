package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.plan.LogicalPlan;

public class SetTransactionIsolationLevelPlan extends LogicalPlan {
  private boolean mode = false; // true for serializable, false for read committed

  public SetTransactionIsolationLevelPlan(boolean mode) {
    super(LogicalPlanType.SET_TRANSACTION_ISOLATION_LEVEL);
    this.mode = mode;
  }

  public boolean getMode() {
    return mode;
  }
}
