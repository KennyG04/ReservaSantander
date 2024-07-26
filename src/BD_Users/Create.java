package BD_Users;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class Create {
    public void CreateDefaultRoomsCode(Conection conexion){
        if(conexion.collection.countDocuments()==0){
            //si no hay documentos crear las habitaciones
            List<Document> rooms = new ArrayList<>();

            // Crear 10 habitaciones por defecto
            for (int i = 0; i < 10; i++) {
                // Crear documento para cada habitación
                Document roomPersonal = new Document("Tipo", "Personal")
                        .append("Disponibilidad", "Libre")
                        .append("Precio", 30)
                        .append("Limpieza", "Limpio")
                        .append("Codigo", i == 9 ? "P010" : "P00" + i); // Para manejar correctamente el código P010

                // Añadir documento a la lista
                rooms.add(roomPersonal);
            }
            for (int i = 0; i < 10; i++) {
                // Crear documento para cada habitación
                Document roomFamiliar = new Document("Tipo", "Familiar")
                        .append("Disponibilidad", "Libre")
                        .append("Precio", 80)
                        .append("Limpieza", "Limpio")
                        .append("Codigo", i == 9 ? "F010" : "F00" + i); // Para manejar correctamente el código P010

                // Añadir documento a la lista
                rooms.add(roomFamiliar);
            }
            for (int i = 0; i < 10; i++) {
                // Crear documento para cada habitación
                Document roomFamiliar = new Document("Tipo", "Matrimonial")
                        .append("Disponibilidad", "Libre")
                        .append("Precio", 60)
                        .append("Limpieza", "Limpio")
                        .append("Codigo", i == 9 ? "M010" : "M00" + i); // Para manejar correctamente el código P010

                // Añadir documento a la lista
                rooms.add(roomFamiliar);
            }
            

            // Insertar todos los documentos en la colección
            conexion.collection.insertMany(rooms);
        }
    }
}
