package tonels.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Data
@Entity
@Table(name = "offices", schema = "classicmodels", catalog = "")
public class OfficesEntity {

    @Id
    @Column(name = "officeCode")
    private String officeCode;

    @Basic
    @Column(name = "city")
    private String city;

    @Basic
    @Column(name = "phone")
    private String phone;

    @Basic
    @Column(name = "addressLine1")
    private String addressLine1;

    @Basic
    @Column(name = "addressLine2")
    private String addressLine2;

    @Basic
    @Column(name = "state")
    private String state;

    @Basic
    @Column(name = "country")
    private String country;

    @Basic
    @Column(name = "postalCode")
    private String postalCode;

    @Basic
    @Column(name = "territory")
    private String territory;


}
