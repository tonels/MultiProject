package samples.lambda;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class Lambda1 {

    private List<Person2> list;

    // 初始化数据需要的
    @Before
    public void inti() {
        Person2 p1 = new Person2(13, "男", "张2");
        Person2 p2 = new Person2(2, "男", "张6");
        Person2 p3 = new Person2(4, "男", "张1");
        Person2 p4 = new Person2(6, "女", "张3");
        Person2 p5 = new Person2(1, "男", "张5");
        Person2 p6 = new Person2(10, "男", "张8");
        Person2 p7 = new Person2(3, "女", "张4");
        Person2 p8 = null;

        list = Lists.newArrayList(p1, p2, p3, p4, p5, p6, p7);
    }

    @Test
    public void t1() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, (a, b) -> b.compareTo(a));

        Collections.sort(names, Comparator.reverseOrder());

        Collections.sort(names, Comparator.reverseOrder());

        Collections.sort(names, (a, b) -> b.compareTo(a));

        System.out.println(names);

        names.sort(Collections.reverseOrder());

        System.out.println(names);

        List<String> names2 = Arrays.asList("peter", null, "anna", "mike", "xenia");
        names2.sort(Comparator.nullsLast(String::compareTo));
        System.out.println(names2);

        List<String> names3 = null;

        Optional.ofNullable(names3).ifPresent(list -> list.sort(Comparator.naturalOrder()));

        System.out.println(names3);
    }

    @Test
    public void t2() {
        // 初始list的顺序，和放置的一致
        list.forEach(System.out::println);
        System.out.println("==========");

        // 将list 放置 set 中，会重新基于对象的hash值，排列顺序
        // 后来我重写对象的 hashcode 方法，确实可以通过调整 hashcode的方法，从list到set重新排序，
        // 但有个奇怪的问题， 当第一元素最大时，就会出现排序错误的情况，中间元素就能正常根据hashcode的排序
        Sets.newHashSet(list).forEach(System.out::println);
    }

    @Test
    public void t3() {

        // 根据 Id 排序,默认升序
        list.sort(Comparator.comparing(Person2::getId));
        list.forEach(System.out::println);
    }

    @Test
    public void t4() {
        Optional.ofNullable(list).ifPresent(e -> e.sort(Comparator.comparing(Person2::getId)));
        list.forEach(System.out::println);

    }
}