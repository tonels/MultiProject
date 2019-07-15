package tonels;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tonel.model.CustomersEntity;
import tonels.repo.CustomerRepo;
import tonels.service.CustomerService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpecificationApp.class)
public class AppTest {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CustomerService customerService;


    @Test
    public void t1(){
        CustomersEntity c1 = new CustomersEntity().setContactFirstName("al");
        CustomersEntity c2 = new CustomersEntity().setCity("asd");
        CustomersEntity c3 = new CustomersEntity().setSalesRepEmployeeNumber(111);
        CustomersEntity c4 = new CustomersEntity().setContactFirstName("al").setCity("ss");
        CustomersEntity c5 = new CustomersEntity().setContactFirstName("al").setCity("s").setSalesRepEmployeeNumber(111);

        List<CustomersEntity> allByExample = customerService.findAllByExample(c1);
        System.out.println(allByExample);
    }




}
