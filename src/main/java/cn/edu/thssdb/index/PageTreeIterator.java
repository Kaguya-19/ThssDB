package cn.edu.thssdb.index;

import cn.edu.thssdb.schema.Entry;
import cn.edu.thssdb.schema.Row;
import cn.edu.thssdb.schema.Table;
import cn.edu.thssdb.utils.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class PageTreeIterator implements Iterator<Pair<Entry, Row>> {
  private final LinkedList<BPlusTreeNode<Entry, Row>> queue;
  private final LinkedList<Pair<Entry, Row>> buffer;

  private BufferManager bufferManager;

  public PageTreeIterator(BPlusTree<Entry, Row> tree, Table table) {
    queue = new LinkedList<>();
    buffer = new LinkedList<>();
    if (tree.size() == 0) return;
    queue.add(tree.root);
    bufferManager = tree.bufferManager;
  }

  @Override
  public boolean hasNext() {
    return !queue.isEmpty() || !buffer.isEmpty();
  }

  @Override
  public Pair<Entry, Row> next() {
    if (buffer.isEmpty()) {
      while (true) {
        BPlusTreeNode<Entry, Row> node = queue.poll();
        if (node instanceof BPlusTreeLeafNode) {
          int pageIndex = ((BPlusTreeLeafNode<Entry, Row>) node).getPageId();
          ArrayList<Row> pageRows = bufferManager.readPage(pageIndex);
          try {
            for (int i = 0; i < node.nodeSize; i++) {
              buffer.add(new Pair<>(node.keys.get(i), pageRows.get(i)));
            }
          } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            throw e;
          }
          break;
        } else if (node instanceof BPlusTreeInternalNode)
          for (int i = 0; i <= node.nodeSize; i++)
            queue.add(((BPlusTreeInternalNode<Entry, Row>) node).children.get(i));
      }
    }
    return buffer.poll();
  }
}
