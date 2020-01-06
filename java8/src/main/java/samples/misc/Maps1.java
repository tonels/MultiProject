package samples.misc;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class Maps1 {

    @Test
    public void t1() {
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println(val));


        map.computeIfPresent(3, (num, val) -> val + num);
        System.out.println(map.get(3));             // val33

        map.computeIfPresent(9, (num, val) -> null); // 相当于 remove 操作
        System.out.println(map.containsKey(9));     // false

        map.computeIfAbsent(23, num -> "val" + num);
        System.out.println(map.containsKey(23));    // true

        map.computeIfAbsent(3, num -> "bam");
        System.out.println(map.get(3));             // val33

        System.out.println(map.getOrDefault(42, "not found"));      // not found

        map.remove(3, "val3");
        System.out.println(map.get(3));             // val33

        map.remove(3, "val33");
        System.out.println(map.get(3));             // null

        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));             // val9

        map.merge(9, "concat", String::concat);
        System.out.println(map.get(9));             // val9concat
    }

}