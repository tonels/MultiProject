package mbDemo2.enti;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Blob;

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
public class Productlines implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("productLine")
    private String productLine;

    @TableField("textDescription")
    private String textDescription;

    @TableField("htmlDescription")
    private String htmlDescription;

    private Blob image;


}
