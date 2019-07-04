package tonels.db.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import java.sql.Date;

@Data
@Accessors(chain = true)
@Entity
public class User {
    private Integer id;

    private String name;

    private Date birthday;

    private String gender;



}
