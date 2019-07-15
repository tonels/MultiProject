package tonels.qbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages ={ "tonel.model","tonels.qbe.model"})
//@SpringBootApplication(scanBasePackages = {"org.ashu.java.*"})
//@EnableJpaRepositories(basePackages ={ "org.ashu.java.*"})
//@EntityScan(basePackages ={ "org.ashu.java.*"})
public class QbeApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(QbeApp.class);
    }
}
