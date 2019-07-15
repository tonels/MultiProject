package tonels;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;
import tonels.qbe.QbeApp;
import tonels.qbe.model.Employee;
import tonels.qbe.model.Person;
import tonels.qbe.repo.EmployeeRepository;
import tonels.qbe.repo.PersonRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QbeApp.class)
public class AppTest {

    @Autowired
    private EmployeeRepository exampleRepo;
    @Autowired
    private PersonRepository personRepo;

    @Test
    public void save1(){
        List<Employee> employees = createEmployees();
        exampleRepo.saveAll(employees);
    }

    public List<Employee> createEmployees() {
        return Arrays.asList(Employee.create("Diana", "IT"),
                Employee.create("Mike", "Admin"),
                Employee.create("Tim", "QA"),
                Employee.create("Jack", "IT"));
    }

    @Test
    public void save2(){
        List<Person> persons = createPersons();
        personRepo.saveAll(persons);
    }
    public List<Person> createPersons() {
        return Arrays.asList(Person.create("Joe"),
                Person.create("Tara"),
                Person.create(null));
    }

    @Test // select * from Employee  e where e.name=? or e.dept=?
    public void findEmployeesByNameOrDept() {
        System.out.println("-- finding employees with name Diana or dept IT --");
        Employee employee = new Employee();
        employee.setName("Diana");
        employee.setDept("IT");
        System.out.println("Example entity: "+employee);
        Example<Employee> employeeExample = Example.of(employee, ExampleMatcher.matchingAny());
        Iterable<Employee> employees = exampleRepo.findAll(employeeExample);
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    @Test // select * from Employee  e where e.name=? and e.dept=?
    public void findEmployeesByNameAndDept() {
        System.out.println("-- finding employees with name Diana and dept IT --");
        Employee employee = new Employee();
        employee.setName("Diana");
        employee.setDept("IT");
        System.out.println("Example entity: "+employee);
        Example<Employee> employeeExample = Example.of(employee, ExampleMatcher.matchingAll());
        Iterable<Employee> employees = exampleRepo.findAll(employeeExample);
        for (Employee e : employees) {
            System.out.println(e);
        }
    }
    @Test // select * from Person person0_ where person0_.name=?
    public void findPersonByNameIgnoringIdPath() {
        System.out.println("-- finding person by name Tara ignoring id=0 --");
        Person person = new Person();
        person.setName("Tara");
        System.out.println("Example entity: "+person);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id");
        Example<Person> personExample = Example.of(person, exampleMatcher);
        personRepo.findAll(personExample).forEach(System.out::println);
    }

    // 先将字段全部转小写，对应大写为 upper()
    @Test // select * from Employee employee0_ where lower(employee0_.name)=? and lower(employee0_.dept)=?
    public void findEmployeeByNameAndDeptCaseInsensitive() {
        System.out.println("-- finding employees with name tim and dept qa ignoring cases --");
        Employee employee = new Employee();
        employee.setName("tim");
        employee.setDept("qa");
        System.out.println("Example entity: "+employee);
        Example<Employee> employeeExample = Example.of(employee, ExampleMatcher.matchingAll().withIgnoreCase());
        Iterable<Employee> employees = exampleRepo.findAll(employeeExample);
        for (Employee e : employees) {
            System.out.println(e);
        }
    }
    @Test // select * from Employee employee0_ where lower(employee0_.name)=? and employee0_.dept=? //
    // Employee(id=10, name=Tim, dept=QA)查到这个，好像不对的，刚查到数据库默认支持大小写
    public void findEmployeeByNameAndDeptIgnoringPathForCase() {
        System.out.println("-- finding employees with name tim (ignoring case) and dept QA (not ignoring case) --");
        Employee employee = new Employee();
        employee.setName("TIM");
        employee.setDept("qA");
        System.out.println("Example entity: "+employee);
        Example<Employee> employeeExample = Example.of(employee, ExampleMatcher.matchingAll().withIgnoreCase("name"));
        Iterable<Employee> employees = exampleRepo.findAll(employeeExample);
        for (Employee e : employees) {
            System.out.println(e);
        }
    }
    @Test // select * from Employee employee0_ where employee0_.name like %?  endwith
    public void findEmployeeByNameEnding() {
        System.out.println("-- finding employees with name ending k --");
        Employee employee = new Employee();
        employee.setName("k");
        System.out.println("Example entity: "+employee);
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withStringMatcher(ExampleMatcher.StringMatcher.ENDING);
        Example<Employee> employeeExample = Example.of(employee, matcher);
        Iterable<Employee> employees = exampleRepo.findAll(employeeExample);
        for (Employee e : employees) {
            System.out.println(e);
        }
    }
    @Test
    // select * from Employee employee0_ where employee0_.dept=? and (employee0_.name like ?)
    public void findEmployeeByNameEndingAndByDept() {
        System.out.println("-- finding employees with name ending k and dept IT --");
        Employee employee = new Employee();
        employee.setName("k");
        employee.setDept("IT");
        System.out.println("Example entity: "+employee);
        ExampleMatcher name =
                ExampleMatcher.matchingAll()
                        .withMatcher("name",
                                //can be replaced with lambda
                                new ExampleMatcher.MatcherConfigurer<ExampleMatcher.GenericPropertyMatcher>() {
                                    @Override
                                    public void configureMatcher(ExampleMatcher.GenericPropertyMatcher matcher) {
                                        matcher.endsWith();
                                    }
                                });
        Example<Employee> employeeExample = Example.of(employee, name);
        Iterable<Employee> employees = exampleRepo.findAll(employeeExample);
        for (Employee e : employees) {
            System.out.println(e);
        }
    }
    @Test
    // Unsupported StringMatcher REGEX,可能版本的原因，会报错
    public void findEmployeeByNameUsingRegex() {
        System.out.println(" -- getting all Employees with name regex D.*a.*a --");
        Employee employee = new Employee();
        employee.setName("D.*a.*a");
        System.out.println("Example entity: "+employee);
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withStringMatcher(ExampleMatcher.StringMatcher.REGEX);
        Example<Employee> employeeExample = Example.of(employee, matcher);
        Iterable<Employee> employees = exampleRepo.findAll(employeeExample);
        for (Employee e : employees) {
            System.out.println(e);
        }
    }
    @Test
    // select * from Employee employee0_ where employee0_.name=? and employee0_.dept=?
    public void findEmployeeByTransformingName() {
        System.out.println(" -- getting all Employees with dept and transforming name during execution time  --");
        Employee employee = new Employee();
        employee.setName("Tim");
        employee.setDept("qa");
        System.out.println("Example entity: "+employee);
        ExampleMatcher matcher = ExampleMatcher.matchingAll().

                withTransformer("dept",
                        //can be replaced with lambda
                        new ExampleMatcher.PropertyValueTransformer() {
                            @Override
                            public Optional<Object> apply(Optional<Object> o) {
                                if (o.isPresent()) {
                                    return Optional.of(((String) o.get()).toUpperCase());
                                }
                                return o;
                            }
                        });
        Example<Employee> employeeExample = Example.of(employee, matcher);
        Iterable<Employee> employees = exampleRepo.findAll(employeeExample);
        for (Employee e : employees) {
            System.out.println(e);
        }
    }
    @Test
    // select * from Person person0_ where person0_.name is null
    public void findPersonWithNullName() {
        System.out.println("-- finding person with null name --");
        Person person = new Person();
        person.setName(null);
        System.out.println("Example entity: "+person);
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
                .withIgnorePaths("id")
                .withIncludeNullValues();
        Example<Person> personExample = Example.of(person, exampleMatcher);
        personRepo.findAll(personExample).forEach(System.out::println);
    }

    @Test
   // select * from Employee employee0_ where (employee0_.dept like ?) and (lower(employee0_.name) like ?)
    public void findEmployeeByNameIgnoringCaseAndContains() {
        System.out.println("-- finding employees with ignored case name contains 'AC' --");
        Employee employee = new Employee();
        employee.setName("AC");
        employee.setDept("IT");
        System.out.println("Example entity: "+employee);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase("name")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Employee> employeeExample = Example.of(employee, matcher);
        Iterable<Employee> employees = exampleRepo.findAll(employeeExample);
        for (Employee e : employees) {
            System.out.println(e);
        }
    }
    @Test
    public void findAllEmployees() {
        System.out.println(" -- getting all Employees --");
        Iterable<Employee> iterable = exampleRepo.findAll();
        for (Employee employee : iterable) {
            System.out.println(employee);
        }
    }
    @Test
    public void findAllPersons() {
        System.out.println(" -- getting all Persons --");
        Iterable<Person> iterable = personRepo.findAll();
        for (Person person : iterable) {
            System.out.println(person);
        }
    }


}
