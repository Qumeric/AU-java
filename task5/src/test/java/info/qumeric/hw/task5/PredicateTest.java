package info.qumeric.hw.task5;

import info.qumeric.hw.task5.predicates.IsInteger;
import info.qumeric.hw.task5.predicates.IsNull;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class PredicateTest {
  final Predicate TRUE = Predicate.alwaysTrue();
  final Predicate FALSE = Predicate.alwaysFalse();
  final Predicate isNull = new IsNull();
  final Predicate isInteger = new IsInteger();

  @Test
  public void apply() throws Exception {
    assertTrue(isNull.apply(null));
    assertFalse(isNull.apply(0));
    assertTrue(isInteger.apply(1));
    assertFalse(isInteger.apply("1"));
  }

  @Test
  public void or() throws Exception {
    assertTrue(TRUE.or(TRUE).apply());
    assertTrue(TRUE.or(FALSE).apply());
    assertTrue(FALSE.or(TRUE).apply());
    assertFalse(FALSE.or(FALSE).apply());
  }

  @Test
  public void and() throws Exception {
    assertTrue(TRUE.and(TRUE).apply());
    assertFalse(TRUE.and(FALSE).apply());
    assertFalse(FALSE.and(TRUE).apply());
    assertFalse(FALSE.and(FALSE).apply());
  }

  @Test
  public void not() throws Exception {
    assertTrue(Predicate.not(FALSE).apply());
  }

  @Test
  public void ALWAYS_TRUE() throws Exception {
    assertTrue(TRUE.apply());
  }

  @Test
  public void ALWAYS_FALSE() throws Exception {
    assertFalse(FALSE.apply());
  }

}