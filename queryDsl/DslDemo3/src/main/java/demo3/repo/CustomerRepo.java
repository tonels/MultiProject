package demo3.repo;

import demo3.model.CustomersEntity;
import demo3.repo.customize.CustomerRepoCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<CustomersEntity,Long>, JpaSpecificationExecutor<CustomersEntity>, CustomerRepoCustom {

    List<CustomersEntity> findByStateLike(String s);
}
