package queryDsl.dao;

import queryDsl.entity.Person;

import java.util.List;
import java.util.Map;

public interface PersonDao {

    public Person save(Person person);

    public List<Person> findPersonsByFirstnameQueryDSL(String firstname);

    public List<Person> findPersonsByFirstnameAndSurnameQueryDSL(String firstname,String surname);

    public List<Person> findPersonsByFirstnameIndescendingOrderQueryDSL(String firstname);

    public int findMaxAge();

    public Map<String,Integer> findMaxAgeByName();

    public Map<String,String> findfirAndSurName();
    public Map<String,String> findfirAndSurName2(); // 加条件

//    public Map<String,Integer> findfirAndSurAndAge();

}
