package tonels;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import jpaCommon.model.CustomersEntity;
import tonels.service.CustomerService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpecificationApp.class)
public class CriQuerybuilderTest {

    @Autowired
    private CustomerService customerService;

    private List<CustomersEntity> list;

    @Before
    public void init(){
        CustomersEntity c1 = new CustomersEntity().setCity("NYC");
        CustomersEntity c2 = new CustomersEntity().setCountry("USA");
        CustomersEntity c3 = new CustomersEntity().setState("NY");
        CustomersEntity c4 = new CustomersEntity().setCustomerNumber(114);
        CustomersEntity c5 = new CustomersEntity().setCity("NYC").setCountry("USA").setState("NY").setCustomerNumber(114);
        list = Lists.newArrayList(c1,c2,c3,c4,c5);
    }

    @Test
    // select * from customers customerse0_ where customerse0_.contactFirstName like ?
    public void t1(){
        List<CustomersEntity> allByExample = customerService.findBySpecification2(list.get(0));
        System.out.println(allByExample.size()); // 4
    }
    @Test
    // select * from customers customerse0_ where customerse0_.city=?
    public void t2(){
        List<CustomersEntity> allByExample = customerService.findBySpecification2(list.get(1));
        System.out.println(allByExample.size()); // 3
    }

    @Test
    // select * from customers customerse0_ where customerse0_.salesRepEmployeeNumber>=1300
    public void t3(){
        List<CustomersEntity> allByExample = customerService.findBySpecification2(list.get(2));
        System.out.println(allByExample.size()); //69
    }
    @Test
    // select * from customers customerse0_ where (customerse0_.contactFirstName like ?) and customerse0_.city=?
    public void t4(){
        List<CustomersEntity> allByExample = customerService.findBySpecification2(list.get(3));
        System.out.println(allByExample.size());
    }

    @Test
    // select * from customers customerse0_ where (customerse0_.contactFirstName like ?) and customerse0_.city=? and customerse0_.salesRepEmployeeNumber>=111
    public void t5(){
        List<CustomersEntity> allByExample = customerService.findBySpecification2(list.get(4));
        System.out.println(allByExample.size());
    }



}
