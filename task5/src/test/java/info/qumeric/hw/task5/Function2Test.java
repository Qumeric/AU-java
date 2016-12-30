package info.qumeric.hw.task5;

import info.qumeric.hw.task5.functions.AddInteger;
import info.qumeric.hw.task5.functions.NegateInteger;
import info.qumeric.hw.task5.functions.SubtractInteger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Function2Test {
  final Function1 negate = new NegateInteger();
  final Function2 add = new AddInteger();
  final Function2 subtract = new SubtractInteger();

  @Test
  public void apply() throws Exception {
    assertEquals(10, new AddInteger().apply(4, 6));
    assertEquals(add.apply(5, negate.apply(3)), subtract.apply(4, 2));
  }

  @Test
  public void swap() throws Exception {
    assertEquals(negate.apply(subtract.apply(3, 4)), subtract.swap().apply(3, 4));

    Function1 subtract3fromFirst = new SubtractInteger().bind2(3);
    assertEquals(7, subtract3fromFirst.apply(10));
  }

  @Test
  public void bind1() throws Exception {
    Function1 add10 = add.bind1(10);
    assertEquals(20, add10.apply(10));
    assertEquals(30, add10.apply(20));
  }

  @Test
  public void curry() throws Exception {
    Function1 add5 = add.curry(5);
    assertEquals(8, add5.apply(3));
  }

  @Test
  public void compose() throws Exception {
    assertEquals(-10, new AddInteger().compose(new NegateInteger()).apply(6, 4));
  }

}