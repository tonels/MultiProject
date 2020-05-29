package jpaCommon.repo;

import jpaCommon.model.CustomersEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersEntityRepo extends PagingAndSortingRepository<CustomersEntity, Integer>, JpaSpecificationExecutor<CustomersEntity> {
}
