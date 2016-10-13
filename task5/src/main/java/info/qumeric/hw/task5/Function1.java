package info.qumeric.hw.task5;

import java.util.ArrayList;
import java.util.List;

/**
 * A function of one argument
 */
public abstract class Function1 {

  protected Object arg = null;

  protected List<Function1> chain = new ArrayList<>();

  /**
   * You have to implement only this to create a proper <code>Function1</code> implementation
   */
  abstract protected Object calculate(Object x);

  public final Object apply(Object x) {
    if (arg != null)
      x = arg;
    Object val = calculate(x);
    for (Function1 f: chain) {
      val = f.calculate(val);
    }
    return val;
  }

  public final Object apply() {
    return apply(null);
  }

  public Function1 bind(Object x) {
    arg = x;
    return this;
  }

  public Function1 compose (Function1 g) {
    chain.add(g);
    return this;
  }
}


class NegateInteger extends Function1 {
  protected Integer calculate(Object x) {
    return -(Integer)x;
  }
}

class SquareInteger extends Function1 {
  protected Integer calculate(Object x) {
    return (Integer)x * (Integer)x;
  }
}