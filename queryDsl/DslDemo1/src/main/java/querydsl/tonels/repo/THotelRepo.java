package querydsl.tonels.repo;

import org.springframework.stereotype.Repository;
import querydsl.entity.THotel;
import querydsl.tonels.repo.customer.HotelRepoCustom2;

@Repository
public interface THotelRepo extends BaseRepository<THotel>, HotelRepoCustom2 {

}

