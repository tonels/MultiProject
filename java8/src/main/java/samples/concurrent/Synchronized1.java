package samples.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * 这里测试了多线程/并发场景下，同步方法和非同步方法，
 * 在一定数量级（1w左右）下，非同步方法几乎很少出错（计算错误），但毕竟是线程不安全的，在大的数量级下操作数据，就错误频频了
 */
public class Synchronized1 {

    // static 变量
    private static final Integer NUM_INCREMENTS = 50000;
    private static int count = 0;

    public static void main(String[] args) {
        testSyncIncrement(); // 10000
        testNonSyncIncrement(); // 9995
    }


    private static void testSyncIncrement() {
        count = 0;

        System.out.println("同步线程处理开始...");
        ExecutorService executor = Executors.newFixedThreadPool(1);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> executor.submit(Synchronized1::incrementSync));

        ConcurrentUtils.stop(executor);

        System.out.println("同步线程处理结束...，结果为： " + count);
    }

    private static void testNonSyncIncrement() {
        count = 0;

        System.out.println("非同步线程处理开始...");
        ExecutorService executor = Executors.newFixedThreadPool(4);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> executor.submit(Synchronized1::increment));

        ConcurrentUtils.stop(executor);

        System.out.println("非同步线程处理结束...，结果为： " + count);
    }

    private static synchronized void incrementSync() {
        count = count + 1;
    }

    private static void increment() {
        count = count + 1;
    }

}
