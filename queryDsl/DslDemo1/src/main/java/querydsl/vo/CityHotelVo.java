package querydsl.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 查询条件
 */

@Data
//@Builder
@AllArgsConstructor
public class CityHotelVo {

    private Integer id;

    private String cityName;

    private String hotelName;

    private String address;

}
