package info.qumeric.hw.task5;

import info.qumeric.hw.task5.functions.NegateInteger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class Function1Test {
  @Test
  public void apply() throws Exception {
    assertEquals(-1, (long) new NegateInteger().apply(1));
  }

  @Test
  public void compose() throws Exception {
    assertEquals(2, new NegateInteger().compose(new NegateInteger()).apply(2));
  }

}