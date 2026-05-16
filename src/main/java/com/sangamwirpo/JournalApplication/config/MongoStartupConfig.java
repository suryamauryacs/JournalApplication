package com.sangamwirpo.JournalApplication.config;

import com.mongodb.client.MongoClient;
import com.sangamwirpo.JournalApplication.Entity.JournalEntry;
import com.mongodb.client.MongoDatabase;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoStartupConfig {

    @Autowired
    private MongoTemplate mongoTemplate;  // ← inject MongoTemplate instead

    @PostConstruct
    public void checkConnection() {
        String dbName = mongoTemplate.getDb().getName(); // ← reads from application.properties
//        log.info("MongoDB connected successfully to database: {}", dbName);
    }

    private static final Logger logger = LoggerFactory.getLogger(MongoStartupConfig.class);

    @Bean
    public ApplicationRunner initializeMongo(MongoTemplate mongoTemplate) {
        return args -> {
            MongoDatabase database = mongoTemplate.getDb();
            database.runCommand(new org.bson.Document("ping", 1));

            if (!mongoTemplate.collectionExists(JournalEntry.class)) {
                mongoTemplate.createCollection(JournalEntry.class);
                logger.info("Created MongoDB collection: {}", mongoTemplate.getCollectionName(JournalEntry.class));
            }

            logger.info("MongoDB connected successfully to database: {}", database.getName());
        };
    }
}
