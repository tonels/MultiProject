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
public class Orderdetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("orderNumber")
    private Integer orderNumber;

    @TableField("productCode")
    private String productCode;

    @TableField("quantityOrdered")
    private Integer quantityOrdered;

    @TableField("priceEach")
    private BigDecimal priceEach;

    @TableField("orderLineNumber")
    private Integer orderLineNumber;


}
