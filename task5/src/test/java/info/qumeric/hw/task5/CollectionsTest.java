package info.qumeric.hw.task5;

import info.qumeric.hw.task5.functions.AddInteger;
import info.qumeric.hw.task5.functions.NegateInteger;
import info.qumeric.hw.task5.predicates.IntegerMoreThan;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class CollectionsTest {
  final Function1 negate = new NegateInteger();
  final Function2 add = new AddInteger();
  final Predicate moreThan1 = new IntegerMoreThan(1);
  final List<Object> list = new ArrayList<Object>() {{
    add(1);
    add(2);
    add(0);
    add(3);
  }};

  @Test
  public void map() throws Exception {
    assertEquals(-3, Collections.map(negate, list).get(3));
  }

  @Test
  public void filter() throws Exception {
    assertEquals(2, Collections.filter(moreThan1, list).size());
  }

  @Test
  public void takeUnless() throws Exception {
    assertEquals(1, Collections.takeUnless(moreThan1, list).size());
  }

  @Test
  public void foldl() throws Exception {
    assertEquals(7, Collections.foldl(add, 1, list));
  }

}