package querydsl.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import querydsl.entity.TCity;
import querydsl.repo.customer.TCityRepoCustom;

@Repository
public interface TCityRepo extends JpaRepository<TCity,Long>, QuerydslPredicateExecutor<TCity>, TCityRepoCustom {

}
