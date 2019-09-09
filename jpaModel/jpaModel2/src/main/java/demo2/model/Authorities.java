package demo2.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
@Data
public class Authorities {
    private static final long serialVersionUID = -6058060376656180793L;

    @Id
    private String authority;

    private String authorityName;

    private String parentName;

    private Integer sort;

    private Date createTime;

    private int checked;  // 回显选中状态

}
