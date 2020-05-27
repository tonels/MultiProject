package samples.stream;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import samples.lambda.Person2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectorsTest {

    private List<Person2> list;
    private List<String> strings;

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
        Person2 p8 = new Person2(31, "女", "张4");
        Person2 p9 = new Person2(21, "女", "张4");
        Person2 p10 = new Person2(19, "女", "张4");

        list = Lists.newArrayList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);
        strings = Lists.newArrayList("a1", "a2", "b1", "c2", "c1");
    }


    /**
     * partitioningBy
     * 根据相关条件分两组
     * True /False
     */
    @Test
    public void t1() {
        Map<Boolean, List<Person2>> collect = list.stream().collect(Collectors.partitioningBy(e -> e.getId().equals(2)));
        System.out.println(collect.toString());
    }

    @Test
    public void t2() {
        ArrayList<Integer> ids = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);
        Map<Boolean, List<Integer>> collect = list.stream().map(Person2::getId)
                .collect(Collectors.partitioningBy(e -> ids.stream().allMatch(ids::contains)));
        System.out.println(collect.toString());
    }

    /**
     * {false=[Person2(id=13, sex=男, lastName=张2), Person2(id=10, sex=男, lastName=张8), Person2(id=31, sex=女, lastName=张4), Person2(id=21, sex=女, lastName=张4), Person2(id=19, sex=女, lastName=张4)],
     * true=[Person2(id=2, sex=男, lastName=张6), Person2(id=4, sex=男, lastName=张1), Person2(id=6, sex=女, lastName=张3), Person2(id=1, sex=男, lastName=张5), Person2(id=3, sex=女, lastName=张4)]}
     */
    @Test
    public void t3() {
        ArrayList<Integer> ids = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);
        Map<Boolean, List<Person2>> collect = list.stream()
                .collect(Collectors.partitioningBy(e -> ids.contains(e.getId())));
        System.out.println(collect.toString());
    }


}
