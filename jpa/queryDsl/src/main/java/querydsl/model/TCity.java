package querydsl.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "t_city",schema = "test",catalog = "")
public class TCity {

    @Id@GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String state;
    private String country;
    private String map;

}
