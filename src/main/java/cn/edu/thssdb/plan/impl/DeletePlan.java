package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.parser.MultipleConditions;
import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.schema.Database;
import cn.edu.thssdb.schema.Row;
import cn.edu.thssdb.schema.Table;

import java.util.Iterator;

public class DeletePlan extends LogicalPlan {
  private String tableName;
  private MultipleConditions conditions;

  public DeletePlan(String tableName, MultipleConditions conditions) {
    super(LogicalPlanType.DELETE);
    this.tableName = tableName;
  }

  @Override
  public String toString() {
    return "DeletePlan{" + "tableName='" + tableName + '\'' + '}';
  }

  public String getTableName() {
    return tableName;
  }

  public void doDelete(Database database) {
    Table table = database.getTableByName(tableName);
    if (table == null) {
      throw new RuntimeException("Table not found!");
    }
    for (Iterator<Row> it = table.iterator(); it.hasNext(); ) {
      Row row = it.next();
      if (conditions != null) {
        if (conditions.check(row, table)) {
          table.delete(row);
        }
      } else {
        table.delete(row);
      }
    }
  }
}
