package demo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@SpringBootApplication
public class MongoApp3 {
    public static void main(String[] args) {
        SpringApplication.run(MongoApp3.class);
    }

    //remove _class
//    @Bean
//    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory,
//                                       MongoMappingContext mongoMappingContext) {
//
//        MappingMongoConverter converter =
//                new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), mongoMappingContext);
//        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
//
//        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);
//
//        return mongoTemplate;
//
//    }



}
