package tonels.qbe.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Accessors(chain = true)
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String dept;

    public static Employee create(String name, String dept) {
        Employee employee = new Employee().setDept(name).setDept(dept);
        return employee;
    }
}
