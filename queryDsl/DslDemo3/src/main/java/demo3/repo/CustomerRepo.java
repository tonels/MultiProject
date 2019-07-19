package demo3.repo;

import demo3.model.CustomersEntity;
import demo3.repo.customize.CustoRepoCustomize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//@Repository
public interface CustomerRepo extends JpaRepository<CustomersEntity,Long>, JpaSpecificationExecutor<CustomersEntity>, CustoRepoCustomize {
}
