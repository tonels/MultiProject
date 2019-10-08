package querydsl.vo;

import com.querydsl.core.Tuple;
import lombok.Data;
import querydsl.entity.QTCity;
import querydsl.entity.QTHotel;
import querydsl.entity.THotel;

@Data
public class Vo1 {
    private Integer id;
    private THotel tHotel;

    public Vo1(Tuple t) {
        this.id = t.get(QTCity.tCity.id);
        this.tHotel = t.get(QTHotel.tHotel);
    }

    public Vo1() {
    }
}
