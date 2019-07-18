package queryDsl.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Data
@Entity
@Accessors(chain = true)
public class Person {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    @Column
    private String firstname;

    @Column
    private String surname;

    @Column
    private Integer age;

    public Person(final String firstname, final String surname) {
        this.firstname = firstname;
        this.surname = surname;
    }


    public Person(final String firstname, final String surname, final Integer age) {
        this.firstname = firstname;
        this.surname = surname;
        this.age = age;
    }
}
