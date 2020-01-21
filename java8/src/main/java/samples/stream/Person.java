package samples.stream;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Person {

    public enum Sex {
        MALE, FEMALE
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;
    Integer age;

    public Person(String name, LocalDate birthday, Sex gender, String emailAddress,Integer age) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.emailAddress = emailAddress;
        this.age = age;
    }
}
