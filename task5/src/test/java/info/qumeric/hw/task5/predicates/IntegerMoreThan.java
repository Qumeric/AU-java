package info.qumeric.hw.task5.predicates;

import info.qumeric.hw.task5.Predicate;

public class IntegerMoreThan extends Predicate {
  private Integer what;

  public  IntegerMoreThan(Integer what) {
    this.what = what;
  }

  @Override
  public boolean calculate(Object cond) {
    return (Integer)cond > what;
  }
}
