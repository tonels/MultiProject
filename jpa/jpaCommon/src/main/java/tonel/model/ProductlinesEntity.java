package tonel.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "productlines", schema = "classicmodels", catalog = "")
public class ProductlinesEntity {

    @Id
    @Column(name = "productLine")
    private String productLine;

    @Basic
    @Column(name = "textDescription")
    private String textDescription;

    @Basic
    @Column(name = "htmlDescription")
    private String htmlDescription;

    @Basic
    @Column(name = "image")
    private byte[] image;

}
