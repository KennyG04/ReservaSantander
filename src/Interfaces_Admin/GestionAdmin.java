/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces_Admin;

import Clases.gestionRecepcionistas;

/**
 *
 * @author HP
 */
public class GestionAdmin extends javax.swing.JFrame {
gestionRecepcionistas c=new gestionRecepcionistas();
    /**
     * Creates new form GestionAdmin
     */
    public GestionAdmin() {
        initComponents();
        c.seeUssers(seeUsers); 
        modRecepcionistas.setVisible(true);
     modClientes.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modRecepcionistas = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        seeUsers = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        codNew = new javax.swing.JTextField();
        WMFindL = new javax.swing.JLabel();
        WMNewDataL = new javax.swing.JLabel();
        usserNew = new javax.swing.JTextField();
        WMNewDataL1 = new javax.swing.JLabel();
        AddWorker = new javax.swing.JButton();
        WMNewDataL2 = new javax.swing.JLabel();
        idNew = new javax.swing.JTextField();
        idNew1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        modClientes = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        recepcionistas = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        seeUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(seeUsers);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 108, -1, 200));

        jButton1.setText("Actualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, -1, -1));

        jTabbedPane1.addTab("Ver Usuarios", jPanel5);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(codNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 340, 40));

        WMFindL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        WMFindL.setText("Código Usuario");
        jPanel2.add(WMFindL, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 180, 30));

        WMNewDataL.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        WMNewDataL.setText("Usuario");
        jPanel2.add(WMNewDataL, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 110, 30));
        jPanel2.add(usserNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 340, 40));

        WMNewDataL1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        WMNewDataL1.setText("Confirmar Cédula");
        jPanel2.add(WMNewDataL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 290, 30));

        AddWorker.setBackground(new java.awt.Color(166, 166, 166));
        AddWorker.setForeground(new java.awt.Color(255, 255, 255));
        AddWorker.setText("Añadir");
        AddWorker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddWorkerActionPerformed(evt);
            }
        });
        jPanel2.add(AddWorker, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 160, 40));

        WMNewDataL2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        WMNewDataL2.setText("Cédula");
        jPanel2.add(WMNewDataL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 110, 30));
        jPanel2.add(idNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 340, 40));
        jPanel2.add(idNew1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 340, 40));

        jTabbedPane1.addTab("Agregar Recepcionista ", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 606, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Modificar Recepcionista", jPanel3);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jTabbedPane1.addTab("Eliminar Recepcionista", jPanel4);

        javax.swing.GroupLayout modRecepcionistasLayout = new javax.swing.GroupLayout(modRecepcionistas);
        modRecepcionistas.setLayout(modRecepcionistasLayout);
        modRecepcionistasLayout.setHorizontalGroup(
            modRecepcionistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modRecepcionistasLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        modRecepcionistasLayout.setVerticalGroup(
            modRecepcionistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modRecepcionistasLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(modRecepcionistas, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 600, 400));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Modificar Clientes", jPanel8);

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jTabbedPane2.addTab("Eliminar Clientes", jPanel9);

        javax.swing.GroupLayout modClientesLayout = new javax.swing.GroupLayout(modClientes);
        modClientes.setLayout(modClientesLayout);
        modClientesLayout.setHorizontalGroup(
            modClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(modClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPane2))
        );
        modClientesLayout.setVerticalGroup(
            modClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 415, Short.MAX_VALUE)
            .addGroup(modClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modClientesLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        getContentPane().add(modClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 600, 400));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        recepcionistas.setText("Gestión Recepcionistas");
        recepcionistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recepcionistasActionPerformed(evt);
            }
        });
        jPanel1.add(recepcionistas, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 195, 166, -1));

        jButton2.setText("Gestión ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 166, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void recepcionistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recepcionistasActionPerformed
     modRecepcionistas.setVisible(true);
     modClientes.setVisible(false);
// TODO add your handling code here:
    }//GEN-LAST:event_recepcionistasActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      modRecepcionistas.setVisible(false);
     modClientes.setVisible(true);  // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     c.seeUssers(seeUsers);  // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void AddWorkerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddWorkerActionPerformed
      String newCod= codNew.getText();
    String newUsser= usserNew.getText();
    String newId= idNew.getText();
    String newId1= idNew1.getText();
    gestionRecepcionistas g=new gestionRecepcionistas();
    g.addUsser(newCod, newUsser, newId,newId1);
    codNew.setText("");
    usserNew.setText("");
    idNew.setText("");
    idNew1.setText("");
    
 // TODO add your handling code here:
    }//GEN-LAST:event_AddWorkerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GestionAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddWorker;
    private javax.swing.JLabel WMFindL;
    private javax.swing.JLabel WMNewDataL;
    private javax.swing.JLabel WMNewDataL1;
    private javax.swing.JLabel WMNewDataL2;
    private javax.swing.JTextField codNew;
    private javax.swing.JTextField idNew;
    private javax.swing.JTextField idNew1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JPanel modClientes;
    private javax.swing.JPanel modRecepcionistas;
    private javax.swing.JButton recepcionistas;
    private javax.swing.JTable seeUsers;
    private javax.swing.JTextField usserNew;
    // End of variables declaration//GEN-END:variables
}