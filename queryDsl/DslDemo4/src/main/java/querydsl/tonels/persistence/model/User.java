package querydsl.tonels.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "user_account")
public class User extends AbstractAuditingEntity implements Serializable {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    @Column(length = 60)
    private String password;

    private boolean enabled;

    private boolean isUsing2FA;

    private String secret;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
               joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;


}