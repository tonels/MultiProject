package querydsl.repo;

import org.springframework.stereotype.Repository;
import querydsl.entity.THotel;
import querydsl.repo.customer.HotelRepoCustom2;

@Repository
public interface THotelRepo extends BaseRepository<THotel>, HotelRepoCustom2 {

}

