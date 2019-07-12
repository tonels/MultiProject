package demo3.repo;

import demo3.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

// 这里先不引入QuerydslPredicateExecutor,Type-safe query
@Repository
public interface PersonRepo extends MongoRepository<Person,Long>/*, QuerydslPredicateExecutor<Person>*/ {

    List<Person> findByBirthdayAfter(LocalDate birthday);

    List<Person> findByLastname(String lastname);

    Page<Person> findByFirstname(String firstname, Pageable pageable);

    Person findFirstByLastname(String lastname);

    Stream<Person> findAllBy();
}
