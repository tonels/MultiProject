package querydsl.tonels.persistence.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer nameValue;
    @Transient
    private RoleEnum roleEnum;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private List<Privilege> privileges;


    @PostLoad
    void fillTransient() {
        if (nameValue > 0) {
            this.roleEnum = RoleEnum.of(nameValue);
        }
    }

    @PrePersist
    void fillPersistent() {
        if (nameValue != null) {
            this.nameValue = roleEnum.getRoleNum();
        }
    }


}