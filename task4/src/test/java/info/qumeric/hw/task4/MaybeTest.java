package info.qumeric.hw.task4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class MaybeTest {
  Maybe<Integer> nothing = Maybe.nothing();
  Maybe<Integer> just1 = Maybe.just(1);

  @Test(expected=Maybe.EmptyException.class)
  public void get() throws Exception {
    assertEquals(new Integer(1), just1.get());
    nothing.get();
  }

  @Test
  public void isPresent() throws Exception {
    assertTrue(just1.isPresent());
    assertFalse(nothing.isPresent());
  }

  @Test
  public void map() throws Exception {
    assertEquals(new Integer(4), just1.map(x -> x * 2).map(x -> x * 2).get());
  }
}