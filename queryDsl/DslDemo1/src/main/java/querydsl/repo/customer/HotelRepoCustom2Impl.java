package querydsl.repo.customer;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import querydsl.entity.QTCity;
import querydsl.entity.QTHotel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//@Repository // 这里加或不加都是可以的
public class HotelRepoCustom2Impl implements HotelRepoCustom2 {

    @PersistenceContext
    private EntityManager em;

    @Override
    public QueryResults<Tuple> findCityAndHotelPage(Predicate predicate, Pageable pageable) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QTCity c = QTCity.tCity;
        QTHotel h = QTHotel.tHotel;

        JPAQuery<Tuple> jpaQuery = queryFactory.select(c.id, h.tHotel)
                .from(c)
                .leftJoin(h)
                .on(h.city.longValue().eq(c.id.longValue()))
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        return jpaQuery.fetchResults();
    }

}
