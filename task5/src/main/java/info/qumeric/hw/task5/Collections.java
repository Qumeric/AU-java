package info.qumeric.hw.task5;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Collections {

  public static List<Object> map(Function1 f, Iterable<Object> c) {
    List<Object> list = new ArrayList<>();
    for (Object elem: c) {
      list.add(f.apply(elem));
    }
    return list;
  }

  public static List<Object> filter(Predicate p, Iterable<Object> c) {
    List<Object> list = new ArrayList<>();
    for (Object elem: c) {
      if (p.apply(elem)) {
        list.add(elem);
      }
    }
    return list;
  }

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

  public static List<Object> takeUnless(Predicate p, Iterable<Object> c) {
    return takeWhile(Predicate.not(p), c);
  }

  public static Object foldl(Function2 f, Object acc, Iterable<Object> c) {
    return foldlRec(f, acc, c.iterator());
  }

  private static Object foldlRec(Function2 f, Object acc, Iterator<Object> it) {
    if (it.hasNext()) {
      return foldlRec(f, f.apply(acc, it.next()), it);
    } else {
      return acc;
    }
  }
}

/*map — принимает f и a, применяет f к каждому элементу ai и возвращает список [f(a1), ..., f(an)]
        filter — принимает p и a, возвращает список, содержащий элементы ai, на которых p(ai) == true
        takeWhile — принимает p и a, возвращает список с началом a до первого элемента ai, для которого p(ai) == false
        takeUnless — то же, что и takeWhile, только для p(ai) == true
        foldr / foldl — принимает функцию двух аргументов, начальное значение и коллекцию, работает так: https://ru.wikipedia.org/wiki/%D0%A1%D0%B2%D1%91%D1%80%D1%82%D0%BA%D0%B0_%D1%81%D0%BF%D0%B8%D1%81%D0%BA%D0%B0
        a – Iterable*/
