package cn.edu.thssdb.parser;

import cn.edu.thssdb.schema.Row;
import cn.edu.thssdb.schema.Table;

public class MultipleConditions extends BinaryTree {

  protected MultipleConditions left;
  protected MultipleConditions right;

  public MultipleConditions(MultipleConditions left, MultipleConditions right, Object value) {
    super(left, right, value);
    this.left = left;
    this.right = right;
  }

  private boolean postOrderTraverse(Row row, Table table) {
    if (isLeaf()) {
      return ((Condition) value).check(row, table);
    } else {
      boolean leftResult = left.postOrderTraverse(row, table);
      boolean rightResult = right.postOrderTraverse(row, table);
      if (value.equals("AND")) {
        return leftResult && rightResult;
      } else {
        return leftResult || rightResult;
      }
    }
  }

  public boolean check(Row row, Table table) {
    return postOrderTraverse(row, table);
  }
}
