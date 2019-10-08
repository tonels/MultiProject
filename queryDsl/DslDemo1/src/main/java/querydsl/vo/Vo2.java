package querydsl.vo;

import com.querydsl.core.Tuple;
import lombok.Data;
import querydsl.entity.QTCity;
import querydsl.entity.QTHotel;
import querydsl.entity.TCity;
import querydsl.entity.THotel;

@Data
public class Vo2 {

    private TCity tCity;

    private THotel tHotel;

    public Vo2(Tuple t) {
        this.tCity = t.get(QTCity.tCity);
        this.tHotel = t.get(QTHotel.tHotel);
    }

    public Vo2() {
    }
}
