package BD_Users;

import com.mongodb.client.model.Filters;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import javax.swing.JLabel;

public class Regist {

    public void registrarCliente(ConectionUsers conexionU, String nombre, String cedula, String telefono, String correoElectronico, JLabel mensajeError) {
        // Obtener la colección de clientes
        MongoCollection<Document> collection = conexionU.collection;

        // Verificar si ya existe un cliente con la misma cédula
        Document clienteExistente = collection.find(Filters.eq("cedula", cedula)).first();

        if (clienteExistente != null) {
            // Mostrar mensaje de error si el cliente ya existe
            mensajeError.setText("Error: Cliente con esta cédula ya existe.");
            mensajeError.setForeground(new java.awt.Color(255, 0, 0)); // Color rojo para error
        } else {
            // Crear un documento para el nuevo cliente
            Document nuevoCliente = new Document("nombre", nombre)
                                        .append("cedula", cedula)
                                        .append("telefono", telefono)
                                        .append("correoElectronico", correoElectronico);

            // Insertar el nuevo cliente en la colección
            collection.insertOne(nuevoCliente);

            // Mostrar mensaje de éxito
            mensajeError.setText("Cliente registrado exitosamente.");
            mensajeError.setForeground(new java.awt.Color(0, 128, 0)); // Color verde para éxito
        }
    }
}
