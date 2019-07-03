package tonels.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tonels.model.City;

@Repository
public interface CityRepo extends JpaRepository<City,Long>, JpaSpecificationExecutor<City> {


}
