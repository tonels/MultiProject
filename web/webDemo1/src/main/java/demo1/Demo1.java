package demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Demo1 {
    public static void main(String[] args) {
        SpringApplication.run(Demo1.class);
    }

    // TODO: 2019/7/30 ssss
    @Bean
    WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static_extra/**")
                        .addResourceLocations("classpath:/static_extra/");
                super.addResourceHandlers(registry);
            }
        };
    }



}
