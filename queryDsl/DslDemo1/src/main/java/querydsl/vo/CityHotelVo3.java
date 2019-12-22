package querydsl.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 查询条件
 */

@Data
@Accessors(chain = true)
public class CityHotelVo3 implements Serializable {

    private static final long serialVersionUID = 2546523L;

    private Integer id;

    private String cityName;

    private String hotelName;

    private String address;


    public CityHotelVo3(Integer id, String cityName, String hotelName, String address) {
        this.id = id;
        this.cityName = cityName;
        this.hotelName = hotelName;
        this.address = address;
    }


}
