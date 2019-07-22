package mb_test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("mb_test.dao")
public class MbRun {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MbRun.class);
    }


}
