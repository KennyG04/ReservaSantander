
package Clases;

import BD_Facturas.conexionBD;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javax.swing.JLabel;
import org.bson.Document;

/**
 *
 * @author HP
 */
public class disponibilidad {
     public void seedisp(String tipHa, JLabel hab1, JLabel hab11, JLabel hab12, JLabel hab10, JLabel hab101, JLabel hab102, JLabel hab2, JLabel hab22, JLabel hab23, JLabel hab3, JLabel hab32, JLabel hab33, JLabel hab4, JLabel hab42, JLabel hab43, JLabel hab5, JLabel hab52, JLabel hab53, JLabel hab6, JLabel hab62, JLabel hab63, JLabel hab7, JLabel hab72, JLabel hab73, JLabel hab8, JLabel hab82, JLabel hab83, JLabel hab9, JLabel hab92, JLabel hab93, JLabel cod1, JLabel cod2, JLabel cod3, JLabel cod4, JLabel cod5, JLabel cod6, JLabel cod7, JLabel cod8, JLabel cod9, JLabel cod10){
      if (tipHa.equals("Personal")) {
            tipHa = "H_personal";
        } else if (tipHa.equals("Familiar")) {
            tipHa = "H_familiar";
        } else {
            tipHa = "H_matrimonial";
        }
        
        conexionBD iM = new conexionBD();
        iM.openMongo();
        MongoDatabase database = iM.getDatabase();
        MongoCollection<Document> collection = database.getCollection(tipHa);
        // Recuperación de documentos de la colección
        FindIterable<Document> documents = collection.find();
        // Formateo de los datos del primer documento recuperado
        StringBuilder data = new StringBuilder();
        StringBuilder data11 = new StringBuilder();
        StringBuilder data12 = new StringBuilder();
        StringBuilder data13 = new StringBuilder();
        StringBuilder data2 = new StringBuilder();
        StringBuilder data22 = new StringBuilder();
        StringBuilder data23 = new StringBuilder();
        StringBuilder data24 = new StringBuilder();
        StringBuilder data3 = new StringBuilder();
        StringBuilder data32 = new StringBuilder();
        StringBuilder data33 = new StringBuilder();
        StringBuilder data34 = new StringBuilder();
        StringBuilder data4 = new StringBuilder();
        StringBuilder data42 = new StringBuilder();
        StringBuilder data43 = new StringBuilder();
        StringBuilder data44 = new StringBuilder();
        StringBuilder data5 = new StringBuilder();
        StringBuilder data52 = new StringBuilder();
        StringBuilder data53 = new StringBuilder();
        StringBuilder data54 = new StringBuilder();
        StringBuilder data6 = new StringBuilder();
        StringBuilder data62 = new StringBuilder();
        StringBuilder data63 = new StringBuilder();
        StringBuilder data64 = new StringBuilder();
        StringBuilder data7 = new StringBuilder();
        StringBuilder data72 = new StringBuilder();
        StringBuilder data73 = new StringBuilder();
        StringBuilder data74 = new StringBuilder();
        StringBuilder data8 = new StringBuilder();
        StringBuilder data82 = new StringBuilder();
        StringBuilder data83 = new StringBuilder();
        StringBuilder data84 = new StringBuilder();
        StringBuilder data9 = new StringBuilder();
        StringBuilder data92 = new StringBuilder();
        StringBuilder data93 = new StringBuilder();
        StringBuilder data94 = new StringBuilder();
        StringBuilder data10 = new StringBuilder();
        StringBuilder data101 = new StringBuilder();
        StringBuilder data102 = new StringBuilder();
        StringBuilder data103 = new StringBuilder();
        
        Document doc = documents.first();  // Obtener el primer documento
       if (doc != null) {
            Object disponibilidadObj = doc.get("Disponibilidad");
            Object precioObj = doc.get("Precio");
            Object limpiezaObj = doc.get("Estado limpieza");
            Object codigoObj = doc.get("Codigo");
            String disponibilidad = disponibilidadObj != null ? disponibilidadObj.toString() : "N/A";
            String precio = precioObj != null ? precioObj.toString() : "N/A";
            String limpieza = limpiezaObj != null ? limpiezaObj.toString() : "N/A";
            String codigo = codigoObj != null ? codigoObj.toString() : "N/A";
            
            data.append("Disponibilidad: ").append(disponibilidad);
            data11.append("Precio: ").append(precio);
            data12.append("Estado de limpieza: ").append(limpieza);
            data13.append(codigo);
        } else {
            data.append("No se encontraron datos.");
       }
       
       Document doc2 = documents.skip(1).first(); // Obtener el segundo documento
        if (doc2 != null) {
        Object disponibilidadObj2 = doc2.get("Disponibilidad");
        Object precioObj2 = doc2.get("Precio");
        Object limpiezaObj2 = doc2.get("Estado limpieza");
        Object codigoObj2 = doc2.get("Codigo");
        String disponibilidad2 = disponibilidadObj2 != null ? disponibilidadObj2.toString() : "N/A";
        String precio2 = precioObj2 != null ? precioObj2.toString() : "N/A";
        String limpieza2 = limpiezaObj2 != null ? limpiezaObj2.toString() : "N/A";
        String codigo2 = codigoObj2 != null ? codigoObj2.toString() : "N/A";
        
        data2.append("Disponibilidad: ").append(disponibilidad2);
        data22.append("Precio: ").append(precio2);
        data23.append("Estado de limpieza: ").append(limpieza2);
        data24.append(codigo2);
        Document doc3 = documents.skip(2).first(); // Obtener el segundo documento
        if (doc3 != null) {
        Object disponibilidadObj3 = doc3.get("Disponibilidad");
        Object precioObj3 = doc3.get("Precio");
        Object limpiezaObj3 = doc3.get("Estado limpieza");
        Object codigoObj3 = doc3.get("Codigo");
        String disponibilidad3 = disponibilidadObj3 != null ? disponibilidadObj3.toString() : "N/A";
        String precio3 = precioObj3 != null ? precioObj3.toString() : "N/A";
        String limpieza3 = limpiezaObj3 != null ? limpiezaObj3.toString() : "N/A";
        String codigo3 = codigoObj3 != null ? codigoObj3.toString() : "N/A";
        data3.append("Disponibilidad: ").append(disponibilidad3);
        data32.append("Precio: ").append(precio3);
        data33.append("Estado de limpieza: ").append(limpieza3);
        data34.append(codigo3);
        Document doc4 = documents.skip(3).first(); // Obtener el segundo documento
        if (doc4 != null) {
        Object disponibilidadObj4 = doc4.get("Disponibilidad");
        Object precioObj4 = doc4.get("Precio");
        Object limpiezaObj4 = doc4.get("Estado limpieza");
        Object codigoObj4 = doc4.get("Codigo");
        String disponibilidad4 = disponibilidadObj4 != null ? disponibilidadObj4.toString() : "N/A";
        String precio4 = precioObj4 != null ? precioObj4.toString() : "N/A";
        String limpieza4 = limpiezaObj4 != null ? limpiezaObj4.toString() : "N/A";
        String codigo4 = codigoObj4 != null ? codigoObj4.toString() : "N/A";
        data4.append("Disponibilidad: ").append(disponibilidad4);
        data42.append("Precio: ").append(precio4);
        data43.append("Estado de limpieza: ").append(limpieza4);
        data44.append(codigo4);
        Document doc5 = documents.skip(4).first(); // Obtener el segundo documento
        if (doc5 != null) {
        Object disponibilidadObj5 = doc5.get("Disponibilidad");
        Object precioObj5 = doc5.get("Precio");
        Object limpiezaObj5 = doc5.get("Estado limpieza");
        Object codigoObj5 = doc5.get("Codigo");
        String disponibilidad5 = disponibilidadObj5 != null ? disponibilidadObj5.toString() : "N/A";
        String precio5 = precioObj5 != null ? precioObj5.toString() : "N/A";
        String limpieza5 = limpiezaObj5 != null ? limpiezaObj5.toString() : "N/A";
        String codigo5 = codigoObj5 != null ? codigoObj5.toString() : "N/A";
        data5.append("Disponibilidad: ").append(disponibilidad5);
        data52.append("Precio: ").append(precio5);
        data53.append("Estado de limpieza: ").append(limpieza5);
        data54.append(codigo5);
        
        Document doc6 = documents.skip(5).first(); // Obtener el segundo documento
        if (doc6 != null) {
        Object disponibilidadObj6 = doc6.get("Disponibilidad");
        Object precioObj6 = doc6.get("Precio");
        Object limpiezaObj6 = doc6.get("Estado limpieza");
        Object codigoObj6 = doc6.get("Codigo");
        String disponibilidad6 = disponibilidadObj6 != null ? disponibilidadObj6.toString() : "N/A";
        String precio6 = precioObj6 != null ? precioObj6.toString() : "N/A";
        String limpieza6 = limpiezaObj6 != null ? limpiezaObj6.toString() : "N/A";
        String codigo6 = codigoObj6 != null ? codigoObj6.toString() : "N/A";
        data6.append("Disponibilidad: ").append(disponibilidad6);
        data62.append("Precio: ").append(precio6);
        data63.append("Estado de limpieza: ").append(limpieza6);
        data64.append(codigo6);
        Document doc7 = documents.skip(6).first(); // Obtener el segundo documento
        if (doc7 != null) {
        Object disponibilidadObj7 = doc7.get("Disponibilidad");
        Object precioObj7 = doc7.get("Precio");
        Object limpiezaObj7 = doc7.get("Estado limpieza");
        Object codigoObj7 = doc7.get("Codigo");
        String disponibilidad7 = disponibilidadObj7 != null ? disponibilidadObj7.toString() : "N/A";
        String precio7 = precioObj7 != null ? precioObj7.toString() : "N/A";
        String limpieza7 = limpiezaObj7 != null ? limpiezaObj7.toString() : "N/A";
        String codigo7 = codigoObj7 != null ? codigoObj7.toString() : "N/A";
        data7.append("Disponibilidad: ").append(disponibilidad7);
        data72.append("Precio: ").append(precio7);
        data73.append("Estado de limpieza: ").append(limpieza7);
        data74.append(codigo7);
        Document doc8 = documents.skip(7).first(); // Obtener el segundo documento
        if (doc8 != null) {
        Object disponibilidadObj8 = doc8.get("Disponibilidad");
        Object precioObj8 = doc8.get("Precio");
        Object limpiezaObj8 = doc8.get("Estado limpieza");
        Object codigoObj8 = doc8.get("Codigo");
        String disponibilidad8 = disponibilidadObj8 != null ? disponibilidadObj8.toString() : "N/A";
        String precio8 = precioObj8 != null ? precioObj8.toString() : "N/A";
        String limpieza8 = limpiezaObj8 != null ? limpiezaObj8.toString() : "N/A";
        String codigo8 = codigoObj8 != null ? codigoObj8.toString() : "N/A";
        data8.append("Disponibilidad: ").append(disponibilidad8);
        data82.append("Precio: ").append(precio8);
        data83.append("Estado de limpieza: ").append(limpieza8);
        data84.append(codigo8);
        Document doc9 = documents.skip(8).first(); // Obtener el segundo documento
        if (doc9 != null) {
        Object disponibilidadObj9 = doc9.get("Disponibilidad");
        Object precioObj9 = doc9.get("Precio");
        Object limpiezaObj9 = doc9.get("Estado limpieza");
        Object codigoObj9 = doc9.get("Codigo");
        String disponibilidad9 = disponibilidadObj9 != null ? disponibilidadObj9.toString() : "N/A";
        String precio9 = precioObj9 != null ? precioObj9.toString() : "N/A";
        String limpieza9 = limpiezaObj9 != null ? limpiezaObj9.toString() : "N/A";
        String codigo9 = codigoObj9 != null ? codigoObj9.toString() : "N/A";
        data9.append("Disponibilidad: ").append(disponibilidad9);
        data92.append("Precio: ").append(precio9);
        data93.append("Estado de limpieza: ").append(limpieza9);
        data94.append(codigo9);
        Document doc10 = documents.skip(9).first(); // Obtener el segundo documento
        if (doc10 != null) {
        Object disponibilidadObj10 = doc10.get("Disponibilidad");
        Object precioObj10 = doc10.get("Precio");
        Object limpiezaObj10 = doc10.get("Estado limpieza");
        Object codigoObj10 = doc10.get("Codigo");
        String disponibilidad10 = disponibilidadObj10 != null ? disponibilidadObj10.toString() : "N/A";
        String precio10 = precioObj10 != null ? precioObj10.toString() : "N/A";
        String limpieza10 = limpiezaObj10 != null ? limpiezaObj10.toString() : "N/A";
        String codigo10 = codigoObj10 != null ? codigoObj10.toString() : "N/A";
        data10.append("Disponibilidad: ").append(disponibilidad10);
        data101.append("Precio: ").append(precio10);
        data102.append("Estado de limpieza: ").append(limpieza10);
        data103.append(codigo10);
        hab1.setText(data.toString());
        hab11.setText(data11.toString());
        hab12.setText(data12.toString());
        hab2.setText(data2.toString());
        hab22.setText(data22.toString());
        hab23.setText(data23.toString());
        hab3.setText(data3.toString());
        hab32.setText(data32.toString());
        hab33.setText(data33.toString());
        hab4.setText(data4.toString());
        hab42.setText(data42.toString());
        hab43.setText(data43.toString());
        hab5.setText(data5.toString());
        hab52.setText(data52.toString());
        hab53.setText(data53.toString());
        hab6.setText(data6.toString());
        hab62.setText(data62.toString());
        hab63.setText(data63.toString());
        hab7.setText(data7.toString());
        hab72.setText(data72.toString());
        hab73.setText(data73.toString());
        hab8.setText(data8.toString());
        hab82.setText(data82.toString());
        hab83.setText(data83.toString());
        hab9.setText(data9.toString());
        hab92.setText(data92.toString());
        hab93.setText(data93.toString());
        hab10.setText(data10.toString());
        hab101.setText(data101.toString());
        hab102.setText(data102.toString());
        cod1.setText(data13.toString());
        cod2.setText(data24.toString());
        cod3.setText(data34.toString());
        cod4.setText(data44.toString());
        cod5.setText(data54.toString());
        cod6.setText(data64.toString());
        cod7.setText(data74.toString());
        cod8.setText(data84.toString());
        cod9.setText(data94.toString());
        cod10.setText(data103.toString());
        
        }  }
        }}}}}}}  
    }
}
        
