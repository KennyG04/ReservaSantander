package BD_Facturas;

import com.mongodb.client.MongoDatabase;

/**
 *
 * @author HP
 */
public interface conectMongo {
    void openMongo();
        MongoDatabase getDatabase(); 
}
