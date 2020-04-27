package tonels.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class PaymentsEntityPK implements Serializable {

    @Column(name = "customerNumber")
    @Id
    private Integer customerNumber;

    @Column(name = "checkNumber")
    @Id
    private String checkNumber;

}
