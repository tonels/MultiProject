package querydsl.tonels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
public class QueryDslRun extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(QueryDslRun.class);
    }

    /**
     * Called to run a fully configured {@link SpringApplication}.
     *
     * @param application the application to run
     * @return the {@link WebApplicationContext}
     */
    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
}
