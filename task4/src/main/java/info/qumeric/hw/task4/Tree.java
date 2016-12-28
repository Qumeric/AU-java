package info.qumeric.hw.task4;

/**
 * A simple BST (unbalanced)
 * @param <T> type of values in nodes
 */
public class Tree<T extends Comparable<T>> {
  private int size = 0;
  private T value;
  private Tree<T> left = null;
  private Tree<T> right = null;

  public Tree() {}

  public Tree(T e) {
    size = 1;
    value = e;
  }

  /**
   * If <code>e</code> contains is this tree?
   * @param e an element to check
   * @return true if <code>e</code> contains in this tree, else false.
   */
  public boolean contains(T e) {
    if (size == 0)
      return false;
    if (value.compareTo(e) < 0) {
      return left != null && left.contains(e);
    } else if (value.compareTo(e) > 0) {
      return right != null && right.contains(e);
    } else {
      return true;
    }
  }

  /**
   * Add a value to this tree.
   * @param e an element to add
   * @return true if <code>e</code> was added, else false (if <code>e</code> already was in this tree)
   */
  public boolean add(T e) {
    if (size == 0) {
      value = e;
      size = 1;
      return true;
    }

    if (contains(e)) {
      return false;
    }

    size++;

    if (value.compareTo(e) < 0) {
      if (left != null)
        return left.add(e);
      left = new Tree<>(e);
    } else if (value.compareTo(e) < 0) {
      if (right != null)
        return right.add(e);
      right = new Tree<>(e);
    }
    return true;
  }


  /**
   * @return number of nodes in this tree.
   */
  public int size() {
    return size;
  }
}
