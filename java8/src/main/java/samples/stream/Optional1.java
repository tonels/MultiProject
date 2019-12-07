package samples.stream;


import org.junit.Test;

import java.util.Optional;
import java.util.function.Supplier;

public class Optional1 {

    static class Outer {
        Nested nested = new Nested();

        public Nested getNested() {
            return nested;
        }
    }

    static class Nested {
        Inner inner = new Inner();

        public Inner getInner() {
            return inner;
        }
    }

    static class Inner {
        String foo = "boo";

        public String getFoo() {
            return foo;
        }
    }

    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }


    @Test
    public void t11() {
        Optional<String> optional = Optional.of("bam");

        optional.isPresent();           // true
        optional.get();                 // "bam"
        optional.orElse("fallback");    // "bam"

        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"

    }

    @Test
    public void t22() {
        Outer outer = new Outer();
        resolve(() -> outer.getNested().getInner().getFoo())
                .ifPresent(System.out::println);
    }

    @Test
    public void t33() {
        Optional.of(new Outer())
                .map(Outer::getNested)
                .map(Nested::getInner)
                .map(Inner::getFoo)
                .ifPresent(System.out::println);
    }

    @Test
    public void t44() {
        Optional.of(new Outer())
                .flatMap(o -> Optional.ofNullable(o.nested))
                .flatMap(n -> Optional.ofNullable(n.inner))
                .flatMap(i -> Optional.ofNullable(i.foo))
                .ifPresent(System.out::println);
    }

    @Test
    public void t55() {

    }

    @Test
    public void t66() {

    }

    @Test
    public void t77() {

    }

    @Test
    public void t88() {

    }

    @Test
    public void t99() {

    }

    @Test
    public void t1010() {

    }

    @Test
    public void t1111() {

    }

    @Test
    public void t1212() {

    }

    @Test
    public void t1313() {

    }

    @Test
    public void t1414() {

    }

    @Test
    public void t1515() {

    }

    @Test
    public void t1616() {

    }

    @Test
    public void t1717() {

    }

    @Test
    public void t1818() {

    }

    @Test
    public void t1919() {

    }

    @Test
    public void t2020() {

    }


}