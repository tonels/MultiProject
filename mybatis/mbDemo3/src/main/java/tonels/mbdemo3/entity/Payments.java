package tonels.mbdemo3.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Payments extends PaymentsKey {

    private Date paymentdate;

    private BigDecimal amount;

}