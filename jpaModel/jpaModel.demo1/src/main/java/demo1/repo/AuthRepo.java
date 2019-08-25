package demo1.repo;

import demo1.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepo extends JpaRepository<Authority,Long> {
}
