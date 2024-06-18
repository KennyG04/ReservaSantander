package Clases;

import BD_Facturas.conexionBD;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

public class gestionRecepcionistas {
  conexionBD iM = new conexionBD();
  
  public void seeUssers(JTable seeUsser){
  iM.openMongo();
  MongoDatabase database = iM.getDatabase();
  MongoCollection<Document> collection = database.getCollection("usuarios");
  DefaultTableModel tabla1 = new DefaultTableModel();
  FindIterable<Document> documents = collection.find();
  tabla1.setColumnIdentifiers(new String[] {"Código usuario","Usuario", "Cargo", "Cédula"});
  for(Document doc:documents){
      tabla1.addRow(new Object[]{doc.get("CódigoUsuario:"), doc.get("Usuario:"),doc.get("Cargo:"), doc.get("Cedula")});
  }seeUsser.setModel(tabla1);
 // JOptionPane.showMessageDialog(null, "Tabla actualizada", "Exito", JOptionPane.INFORMATION_MESSAGE);
  
}
  public void addUsser(String newCode, String newUsser, String newId, String newId1){
       if (newCode.isEmpty() || newUsser.isEmpty() ||newId.isEmpty() || newId1.isEmpty() ) {
           JOptionPane.showMessageDialog(null, "Debes llenar todos los campos","Campos vacios",JOptionPane.WARNING_MESSAGE);     
          return;}
    if (!newCode.matches("[A-Z]\\d{4}")) {
            JOptionPane.showMessageDialog(null, "Ingrese un código válido (una letra mayuscula seguida de cuatro números)", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    try{
        if (!newId.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(null, "Ingrese una cedula válida", "Error", JOptionPane.ERROR_MESSAGE);
            return;
    }} catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Ingrese una cedula válida", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (!newId.equals(newId1)){
        JOptionPane.showMessageDialog(null, "Las contraseñas ingresadas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
     if (!newUsser.matches("[A-Za-z]{1,7}")) {
            JOptionPane.showMessageDialog(null, "El usuario debe de contener maximo 7 letras", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
     //Facade: Un método es llamado dentro de otro (De la misma clase)para posteriormente llamar esa clase en la acción necesaria 
     //Luego de las validaciones se llama al método en donde se guardarán los datos
     saveMongoWorker( newCode, newUsser,  newId, newId1);
    }
  public void saveMongoWorker(String newCode, String newUsser, String newId, String newId1){
        iM.openMongo();
        MongoDatabase database = iM.getDatabase();
        MongoCollection<Document> collection = database.getCollection("usuarios");
        Document filter = new Document("Usuario:", newUsser);
        Document query = new Document("CódigoUsuario:", newCode);
        FindIterable<Document> result = collection.find(filter);
        FindIterable<Document> results = collection.find(query);
        if (result.iterator().hasNext()) {
            JOptionPane.showMessageDialog(null, "Los datos ingresados ya existen dentro del registro, porfavor, ingrese datos distintos", "Error", JOptionPane.ERROR_MESSAGE);                    
            return;
        } 
        if (results.iterator().hasNext()) {
            JOptionPane.showMessageDialog(null, "Los datos ingresados ya existen dentro del registro, porfavor, ingrese datos distintos", "Error", JOptionPane.ERROR_MESSAGE);                   
            return;
        } else {    
         Document documents= new Document().append("CódigoUsuario:", newCode).append("Usuario:", newUsser).append("Cargo:", "Recepcionista").append("Cedula", newId1);
         collection.insertOne(documents);
         JOptionPane.showMessageDialog(null, "Datos guardados correctamente en la base de datos", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }        
    }
  
}
