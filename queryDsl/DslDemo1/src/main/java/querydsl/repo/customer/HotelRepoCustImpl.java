package querydsl.repo.customer;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;
import querydsl.entity.QTCity;
import querydsl.entity.QTHotel;
import querydsl.vo.CityHotelVo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HotelRepoCustImpl implements HotelRepoCust {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Tuple> findcityHotel() {
        JPAQuery<CityHotelVo> query = new JPAQuery<>(em);
        QTCity c = QTCity.tCity;
        QTHotel h = QTHotel.tHotel;

        JPAQuery<Tuple> on = query.select(
                c.id,
                c.name,
                h.name,
                h.address).from(c).leftJoin(h).on(c.id.eq(h.city));

        QueryResults<Tuple> rts = on.fetchResults();
        return rts.getResults();
    }
}
