package querydsl.vo;

import com.querydsl.core.Tuple;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import querydsl.entity.QTCity;
import querydsl.entity.QTHotel;

/**
 * 查询条件
 */

@Data
@Accessors(chain = true)
public class CityHotelVo {

    private Integer id;

    private String cityName;

    private String hotelName;

    private String address;

    public CityHotelVo() {
    }

    public CityHotelVo(Tuple t) {
        this.id = t.get(QTCity.tCity.id);
        this.cityName = t.get(QTCity.tCity.name);
        this.hotelName = t.get(QTHotel.tHotel.name);
        this.address = t.get(QTHotel.tHotel.address);
    }

}
