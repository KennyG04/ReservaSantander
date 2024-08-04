package BD_Rooms;

import Actors.Habitacion;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import java.time.LocalDate;

public class Create {
    public void CreateDefaultRoomsCode(Conection conexion) {
        if (conexion.collection.countDocuments() == 0) {
            // Si no hay documentos, crear las habitaciones
            List<Document> rooms = new ArrayList<>();

            // Crear 10 habitaciones por defecto de cada tipo
            for (int i = 0; i < 10; i++) {
                // Crear habitación personal
                Habitacion roomPersonal = new Habitacion(
                    "Personal",
                    "Libre",
                    30,
                    "Limpio",
                    i == 9 ? "P010" : "P00" + i
                );
                roomPersonal.setFechaEntrada(LocalDate.now());
                roomPersonal.setFechaSalida(LocalDate.now().plusDays(1));

                // Crear documento para la habitación y añadir a la lista
                Document docPersonal = new Document("Tipo", roomPersonal.getTipo())
                        .append("Disponibilidad", roomPersonal.getDisponibilidad())
                        .append("Precio", roomPersonal.getPrecio())
                        .append("Limpieza", roomPersonal.getLimpieza())
                        .append("Codigo", roomPersonal.getCodigo())
                        .append("FechaEntrada", roomPersonal.getFechaEntrada().toString())
                        .append("FechaSalida", roomPersonal.getFechaSalida().toString())
                        .append("DiasRentados", roomPersonal.getDiasRentados());
                rooms.add(docPersonal);
            }

            for (int i = 0; i < 10; i++) {
                // Crear habitación familiar
                Habitacion roomFamiliar = new Habitacion(
                    "Familiar",
                    "Libre",
                    80,
                    "Limpio",
                    i == 9 ? "F010" : "F00" + i
                );
                roomFamiliar.setFechaEntrada(LocalDate.now());
                roomFamiliar.setFechaSalida(LocalDate.now().plusDays(1));

                // Crear documento para la habitación y añadir a la lista
                Document docFamiliar = new Document("Tipo", roomFamiliar.getTipo())
                        .append("Disponibilidad", roomFamiliar.getDisponibilidad())
                        .append("Precio", roomFamiliar.getPrecio())
                        .append("Limpieza", roomFamiliar.getLimpieza())
                        .append("Codigo", roomFamiliar.getCodigo())
                        .append("FechaEntrada", roomFamiliar.getFechaEntrada().toString())
                        .append("FechaSalida", roomFamiliar.getFechaSalida().toString())
                        .append("DiasRentados", roomFamiliar.getDiasRentados());
                rooms.add(docFamiliar);
            }

            for (int i = 0; i < 10; i++) {
                // Crear habitación matrimonial
                Habitacion roomMatrimonial = new Habitacion(
                    "Matrimonial",
                    "Libre",
                    60,
                    "Limpio",
                    i == 9 ? "M010" : "M00" + i
                );
                roomMatrimonial.setFechaEntrada(LocalDate.now());
                roomMatrimonial.setFechaSalida(LocalDate.now().plusDays(1));

                // Crear documento para la habitación y añadir a la lista
                Document docMatrimonial = new Document("Tipo", roomMatrimonial.getTipo())
                        .append("Disponibilidad", roomMatrimonial.getDisponibilidad())
                        .append("Precio", roomMatrimonial.getPrecio())
                        .append("Limpieza", roomMatrimonial.getLimpieza())
                        .append("Codigo", roomMatrimonial.getCodigo())
                        .append("FechaEntrada", roomMatrimonial.getFechaEntrada().toString())
                        .append("FechaSalida", roomMatrimonial.getFechaSalida().toString())
                        .append("DiasRentados", roomMatrimonial.getDiasRentados());
                rooms.add(docMatrimonial);
            }

            // Insertar todos los documentos en la colección
            conexion.collection.insertMany(rooms);
        }
    }
}
