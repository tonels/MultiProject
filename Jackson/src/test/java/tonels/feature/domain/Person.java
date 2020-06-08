package tonels.feature.domain;

import java.util.Date;

public class Person {
    private Integer id;
    private String name;
    private Date birthDate;

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
    }

    public Person(Integer id, String name, Date birthDate) {
        super();
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Person() {
        // TODO Auto-generated constructor stub
    }

}
