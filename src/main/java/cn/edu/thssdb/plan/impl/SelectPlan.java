package cn.edu.thssdb.plan.impl;

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
    //    this.queryTable = new QueryTable();

  }

  private void generatrQueryTable(Database database) {
    // TODO
    for (TableQuery tableQuery : tableQueries) {
      Table table = database.getTableByName(tableQuery.getOriginTableName());
      if (table == null) {
        throw new RuntimeException("Table not found!");
      }

      QueryTable queryTable = new QueryTable(table);
      for (String tableName : tableQuery.getJoinTableNames()) {
        Table joinTable = database.getTableByName(tableName);
        if (joinTable == null) {
          throw new RuntimeException("Table not found!");
        }
        queryTable.join(joinTable, tableQuery.getConditions());
      }
      this.queryTables.add(queryTable);
    }
  }

  public void doSelect(Database database) {
    // TODO
    generatrQueryTable(database);
    queryResult =
        new QueryResult(queryTables.toArray(new QueryTable[0]), resultColumns, conditions);
    // Print the result
    queryResult.print();
  }
}
