package demo.basics;

import guava.basics.Person;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

@Test
public class ObjectsTest {

    private Person person;

    @BeforeMethod
    public void setup() {
        person = new Person(1, "Bob", "Smith");
    }

    public void shouldGetPersonAsString() {
        final String expected = "Person{id=1, Bob, Smith}";

        assertEquals(expected, person.toString());
    }
}
