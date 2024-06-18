package Clases;

import BD_Facturas.conexionBD;
import BD_Facturas.crearPDF;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

public class agregarDatosFa {
    conexionBD iM = new conexionBD();
    
    public void agregarDatosTabla(String cood, String tipHa, int dia, JTable factura, JTextField subTotal, JTextField total, JTextField iva) {
        if (tipHa.equals("Personal")) {
            tipHa = "H_personal";
        } else if (tipHa.equals("Familiar")) {
            tipHa = "H_familiar";
        } else {
            tipHa = "H_matrimonial";
        }
        iM.openMongo();
MongoDatabase database = iM.getDatabase();
MongoCollection<Document> collection = database.getCollection(tipHa);
Document query = new Document("Codigo", cood);
long count = collection.countDocuments(query);  
if (dia<=0){
    JOptionPane.showMessageDialog(null, "Ingrese una cantidad de dias de hospedaje correcto", "Error", JOptionPane.ERROR_MESSAGE);
}else{

if (count <= 0) {
    JOptionPane.showMessageDialog(null, "No se encontró la habitación con el código especificado", "Error", JOptionPane.ERROR_MESSAGE);
} else {
    DefaultTableModel tabla1 = (DefaultTableModel) factura.getModel();
   int subtotal=0;
    limpiarFilasVacias(tabla1);
    FindIterable<Document> documents = collection.find(query);  
    int subtotalText=0;
    for (Document document : documents) {
        String codigo = document.getString("Codigo");
        String tipoHabitacion = document.getString("Tipo de habitacion");
        Double precio = document.getDouble("Precio");
        int precioI = precio.intValue();
         subtotal=precioI*dia; 
        
        boolean existe = false;
        for (int i = 0; i < tabla1.getRowCount(); i++) {
            Object codigoEnTabla = tabla1.getValueAt(i, 0);
            Object tipoHabitacionEnTabla = tabla1.getValueAt(i, 1);
            Object diaEnTabla = tabla1.getValueAt(i, 2);
            Object precioEnTabla = tabla1.getValueAt(i, 3);
            if (codigo.equals(codigoEnTabla) &&
                tipoHabitacion.equals(tipoHabitacionEnTabla) &&
                (diaEnTabla != null && dia == (int) diaEnTabla) &&
                (precioEnTabla != null && precio.equals(precioEnTabla)) )
                     {
                existe = true;
                break;
            }
        }
        
        if (!existe) {
            tabla1.addRow(new Object[]{codigo, tipoHabitacion, dia, precio, subtotal});
            JOptionPane.showMessageDialog(null, "Habitación agregada a la factura", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            for (int i = 0; i < tabla1.getRowCount(); i++) {
                    subtotalText += (int) tabla1.getValueAt(i, 4);
                }
        } else {
            JOptionPane.showMessageDialog(null, "La habitación ya está en la factura", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } 
        crearPDF c=new crearPDF();
        c.crearPDF(codigo, tipoHabitacion, dia, precio, subtotal , factura);
    }

        
        subTotal.setText(String.valueOf(subtotalText));
        iva.setText(String.valueOf(subtotalText*0.15));
        total.setText(String.valueOf(subtotalText*1.15));
    factura.setModel(tabla1);
    
}}}


   private void limpiarFilasVacias(DefaultTableModel tabla1) {
        for (int i = tabla1.getRowCount() - 1; i >= 0; i--) {
            boolean filaVacia = true;
            for (int j = 0; j < tabla1.getColumnCount(); j++) {
                if (tabla1.getValueAt(i, j) != null) {
                    filaVacia = false;
                    break;
                }
            }
            if (filaVacia) {
                tabla1.removeRow(i);
            }
        }
    }
}