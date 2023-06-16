package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.LockManager;
import cn.edu.thssdb.parser.ColumnFullName;
import cn.edu.thssdb.parser.MultipleConditions;
import cn.edu.thssdb.parser.TableQuery;
import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.query.QueryResult;
import cn.edu.thssdb.query.QueryTable;
import cn.edu.thssdb.schema.Database;
import cn.edu.thssdb.schema.Table;

import java.util.ArrayList;

public class SelectPlan extends LogicalPlan {
  private ArrayList<ColumnFullName> resultColumns;
  private ArrayList<TableQuery> tableQueries;
  private MultipleConditions conditions;
  private ArrayList<QueryTable> queryTables;
  private QueryResult queryResult;

  public SelectPlan(
      ArrayList<ColumnFullName> resultColumns,
      ArrayList<TableQuery> tableQueries,
      MultipleConditions conditions) {
    super(LogicalPlanType.SELECT);
    this.resultColumns = resultColumns;
    this.tableQueries = tableQueries;
    this.conditions = conditions;
    this.queryTables = new ArrayList<>();
  }

  private void generateQueryTable(LockManager lockManager, Database database) {
    // TODO
    for (TableQuery tableQuery : tableQueries) {
      Table table = database.getTableByName(tableQuery.getOriginTableName());
      if (table == null) {
        throw new RuntimeException("Table not found!");
      }
      table.useReadLock(lockManager, "generateQueryTable");

      QueryTable queryTable = new QueryTable(lockManager, table);
      if (tableQuery.getJoinTableNames() != null) {
        for (String tableName : tableQuery.getJoinTableNames()) {
          Table joinTable = database.getTableByName(tableName);
          if (joinTable == null) {
            throw new RuntimeException("Table not found!");
          }
          // TODO: preserve the whole table might be inefficient
          joinTable.useReadLock(lockManager, "joinTable");
          queryTable.join(lockManager, joinTable, tableQuery.getConditions());
          joinTable.releaseReadLockIfReadCommitted(lockManager, "joinTable");
        }
      } else {
        System.out.println("No join table used.");
      }
      this.queryTables.add(queryTable);
      table.releaseReadLockIfReadCommitted(lockManager, "generateQueryTable");
    }
  }

  public QueryResult doSelect(LockManager lockManager, Database database) {
    generateQueryTable(lockManager, database);
    return new QueryResult(
        lockManager, queryTables.toArray(new QueryTable[0]), resultColumns, conditions);
    // Print the result
  }
}
