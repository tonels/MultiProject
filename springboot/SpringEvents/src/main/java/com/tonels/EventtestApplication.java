package com.tonels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Slf4j
public class EventtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventtestApplication.class, args);
    }

}
/*

@Slf4j
@Component
class UserSrvc implements CommandLineRunner {
    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 200; i++) {
            log.info("New User saved " + userService.addUser(new User("Bob-" + i)));
        }
    }
}

*/
