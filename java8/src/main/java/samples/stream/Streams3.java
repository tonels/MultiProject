package samples.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 */
public class Streams3 {

    public static final int MAX = 5000000;

    public static void sortSequential() {
        List<String> values = new ArrayList<>(MAX);
        for (int i = 0; i < MAX; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        // sequential

        long t0 = System.nanoTime();

        long count = values.stream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis)); // 844 ms
    }

    public static void sortParallel() {
        List<String> values = new ArrayList<>(MAX);
        for (int i = 0; i < MAX; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        // sequential

        long t0 = System.nanoTime();

        long count = values.parallelStream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));
    }

    @Test
    public void t1(){
        sortSequential(); //844 ms,4724 ms
    }

    @Test
    public void t2(){
        sortParallel(); // 508 ms,
    }

    /*
    * 比较时间差
    * 数据量            10，000，000    50，000，000
    * sortSequential    844 ms           4724 ms
    * sortParallel     508 ms             3272 ms
    * 结论 数据量大的时候，sortParallel 排序会更优化，将近减少 1/3 时间
    * */
}
