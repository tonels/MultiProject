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
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("productCode")
    private String productCode;

    @TableField("productName")
    private String productName;

    @TableField("productLine")
    private String productLine;

    @TableField("productScale")
    private String productScale;

    @TableField("productVendor")
    private String productVendor;

    @TableField("productDescription")
    private String productDescription;

    @TableField("quantityInStock")
    private Integer quantityInStock;

    @TableField("buyPrice")
    private BigDecimal buyPrice;

    @TableField("MSRP")
    private BigDecimal msrp;


}
