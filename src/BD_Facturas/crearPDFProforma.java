/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD_Facturas;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class crearPDFProforma {
    conexionBD iM = new conexionBD();   
    String nombre1;
    String tel;
    String cedu1;
    String correo1;
   public void guardarProfBD(String nombre, String telefono, String cedu, String correo){
     nombre1=nombre;
     tel=telefono;
     cedu1=cedu;
     correo1=correo;
     
       iM.openMongo();  
    MongoDatabase database = iM.getDatabase();
    MongoCollection<org.bson.Document> collection = database.getCollection("Proformas");
     org.bson.Document doc = new org.bson.Document()
     .append("Cliente", nombre1).append("Teléfono", telefono).append("Cédula", cedu1).append("Correo electrónico", correo1).append("Detalle", "Alquiler habitación");
    collection.insertOne(doc);
   }
   public void crearPDFProf(String codigo,String tipoHabitacion, int dia,Double precio,Double subtotal, JTable factura ){ 
       iM.openMongo();  
        MongoDatabase database = iM.getDatabase();       
       DefaultTableModel model = (DefaultTableModel) factura.getModel();
       String pdfPath = "Proforma"+ nombre1 +".pdf";
        Document pdfDocument = new Document();    
        double suma=0;
        try {
            PdfWriter.getInstance(pdfDocument, new FileOutputStream(pdfPath));
            pdfDocument.open();
            // Agregar datos al PDF
            
             try {
            String nombreImagen = "logo_PDF.png"; // Nombre del archivo de imagen
            Image logo = Image.getInstance(getClass().getResource("/Imagenes/" + nombreImagen)); // Ruta relativa en el classpath
            logo.setAlignment(Image.ALIGN_CENTER);
            pdfDocument.add(logo);
        } catch (Exception e) {
            e.printStackTrace();
        }
            pdfDocument.add(new Paragraph("======================================================================="));
            pdfDocument.add(new Paragraph("                                      PROFORMA DE RESERVAS SANTANDER "));
            pdfDocument.add(new Paragraph("======================================================================="));
            pdfDocument.add(new Paragraph(" "));
            pdfDocument.add(new Paragraph("DATOS DEL CLIENTE"));
            pdfDocument.add(new Paragraph("Cliente: " + nombre1));
            pdfDocument.add(new Paragraph("Teléfono: " + tel));
            pdfDocument.add(new Paragraph("Cédula: " + cedu1));
            pdfDocument.add(new Paragraph("Correo electrónico: " +  correo1));
            pdfDocument.add(new Paragraph(" "));
            pdfDocument.add(new Paragraph("DATOS DE PROFORMA"));
            pdfDocument.add(new Paragraph("======================================================================="));
            pdfDocument.add(new Paragraph("Detalle: Alquiler de habitación"));
            pdfDocument.add(new Paragraph("-----------------------------------------------------------------------------------------------------------------------------"));
            pdfDocument.add(new Paragraph("|       Codigo    |   Tipo de habitación  |     Noches     |      Precio por noche  |    Total    |"));
            pdfDocument.add(new Paragraph("-----------------------------------------------------------------------------------------------------------------------------"));
            for (int i = 0; i < model.getRowCount(); i++) {
            String codigoHabitacion = model.getValueAt(i, 0).toString();
            String tipoHab = model.getValueAt(i, 1).toString();
            int noches = Integer.parseInt(model.getValueAt(i, 2).toString());
            double precioPorNoche = Double.parseDouble(model.getValueAt(i, 3).toString());
            double total = noches * precioPorNoche;
            
            pdfDocument.add(new Paragraph("|        " + codigoHabitacion + "      |        " + tipoHab + "         |              " + noches + "         |           " + precioPorNoche + "           |     " + total + "  |"));
            suma =suma +total;
        } pdfDocument.add(new Paragraph("-----------------------------------------------------------------------------------------------------------------------------"));
          pdfDocument.add(new Paragraph("                                                                                                           |  Subtotal:     "  + suma));
          pdfDocument.add(new Paragraph("                                                                                                           |  IVA:          "  + Math.round((suma*0.15) * 100.0) / 100.0));
          pdfDocument.add(new Paragraph("                                                                                                           |  TOTAL:        "  + Math.round((suma*1.15) * 100.0) / 100.0));
    } catch (DocumentException | IOException e) {
        e.printStackTrace();
    } finally {
        pdfDocument.close();
    }
   }
}
