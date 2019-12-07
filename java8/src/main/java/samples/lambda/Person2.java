package samples.lambda;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Data
public class Person2 {

    private Integer id;
    private String sex;
    private String lastName;

    public Person2(Integer id, String sex, String lastName) {
        this.id = id;
        this.sex = sex;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Person2 person2 = (Person2) o;

        return new EqualsBuilder()
                .append(id, person2.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }
}