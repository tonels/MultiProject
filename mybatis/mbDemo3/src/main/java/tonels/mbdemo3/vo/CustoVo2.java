package tonels.mbdemo3.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CustoVo2 {

    private String customernumber;

    private String customername;

    private Date paymentdate;

    private BigDecimal amount;
}
