package querydsl.tonels.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import querydsl.tonels.persistence.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}
