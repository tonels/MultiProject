package querydsl.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "t_hotel",schema = "test",catalog = "")
public class THotel {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String address;
    private Integer city;//保存着城市的id主键



}
