package demo2.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
public class User {
    private static final long serialVersionUID = 242146703513492331L;


    @Id
    private Integer userId;  // 用户id

    private String username;  // 账号

    private String password;  // 密码

    private String nickName;  // 昵称

    private String avatar;  // 头像

    private String sex;  // 性别

    private String phone;  // 手机号

    private String email;  // 邮箱

    private Integer emailVerified;  // 邮箱是否验证，0未验证，1已验证

    private String trueName;  // 真实姓名

    private String idCard;  // 身份证号

    private Date birthday;  // 出生日期

    private Integer departmentId; // 部门id

    private Integer state;  // 状态，0正常，1冻结

    private Date createTime;  // 注册时间

    private Date updateTime;  // 修改时间

    @Transient
    private List<Authorities> authorities;  // 权限

    @Transient
    private List<Role> roles;  // 角色

}
