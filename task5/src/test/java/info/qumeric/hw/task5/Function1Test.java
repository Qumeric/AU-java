package info.qumeric.hw.task5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class Function1Test {
  @Test
  public void apply() throws Exception {
    assertEquals(-1, new NegateInteger().apply(1));
  }

  @Test
  public void bind() throws Exception {
    assertEquals(3*3, new SquareInteger().bind(3).apply(4));
  }

  @Test
  public void compose() throws Exception {
    assertEquals(2, new NegateInteger().compose(new NegateInteger()).apply(2));
  }

}