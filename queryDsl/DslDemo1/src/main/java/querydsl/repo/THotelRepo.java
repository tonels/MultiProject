package querydsl.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import querydsl.entity.THotel;

@Repository
public interface THotelRepo extends JpaRepository<THotel,Integer>, JpaSpecificationExecutor<THotel> {
}
