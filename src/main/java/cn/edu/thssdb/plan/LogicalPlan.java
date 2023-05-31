package cn.edu.thssdb.plan;

public abstract class LogicalPlan {

  protected LogicalPlanType type;

  public LogicalPlan(LogicalPlanType type) {
    this.type = type;
  }

  public LogicalPlanType getType() {
    return type;
  }

  public enum LogicalPlanType {
    // TODO: add more LogicalPlanType
    QUIT,
    CREATE_DB,
    DROP_DB,
    USE_DB,
    CREATE_TABLE,
    DROP_TABLE,
    SHOW_TABLE,
    SELECT,
    INSERT,
    DELETE,
    UPDATE,
    BEGIN_TRANSACTION,
    COMMIT,
    SET_TRANSACTION_ISOLATION_LEVEL,
  }
}
