package tonels.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import tonels.entity.TCity;
import tonels.repo.customer.TCityRepoCustom;

//@Repository
public interface TCityRepo extends JpaRepository<TCity, Long>, QuerydslPredicateExecutor<TCity>, TCityRepoCustom {

}
