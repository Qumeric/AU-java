package info.qumeric.hw.task5;

import java.util.ArrayList;
import java.util.List;

/**
 * A function of one argument
 */
public abstract class Function1 {

  /**
   * You have to implement only this to create a proper <code>Function1</code> implementation
   */
  abstract protected Object apply(Object x);

  public final Object apply() {
    return apply(null);
  }

  /**
   * Composition of functions
   * @param g function to apply second
   */
  public Function1 compose (Function1 g) {
    return new Function1() {
      @Override
      protected Object apply(Object x) {
        return g.apply(Function1.this.apply(x));
      }
    };
  }
}


