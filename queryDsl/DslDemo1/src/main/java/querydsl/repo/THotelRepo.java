package querydsl.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import querydsl.entity.THotel;
import querydsl.repo.customer.HotelRepoCust;
import querydsl.repo.customer.HotelRepoCustom2;

@Repository
public interface THotelRepo extends BaseRepository<THotel> , HotelRepoCustom2 {

}

