package info.qumeric.hw.task6;

import java.util.*;

/**
 * A simple implementation of TreeSet using non-balanced binary trees.
 * Thereby most operations work in linear time.
 * @param <E> type of stored objects
 */
public class MyTreeSetImpl<E extends Comparable<? super E>> extends AbstractSet<E> implements MyTreeSet<E> {
  private Comparator<? super E> comparator = Comparator.naturalOrder();
  private Node root = null;
  private Node first = null;
  private Node last = null;
  private int size = 0;

  public MyTreeSetImpl() {
  }

  /**
   * Constructor with custom comparator.
   */
  public MyTreeSetImpl(Comparator<? super E> cmp) {
    comparator = cmp;
    last = null;
  }

  /**
   * Get iterator which iterates in descending order.
   */
  public Iterator<E> descendingIterator() {
    return new MyTreeSetIterator(true);
  }

  /**
   * Reverse elements in set.
   * @return new <code>MyTreeSet</code> with reversed order
   */
  public MyTreeSet<E> descendingSet() {
    MyTreeSet<E> dSet = new MyTreeSetImpl<E>(Collections.reverseOrder(comparator));
    for (E e: this) {
      dSet.add(e);
    }
    return dSet;
  }

  /**
   * Get an ordinary iterator.
   */
  public Iterator<E> iterator() {
    return new MyTreeSetIterator();
  }


  /**
   * Add an element to TreeSet.
   * @param e element to add
   * @return true if element was added, false otherwise (duplicate elements aren't allowed)
   */
  public boolean add(E e) {
    if (root == null) {
      root = new Node(e);
      first = last = root;
      size++;
      return true;
    }
    Node current = root;
    while (true) {
      int compareResult = comparator.compare(current.value, e);
      if (compareResult < 0) {
        if (current.right == null) {
          current.right = new Node(e);
          current.right.parent = current;
          size++;
          if (comparator.compare(last.value, e) < 0) {
            last = current.right;
          }
          return true;
        }
        current = current.right;
      } else if (compareResult > 0) {
        if (current.left == null) {
          current.left = new Node(e);
          current.left.parent = current;
          size++;
          if (comparator.compare(first.value, e) > 0) {
            first = current.left;
          }
          return true;
        }
        current = current.left;
      } else {
        return false;
      }
    }
  }

  /** {@link TreeSet#size()} **/
  public int size() {
    return size;
  }

  /** {@link TreeSet#first()} **/
  public E first() throws NoSuchElementException {
    if (first == null)
      throw new NoSuchElementException();
    return first.value;
  }

  /** {@link TreeSet#last()} **/
  public E last() throws NoSuchElementException {
    if (last == null)
      throw new NoSuchElementException();
    return last.value;
  }

  /** {@link TreeSet#floor(Object)} **/
  public E floor(E e) {
    Iterator<E> it = descendingIterator();
    while (it.hasNext()) {
      E val = it.next();
      if (comparator.compare(e, val) >= 0) {
        return val;
      }
    }
    return null;
  }

  /** {@link TreeSet#ceiling(Object)} **/
  public E ceiling(E e) {
    for (E i : this) {
      if (comparator.compare(i, e) >= 0) {
        return i;
      }
    }
    return null;
  }

  /** {@link TreeSet#lower(Object)} **/
  public E lower(E e) {
    Iterator<E> it = descendingIterator();
    while (it.hasNext()) {
      E val = it.next();
      if (comparator.compare(e, val) > 0) {
        return val;
      }
    }
    return null;
  }

  /** {@link TreeSet#higher(Object)} **/
  public E higher(E e) {
    for (E i : this) {
      if (comparator.compare(i, e) > 0) {
        return i;
      }
    }
    return null;
  }

  /**
   * HeapSort analogue (but O(n^2) because tree isn't balanced. Used mainly for debug.
   * @return list of elements in sorted order
   */
  List<E> getSortedList() {
    List<E> list = new ArrayList<>();
    getRecursive(list, root);
    return list;
  }

  private void getRecursive(List<E> list, Node node) {
    if (node == null)
      return;
    getRecursive(list, node.left);
    list.add(node.value);
    getRecursive(list, node.right);
  }

  private class Node {
    Node parent = null;
    Node left = null;
    Node right = null;
    E value;

    Node(E e) {
      value = e;
    }
  }

  private class MyTreeSetIterator implements Iterator<E> {
    boolean isReversed = false;
    Node current = null;
    Comparator<? super E> cmp = MyTreeSetImpl.this.comparator;

    MyTreeSetIterator() {
    }

    MyTreeSetIterator(boolean isReversed) {
      this.isReversed = isReversed;
    }

    @Override
    public boolean hasNext() {
      if (current == null)
        return MyTreeSetImpl.this.size > 0;

      if (!isReversed)
        return cmp.compare(current.value, MyTreeSetImpl.this.last.value) != 0;
      else
        return cmp.compare(current.value, MyTreeSetImpl.this.first.value) != 0;
    }

    @Override
    public E next() throws NoSuchElementException {
      if (hasNext()) {
        if (current == null) {
          if (!isReversed)
            current = MyTreeSetImpl.this.first;
          else
            current = MyTreeSetImpl.this.last;
          return current.value;
        }
        if (!isReversed) {
          if (current.right != null) {
            current = current.right;
            while (current.left != null) {
              current = current.left;
            }
          } else {
            E oldValue = current.value;
            while (cmp.compare(current.value, oldValue) <= 0) {
              current = current.parent; // Always exists because hasNext() returned true
            }
          }
        } else {
          if (current.left != null) {
            current = current.left;
            while (current.right != null) {
              current = current.right;
            }
          } else {
            E oldValue = current.value;
            while (cmp.compare(current.value, oldValue) >= 0) {
              current = current.parent; // Always exists because hasNext() returned true
            }
          }
        }
        return current.value;
      } else {
        throw new NoSuchElementException();
      }
    }
  }
}
