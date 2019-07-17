package querydsl.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import querydsl.model.TCity;
import querydsl.repo.customer.TCityRepoCustom;

public interface TCityRepo extends JpaRepository<TCity,Long>, QuerydslPredicateExecutor<TCity>, TCityRepoCustom {
}
