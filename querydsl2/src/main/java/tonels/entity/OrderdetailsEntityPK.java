package tonels.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Accessors(chain = true)
@Data
public class OrderdetailsEntityPK implements Serializable {

    @Column(name = "orderNumber")
    @Id
    private Integer orderNumber;

    @Column(name = "productCode")
    @Id
    private String productCode;

}
