package demo3.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"demo3.config", "demo3.seq", "demo3.hosting"})
@Import({ MongoConfig.class })
public class AppConfig {

}