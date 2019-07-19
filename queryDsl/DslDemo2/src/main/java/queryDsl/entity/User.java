package queryDsl.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String login;

    @Column
    private Boolean disables;

    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "user")
    private Set<BlogPost> blogPosts = new HashSet<>();

}
