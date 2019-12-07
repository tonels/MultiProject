package samples.stream;

import com.google.common.collect.Lists;
import org.junit.Test;
import samples.lambda.Person2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Streams7 {

    static class Foo {
        String name;
        List<Bar> bars = new ArrayList<>();

        Foo(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Foo{" +
                    "name='" + name + '\'' +
                    ", bars=" + bars +
                    '}';
        }
    }

    static class Bar {
        String name;

        Bar(String name) {
            this.name = name;
        }
    }


    @Test
    public void t1() {
        IntStream.range(1, 4)
                .mapToObj(num -> new Foo("Foo" + num))
                .peek(f -> IntStream.range(1, 4)
                        .mapToObj(num -> new Bar("Bar" + num + " <- " + f.name))
                        .forEach(f.bars::add))
                .flatMap(f -> f.bars.stream())
                .forEach(b -> System.out.println(b.name));
    }

    @Test
    public void t2() {
        List<Foo> foos = new ArrayList<>();

        IntStream
                .range(1, 4)
                .forEach(num -> foos.add(new Foo("Foo" + num)));

        foos.forEach(f ->
                IntStream
                        .range(1, 4)
                        .forEach(num -> f.bars.add(new Bar("Bar" + num + " <- " + f.name))));

        foos.stream()
                .flatMap(f -> f.bars.stream())
                .forEach(b -> System.out.println(b.name));
    }

    @Test
    public void t3(){
        IntStream.range(1,5)
                .mapToObj(e -> new Foo("foo" + e))
                .forEach(System.out::println);
    }

    @Test
    public void t4() {
        List<Integer> list = Lists.newArrayList(1, 3, 5);
        list.stream()
                .mapToInt(Integer::valueOf)
                .mapToObj(e -> new Person2(e, "man", "Name" + e))
                .forEach(System.out::println);
    }


}