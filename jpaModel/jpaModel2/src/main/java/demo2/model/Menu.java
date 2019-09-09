package demo2.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer menuId;  // 菜单id

    private Integer parentId;  // 父级id

    private String menuName;  // 菜单名称

    private String menuUrl;  // 菜单url

    private String menuIcon;  // 菜单图标

    private String authority;  // 对应权限

    private Integer sortNumber;  // 排序号

    private Date createTime;  // 创建时间

    private Date updateTime;  // 修改时间

    private String parentName;  // 父级菜单

}