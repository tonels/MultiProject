package querydsl.repo.customer;

import cn.hutool.json.JSONUtil;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import querydsl.entity.QEntity.QTCity;
import querydsl.entity.QEntity.QTHotel;
import querydsl.vo.CityHotelVo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

public class TCityRepoImpl implements TCityRepoCustom{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Tuple> findCityAndHotel(Predicate predicate) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        JPAQuery<Tuple> jpaQuery = queryFactory.select(QTCity.tCity, QTHotel.tHotel)
                .from(QTCity.tCity)
                .leftJoin(QTHotel.tHotel)
                .on(QTHotel.tHotel.city.longValue().eq(QTCity.tCity.id.longValue()));
        jpaQuery.where(predicate);
        return jpaQuery.fetch();
    }

    @Override
    public QueryResults<Tuple> findCityAndHotelPage(Predicate predicate, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        JPAQuery<Tuple> jpaQuery = queryFactory.select(QTCity.tCity.id, QTHotel.tHotel)
                .from(QTCity.tCity)
                .leftJoin(QTHotel.tHotel)
                .on(QTHotel.tHotel.city.longValue().eq(QTCity.tCity.id.longValue()))
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        return jpaQuery.fetchResults();
    }

    @Override
    public List<CityHotelVo> findcityHotel() {
        JPAQuery<CityHotelVo> query = new JPAQuery<>(em);
        QTCity c = QTCity.tCity;
        QTHotel h = QTHotel.tHotel;

        JPAQuery<Tuple> on = query.select(c.id,
                c.name.as("cityName"),
                h.name.as("hotelName"),
                h.address).from(c).leftJoin(h).on(c.id.eq(h.city));

        QueryResults<Tuple> rts = on.fetchResults();
        List<Tuple> results = rts.getResults();
        List<CityHotelVo> collect = results.stream().map(r -> JSONUtil.toBean(JSONUtil.toJsonStr(r), CityHotelVo.class)).collect(Collectors.toList());

        return collect;
    }
}
