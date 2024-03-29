package cn.edu.thssdb.index;

import cn.edu.thssdb.schema.Row;
import cn.edu.thssdb.schema.Table;
import cn.edu.thssdb.utils.Pair;

public final class BPlusTree<K extends Comparable<K>, V extends Row>
    implements Iterable<Pair<K, V>> {

  public BPlusTreeNode<K, V> root;
  private int size;
  public PageCounter pageCounter = new PageCounter();
  public BufferManager bufferManager;

  public BPlusTree(Table table) {

    bufferManager = new BufferManager(table);
    root = new BPlusTreeLeafNode<>(0, pageCounter, bufferManager);
  }

  public int size() {
    return size;
  }

  public V get(K key) {
    if (key == null) throw new IllegalArgumentException("argument key to get() is null");
    return root.get(key);
  }

  public BPlusTreeLeafNode<K, V> getLeafNode(K key) {
    if (key == null) throw new IllegalArgumentException("argument key to get() is null");
    return root.getLeafNode(key);
  }

  public void update(K key, V value) {
    root.remove(key);
    root.put(key, value);
  }

  public void put(K key, V value) {
    if (key == null) throw new IllegalArgumentException("argument key to put() is null");
    root.put(key, value);
    size++;
    checkRoot();
  }

  public void remove(K key) {
    if (key == null) throw new IllegalArgumentException("argument key to remove() is null");
    root.remove(key);
    size--;
    if (root instanceof BPlusTreeInternalNode && root.size() == 0) {
      root = ((BPlusTreeInternalNode<K, V>) root).children.get(0);
    }
  }

  public boolean contains(K key) {
    if (key == null) throw new IllegalArgumentException("argument key to contains() is null");
    return root.containsKey(key);
  }

  private void checkRoot() {
    if (root.isOverFlow()) {
      BPlusTreeNode<K, V> newSiblingNode = root.split();
      BPlusTreeInternalNode<K, V> newRoot = new BPlusTreeInternalNode<>(1);
      newRoot.keys.set(0, newSiblingNode.getFirstLeafKey());
      newRoot.children.set(0, root);
      newRoot.children.set(1, newSiblingNode);
      root = newRoot;
    }
  }

  @Override
  public BPlusTreeIterator<K, V> iterator() {
    return new BPlusTreeIterator<>(this);
  }
}
