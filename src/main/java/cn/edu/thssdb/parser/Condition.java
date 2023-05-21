package cn.edu.thssdb.parser;

import cn.edu.thssdb.schema.Row;

public class Condition {
  private ColumnFullName c1;
  private ColumnFullName c2;
  private String op;

  public Condition(ColumnFullName c1, ColumnFullName c2, String op) {
    this.c1 = c1;
    this.c2 = c2;
    this.op = op;
  }

  public Boolean check(Row row) {
    // TODO
    return true;
  }
}
