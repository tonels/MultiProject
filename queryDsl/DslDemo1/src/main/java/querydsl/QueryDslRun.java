package querydsl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("querydsl.model")
public class QueryDslRun {

    public static void main(String[] args) {
        SpringApplication.run(QueryDslRun.class);
    }
}
