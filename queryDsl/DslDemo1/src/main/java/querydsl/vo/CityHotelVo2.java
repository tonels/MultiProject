package querydsl.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 本来这里映射 null，加上构造方法就好了
 * 查询条件
 */

@Data
@Accessors(chain = true)
public class CityHotelVo2 implements Serializable {

    private static final long serialVersionUID = 2546523L;

    private Integer id;

    private String name;

    private String address;

    public CityHotelVo2(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
