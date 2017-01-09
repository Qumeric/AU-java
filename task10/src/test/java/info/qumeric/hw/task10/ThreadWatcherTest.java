package info.qumeric.hw.task10;

import org.jetbrains.annotations.NotNull;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ThreadWatcherTest {
    @Rule
    public ThreadsWatcher threadsWatcher = new ThreadsWatcher();

    @Test
    public void testInfiniteThread() throws Throwable {
        threadsWatcher.expect(ThreadsWatcher.AliveThreadException.class);
        run(() -> { while(true); });
    }

    @Test
    public void testThrowingThread() throws Throwable {
        threadsWatcher.expect(ThreadsWatcher.InThreadException.class);
        Thread t = run(() -> { throw new RuntimeException(); });
        t.join();
    }

    @Test
    public void testNormalThread() throws Throwable {
        Thread t = run(() -> {});
        t.join();
    }

    private Thread run(@NotNull Runnable runnable) throws Throwable {
        Thread thread = new Thread(runnable);
        threadsWatcher.watch(thread);
        thread.start();
        return thread;
    }


}