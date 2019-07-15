package tonels.qbe.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Accessors(chain = true)
@Data
@Entity
public class Person {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    public static Person create(String name) {
        return new Person().setName(name);
    }

}
