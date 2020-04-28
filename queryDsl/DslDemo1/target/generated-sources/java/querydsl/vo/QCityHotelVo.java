package querydsl.vo;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * querydsl.vo.QCityHotelVo is a Querydsl Projection type for CityHotelVo
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QCityHotelVo extends ConstructorExpression<CityHotelVo> {

    private static final long serialVersionUID = -480958746L;

    public QCityHotelVo(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> cityName, com.querydsl.core.types.Expression<String> hotelName, com.querydsl.core.types.Expression<String> address) {
        super(CityHotelVo.class, new Class<?>[]{int.class, String.class, String.class, String.class}, id, cityName, hotelName, address);
    }

}

