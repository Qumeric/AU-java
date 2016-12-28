package info.qumeric.hw.task5;

import java.util.ArrayList;
import java.util.List;

/**
 * A function of two arguments
 */
public abstract class Function2 {
  /**
   * You have to implement only this to create a proper <code>Function2</code> implementation
   */
  abstract protected Object apply(Object x, Object y);

  public Function2 compose (Function1 g) {
    return new Function2() {
      @Override
      protected Object apply(Object x, Object y) {
         return g.apply(Function2.this.apply(x, y));
      }
    };
  }

  public Function2 bind1(Object x) {
    return new Function2() {
      @Override
      protected Object apply(Object empty, Object y) {
        return Function2.this.apply(x, y);
      }
    };
  }

  public Function2 bind2(Object y) {
    return new Function2() {
      @Override
      protected Object apply(Object x, Object empty) {
        return Function2.this.apply(x, y);
      }
    };
  }

  /**
   * Swap arguments of function
   * Example: (a -> b -> c) -> (b -> a -> c)
   * Note: doesn't swap binded args
   * @return new Function2 object with swapped variables
   */
  public Function2 swap() {
    return new Function2() {
      @Override
      protected Object apply(Object x, Object y) {
        return Function2.this.apply(y, x);
      }
    };
  }

  public Function1 curry(Object x) {
    return new Function1() {
      @Override
      protected Object apply(Object y) {
        return Function2.this.apply(x, y);
      }
    };
  }
}


