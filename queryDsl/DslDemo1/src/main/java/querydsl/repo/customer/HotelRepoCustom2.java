package querydsl.repo.customer;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;

public interface HotelRepoCustom2 {

    QueryResults<Tuple> findCityAndHotelPage(Predicate predicate, Pageable pageable);

}
