package cn.edu.thssdb.query;

import cn.edu.thssdb.parser.MultipleConditions;
import cn.edu.thssdb.schema.Row;
import cn.edu.thssdb.schema.Table;

import java.util.ArrayList;
import java.util.Iterator;

public class QueryTable implements Iterator<Row> {

  private ArrayList<Table> tables;
  Iterator<Row> iterator;
  private Table resultTable;

  public QueryTable(ArrayList<Table> tables, MultipleConditions conditions) {
    // TODO
    this.tables = tables;
    this.iterator = tables.get(0).iterator();
    //    this.resultTable = new Table(null, null, null);
    //
    //    for (Table table : tables) {
    //      for (Row row : table) {
    //        if (conditions == null) {
    //          //          resultTable.insert(row);
    //        } else {
    //          for (Condition condition : conditions) {
    //            if (condition.check(row, table)) {
    //              //              resultTable.insert(row);
    //            }
    //          }
    //        }
    //      }
    //    }
  }

  @Override
  public boolean hasNext() {
    // TODO
    return iterator.hasNext();
  }

  @Override
  public Row next() {
    // TODO
    return iterator.next();
  }
}
