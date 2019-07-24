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
public class Employees implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("employeeNumber")
    private Integer employeeNumber;

    @TableField("lastName")
    private String lastName;

    @TableField("firstName")
    private String firstName;

    private String extension;

    private String email;

    @TableField("officeCode")
    private String officeCode;

    @TableField("reportsTo")
    private Integer reportsTo;

    @TableField("jobTitle")
    private String jobTitle;


}
