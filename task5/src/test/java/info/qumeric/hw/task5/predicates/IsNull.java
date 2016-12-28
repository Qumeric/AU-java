package info.qumeric.hw.task5.predicates;

import info.qumeric.hw.task5.Predicate;

public class IsNull extends Predicate {
  @Override
  public boolean calculate(Object cond) {
    return cond == null;
  }
}
