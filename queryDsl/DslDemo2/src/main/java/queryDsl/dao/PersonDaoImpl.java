package queryDsl.dao;

import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import queryDsl.entity.Person;
import queryDsl.entity.QEntity.QPerson;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Person save(Person person) {
        em.persist(person);
        return person;
    }

    @Override
    public List<Person> findPersonsByFirstnameQueryDSL(String firstname) {
        JPAQuery<Person> query = new JPAQuery<>(em);

        QPerson person = QPerson.person;
        List<Person> list = query.from(person).where(person.firstname.eq(firstname)).fetch();
        return list;
    }

    @Override
    public List<Person> findPersonsByFirstnameAndSurnameQueryDSL(String firstname, String surname) {

        JPAQuery<Person> query = new JPAQuery<>(em);
        QPerson person = QPerson.person;
        List<Person> list = query.from(person).where(person.firstname.eq(firstname).and(person.surname.eq(surname))).fetch();

        // 打印sql
        // select .. from Person person0_ where person0_.firstname=? and person0_.surname=?
        return list;
    }

    @Override
    public List<Person> findPersonsByFirstnameIndescendingOrderQueryDSL(String firstname) {
        JPAQuery<Person> query = new JPAQuery<>(em);
        QPerson person = QPerson.person;

        // select .. from Person person0_ where person0_.firstname=? order by person0_.surname desc
        return query.from(person).where(person.firstname.eq(firstname)).orderBy(person.surname.desc()).fetch();
    }

    @Override
    public int findMaxAge() {
        JPAQuery<Person> query = new JPAQuery<>(em);
        QPerson person = QPerson.person;

        // 注意这个 fetchFirst() 打印的 SQL
        // select max(person0_.age) as col_0_0_ from Person person0_ limit ?

        // fetch() 打印的 SQL,返回的是一个数组 [62]
        // select max(person0_.age) as col_0_0_ from Person person0_
        List<Integer> fetch = query.from(person).select(person.age.max()).fetch();
        System.out.println(fetch);
        return fetch.get(0);

    }

    @Override
    public Map<String, Integer> findMaxAgeByName() {
        JPAQuery<Person> query = new JPAQuery<>(em);
        QPerson person = QPerson.person;

        Map<String, Integer> map = query.from(person).transform(GroupBy.groupBy(person.firstname).as(GroupBy.max(person.age)));

        // 为什么没有 GroupBy 分组？
        // select person0_.firstname as col_0_0_, person0_.age as col_1_0_ from Person person0_

        return map;
    }

    @Override
    public Map<String, String> findfirAndSurName() {
        JPAQuery<Person> query = new JPAQuery<>(em);
        QPerson person = QPerson.person;

        Map<String, String> map = query.from(person).transform(GroupBy.groupBy(person.firstname).as(GroupBy.max(person.surname)));

        // select p.firstname , p.surname from Person p
        return map;
    }

    @Override
    public Map<String, String> findfirAndSurName2() {
        JPAQuery<Person> query = new JPAQuery<>(em);
        QPerson person = QPerson.person;

        // 这个为什么返回 Null,数据库是有 4 条记录的！！
        // select p.firstname , p.surname from Person p where cast(p.age as signed)<? and p.id>?
        Map<String, String> map = query.from(person).where(person.age.longValue().lt(35).and(person.id.longValue().gt(4))).transform(GroupBy.groupBy(person.firstname).as(GroupBy.max(person.surname)));
        return map;

    }
}
