package tonels;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import tonels.model.User;
import tonels.repo.UserRepo;
import tonels.searchCriteria.SearchCriteria;
import tonels.specification.UserSpecification;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest()
public class UserTest {

    @Autowired
    private UserRepo repository;

    private User userJohn;
    private User userTom;

    @Before
    public void init() {
        userJohn = new User();
        userJohn.setFirstName("John");
        userJohn.setLastName("Doe");
        userJohn.setEmail("john@doe.com");
        userJohn.setAge(22);
        repository.save(userJohn);

        userTom = new User();
        userTom.setFirstName("Tom");
        userTom.setLastName("Doe");
        userTom.setEmail("tom@doe.com");
        userTom.setAge(26);
        repository.save(userTom);
    }

    @Test
    public void givenLast_whenGettingListOfUsers_thenCorrect() {
        UserSpecification spec = new UserSpecification(new SearchCriteria("lastName", ":", "doe"));
        List<User> results = repository.findAll(spec);
    }

    @Test
    public void givenFirstAndLastName_whenGettingListOfUsers_thenCorrect() {
        UserSpecification spec1 =
                new UserSpecification(new SearchCriteria("firstName", ":", "john"));
        UserSpecification spec2 =
                new UserSpecification(new SearchCriteria("lastName", ":", "doe"));

        List<User> results = repository.findAll(Specification.where(spec1).and(spec2));
    }

    @Test
    public void givenLastAndAge_whenGettingListOfUsers_thenCorrect() {
        UserSpecification spec1 = new UserSpecification(new SearchCriteria("age", ">", "25"));
        UserSpecification spec2 = new UserSpecification(new SearchCriteria("lastName", ":", "doe"));

        List<User> results = repository.findAll(Specification.where(spec1).and(spec2));
    }

    @Test
    public void givenWrongFirstAndLast_whenGettingListOfUsers_thenCorrect() {
        UserSpecification spec1 =new UserSpecification(new SearchCriteria("firstName", ":", "Adam"));
        UserSpecification spec2 =new UserSpecification(new SearchCriteria("lastName", ":", "Fox"));

        List<User> results =repository.findAll(Specification.where(spec1).and(spec2));
    }

}