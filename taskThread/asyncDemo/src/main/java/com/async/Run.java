package com.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Run /*implements CommandLineRunner*/ {
//    private static final Logger logger = LoggerFactory.getLogger(Run.class);
//    @Autowired
//    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        // Start the clock
//        long start = System.currentTimeMillis();
//
//        CompletableFuture<User> page1 = userService.findUser("tonels");
//
//        // 等待
//        CompletableFuture.allOf(page1).join();
//        // 打印结果
//        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
//        logger.info("--> " + page1.get());
//    }
}
