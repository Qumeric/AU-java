package info.qumeric.hw.task5.predicates;

import info.qumeric.hw.task5.Predicate;

public class IsInteger extends Predicate {
  @Override
    public boolean calculate(Object cond) {
    return cond instanceof Integer;
  }
}
