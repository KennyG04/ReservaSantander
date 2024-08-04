package BD_Rooms;

import Actors.Habitacion;
import com.mongodb.client.FindIterable;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LoadData {

    public List<Habitacion> loadData(Conection conexion) {

        FindIterable<Document> documents = conexion.collection.find();
        List<Habitacion> habitaciones = new ArrayList<>();

        for (Document document : documents) {
            String tipo = document.getString("Tipo");
            String disponibilidad = document.getString("Disponibilidad");
            int precio = document.getInteger("Precio");
            String limpieza = document.getString("Limpieza");
            String codigo = document.getString("Codigo");

            // Crear una nueva instancia de Habitacion
            Habitacion habitacion = new Habitacion(tipo, disponibilidad, precio, limpieza, codigo);

            // Asignar fechas si están disponibles
            String fechaEntradaStr = document.getString("FechaEntrada");
            String fechaSalidaStr = document.getString("FechaSalida");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Ajusta el formato según tus datos

            if (fechaEntradaStr != null && !fechaEntradaStr.isEmpty()) {
                LocalDate fechaEntrada = LocalDate.parse(fechaEntradaStr, formatter);
                habitacion.setFechaEntrada(fechaEntrada);
            }

            if (fechaSalidaStr != null && !fechaSalidaStr.isEmpty()) {
                LocalDate fechaSalida = LocalDate.parse(fechaSalidaStr, formatter);
                habitacion.setFechaSalida(fechaSalida);
            }

            // Agregar la habitación a la lista
            habitaciones.add(habitacion);
        }

        return habitaciones;
    }
}
