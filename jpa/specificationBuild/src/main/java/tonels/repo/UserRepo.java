package tonels.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tonels.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long> , JpaSpecificationExecutor<User> {
}
