package tonels.model.basic;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Accessors(chain = true)
@Data
@Entity
@Table(name = "orderdetails", schema = "classicmodels", catalog = "")
@IdClass(OrderdetailsEntityPK.class)
public class OrderdetailsEntity {

    @Id
    @Column(name = "orderNumber")
    private Integer orderNumber;

    @Id
    @Column(name = "productCode")
    private String productCode;

    @Basic
    @Column(name = "quantityOrdered")
    private Integer quantityOrdered;

    @Basic
    @Column(name = "priceEach")
    private BigDecimal priceEach;

    @Basic
    @Column(name = "orderLineNumber")
    private Short orderLineNumber;

}
