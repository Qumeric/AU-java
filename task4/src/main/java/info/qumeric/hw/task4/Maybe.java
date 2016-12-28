package info.qumeric.hw.task4;


import java.util.function.Function;

public class Maybe<T> {

  public static class EmptyException extends IllegalStateException {}

  private T value = null;

  private Maybe(T t) {
    value = t;
  }

  private Maybe() {
    this(null);
  }

  /**
   * Get <code>Maybe</code> holding a value
   * @param t value
   * @param <T> type of value
   * @return Empty holding given value
   */
  public static <T> Maybe<T> just(T t) {
    return new Maybe<T>(t);
  }

  /**
   * Get empty <code>Maybe</code>
   * @param <T> type of value (doesn't really matter)
   * @return Empty maybe
   */
  public static <T> Maybe<T> nothing() {
    return new Maybe<T>();
  }

  /**
   * If <code>this</code> is not empty return the value,
   * else throw {@link Maybe.EmptyException}
   * @return the value or throw exception
   */
  public T get() {
    if (value == null) {
      throw new EmptyException();
    }

    return value;
  }

  /**
   * @return true if <code>this</code> isn't empty, else false
   */
  public boolean isPresent() {
    return value != null;
  }

  /**
   * Evaluates given mapper on <code>this</code>'s value.
   * if <code>this</code> is empty return a new empty {@link Maybe<T>.nothing()}.
   * @param mapper A function to evaluate
   * @param <U> Return type of <code>mapper</code>.
   * @return A new maybe with function calculated by mapper or {@link Maybe<T>.nothing()}
   */
  public <U> Maybe<U> map(Function<T, U> mapper) {
    try {
      return Maybe.just(mapper.apply(get()));
    } catch (EmptyException e) {
      return Maybe.nothing();
    }
  }
}

