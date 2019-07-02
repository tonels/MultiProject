package tonels.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Atest {
    @Id
   private Integer id;
   private String ba;
   private Enum ss;
}
