package querydsl.tonels.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import querydsl.tonels.persistence.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Override
    void delete(User user);

    User findByUserName(String userName);
}
