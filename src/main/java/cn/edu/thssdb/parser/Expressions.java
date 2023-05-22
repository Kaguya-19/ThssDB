package cn.edu.thssdb.parser;

public class Expressions extends BinaryTree {

  protected Expressions left;
  protected Expressions right;

  public Expressions(Expressions left, Expressions right, Object value) {
    super(left, right, value);
    this.left = left;
    this.right = right;
  }

  public Object calculate() {
    if (isLeaf()) {
      return value;
    } else {
      Object leftResult = left.calculate();
      Object rightResult = right.calculate();
      if (value.equals("+")) {
        return (int) leftResult + (int) rightResult;
      } else if (value.equals("-")) {
        return (int) leftResult - (int) rightResult;
      } else if (value.equals("*")) {
        return (int) leftResult * (int) rightResult;
      } else if (value.equals("/")) {
        return (int) leftResult / (int) rightResult;
      } else {
        return null;
      }
    }
  }
}
