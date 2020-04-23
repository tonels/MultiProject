package querydsl.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Data
@Entity
@Table(name = "employees", schema = "classicmodels", catalog = "")
public class EmployeesEntity {

    @Id
    @Column(name = "employeeNumber")
    private Integer employeeNumber;

    @Basic
    @Column(name = "lastName")
    private String lastName;

    @Basic
    @Column(name = "firstName")
    private String firstName;

    @Basic
    @Column(name = "extension")
    private String extension;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "officeCode")
    private String officeCode;

    @Basic
    @Column(name = "reportsTo")
    private Integer reportsTo;

    @Basic
    @Column(name = "jobTitle")
    private String jobTitle;

}
