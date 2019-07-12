package demo3.repo;

import demo3.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepo extends MongoRepository<Article,String> {


    Optional<Article> findByTitle(String title);

//    @Query()
    List<Article> findByAuthor(String author);

    @Query(value ="{'author': ?0}")
    List<Article> find1(String author);

}
