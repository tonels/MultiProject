package tonels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EntityScan("jpaCommon.model")
public class SpecificationApp {
    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(SpecificationApp.class);
//        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
    }

}
