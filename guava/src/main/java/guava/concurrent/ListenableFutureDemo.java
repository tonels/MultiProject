package guava.concurrent;

import com.google.common.base.Function;
import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: gunters
 * Created: 05/12/2011 10:35
 */
public class ListenableFutureDemo {

    public static void main(String... args) throws Exception {
        final ExecutorService executorService = Executors.newFixedThreadPool(1);

        ListenableFutureDemo app = new ListenableFutureDemo(executorService);
        app.runWithListener();
        //app.runWithCallback();
        //app.runWithChain();

        app.stop();
    }
    

    private final ListeningExecutorService listeningExecutorService;

    public ListenableFutureDemo(ExecutorService executorService) {
        this.listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
    }

    public void stop() {
        listeningExecutorService.shutdown();
    }

    public void runWithListener() throws Exception {
        // calling addListener directly
        ListenableFuture<String> future1 = listeningExecutorService.submit(new SleepingCallable("Hello", 2000));
        future1.addListener(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": Listener: DONE");
            }
        }, listeningExecutorService);
        
        System.out.println(Thread.currentThread().getName() + ": Future1: " + future1.get());
    }

    public void runWithCallback() throws Exception {
        ListenableFuture<String> future2 = listeningExecutorService.submit(new SleepingCallable("Hello", 2000));
        Futures.addCallback(future2, new FutureCallback<String>() {
            @Override
            public void onSuccess(@org.checkerframework.checker.nullness.qual.Nullable String result) {
                System.out.println(Thread.currentThread().getName() + ": FutureCallback: SUCCESS - " + result);

            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(Thread.currentThread().getName() + ": FutureCallback: FAILURE - " + t.getMessage());

            }
        }, new Executor() {
            @Override
            public void execute(Runnable command) {
                System.out.println("sss");
            }
        });

        System.out.println(Thread.currentThread().getName() + ": Future2: " + future2.get());
    }
}
