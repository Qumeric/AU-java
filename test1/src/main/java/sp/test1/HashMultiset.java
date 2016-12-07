package sp.test1;

import java.util.*;


/**
 * Multiset which works in constant time using hashing.
 *
 * @param <E> a type of stored value
 */
public class HashMultiset<E> extends AbstractCollection<E> implements Multiset<E> {

  private int numberOfKeys = 0;
  private int size = 0;
  private Map<E, Integer> storage = new LinkedHashMap<>();


  /**
   * Expected complexity: Same as `contains`
   *
   * @return the number of occurrences of an element in this multiset
   */
  public int count(Object element) {
    Integer count = storage.get((E) element);
    if (count == null) {
      count = 0;
    }
    return count;
  }

  public int size() {
    return size;
  }

  /**
   * Get the set of keys.
   * Expected complexity: O(1)
   *
   * @return the set of distinct elements contained in this multiset.
   */
  public Set<E> elementSet() {
    return storage.keySet();
  }

  /**
   * Get the set of entries.
   * Expected complexity: O(1)
   *
   * @return the set of entries representing the data of this multiset.
   */
  @Override
  public Set<Multiset.Entry<E>> entrySet() {
    return new MySet();
  }

  /**
   * Add an element to this multiset.
   * Expected complexity: O(1)
   *
   * @param o an element to add
   * @return if the element was added (always <code>true</code>)
   */
  @Override
  public boolean add(E o) {
    E e = (E) o;
    int count = count(e);
    if (count == 0) {
      numberOfKeys++;
    }
    storage.put(e, count + 1);
    size++;
    return true;
  }

  /**
   * Remove an element from this multiset
   * Expected complexity: O(1)
   *
   * @param o an element to remove
   * @return if the element was removed
   */
  @Override
  public boolean remove(Object o) {
    E e = (E) o;
    Integer count = count(e);
    if (count > 1) {
      storage.put(e, count - 1);
      size--;
      return true;
    } else if (count == 1) {
      storage.remove(e);
      numberOfKeys--;
      size--;
      return true;
    } else {
      return false;
    }
  }

  /**
   * Elements that occur multiple times in the multiset will appear multiple times in this iterator.
   * Expected complexity: O(1)
   */
  @Override
  public Iterator<E> iterator() {
    return new HMIterator<>();
  }

  static class HashEntry<E> implements Multiset.Entry<E> {

    private E element;
    private Integer count;

    public HashEntry(E element, Integer count) {
      this.element = element;
      this.count = count;
    }

    public E getElement() {
      return element;
    }

    public int getCount() {
      return count;
    }
  }

  private class MySet extends AbstractSet<Multiset.Entry<E>> {
    @Override
    public int size() {
      return HashMultiset.this.numberOfKeys;
    }

    @Override
    public Iterator<Multiset.Entry<E>> iterator() {
      return new MySetIterator();
    }

    class MySetIterator implements Iterator<Multiset.Entry<E>> {
      Iterator<Map.Entry<E, Integer>> insideIterator = storage.entrySet().iterator();

      public boolean hasNext() {
        return insideIterator.hasNext();
      }

      public Multiset.Entry<E> next() {
        Map.Entry<E, Integer> entry = insideIterator.next();
        return new HashEntry<>(entry.getKey(), entry.getValue());
      }

      public void remove() {
        insideIterator.remove();
      }
    }
  }

  private class HMIterator<T> implements Iterator<T> {
    private int count = 0;
    Iterator<Multiset.Entry<E>> insideIterator = entrySet().iterator();
    Multiset.Entry<E> current = null;

    public boolean hasNext() {
      if (current != null && count < current.getCount()) {
        return true;
      } else {
        return insideIterator.hasNext();
      }
    }

    public boolean add(E elem) {
      return HashMultiset.this.add(elem);
    }

    public T next() {
      if (current == null) {
        current = insideIterator.next();
      }
      if (count < current.getCount()) {
        count++;
      } else {
        count = 1;
        current = insideIterator.next();
      }
      return (T) current.getElement();
    }

    public void remove() {
      insideIterator.remove();
    }
  }
}
