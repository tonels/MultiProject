package jpaCommon.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "products", schema = "classicmodels", catalog = "")
public class ProductsEntity {

    @Id
    @Column(name = "productCode")
    private String productCode;


    @Basic
    @Column(name = "productName")
    private String productName;

    @Basic
    @Column(name = "productLine")
    private String productLine;

    @Basic
    @Column(name = "productScale")
    private String productScale;

    @Basic
    @Column(name = "productVendor")
    private String productVendor;

    @Basic
    @Column(name = "productDescription")
    private String productDescription;

    @Basic
    @Column(name = "quantityInStock")
    private short quantityInStock;

    @Basic
    @Column(name = "buyPrice")
    private BigDecimal buyPrice;

    @Basic
    @Column(name = "MSRP")
    private BigDecimal msrp;

}
