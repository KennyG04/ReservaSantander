package BD_Users;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Conection {
    MongoClient mongoClient;
    MongoDatabase database;
    public MongoCollection<Document> collection;
    public void conexion(String coleccion){
        mongoClient = MongoClients.create("mongodb://localhost:27017/");
        database = mongoClient.getDatabase("Reservas_Santander");
        collection = database.getCollection(coleccion);
    }
}
