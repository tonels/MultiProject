package querydsl.vo;

import com.querydsl.core.Tuple;
import lombok.Data;
import lombok.experimental.Accessors;
import querydsl.entity.QTCity;
import querydsl.entity.QTHotel;

import java.io.Serializable;

/**
 * 查询条件
 */

@Data
@Accessors(chain = true)
public class CityHotelVo implements Serializable {

    private static final long serialVersionUID = 2546523L;

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
