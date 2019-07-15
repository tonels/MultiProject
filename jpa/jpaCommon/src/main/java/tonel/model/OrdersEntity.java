package tonel.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Date;

@Accessors(chain = true)
@Data
@Entity
@Table(name = "orders", schema = "classicmodels", catalog = "")
public class OrdersEntity {

    @Id
    @Column(name = "orderNumber")
    private Integer orderNumber;

    @Basic
    @Column(name = "orderDate")
    private Date orderDate;

    @Basic
    @Column(name = "requiredDate")
    private Date requiredDate;

    @Basic
    @Column(name = "shippedDate")
    private Date shippedDate;

    @Basic
    @Column(name = "status")
    private String status;

    @Basic
    @Column(name = "comments")
    private String comments;

    @Basic
    @Column(name = "customerNumber")
    private Integer customerNumber;

}
