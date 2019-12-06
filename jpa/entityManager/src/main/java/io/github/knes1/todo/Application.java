package io.github.knes1.todo;

import io.github.knes1.todo.util.HibernateStatisticsInterceptor;
import io.github.knes1.todo.util.RequestStatisticsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public HibernateStatisticsInterceptor hibernateInterceptor() {
        return new HibernateStatisticsInterceptor();
    }

    @Configuration
    public static class WebApplicationConfig extends WebMvcConfigurerAdapter {

        @Autowired
        RequestStatisticsInterceptor requestStatisticsInterceptor;

        @Bean
        public RequestStatisticsInterceptor requestStatisticsInterceptor() {
            return new RequestStatisticsInterceptor();
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(requestStatisticsInterceptor).addPathPatterns("/**");
        }
    }

}
