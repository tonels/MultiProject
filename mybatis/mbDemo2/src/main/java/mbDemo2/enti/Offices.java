package mbDemo2.enti;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class Offices implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("officeCode")
    private String officeCode;

    private String city;

    private String phone;

    @TableField("addressLine1")
    private String addressLine1;

    @TableField("addressLine2")
    private String addressLine2;

    private String state;

    private String country;

    @TableField("postalCode")
    private String postalCode;

    private String territory;


}
