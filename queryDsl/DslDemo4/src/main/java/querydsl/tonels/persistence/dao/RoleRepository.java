package querydsl.tonels.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import querydsl.tonels.persistence.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByNameValue(Integer name);

    @Override
    void delete(Role role);

}
