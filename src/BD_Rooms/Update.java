package BD_Rooms;

import Interfaces_Recepcionista.Principal_Hotel;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

import java.time.LocalDate;
import javax.swing.JOptionPane;

public class Update {
    public void actualizarHabitacion(Conection conexion, String codigoHabitacion, LocalDate nuevaFechaEntrada, LocalDate nuevaFechaSalida,String nuevoEstado) {
        // Crear un documento para buscar la habitación por código
        Document habitacionDestino = new Document("Codigo", codigoHabitacion);

        // Buscar la habitación en la colección
        FindIterable<Document> busqueda = conexion.collection.find(habitacionDestino);

        // Verificar si la habitación existe
        if (busqueda.iterator().hasNext()) {
            // Calcular los días rentados
            int diasRentados = (int) java.time.temporal.ChronoUnit.DAYS.between(nuevaFechaEntrada, nuevaFechaSalida);

            // Actualizar la habitación seleccionada
            UpdateResult resultado = conexion.collection.updateOne(
                habitacionDestino, 
                Updates.combine(
                    Updates.set("Disponibilidad", nuevoEstado),
                    Updates.set("FechaEntrada", nuevaFechaEntrada.toString()),
                    Updates.set("FechaSalida", nuevaFechaSalida.toString()),
                    Updates.set("DiasRentados", diasRentados)
                )
            );

            // Verificar el resultado de la actualización
            if (resultado.getMatchedCount() > 0) {
                JOptionPane.showMessageDialog(null,"¡Habitacion rentada exitosamente!","Alquiler exitoso",JOptionPane.INFORMATION_MESSAGE);
            } else {                
                JOptionPane.showMessageDialog(null,"Ocurrio un error al rentar la habitacion, vuelve a intentarlo","Alquiler fallido",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("La habitación con código " + codigoHabitacion + " no existe.");
        }
    }
    public void restablecerHabitacion(Conection conexion, String codigoHabitacion) {
        // Crear un documento para buscar la habitación por código
        Document habitacionDestino = new Document("Codigo", codigoHabitacion);

        // Restablecer los valores predeterminados
        UpdateResult resultado = conexion.collection.updateOne(
            habitacionDestino,
            Updates.combine(
                Updates.set("Disponibilidad", "Libre"),
                Updates.set("Limpieza", "Disponible para limpiar"),
                Updates.set("FechaEntrada", null),
                Updates.set("FechaSalida", null),
                Updates.set("DiasRentados", 0)
            )
        );

        // Verificar el resultado de la actualización
        /*if (resultado.getMatchedCount() > 0) {
            //JOptionPane.showMessageDialog(null, "¡Habitacion restablecida exitosamente!", "Restablecimiento exitoso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al restablecer la habitación, vuelve a intentarlo", "Restablecimiento fallido", JOptionPane.ERROR_MESSAGE);
        }*/
    }
}
