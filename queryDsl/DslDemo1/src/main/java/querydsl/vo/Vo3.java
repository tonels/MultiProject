package querydsl.vo;

import com.querydsl.core.Tuple;
import lombok.Data;
import querydsl.entity.QTCity;
import querydsl.entity.QTHotel;
import querydsl.entity.TCity;
import querydsl.entity.THotel;

@Data
public class Vo3 {

    private String map;

    private Long total;

    public Vo3() {
    }

    public Vo3(Tuple t) {
        this.map = t.get(QTCity.tCity.map);
        this.total = t.get(QTCity.tCity.map.count());
    }
}
