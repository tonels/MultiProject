package tonels.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tonels.model.State;

@Repository
public interface ExcelRepo extends JpaRepository<State,Long>, JpaSpecificationExecutor<State> {


}
