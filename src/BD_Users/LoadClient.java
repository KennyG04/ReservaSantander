package BD_Users;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import javax.swing.JLabel;
import org.bson.Document;
import javax.swing.JTextField;

public class LoadClient {

    public void cargarClienteRegistrado(ConectionUsers conexionU, String cedula, JTextField nombreField, JTextField cedulaField, JTextField telefonoField, JTextField correoField, JLabel mensajeError) {
        // Asegúrate de que la conexión esté abierta y la colección esté configurada
        if (conexionU.collection == null) {
            mensajeError.setText("No se pudo conectar a la base de datos.");
            mensajeError.setForeground(new java.awt.Color(255, 0, 0)); // Color rojo para error
            return;
        }

        // Buscar el cliente por cédula
        FindIterable<Document> elementoBuscado = conexionU.collection.find(Filters.eq("cedula", cedula));

        // Verificar si el cliente fue encontrado
        Document cliente = elementoBuscado.first();
        if (cliente != null) {
            // Establecer los datos del cliente en los campos de texto
            nombreField.setText(cliente.getString("nombre"));  // Asegúrate de que los nombres de los campos sean correctos
            cedulaField.setText(cliente.getString("cedula"));
            telefonoField.setText(cliente.getString("telefono"));
            correoField.setText(cliente.getString("correoElectronico"));

            // Limpiar mensaje de error
            mensajeError.setText("");
        } else {
            // Mostrar mensaje de error si no se encuentra el cliente
            mensajeError.setText("Cliente no encontrado.");
            mensajeError.setForeground(new java.awt.Color(255, 0, 0)); // Color rojo para error

            // Limpiar campos de texto
            nombreField.setText("");
            cedulaField.setText("");
            telefonoField.setText("");
            correoField.setText("");
        }
    }
}
