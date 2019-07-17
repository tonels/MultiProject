package tonels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("jpaCommon.model")
public class SqlAppRun {

    public static void main(String[] args) {
        SpringApplication.run(SqlAppRun.class);
    }
}
