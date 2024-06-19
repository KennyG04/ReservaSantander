package BD_Facturas;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class conexionBD implements conectMongo {
      protected MongoClient mongoClient;
    protected MongoDatabase database;
//Open/Close: está clase está abierta para ser llamada, pero cerrada para su modificación
      //  @Override
        @Override
        public void openMongo() {
	mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("Reservas_Santander");
}

       // @Override
        @Override
        public MongoDatabase getDatabase() {
    return database;
}
}
