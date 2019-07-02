package tonels.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "city")
public class City {

    @Id
    @Column(name = "rank_id")
    private Long rankId;

    private String city;

    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "eng_code")
    private String engName;

    private Long population;
}
