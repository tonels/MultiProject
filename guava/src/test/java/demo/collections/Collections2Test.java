package demo.collections;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import guava.basics.Person;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.Collection;

import static org.testng.AssertJUnit.assertEquals;

@Test
public class Collections2Test {

    private Collection<Person> people;

    @BeforeMethod
    public void setup() {
        people = Lists.newArrayList(
                new Person(0, "First", "Person"),
                new Person(1, "Mary", "Jones"),
                new Person(2, "Michael", "Parker")
        );
    }

    public void shouldFilterCollection() {
        final Collection<Person> filtered = Collections2.filter(people,
                new Predicate<Person>() {
                    public boolean apply(@Nullable Person input) {
                        return input != null && input.getFirstName().startsWith("M");
                    }
                });
        filtered.forEach(System.out::println);

        assertEquals(2, filtered.size());
    }

    public void shouldFilterCollection2() {
        final Collection<Person> filtered = Collections2.filter(people,
                new Predicate<Person>() {
                    @Override
                    public boolean apply(@org.checkerframework.checker.nullness.qual.Nullable Person input) {
                        return false;
                    }
                });
        filtered.forEach(System.out::println);

        assertEquals(2, filtered.size());
    }

    public void shouldTransformCollection() {
        // better to use the collection-specific version on Lists, Maps, Sets, etc.
        final Collection<String> transformed = Collections2.transform(people,
                input -> input != null ? input.getFirstName() : "");
        transformed.forEach(System.out::println);
    }

    public void ImmutableSetUnionTest() {
        Person p1 = new Person(1, "zhang", "san");
        Person p2 = new Person(2, "wang", "wu");
        Person p3 = new Person(3, "li", "qi");
        Person p4 = new Person(4, "zhao", "si");

        ImmutableSet<Person> set1 = ImmutableSet.of(p1, p2, p3);
        ImmutableSet<Person> set2= ImmutableSet.of(p2, p4);
        assertEquals(4, Sets.union(set1, set2).size());

        assertEquals(ImmutableSet.of(p2), Sets.intersection(set1, set2)); // 取交集
    }

}
