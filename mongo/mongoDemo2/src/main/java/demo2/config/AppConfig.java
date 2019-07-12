package demo2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({ "demo2.config", "demo2.seq", "demo2.hosting" })
@Import({ MongoConfig.class })
public class AppConfig {

}