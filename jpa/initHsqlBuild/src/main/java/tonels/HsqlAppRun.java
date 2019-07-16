package tonels;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("tonel.model")
public class HsqlAppRun {

    public static void main(String[] args) {
        SpringApplication.run(HsqlAppRun.class);
    }
}
