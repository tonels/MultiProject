package io.github.knes1.todo.repositories;

import io.github.knes1.todo.model.Todo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;
import java.util.stream.Stream;

import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

/**
 * @author knesek
 * Created on: 07/07/15
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

	/**
	 * @QueryHints 这里是hibernate中的SQL，修饰，包括返回映射，缓存处理，超时等
	 * @return
	 */
	@QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE))
	@Query(value = "select t from Todo t")
	Stream<Todo> streamAll();

	/**
	 * We need this method variant instead of one that returns Page because methods that return Page
	 * always execute count query, which is slow when using pagination in batch to export large tables.
	 */
	@Transactional(readOnly = true)
    Slice<Todo> findAllBy(Pageable page);

}
