package querydsl.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.Tuple;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import querydsl.entity.QTCity;
import querydsl.entity.QTHotel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    public CityHotelVo() {
    }

    // todo 这个注解待研究
    @QueryProjection
    public CityHotelVo(Integer id, String cityName, String hotelName, String address) {
        this.id = id;
        this.cityName = cityName;
        this.hotelName = hotelName;
        this.address = address;
    }

    public CityHotelVo(Tuple t) {
        this.id = t.get(QTCity.tCity.id);
        this.cityName = t.get(QTCity.tCity.name);
        this.hotelName = t.get(QTHotel.tHotel.name);
        this.address = t.get(QTHotel.tHotel.address);
    }

}
