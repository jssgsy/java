package com.univ.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

/**
 * @author univ
 * date 2023/11/21
 */
public class MongoDBTest {

    public static void main(String[] args) {
        // 连接信息
        String uri = "mongodb://127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000&appName=mongosh+2.0.2";
        // 连接服务端，使用的是try-with-resources
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            // 指定database
            MongoDatabase database = mongoClient.getDatabase("posts");
            // 指定collections
            MongoCollection<Document> collection = database.getCollection("posts");
            // 查询
            Document doc = collection.find(eq("name", "univ new")).first();
            System.out.println(doc.toJson());
        }
    }
}
