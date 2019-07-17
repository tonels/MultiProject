package querydsl.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import querydsl.model.THotel;

public interface THotelRepo extends JpaRepository<THotel,Integer>, JpaSpecificationExecutor<THotel> {
}
