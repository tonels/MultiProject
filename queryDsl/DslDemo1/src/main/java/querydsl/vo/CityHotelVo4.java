package querydsl.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import querydsl.util.DateUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class CityHotelVo4 implements Serializable {

    private static final long serialVersionUID = 2546523L;
    private Integer id;
    private String cityName;
    private String hotelName;
    private String address;

    private LocalDateTime formatTime;

    public CityHotelVo4(Integer id, String cityName, String hotelName, String address, String formatTime) throws ParseException {
        this.id = id;
        this.cityName = cityName;
        this.hotelName = hotelName;
        this.address = address;
        this.formatTime = DateUtils.parseLocalDateTime(formatTime);
    }
}
