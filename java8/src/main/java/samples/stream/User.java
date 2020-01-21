package samples.stream;

import lombok.Data;

@Data
public class User {

    private String name;
    private Integer age;
    private String gender;
    private Rating rating;


    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, Integer age, Rating rating) {
        this.name = name;
        this.age = age;
        this.rating = rating;
    }

    public User(String name, Integer age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}
