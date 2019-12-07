package samples.stream;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import samples.lambda.Person2;
import sun.management.Agent;

import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 */
public class Streams1 {

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

        list = Lists.newArrayList(p1, p2, p3, p4, p5, p6, p7,p8,p9,p10);
        strings = Lists.newArrayList("a1", "a2", "b1", "c2", "c1");
    }

    @Test
    public void t1(){
        list.stream()
                .filter(e -> e.getId()>=5)
                .forEach(System.out::println);
    }

    @Test
    public void t2(){
        list.stream()
                .sorted(Comparator.comparing(Person2::getLastName)) // 默认升序
                .filter(e -> e.getId() >= 4)
                .forEach(System.out::println);
    }

    @Test
    public void t3(){
        list.stream()
                .map(e -> e.getId())
//                .sorted()  // 升序
                .sorted((a, b) -> b.compareTo(a))// 降序
                .forEach(System.out::println);
    }

    @Test
    public void t4(){
        boolean b = list.stream()
                .anyMatch(e -> e.getId() == 3);
        System.out.println(b);
    }

    @Test
    public void t5(){
        boolean b = list.stream()
                .allMatch(e -> e.getId() == 3);
        System.out.println(b);
    }

    @Test
    public void t6(){
        boolean b = list.stream()
                .noneMatch(e -> e.getId() == 5);
        System.out.println(b);
    }

    @Test
    public void t7(){
        long count = list.stream()
                .filter(e -> e.getId() >= 3)
                .count();
        System.out.println(count);
    }

    @Test
    public void t8(){
        Optional<String> reduced =
                list.stream()
                        .map(e -> e.getLastName())
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);
    }

    // 根据 list 初始化所有相关的对象
    @Test
    public void t9() {
        List<Integer> list = Lists.newArrayList(1, 3, 5);
        list.stream()
                .mapToInt(Integer::valueOf)
                .mapToObj(e -> new Person2(e, "man", "Name" + e))
                .forEach(System.out::println);
    }


    @Test
    public void t10(){
                list.stream()
                        .filter(p -> p.getLastName().startsWith("P"))
                        .collect(Collectors.toList())
                        .forEach(System.out::println);
    }

    // Stream分组函数使用
    @Test
    public void t11(){
        Map<String, List<Person2>> collect = list.stream()
                .collect(Collectors.groupingBy(p -> p.getSex()));
        collect.forEach((age,p) -> System.out.format("age %s: %s\n", age, p));

    }
    // Stream平均函数使用
    @Test
    public void t12(){
        Double averageAge = list.stream()
                .collect(Collectors.averagingInt(p -> p.getId()));
        System.out.println(averageAge);
    }

    // Stream基本统计
    @Test
    public void t13(){
        IntSummaryStatistics ageSummary =
                list.stream()
                        .collect(Collectors.summarizingInt(p -> p.getId()));
        System.out.println(ageSummary);
        // IntSummaryStatistics{count=4, sum=76, min=12, average=19,000000, max=23}

    }

    @Test
    public void t14(){
        String names = list.stream()
                .filter(p -> p.getId() >= 18)
                .map(p -> p.getLastName())
                .collect(Collectors.joining(" and ", "In China ", " are of legal age."));
        System.out.println(names);
        // In China 张4 and 张4 and 张4 are of legal age.
    }

    @Test
    public void t15(){
        Map<Integer, String> map = list.stream()
                .collect(Collectors.toMap(
                        p -> p.getId(),
                        p -> p.getLastName(),
                        (name1, name2) -> name1 + ";" + name2));

        System.out.println(map);
        // {1=张5, 2=张6, 19=张4, 3=张4, 4=张1, 21=张4, 6=张3, 10=张8, 13=张2, 31=张4}
    }

    @Test
    public void t16(){
        Collector<Person2, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner(" | "),          // supplier
                        (j, p) -> j.add(p.getLastName().toUpperCase()),  // accumulator
                        (j1, j2) -> j1.merge(j2),               // combiner
                        StringJoiner::toString);                // finisher

        String names = list.stream()
                .collect(personNameCollector);

        System.out.println(names);  // 张2 | 张6 | 张1 | 张3 | 张5 | 张8 | 张4 | 张4 | 张4 | 张4

    }

    @Test
    public void t17(){
        Collector<Person2, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> {
                            System.out.println("supplier");
                            return new StringJoiner(" | ");
                        },
                        (j, p) -> {
                            System.out.format("accumulator: p=%s; j=%s\n", p, j);
                            j.add(p.getLastName().toUpperCase());
                        },
                        (j1, j2) -> {
                            System.out.println("merge");
                            return j1.merge(j2);
                        },
                        j -> {
                            System.out.println("finisher");
                            return j.toString();
                        });

        String names = list.stream()
                .collect(personNameCollector);

        System.out.println(names);  // 张2 | 张6 | 张1 | 张3 | 张5 | 张8 | 张4 | 张4 | 张4 | 张4


    }

    @Test
    public void t18(){
        Collector<Person2, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> {
                            System.out.println("supplier");
                            return new StringJoiner(" | ");
                        },
                        (j, p) -> {
                            System.out.format("accumulator: p=%s; j=%s\n", p, j);
                            j.add(p.getLastName().toUpperCase());
                        },
                        (j1, j2) -> {
                            System.out.println("merge");
                            return j1.merge(j2);
                        },
                        j -> {
                            System.out.println("finisher");
                            return j.toString();
                        });

        String names = list.parallelStream()
                .collect(personNameCollector);

        System.out.println(names);  // 张2 | 张6 | 张1 | 张3 | 张5 | 张8 | 张4 | 张4 | 张4 | 张4

    }

    @Test
    public void t19(){
        list.stream()
                .reduce((p1, p2) -> p1.getId() > p2.getId() ? p1 : p2)
                .ifPresent(System.out::println);
        // Person2(id=31, sex=女, lastName=张4)
    }

    @Test
    public void t20(){
//        List<Streams11.Person> persons =
//                Arrays.asList(
//                        new Streams11.Person("Max", 18),
//                        new Streams11.Person("Peter", 23),
//                        new Streams11.Person("Pamela", 23),
//                        new Streams11.Person("David", 12));
//
//        Streams11.Person result =
//                persons
//                        .stream()
//                        .reduce(new Streams11.Person("", 0), (p1, p2) -> {
//                            p1.age += p2.age;
//                            p1.name += p2.name;
//                            return p1;
//                        });
//
//        System.out.format("name=%s; age=%s", result.name, result.age);

    }

    @Test
    public void t21(){
        Person2 reduce = list.stream()
                .reduce(new Person2(0, "", ""), (p1, p2) -> {
                    p1.setId(p2.getId());
                    p1.setLastName(p2.getLastName());
                    p1.setSex(p2.getSex());
                    return p1;
                });
        System.out.format("name=%s;name=%s; sex=%s", reduce.getId(),reduce.getLastName(), reduce.getSex());

    }

    @Test
    public void t22(){
        Integer ageSum = list.stream()
                .reduce(0, (sum, p) -> sum += p.getId(), (sum1, sum2) -> sum1 + sum2);
        System.out.println(ageSum);

    }

    @Test
    public void t23(){
        Integer ageSum = list.stream()
                .reduce(0,
                        (sum, p) -> {
                            System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
                            return sum += p.getId();
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
                            return sum1 + sum2;
                        });

        System.out.println(ageSum);

    }

    @Test
    public void t24(){
        Integer ageSum = list.parallelStream()
                .reduce(0,
                        (sum, p) -> {
                            System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
                            return sum += p.getId();
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
                            return sum1 + sum2;
                        });
        System.out.println(ageSum);

    }

    @Test
    public void t25(){
        Integer ageSum = list
                .parallelStream()
                .reduce(0,
                        (sum, p) -> {
                            System.out.format("accumulator: sum=%s; person=%s; thread=%s\n",
                                    sum, p, Thread.currentThread().getName());
                            return sum += p.getId();
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s; thread=%s\n",
                                    sum1, sum2, Thread.currentThread().getName());
                            return sum1 + sum2;
                        });
        System.out.println(ageSum);
    }

    @Test
    public void t26(){
        SecureRandom secureRandom = new SecureRandom(new byte[]{1, 3, 3, 7});
        int[] randoms = IntStream.generate(secureRandom::nextInt)
                .filter(n -> n > 0)
                .limit(10)
                .toArray();
        System.out.println(Arrays.toString(randoms));


        int[] nums = IntStream.iterate(1, n -> n * 2)
                .limit(11)
                .toArray();
        System.out.println(Arrays.toString(nums));

    }

    @Test
    public void t27(){
        List<String> values = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        // sequential
        long t0 = System.nanoTime();

        long count = values
                .parallelStream()
                .sorted((s1, s2) -> {
                    System.out.format("sort:    %s <> %s [%s]\n", s1, s2, Thread.currentThread().getName());
                    return s1.compareTo(s2);
                })
                .count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));

    }

    @Test
    public void t28(){
        strings
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter:  %s [%s]\n", s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map:     %s [%s]\n", s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));

    }
    @Test
    public void t29(){
        // -Djava.util.concurrent.ForkJoinPool.common.parallelism=5
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());
        // 3
    }

    @Test
    public void  t30(){

    }
    @Test
    public void  t31(){

    }
    @Test
    public void  t32(){

    }
    @Test
    public void  t33(){

    }
    @Test
    public void  t34(){

    }
    @Test
    public void  t35(){

    }
    @Test
    public void  t36(){

    }
    @Test
    public void  t37(){

    }
    @Test
    public void  t38(){

    }
    @Test
    public void  t39(){

    }






}
