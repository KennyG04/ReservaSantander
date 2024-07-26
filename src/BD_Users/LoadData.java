package BD_Users;

import Actors.Habitacion;
import com.mongodb.client.FindIterable;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class LoadData {
    public List<Habitacion> loadData(Conection conexion){
        
        FindIterable<Document> documents = conexion.collection.find();
        List<Habitacion> habitaciones = new ArrayList<>();
        for(Document document: documents){
            String tipo = document.getString("Tipo");
            String disponibilidad = document.getString("Disponibilidad");
            int precio = document.getInteger("Precio");
            String limpieza =document.getString("Limpieza");
            String codigo =document.getString("Codigo");
            Habitacion habitacion = new Habitacion(tipo,disponibilidad,precio,limpieza,codigo);
            habitaciones.add(habitacion);
        }
        return habitaciones;
    }
}
