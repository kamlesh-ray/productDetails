package com.product.productDetails.config;

import com.mongodb.ConnectionString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ConditionalOnProperty(prefix = "app.database", name = "type", havingValue = "NOSQL")
@EnableMongoRepositories(basePackages = "com", mongoTemplateRef = "mongoDbTemplate")
@Slf4j
public class NonRelationalDbConfig {


    @Value("${app.database.mongo.host}")
    private String hostName;

    @Value("${app.database.mongo.port}")
    private Integer port;

    @Value("${app.database.mongo.database}")
    private String databaseName;

    public NonRelationalDbConfig() {
        log.info(">>>>>>>>>>>>>>>>>>>db type activated as : NoSQL<<<<<<<<<<<<<<<<<<<");
    }

    @Bean
    public MongoTemplate mongoDbTemplate() throws Exception {
        String mongo_url = String.format("mongodb://%s:%d/%s", hostName, port, databaseName);
        log.info("MongoDb mongo_url : {} ", mongo_url);
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(new ConnectionString(mongo_url)));
    }
}
