package info.qumeric.hw.task5;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Collections {

  /**
   * Apply function <code>f</code> to each argument of <code>c</code>
   **/
  public static List<Object> map(Function1 f, Iterable<Object> c) {
    List<Object> list = new ArrayList<>();
    for (Object elem: c) {
      list.add(f.apply(elem));
    }
    return list;
  }

  /**
   * Get list of elements of <code>c</code> which meet condition <code>p</code>
   **/
  public static List<Object> filter(Predicate p, Iterable<Object> c) {
    List<Object> list = new ArrayList<>();
    for (Object elem: c) {
      if (p.apply(elem)) {
        list.add(elem);
      }
    }
    return list;
  }

  /**
   * Get elements of c from 0 to position of first element which doesn't meet condition <code>p</code>
   * (not inclusive from right)
   **/
  public static List<Object> takeWhile(Predicate p, Iterable<Object> c) {
    List<Object> list = new ArrayList<>();
    for (Object elem: c) {
      if (p.apply(elem)) {
        list.add(elem);
      } else {
        return list;
      }
    }
    return list;
  }

  /**
   * Same as <code>takeWhile</code> but in this case check negation of <code>p</code> instead of <code>p</code> itself
   **/
  public static List<Object> takeUnless(Predicate p, Iterable<Object> c) {
    return takeWhile(Predicate.not(p), c);
  }

  /**
   * Left fold
   * @see <a href="https://en.wikipedia.org/wiki/Fold_(higher-order_function)">Fold (Wikipedia)</a>
   */
  public static Object foldl(Function2 f, Object acc, Iterable<Object> c) {
    Iterator<Object> it = c.iterator();
    while (it.hasNext()) {
      acc = f.apply(acc, it.next());
    }
    return acc;
  }
}
