package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.LockManager;
import cn.edu.thssdb.parser.MultipleConditions;
import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.schema.Database;
import cn.edu.thssdb.schema.Entry;
import cn.edu.thssdb.schema.Row;
import cn.edu.thssdb.schema.Table;

import java.util.ArrayList;
import java.util.Iterator;

public class UpdatePlan extends LogicalPlan {
  private String tableName;
  private String columnName;
  private String value;
  private MultipleConditions conditions;

  public UpdatePlan(
      String tableName, String columnName, String value, MultipleConditions conditions) {
    super(LogicalPlanType.UPDATE);
    this.tableName = tableName;
    this.columnName = columnName;
    this.value = value;
    this.conditions = conditions;
  }

  public String getTableName() {
    return tableName;
  }

  public String getColumnName() {
    return columnName;
  }

  public String getValue() {
    return value;
  }

  public void doUpdate(LockManager lockManager, Database database) {
    Table table = database.getTableByName(tableName);
    ArrayList<Entry> entries = new ArrayList<>();
    for (Iterator<Row> it = table.iterator(); it.hasNext(); ) {
      Row row = it.next();
      if (conditions != null) {
        if (conditions.check(row, table)) {
          Row newRow = new Row(row);
          newRow.update(columnName, value, table);
          table.update(lockManager, row, newRow);
        }
      } else {
        Row newRow = new Row(row);
        newRow.update(columnName, value, table);
        table.update(lockManager, row, newRow);
      }
    }
  }
}
