package info.qumeric.hw.task6;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class MyTreeSetImplTest {
  private final int size = 1000;

  private MyTreeSetImpl<Integer> createNaturalOrderSet(int size) {
    MyTreeSetImpl<Integer> naturalOrderSet = new MyTreeSetImpl<>();
    List<Integer> values = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      values.add(i);
    }
    Collections.shuffle(values);
    for (int i: values) {
      naturalOrderSet.add(i);
    }
    return naturalOrderSet;
  }

  @Test
  public void descendingIterator() throws Exception {
    MyTreeSet<Integer> naturalOrderSet = createNaturalOrderSet(size);
    Integer val = size-1;
    Iterator<Integer> it = naturalOrderSet.descendingIterator();
    while(it.hasNext()) {
      Integer e = it.next();
      assertEquals(val, e);
      val--;
    }
  }

  @Test
  public void iterator() throws Exception {
    MyTreeSet<Integer> naturalOrderSet = createNaturalOrderSet(size);
    Integer val = 0;
    for (Integer e: naturalOrderSet) {
      assertEquals(val, e);
      val++;
    }
  }

  @Test
  public void add() throws Exception {
    MyTreeSet<Integer> naturalOrderSet = createNaturalOrderSet(size);
    assertEquals(size, naturalOrderSet.size());
  }

  @Test(expected = NoSuchElementException.class)
  public void first_fails() throws Exception {
    MyTreeSet<Integer> naturalOrderSet = new MyTreeSetImpl<>();
    naturalOrderSet.first();
  }

  @Test
  public void first() throws Exception {
    MyTreeSet<Integer> naturalOrderSet = new MyTreeSetImpl<>();
    naturalOrderSet.add(1);
    naturalOrderSet.add(2);
    naturalOrderSet.add(0);
    naturalOrderSet.add(5);
    assertEquals((Integer)0, naturalOrderSet.first());
  }

  @Test
  public void getSortedList() throws Exception {
    MyTreeSetImpl<Integer> set = createNaturalOrderSet(size);
    Integer val = 0;
    for (Integer i: set.getSortedList()) {
      assertEquals(val, i);
      val++;
    }
  }

  @Test
  public void ceiling() throws Exception {
    MyTreeSet<Integer> naturalOrderSet = createNaturalOrderSet(size);
    for (Integer i = 0; i < size-1; i++) {
      assertEquals(i, naturalOrderSet.ceiling(i));
    }
  }

  @Test
  public void lower() throws Exception {
    MyTreeSet<Integer> naturalOrderSet = createNaturalOrderSet(size);
    for (Integer i = 1; i < size; i++) {
      assertEquals((Integer)(i-1), naturalOrderSet.lower(i));
    }
  }
}