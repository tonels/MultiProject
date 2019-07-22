package mb_test.dao2;

import mb_test.model.User;

import java.util.List;

public interface UserDaoJpa {

    void save(User user);

    List<User> queryAll();

    List<User> queryByName(String name);

    void deleteAll();

    User update(User user);


}
