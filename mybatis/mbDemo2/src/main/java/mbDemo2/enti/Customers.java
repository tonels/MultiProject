package mbDemo2.enti;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author tonels
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Customers implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("customerNumber")
    private Integer customerNumber;

    @TableField("customerName")
    private String customerName;

    @TableField("contactLastName")
    private String contactLastName;

    @TableField("contactFirstName")
    private String contactFirstName;

    private String phone;

    @TableField("addressLine1")
    private String addressLine1;

    @TableField("addressLine2")
    private String addressLine2;

    private String city;

    private String state;

    @TableField("postalCode")
    private String postalCode;

    private String country;

    @TableField("salesRepEmployeeNumber")
    private Integer salesRepEmployeeNumber;

    @TableField("creditLimit")
    private BigDecimal creditLimit;


}
