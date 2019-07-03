package tonels.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "state")
public class State {

    @Id
    private Integer id;

    @Column(name = "ch_name")
    private String chName;

    @Column(name = "en_name")
    private String enName;

    private String code;

    private String capital;

    private String capitalen;

    private String remark;


}
