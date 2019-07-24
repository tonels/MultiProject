package tonels.mbdemo3.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Customers {

    private Integer customernumber;

    private String customername;

    private String contactlastname;

    private String contactfirstname;

    private String phone;

    private String addressline1;

    private String addressline2;

    private String city;

    private String state;

    private String postalcode;

    private String country;

    private Integer salesrepemployeenumber;

    private BigDecimal creditlimit;



    private Date paymentdate;

    private BigDecimal amount;


}