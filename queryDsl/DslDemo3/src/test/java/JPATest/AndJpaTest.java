package JPATest;


import demo3.Demo3Run;
import demo3.model.CustomersEntity;
import demo3.repo.CustomerRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo3Run.class)
public class AndJpaTest {

    @Resource
    private CustomerRepo customerRepo;

    @Test
    public void findAll(){
        List<CustomersEntity> all = customerRepo.findAll();
        System.out.println(all.size());
    }

    @Test
    public void findbystate(){
        List<CustomersEntity> all = customerRepo.findByStateLike("%" + "ca" +"%");
        System.out.println(all.size());
    }





}
