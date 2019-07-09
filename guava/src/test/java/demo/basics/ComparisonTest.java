package demo.basics;


import guava.basics.Person;
import org.junit.Assert;
import org.testng.annotations.Test;

@Test
public class ComparisonTest {

    public void shouldComparePersons() {
        final Person p1 = new Person(0, "Stuart", "Gunter");
        final Person p2 = new Person(0, "Stuart", "Gunter");
        final Person p3 = new Person(0, "Stuart", "Other");

        Assert.assertTrue(p1.compareTo(p2) == 0);
        Assert.assertTrue(p1.compareTo(p3) < 0);
    }
}
