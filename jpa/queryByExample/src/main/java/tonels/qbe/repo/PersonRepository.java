package tonels.qbe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import tonels.qbe.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long>, JpaSpecificationExecutor<Person>, QueryByExampleExecutor<Person> {
}
