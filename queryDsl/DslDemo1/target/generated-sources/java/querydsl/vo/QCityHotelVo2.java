package querydsl.vo;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * querydsl.vo.QCityHotelVo2 is a Querydsl Projection type for CityHotelVo2
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QCityHotelVo2 extends ConstructorExpression<CityHotelVo2> {

    private static final long serialVersionUID = -2024819188L;

    public QCityHotelVo2(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> address) {
        super(CityHotelVo2.class, new Class<?>[]{int.class, String.class, String.class}, id, name, address);
    }

}

