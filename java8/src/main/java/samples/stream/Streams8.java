package samples.stream;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams8 {

    @Test
    public void t1() {
        Arrays.asList("a1", "a2", "a3")
                .stream()
                .findFirst()
                .ifPresent(System.out::println);
    }

    @Test
    public void t2() {
        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);
    }

    @Test
    public void t3() {
        IntStream.range(1, 4)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);
    }

    @Test
    public void t4() {
        Arrays.stream(new int[]{1, 2, 3})
                .map(n -> 2 * n + 1)
                .average()
                .ifPresent(System.out::println);
    }

    @Test
    public void t5() {
        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);
    }

    @Test
    public void t6() {
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }

    /**
     * 基于初始数据，所有元素相加
     */
    @Test
    public void t7() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int result = numbers
                .stream()
                .reduce(0, (subtotal, element) -> subtotal + element);
        System.out.println(result);
    }

    @Test
    public void t7_1() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int result = numbers.stream().reduce(0, Integer::sum);
        System.out.println(result);
    }

    /**
     * 将所有数据，拼接在一起
     * abcde
     */
    @Test
    public void t8() {
        List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
        String result = letters
                .stream()
                .reduce("", (partialString, element) -> partialString + element);
        System.out.println(result);
    }

    @Test
    public void t8_1() {
        List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
        String result = letters.stream().reduce("", String::concat);
        System.out.println(result);
    }

    /**
     * ABCDE
     */
    @Test
    public void t8_2() {
        List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
        String result = letters.stream().reduce(
                "", (partialString, element) -> partialString.toUpperCase() + element.toUpperCase());
        System.out.println(result);
    }

    /**
     * todo 将某集合中的某一列，合并处理
     * 65
     */
    @Test
    public void t9() {
        List<User> users = Arrays.asList(new User("John", 30), new User("Julie", 35));
        int result = users.stream()
                .reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge(), Integer::sum);
        System.out.println(result);
    }

    /**
     *
     * ,JohnJulie
     */
    @Test
    public void t9_1() {
        List<User> users = Arrays.asList(new User("John", 30), new User("Julie", 35));
        String reduce = users.stream()
                .reduce(",",(partialAgeResult, user) -> partialAgeResult + user.getName(), String::concat);
        System.out.println(reduce);
    }

    /**
     * User(name=John, age=40)
     */
    @Test
    public void t9_2() {
        List<User> users = Arrays.asList(new User("John", 30), new User("Julie", 35),new User("Tolj", 40));
        User user = users.stream()
                .reduce((u1, u2) -> new User(u1.getName(), u2.getAge())).orElse(null);
        System.out.println(user);
    }

    @Test
    public void t10() {
        List<User> users = Arrays.asList(new User("John", 30,new Rating()), new User("Julie", 35,new Rating()),new User("Tolj", 40,new Rating()));
        Rating averageRating = users.stream()
                .reduce(new Rating(),
                        (rating, user) -> Rating.average(rating, user.getRating()),
                        Rating::average);
        System.out.println(averageRating);
    }

    /**
     * todo 集合的高级操作
     * 选出最大年龄的用户
     * User with max age: User(name=Tolj, age=40, rating=samples.stream.Rating@2aafb23c)
     * User(name=Tolj, age=40, rating=samples.stream.Rating@2aafb23c)
     */
    @Test
    public void t11() {
        List<User> users = Arrays.asList(
                new User("John", 30, new Rating()),
                new User("Julie", 35, new Rating()),
                new User("Tolj", 40, new Rating()));

        Optional<User> maxAge = users.stream()
                .reduce((User a, User b) -> a.getAge() < b.getAge() ? b : a);
        if (maxAge.isPresent())
            System.out.println("User with max age: " + maxAge.get());
        System.out.println(maxAge.get().toString());
    }

    /**
     * 分组
     * MALE : [zc, zb, zm]
     * FEMALE : [as, zx, zv, zn]
     *  这个示例返回，分组，并将原始集合处理后的数据
     */
     @Test
    public void t12() {
        List<Person> roster = Arrays.asList(
                new Person("as", LocalDate.now(), Person.Sex.FEMALE,"Test@",12),
                new Person("zx", LocalDate.now(), Person.Sex.FEMALE,"Test@",12),
                new Person("zc", LocalDate.now(), Person.Sex.MALE,"Test@",12),
                new Person("zv", LocalDate.now(), Person.Sex.FEMALE,"Test@",12),
                new Person("zb", LocalDate.now(), Person.Sex.MALE,"Test@",12),
                new Person("zn", LocalDate.now(), Person.Sex.FEMALE,"Test@",12),
                new Person("zm", LocalDate.now(), Person.Sex.MALE,"Test@",12)
        );

         Map<Person.Sex, List<String>> namesByGender =
                 roster.stream()
                         .collect(
                                 Collectors.groupingBy(
                                         Person::getGender,
                                         Collectors.mapping(
                                                 Person::getName,
                                                 Collectors.toList()))
                         );
         namesByGender.forEach((k,v) -> System.out.println(k + " : " + v));


     }

    /**
     * 分组函数使用
     * 这个示例返回，分组后的集合对象
     */
    @Test
    public void t12_2() {
        List<Person> roster = Arrays.asList(
                new Person("as", LocalDate.now(), Person.Sex.FEMALE,"Test@",12),
                new Person("zx", LocalDate.now(), Person.Sex.FEMALE,"Test@",12),
                new Person("zc", LocalDate.now(), Person.Sex.MALE,"Test@",12),
                new Person("zv", LocalDate.now(), Person.Sex.FEMALE,"Test@",12),
                new Person("zb", LocalDate.now(), Person.Sex.MALE,"Test@",12),
                new Person("zn", LocalDate.now(), Person.Sex.FEMALE,"Test@",12),
                new Person("zm", LocalDate.now(), Person.Sex.MALE,"Test@",12)
        );

        Map<Person.Sex, List<Person>> collect = roster.stream()
                .collect(
                        Collectors.groupingBy(Person::getGender)
                );
        collect.forEach((k,v) -> System.out.println(k + " : " + v));
    }

    /**
     * 平均年龄为：12.0
     */
    @Test
    public void t13() {
        List<Person> roster = Arrays.asList(
                new Person("as", LocalDate.now(), Person.Sex.FEMALE,"Test@",12),
                new Person("zx", LocalDate.now(), Person.Sex.FEMALE,"Test@",12),
                new Person("zc", LocalDate.now(), Person.Sex.MALE,"Test@",12),
                new Person("zv", LocalDate.now(), Person.Sex.FEMALE,"Test@",12),
                new Person("zb", LocalDate.now(), Person.Sex.MALE,"Test@",12),
                new Person("zn", LocalDate.now(), Person.Sex.FEMALE,"Test@",12),
                new Person("zm", LocalDate.now(), Person.Sex.MALE,"Test@",12)
        );

        Averager averageCollect = roster.stream()
                .filter(p -> p.getGender() == Person.Sex.MALE)
                .map(Person::getAge)
                .collect(Averager::new, Averager::accept, Averager::combine);
        System.out.println("平均年龄为：" + averageCollect.average());

    }

    /**
     * 分组函数并求和
     * MALE : 36
     * FEMALE : 48
     */
    @Test
    public void t14() {
        List<Person> roster = Arrays.asList(
                new Person("as", LocalDate.now(), Person.Sex.FEMALE,"Test@",12),
                new Person("zx", LocalDate.now(), Person.Sex.FEMALE,"Test@",12),
                new Person("zc", LocalDate.now(), Person.Sex.MALE,"Test@",12),
                new Person("zv", LocalDate.now(), Person.Sex.FEMALE,"Test@",12),
                new Person("zb", LocalDate.now(), Person.Sex.MALE,"Test@",12),
                new Person("zn", LocalDate.now(), Person.Sex.FEMALE,"Test@",12),
                new Person("zm", LocalDate.now(), Person.Sex.MALE,"Test@",12)
        );

        Map<Person.Sex, Integer> totalAgeByGender =
                roster.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Person::getGender,
                                        Collectors.reducing(
                                                0,
                                                Person::getAge,
                                                Integer::sum))
                        );
        totalAgeByGender.forEach((k,v) -> System.out.println(k + " : " + v));
    }

    /**
     * 分组求平均
     * MALE : 12.0
     * FEMALE : 12.0
     */
    @Test
    public void t15() {
        List<Person> roster = Arrays.asList(
                new Person("as", LocalDate.now(), Person.Sex.FEMALE,"Test@",12),
                new Person("zx", LocalDate.now(), Person.Sex.FEMALE,"Test@",12),
                new Person("zc", LocalDate.now(), Person.Sex.MALE,"Test@",12),
                new Person("zv", LocalDate.now(), Person.Sex.FEMALE,"Test@",12),
                new Person("zb", LocalDate.now(), Person.Sex.MALE,"Test@",12),
                new Person("zn", LocalDate.now(), Person.Sex.FEMALE,"Test@",12),
                new Person("zm", LocalDate.now(), Person.Sex.MALE,"Test@",12)
        );

        Map<Person.Sex, Double> averageAgeByGender = roster.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getGender,
                                Collectors.averagingInt(Person::getAge)));
        averageAgeByGender.forEach((k,v) -> System.out.println(k + " : " + v));
    }








}
