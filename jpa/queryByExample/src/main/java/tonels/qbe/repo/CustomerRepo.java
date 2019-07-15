package tonels.qbe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tonel.model.CustomersEntity;

@Repository
public interface CustomerRepo extends JpaRepository<CustomersEntity,Integer>, JpaSpecificationExecutor<CustomersEntity> {



}