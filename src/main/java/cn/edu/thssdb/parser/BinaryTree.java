package cn.edu.thssdb.parser;

public class BinaryTree {
  protected BinaryTree left;
  protected BinaryTree right;
  protected Object value;
  protected BinaryTree root;

  public BinaryTree(BinaryTree left, BinaryTree right, Object value) {
    this.left = left;
    this.right = right;
    this.value = value;
    this.root = new BinaryTree(left.root, right.root, value);
  }

  public void postOrderTraverse() {
    if (left != null) {
      left.postOrderTraverse();
    }
    if (right != null) {
      right.postOrderTraverse();
    }
    System.out.println(value);
  }

  public void insert(BinaryTree node) {
    if (left == null) {
      left = node;
    } else if (right == null) {
      right = node;
    } else {
      left.insert(node);
    }
  }

  public void setLeft(BinaryTree node) {
    left = node;
  }

  public void setRight(BinaryTree node) {
    right = node;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public boolean isLeaf() {
    return left == null && right == null;
  }
}
