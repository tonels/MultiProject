package tonels.mbdemo3.params;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data@Accessors(chain = true)
public class CustoParams implements Serializable {

    private Integer customernumber;

    private String customername;

    private BigDecimal amount;


}
