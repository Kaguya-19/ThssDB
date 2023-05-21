package cn.edu.thssdb.query;

import cn.edu.thssdb.schema.Row;
import cn.edu.thssdb.schema.Table;
import cn.edu.thssdb.utils.Cell;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class QueryResult {

  private List<MetaInfo> metaInfoInfos;
  private List<Integer> index;
  private List<Cell> attrs;
  private String msg;
  private Table resultTable;

  public QueryResult(QueryTable[] queryTables) {
    // TODO
    this.index = new ArrayList<>();
    this.attrs = new ArrayList<>();

    //    for (QueryTable queryTable : queryTables) {
    //      for (MetaInfo metaInfo : queryTable.getMetaInfoInfos()) {
    //        this.metaInfoInfos.add(metaInfo);
    //      }
    //    }
    //
    //    for (int i = 0; i < metaInfoInfos.size(); i++) {
    //      index.add(i);
    //    }
    //
    //    for (int i = 0; i < metaInfoInfos.size(); i++) {
    //      for (int j = 0; j < metaInfoInfos.get(i).getColumns().size(); j++) {
    //        attrs.add(
    //            new Cell(
    //                metaInfoInfos.get(i).getTableName(),
    //                metaInfoInfos.get(i).getColumns().get(j).getName()));
    //      }
    //    }
    //
    //    this.resultTable = new Table(metaInfoInfos, index, attrs);
  }

  public static Row combineRow(LinkedList<Row> rows) {
    // TODO
    return null;
  }

  public Row generateQueryRecord(Row row) {
    // TODO
    return null;
  }

  public Table getResultTable() {
    return resultTable;
  }
}
