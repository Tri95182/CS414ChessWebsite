package com.tco.server;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.tco.game.User;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;


public class Database {
    private static final ConnectionString connectionString = new ConnectionString("mongodb+srv://admin:ynlkzlZWx6c4f6XV@t09.q23w1.mongodb.net/t09?retryWrites=true&w=majority");
    private static final MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
    private static final MongoClient mongoClient = MongoClients.create(settings);
    private static final MongoDatabase database = mongoClient.getDatabase("ChessGame");


    public static void insert(Object object, String collection, String primaryKey) {
        MongoCollection<Document> doc = database.getCollection(collection);
        doc.insertOne(mongoClient.startSession(), objToDoc(object));
    }

    private static Document objToDoc(Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return Document.parse(json);
    }

    private static Object DocToObj(Document doc, Class<Object> t) {
        Gson gson = new Gson();
        return gson.fromJson(doc.toJson(), t);
    }

    // Search the collection for a key with a given value.
    public static boolean keyExists(String key, String value, String collection) {
        MongoCollection<Document> doc = database.getCollection(collection);
        return doc.countDocuments(Filters.eq(key, value)) != 0;
    }

    // Search the collection for a key with a given object.
    public static boolean objExists(Object target, String collection) {
        MongoCollection<Document> doc = database.getCollection(collection);
        return doc.countDocuments(objToDoc(target)) != 0;
    }

    public static Object findObj(String key, String value, String collection) {
        MongoCollection<Document> doc = database.getCollection(collection);
        FindIterable<Document> found = doc.find(Filters.eq(key, value));
        if (found.first() == null) {
            return null;
        }
        return DocToObj(found.first(), Object.class);
    }

    public static Document findDoc(String key, String value, String collection) {
        MongoCollection<Document> doc = database.getCollection(collection);
        FindIterable<Document> found = doc.find(Filters.eq(key, value));
        return found.first();
    }

    // Method to check if blackField exists
    public static boolean checkBlackField(String key, String value, String collection) {
        Document doc = findDoc(key, value, collection);
        return (doc.containsKey("black"));
    }

    // Method to update black field object
    public static void updateGame(String key, String value, String collection, User black) {
        MongoCollection<Document> existingCollection = database.getCollection(collection);
        Bson filter = eq(key, value);
        BasicDBObject updateDoc = new BasicDBObject();
        updateDoc.put("$set", new BasicDBObject("black.password", black.getPassword()).append("black._id", black.getUserEMail()));
        UpdateOptions options = new UpdateOptions().upsert(true);
        existingCollection.updateMany(filter, updateDoc, options).wasAcknowledged();
    }

    public static ArrayList<String> getGamesWithUser(User user, String collectionName) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Gson gson = new Gson();
        Bson toFind = Document.parse(gson.toJson(user));

        return collection.find(or(eq("white", toFind), eq("black", toFind))).map(Document::toJson).into(new ArrayList<>());
    }

}
