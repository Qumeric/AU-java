package info.qumeric.hw.task5;

abstract public class Predicate {

  abstract protected boolean calculate(Object cond);

  public final boolean apply(Object cond) {
    return calculate(cond);
  }

  public final boolean apply() {
    return apply(null);
  }

  public Predicate or(Predicate p) {
    return new Predicate() {
      @Override
      public boolean calculate(Object cond) {
        return Predicate.this.calculate(cond) || p.calculate(cond);
      }
    };
  }

  public Predicate and(Predicate p) {
    return new Predicate() {
      @Override
      public boolean calculate(Object cond) {
        return Predicate.this.calculate(cond) && p.calculate(cond);
      }
    };
  }

  public static Predicate not(Predicate p) {
    return new Predicate() {
      @Override
      public boolean calculate(Object cond) {
        return !p.calculate(cond);
      }
    };
  }

  public static Predicate ALWAYS_TRUE() {
    return new Predicate() {
      @Override
      public boolean calculate(Object cond) {
        return true;
      }
    };
  }

  public static Predicate ALWAYS_FALSE() {
    return not(ALWAYS_TRUE());
  }
}

