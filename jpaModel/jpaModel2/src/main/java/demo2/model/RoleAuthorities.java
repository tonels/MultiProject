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
public class RoleAuthorities implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;

    private Integer roleId;

    private String authority;

    private Date createTime;


}