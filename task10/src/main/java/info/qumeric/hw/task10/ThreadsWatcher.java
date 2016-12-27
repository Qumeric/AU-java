package info.qumeric.hw.task10;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.ArrayList;
import java.util.List;

public class ThreadsWatcher implements TestRule {
    private List<Thread> threads = new ArrayList<>();
    private List<Throwable> exceptions = new ArrayList<>();
    private List<Thread> aliveThreads = new ArrayList<>();
    private Class<? extends Throwable> expectedExceptionType = null;


    /**
     * Checks if there are alive threads after evaluation or if there were any exceptions in threads.
     * @return new <code>Statement</code> with checking.
     */
    @Override
    public Statement apply(@NotNull final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                base.evaluate();
                for (Thread t: threads) {
                    if (t.isAlive()) {
                        aliveThreads.add(t);
                    }
                }
                check();
            }
        };
    }

    /**
     * Start watching on thread. Should be called with all threads before testing.
     */
    public void watch(@NotNull final Thread t) {
        threads.add(t);
        t.setUncaughtExceptionHandler((a, e) -> exceptions.add(e));
    }

    /**
     * <code>expect(cls)</code> should be used instead of @Test(expected=cls) with <code>ThreadsWatcher</code>
     * @param e class of expected exception
     */
    public void expect(@NotNull Class<? extends Throwable> e) {
        expectedExceptionType = e;
    }

    /**
     * Check if there were some problems with threads
     * @throws Exception AliveThreadException or InThreadException
     */
    public void check() throws Exception {
        System.out.println("Alive threads: " + aliveThreads.size() + " exceptions: " + exceptions.size());
        if (aliveThreads.size() > 0) {
            if (expectedExceptionType == null || expectedExceptionType != AliveThreadException.class) {
                throw new AliveThreadException(aliveThreads.size());
            }
        }
        if (exceptions.size() > 0) {
            if (expectedExceptionType == null || expectedExceptionType != InThreadException.class) {
                throw new InThreadException(exceptions.size());
            }
        }
    }

    /**
     * Get list of threads which were alive just after running test.
     */
    public List<Thread> getAliveThreads() {
        return aliveThreads;
    }

    /**
     * Get list of exceptions thrown by threads.
     */
    public List<Throwable> getExceptions() {
        return exceptions;
    }


    public static class AliveThreadException extends Exception {
        AliveThreadException(@Nullable Integer count) {
            super(count + " alive threads");
        }
    }

    public static class InThreadException extends Exception {
        InThreadException(@Nullable Integer count) {
            super(count + " uncaught exceptions in threads");
        }
    }
}

