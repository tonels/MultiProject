package queryDsl.QEntity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BlogPost {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String body;

    @ManyToOne
    private User user;

}
