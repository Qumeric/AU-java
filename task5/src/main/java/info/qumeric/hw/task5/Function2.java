package info.qumeric.hw.task5;

import java.util.ArrayList;
import java.util.List;

/**
 * A function of two arguments
 */
public abstract class Function2 {
  private List<Function1> chain = new ArrayList<>();

  /**
   * You have to implement only this to create a proper <code>Function2</code> implementation
   */
  abstract protected Object calculate(Object x, Object y);

  public final Object apply(Object x, Object y) {
    Object val = calculate(x, y);
    for (Function1 f: chain) {
      val = f.calculate(val);
    }
    return val;
  }

  public Function2 compose (Function1 g) {
    chain.add(g);
    return this;
  }

  public Function2 bind1(Object x) {
    return new Function2() {
      @Override
      protected Object calculate(Object empty, Object y) {
        return Function2.this.calculate(x, y);
      }
    };
  }

  public Function2 bind2(Object y) {
    return new Function2() {
      @Override
      protected Object calculate(Object x, Object empty) {
        return Function2.this.calculate(x, y);
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
      protected Object calculate(Object x, Object y) {
        return Function2.this.calculate(y, x);
      }
    };
  }

  public Function1 curry(Object x) {
    return new Function1()  {
      @Override
      protected Object calculate(Object y) {
        return Function2.this.calculate(x, y);
      }
    };
  }
}


class AddInteger extends Function2 {
  public Object calculate(Object x, Object y) {
    return (Integer)x + (Integer)y;
  }
}

class SubtractInteger extends Function2 {
  public Object calculate(Object x, Object y) {
    return (Integer)x - (Integer)y;
  }
}