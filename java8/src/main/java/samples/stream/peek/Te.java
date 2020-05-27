package samples.stream.peek;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import samples.lambda.Person2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Te {

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

    @Test
    public void t1() {
//        list.stream().peek()


    }

    /**
     * will filter 1
     * will filter 10
     * 10
     */
    @Test
    public void t2() {
        List<Integer> list = Arrays.asList(1, 10, 3, 7, 5);
        int a = list.stream()
                .peek(num -> System.out.println("will filter " + num))
                .filter(x -> x > 5)
                .findFirst()
                .get();
        System.out.println(a);
    }

    /**
     * will filter 1
     * 1
     */
    @Test
    public void t21() {
        List<Integer> list = Arrays.asList(1, 10, 3, 7, 5);
        int a = list.stream()
                .peek(num -> System.out.println("will filter " + num))
//                .filter(x -> x > 5)
                .findFirst()
                .get();
        System.out.println(a);
    }

    /**
     * will filter 1
     * 1
     */
    @Test
    public void t22() {
        List<Integer> list = Arrays.asList(1, 10, 3, 7, 5);
        final List<Integer> collect = list.stream()
                .peek(num -> System.out.println("will filter " + num))
                .filter(x -> x > 5)
//                .findFirst()
                .collect(Collectors.toList());
        System.out.println(collect.size());
    }

    /**
     * will filter 1
     * will filter 10
     * will filter 3
     * will filter 7
     * will filter 5
     * 2
     */
    @Test
    public void testPeek() {
        System.out.println();
        System.out.println("Test peek start");
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");
        // Print debug information for every element
        System.out.print("peak1 = ");
        List<String> peek = collection.stream().map(String::toUpperCase).peek((e) -> System.out.print(e + ",")).
                collect(Collectors.toList());
        System.out.println(); // print  peak1 = A1,A2,A3,A1,
        System.out.println("peek2 = " + peek); // print  peek2 = [A1, A2, A3, A1]

        Collection<StringBuilder> list = Arrays.asList(new StringBuilder("a1"), new StringBuilder("a2"), new StringBuilder("a3"));
        List<StringBuilder> newList = list.stream().peek((p) -> p.append("_new")).collect(Collectors.toList());
        System.out.println("newList = " + newList); // print  newList = [a1_new, a2_new, a3_new]
    }

    // spring 中操作

//    private void updateFilters() {
//
//        if (this.filters.isEmpty()) {
//            return;
//        }
//
//        List<WebFilter> filtersToUse = this.filters.stream()
//                .peek(filter -> {
//                    if (filter instanceof ForwardedHeaderTransformer && this.forwardedHeaderTransformer == null) {
//                        this.forwardedHeaderTransformer = (ForwardedHeaderTransformer) filter;
//                    }
//                })
//                .filter(filter -> !(filter instanceof ForwardedHeaderTransformer))
//                .collect(Collectors.toList());
//
//        this.filters.clear();
//        this.filters.addAll(filtersToUse);
//    }
//


//    @Override
//    public Strategy createStrategyIfMatch(Class target, Method method) {
//        if (switcher.isEmpty()) {
//            return null;
//        }
//        String text = target.getName().concat(".").concat(method.getName());
//
//        return switcher.entrySet().stream()
//                .filter(entry -> antPathMatcher.match(entry.getValue().getExpression(), text))
//                .peek(entry -> entry.getValue().setId(entry.getKey()))
//                .map(Map.Entry::getValue)
//                .findFirst()
//                .orElse(null);
//    }

    /**
     * [1, 2, 3, 4]
     * [2, 4, 6, 8]
     */
    @Test
    public void t3() {
        List<Integer> list = new ArrayList<>();
        List<Integer> result = Stream.of(1, 2, 3, 4)
                .peek(x -> list.add(x))
                .map(x -> x * 2)
                .collect(Collectors.toList());

        System.out.println(list);
        System.out.println(result);
    }

    /**
     * [13, 2, 4, 6, 1, 10, 3, 31, 21, 19]
     * [男, 男, 男, 女, 男, 男, 女, 女, 女, 女]
     * [张2, 张6, 张1, 张3, 张5, 张8, 张4, 张4, 张4, 张4]
     */
    @Test
    public void t4() {
        List<Integer> ids = Lists.newArrayList();
        List<String> strs = Lists.newArrayList();

        List<String> name = list.stream().
                peek(e -> ids.add(e.getId()))
                .peek(e -> strs.add(e.getSex()))
                .map(Person2::getLastName).collect(Collectors.toList());
        System.out.println(ids.toString());
        System.out.println(strs.toString());
        System.out.println(name.toString());
    }


}
