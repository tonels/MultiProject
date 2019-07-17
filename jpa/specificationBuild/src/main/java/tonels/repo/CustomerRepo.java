package tonels.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import jpaCommon.model.CustomersEntity;

@Repository
public interface CustomerRepo extends JpaRepository<CustomersEntity,Integer>, JpaSpecificationExecutor<CustomersEntity> {



}
