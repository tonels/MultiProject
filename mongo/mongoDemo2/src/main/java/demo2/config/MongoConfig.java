package demo2.config;

import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

import java.util.Arrays;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

//	public @Bean
//	MongoDbFactory mongoDbFactory() throws Exception {
//
//        MongoCredential credential = MongoCredential.createCredential("test", "test","test".toCharArray());
//        ServerAddress serverAddress = new ServerAddress("192.168.1.3", 27017);
//
//        MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));
//
//        SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(mongoClient, "tonels_test");
//		return simpleMongoDbFactory;
//
//	}
//public @Bean MongoDbFactory mongoDbFactory() throws Exception {
//
//        String host = "192.168.1.3";
//        int port =27017;
//        String username = "test";
//        String password = "test";
//        String database = "tonels_test";
//
//        // Set credentials
//        MongoCredential credential = MongoCredential.createCredential(username, database, password.toCharArray());
//        ServerAddress serverAddress = new ServerAddress(host, port);
//
//        // Mongo Client
//        MongoClient mongoClient = new MongoClient(serverAddress,Arrays.asList(credential));
//
//        // Mongo DB Factory
//        return new SimpleMongoDbFactory(mongoClient,database);
//
//}
//
//
//    public @Bean
//	MongoTemplate mongoTemplate() throws Exception {
//
//		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
//
//		// show error, should off on production to speed up performance
//		mongoTemplate.setWriteConcern(WriteConcern.SAFE);
//
//		return mongoTemplate;
//
//	}


    @Override
    public MongoClient mongoClient() {
        return new MongoClient();
    }

    @Override
    protected String getDatabaseName() {
        return "tonels_test";
    }


    public @Bean MongoDbFactory mongoDbFactory()  {

        String host = "192.168.1.3";
        int port =27017;
        String username = "test";
        String password = "test";

        // Set credentials
        MongoCredential credential = MongoCredential.createCredential(username, getDatabaseName(), password.toCharArray());
        ServerAddress serverAddress = new ServerAddress(host, port);

        // Mongo Client
        MongoClient mongoClient = new MongoClient(serverAddress,Arrays.asList(credential));

        // Mongo DB Factory
        return new SimpleMongoDbFactory(mongoClient,getDatabaseName());
    }

    public @Bean MongoTemplate mongoTemplate() throws Exception {

		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());

		// show error, should off on production to speed up performance
		mongoTemplate.setWriteConcern(WriteConcern.SAFE);

		return mongoTemplate;

	}




}