package queryDslTest;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import queryDsl.Demo2Run;
import queryDsl.dao.PersonDao;
import queryDsl.entity.Person;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo2Run.class)
public class AppTest {

    @Resource
    private PersonDao dao;


    private List<Person> list;

    // 初始化数据需要的
    @Before
    public void inti(){
        Person p1 = new Person("张", "三", 23);
        Person p2 = new Person("李", "撒", 34);
        Person p3 = new Person("赵", "特", 56);
        Person p4 = new Person("王", "亚", 46);
        Person p5 = new Person("孙", "扒", 62);
        Person p6 = new Person("候", "安然", 12);
        Person p7 = new Person("郑", "问", 9);
        list = Lists.newArrayList(p1, p2, p3, p4, p5, p6, p7);
    }

    @Test
    public void add(){
        long count = list.stream().map(e -> dao.save(e)).count();
        System.out.println(count);
    }

    @Test
    // select .. from Person p where p.firstname=?
    public void t1(){
        List<Person> liang = dao.findPersonsByFirstnameQueryDSL("张");
        System.out.println(liang);
    }
    @Test
    public void testCreation() {
        dao.save(new Person("Erich", "Gamma"));
        final Person person = new Person("Kent", "Beck");
        dao.save(person);
        dao.save(new Person("Ralph", "Johnson"));
    }

    @Test
    // select .. from Person person0_ where person0_.firstname=? and person0_.surname=?
    public void testMultipleFilter() {
        final Person personFromDb = dao.findPersonsByFirstnameAndSurnameQueryDSL("Ralph", "Johnson").get(0);
        System.out.println(personFromDb);
    }

    @Test
    // select .. from Person person0_ where person0_.firstname=? order by person0_.surname desc
    public void testOrdering() {
        final Person personFromDb = dao.findPersonsByFirstnameIndescendingOrderQueryDSL("Kent").get(0);
    }

    @Test
    public void testMaxAge() {
        final int maxAge = dao.findMaxAge();

        //select max(person0_.age) as col_0_0_ from Person person0_ limit ?
        System.out.println(maxAge);
    }

    @Test
    public void testMaxAgeByName() {
        final Map<String, Integer> maxAge = dao.findMaxAgeByName();
        // select p.firstname, p.age from Person p
        System.out.println(maxAge);
    }

    @Test
    public void firAndSur() {
        Map<String, String> map = dao.findfirAndSurName();
        // select p.firstname , p.surname from Person p
        System.out.println(map);
    }

    @Test
    // 加条件的自定义返回
    public void firAndSur2() {
        Map<String, String> map = dao.findfirAndSurName2();

        // select p.firstname , p.surname from Person p where cast(p.age as signed)<? and p.id>?
        System.out.println(map);
    }


}