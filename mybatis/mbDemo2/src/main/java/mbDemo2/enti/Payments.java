package mbDemo2.enti;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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
public class Payments implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("customerNumber")
    private Integer customerNumber;

    @TableField("checkNumber")
    private String checkNumber;

    @TableField("paymentDate")
    private LocalDate paymentDate;

    /**
     * 自己手动添加的，当数据库多于model字段时，不会报错，反之会报错
     */
    private String aaa;

    private BigDecimal amount;


}
