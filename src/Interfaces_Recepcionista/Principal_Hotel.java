package Interfaces_Recepcionista;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Principal_Hotel extends javax.swing.JFrame {
    
    ImageIcon icon = new ImageIcon("src/Imagenes/HotelSantanderIcon.png");
    private int button = 0;
    
    public Principal_Hotel() {
        initComponents();
        setDefaultGUI();
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("Hotel Santander");
        rsscalelabel.RSScaleLabel.setScaleLabel(LogoLateralPanelL, "src/imagenes/HotelSantanderIcon.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(LogoL, "src/imagenes/HotelSantanderIcon.png");
        
        this.setIconImage(icon.getImage());
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Fuentes/more-sugar.regular.ttf")).deriveFont(14f);
            setFontForComponents(getContentPane(), customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setResolution(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        // Obtener el dispositivo gráfico principal
        GraphicsDevice[] gs = ge.getScreenDevices();
        for (GraphicsDevice gd : gs) {
            // Obtener la configuración de pantalla del dispositivo
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            
            // Obtener el ancho y alto de la pantalla
            int width = screenSize.width;
            int height = screenSize.height;
            
            // Imprimir la resolución de la pantalla
            System.out.println("Resolución de la pantalla: " + width + "x" + height);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LateralPanel = new javax.swing.JPanel();
        AboutB = new javax.swing.JButton();
        LogoLateralPanelL = new javax.swing.JLabel();
        RoomsButton = new javax.swing.JButton();
        CleanningB = new javax.swing.JButton();
        SellsB = new javax.swing.JButton();
        FormB = new javax.swing.JButton();
        AdditionalsB = new javax.swing.JButton();
        SwitchAccountB = new javax.swing.JButton();
        DocumentationB = new javax.swing.JButton();
        InformationB = new javax.swing.JButton();
        MainPanel = new javax.swing.JPanel();
        LogoL = new javax.swing.JLabel();
        RentB = new javax.swing.JButton();
        RoomsPanel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        PrintRoomsPanel = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jScrollBar1 = new javax.swing.JScrollBar();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        InformationPanel = new javax.swing.JPanel();
        SimpleRoomL = new javax.swing.JLabel();
        SimpleRoomL1 = new javax.swing.JLabel();
        SimpleRoomL2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        CleaningPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton33 = new javax.swing.JButton();
        FacturationPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        AdditionalsPanel = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jButton31 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton32 = new javax.swing.JButton();
        AboutPanel = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jButton35 = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        ManualPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel36 = new javax.swing.JLabel();
        LateralLoginPanel = new javax.swing.JPanel();
        AboutB1 = new javax.swing.JButton();
        LogoLateralPanelL1 = new javax.swing.JLabel();
        DocumentationB1 = new javax.swing.JButton();
        LoginPanel = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jButton37 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LateralPanel.setBackground(new java.awt.Color(177, 203, 192));
        LateralPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AboutB.setBackground(new java.awt.Color(73, 114, 116));
        AboutB.setForeground(new java.awt.Color(255, 255, 255));
        AboutB.setText("Acerca de");
        AboutB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutBActionPerformed(evt);
            }
        });
        LateralPanel.add(AboutB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 960, 140, -1));
        LateralPanel.add(LogoLateralPanelL, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 260, 260));

        RoomsButton.setBackground(new java.awt.Color(73, 114, 116));
        RoomsButton.setForeground(new java.awt.Color(255, 255, 255));
        RoomsButton.setText("Alquilar");
        RoomsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomsButtonActionPerformed(evt);
            }
        });
        LateralPanel.add(RoomsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 290, -1));

        CleanningB.setBackground(new java.awt.Color(73, 114, 116));
        CleanningB.setForeground(new java.awt.Color(255, 255, 255));
        CleanningB.setText("Limpieza");
        CleanningB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CleanningBActionPerformed(evt);
            }
        });
        LateralPanel.add(CleanningB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 290, -1));

        SellsB.setBackground(new java.awt.Color(73, 114, 116));
        SellsB.setForeground(new java.awt.Color(255, 255, 255));
        SellsB.setText("Facturacion");
        SellsB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SellsBActionPerformed(evt);
            }
        });
        LateralPanel.add(SellsB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 290, -1));

        FormB.setBackground(new java.awt.Color(73, 114, 116));
        FormB.setForeground(new java.awt.Color(255, 255, 255));
        FormB.setText("Proforma");
        LateralPanel.add(FormB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 290, -1));

        AdditionalsB.setBackground(new java.awt.Color(73, 114, 116));
        AdditionalsB.setForeground(new java.awt.Color(255, 255, 255));
        AdditionalsB.setText("Productos adicionales");
        AdditionalsB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdditionalsBActionPerformed(evt);
            }
        });
        LateralPanel.add(AdditionalsB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, 290, -1));

        SwitchAccountB.setBackground(new java.awt.Color(73, 114, 116));
        SwitchAccountB.setForeground(new java.awt.Color(255, 255, 255));
        SwitchAccountB.setText("Logout");
        SwitchAccountB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SwitchAccountBActionPerformed(evt);
            }
        });
        LateralPanel.add(SwitchAccountB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, 290, -1));

        DocumentationB.setBackground(new java.awt.Color(73, 114, 116));
        DocumentationB.setForeground(new java.awt.Color(255, 255, 255));
        DocumentationB.setText("Documentacion");
        DocumentationB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DocumentationBActionPerformed(evt);
            }
        });
        LateralPanel.add(DocumentationB, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 960, 120, -1));

        InformationB.setBackground(new java.awt.Color(73, 114, 116));
        InformationB.setForeground(new java.awt.Color(255, 255, 255));
        InformationB.setText("Informacion");
        InformationB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InformationBActionPerformed(evt);
            }
        });
        LateralPanel.add(InformationB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 290, -1));

        getContentPane().add(LateralPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 1080));

        MainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        MainPanel.add(LogoL, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 450, 450));

        RentB.setBackground(new java.awt.Color(73, 114, 116));
        RentB.setForeground(new java.awt.Color(255, 255, 255));
        RentB.setText("Alquilar");
        RentB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RentBActionPerformed(evt);
            }
        });
        MainPanel.add(RentB, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 640, 290, 100));

        getContentPane().add(MainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 1570, 1080));

        RoomsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setText("Habitaciones");
        RoomsPanel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(766, 5, -1, -1));
        RoomsPanel.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 760, -1));

        jButton10.setText("Buscar");
        RoomsPanel.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 60, -1, -1));

        jLabel21.setText("Buscar Habitacion");
        RoomsPanel.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, 20));

        PrintRoomsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton11.setText("Tipo\\nDisponibilidad \\nRentada");
        PrintRoomsPanel.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 30, 240, 220));

        jButton12.setText("Tipo\\nDisponibilidad \\nRentada");
        PrintRoomsPanel.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 240, 220));

        jButton13.setText("Tipo\\nDisponibilidad \\nRentada");
        PrintRoomsPanel.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, 240, 220));

        jButton14.setText("Tipo\\nDisponibilidad \\nRentada");
        PrintRoomsPanel.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, 240, 220));

        jButton15.setText("Tipo\\nDisponibilidad \\nRentada");
        PrintRoomsPanel.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 30, 240, 220));

        jButton16.setText("Tipo\\nDisponibilidad \\nRentada");
        PrintRoomsPanel.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, 240, 220));

        jButton17.setText("Tipo\\nDisponibilidad \\nRentada");
        PrintRoomsPanel.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 280, 240, 220));

        jButton18.setText("Tipo\\nDisponibilidad \\nRentada");
        PrintRoomsPanel.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 280, 240, 220));

        jButton19.setText("Tipo\\nDisponibilidad \\nRentada");
        PrintRoomsPanel.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 280, 240, 220));

        jButton20.setText("Tipo\\nDisponibilidad \\nRentada");
        PrintRoomsPanel.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 240, 220));

        jButton21.setText("Tipo\\nDisponibilidad \\nRentada");
        PrintRoomsPanel.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 550, 240, 220));

        jButton22.setText("Tipo\\nDisponibilidad \\nRentada");
        PrintRoomsPanel.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 550, 240, 220));

        jButton23.setText("Tipo\\nDisponibilidad \\nRentada");
        PrintRoomsPanel.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 550, 240, 220));

        jButton24.setText("Tipo\\nDisponibilidad \\nRentada");
        PrintRoomsPanel.add(jButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 550, 240, 220));

        jButton25.setText("Tipo\\nDisponibilidad \\nRentada");
        PrintRoomsPanel.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 240, 220));
        PrintRoomsPanel.add(jScrollBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, -2, 20, 950));

        jButton26.setText("Tipo\\nDisponibilidad \\nRentada");
        PrintRoomsPanel.add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 810, 240, 220));

        jButton27.setText("Tipo\\nDisponibilidad \\nRentada");
        PrintRoomsPanel.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 810, 240, 220));

        jButton28.setText("Tipo\\nDisponibilidad \\nRentada");
        PrintRoomsPanel.add(jButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 810, 240, 220));

        jButton29.setText("Tipo\\nDisponibilidad \\nRentada");
        PrintRoomsPanel.add(jButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 810, 240, 220));

        jButton30.setText("Tipo\\nDisponibilidad \\nRentada");
        PrintRoomsPanel.add(jButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 810, 240, 220));

        RoomsPanel.add(PrintRoomsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 1520, 950));

        getContentPane().add(RoomsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 1570, 1080));

        InformationPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        InformationPanel.add(SimpleRoomL, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 370, 320, 220));
        InformationPanel.add(SimpleRoomL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 320, 220));
        InformationPanel.add(SimpleRoomL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 370, 320, 220));

        jLabel1.setText("Descripcion");
        InformationPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 680, -1, -1));

        jLabel2.setText("Nombre");
        InformationPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 620, -1, -1));

        jButton1.setText("Cotizar");
        InformationPanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 780, -1, -1));

        jLabel3.setText("Descripcion");
        InformationPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 680, -1, -1));

        jLabel4.setText("Nombre");
        InformationPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 620, -1, -1));

        jButton2.setText("Cotizar");
        InformationPanel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 780, -1, -1));

        jLabel5.setText("Descripcion");
        InformationPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 670, -1, -1));

        jLabel6.setText("Nombre");
        InformationPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 610, -1, -1));

        jButton3.setText("Cotizar");
        InformationPanel.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 770, -1, -1));

        getContentPane().add(InformationPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 1570, 1080));

        CleaningPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("Limpieza");
        CleaningPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(766, 5, -1, -1));
        CleaningPanel.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 760, -1));

        jButton4.setText("Buscar");
        CleaningPanel.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 60, -1, -1));

        jLabel8.setText("Busqueda");
        CleaningPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, 20));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Habitacion", "Disponibilidad", "Estado", "Encargado"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        CleaningPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 930, -1));

        jButton33.setText("Asignar limpieza");
        CleaningPanel.add(jButton33, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 610, -1, -1));

        getContentPane().add(CleaningPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 1570, 1080));

        FacturationPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setText("Factura");
        FacturationPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(766, 5, -1, -1));
        FacturationPanel.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 760, -1));

        jButton5.setText("Buscar");
        FacturationPanel.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 60, -1, -1));

        jLabel10.setText("Busqueda");
        FacturationPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, 20));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tipo de habitacion", "Noches", "Descripcion"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        FacturationPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, 930, 310));

        jLabel11.setText("Cliente");
        FacturationPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, -1, -1));

        jLabel12.setText("Cedula");
        FacturationPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, -1, -1));

        jLabel13.setText("Telefono");
        FacturationPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, -1));

        jLabel14.setText("Direccion");
        FacturationPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 110, -1, -1));

        jLabel15.setText("Forma de pago");
        FacturationPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 150, -1, -1));
        FacturationPanel.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 400, 30));
        FacturationPanel.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 400, 30));
        FacturationPanel.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 400, 30));
        FacturationPanel.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 110, 400, 30));
        FacturationPanel.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 160, 400, 30));

        jButton6.setText("Añadir");
        FacturationPanel.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 300, -1, -1));

        jButton7.setText("Cancelar");
        FacturationPanel.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 790, -1, -1));

        jButton8.setText("Finalizar");
        FacturationPanel.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 790, -1, -1));

        jButton9.setText("Editar");
        FacturationPanel.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 300, -1, -1));

        jLabel16.setText("Total");
        FacturationPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 710, -1, -1));

        jLabel17.setText("Subtotal");
        FacturationPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 660, -1, -1));

        jLabel18.setText("0,0 $");
        FacturationPanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 710, -1, -1));

        jLabel19.setText("0,0 $");
        FacturationPanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 660, -1, -1));

        getContentPane().add(FacturationPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 1570, 1080));

        AdditionalsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setText("Agregar productos a habitaciones");
        AdditionalsPanel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(766, 5, -1, -1));
        AdditionalsPanel.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 760, -1));

        jButton31.setText("Buscar");
        AdditionalsPanel.add(jButton31, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 60, -1, -1));

        jLabel23.setText("Busqueda");
        AdditionalsPanel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, 20));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Descripcion", "Cantidad", "Valor"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        AdditionalsPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 930, 310));

        jButton32.setText("Añadir producto");
        AdditionalsPanel.add(jButton32, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 490, -1, -1));

        getContentPane().add(AdditionalsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 1570, 1080));

        AboutPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setText("Acerca de");
        AboutPanel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(766, 5, -1, -1));

        jButton35.setText("Asignar limpieza");
        AboutPanel.add(jButton35, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 610, -1, -1));

        jLabel28.setText("Developed by;");
        AboutPanel.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, -1, -1));

        jLabel29.setText("Designed by:");
        AboutPanel.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 150, -1, -1));

        jLabel30.setText("Version");
        AboutPanel.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 200, -1, -1));

        jLabel31.setText("Contact & Support ");
        AboutPanel.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 250, -1, -1));

        jLabel32.setText("099999999/0222222222");
        AboutPanel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 250, -1, -1));

        jLabel33.setText("Lorem Ipsum");
        AboutPanel.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 100, -1, -1));

        jLabel34.setText("Lorem Ipsum");
        AboutPanel.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 150, -1, -1));

        jLabel35.setText("1.1.1.1");
        AboutPanel.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 200, -1, -1));

        getContentPane().add(AboutPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 1570, 1080));

        ManualPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.addTab("Simbologia", jPanel2);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setText("Explicacion de interfaces");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. \nUt enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. \nDuis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. \nExcepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus luctus urna sed urna ultricies ac tempor dui sagittis. \nIn condimentum facilisis porta. \nSed nec diam eu diam mattis viverra. Nulla fringilla, \n");
        jScrollPane4.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 1510, 160));

        jLabel27.setText("¿Como se usa?");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. \nUt enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. \nDuis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. \nExcepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus luctus urna sed urna ultricies ac tempor dui sagittis. \nIn condimentum facilisis porta. \nSed nec diam eu diam mattis viverra. Nulla fringilla, \n");
        jScrollPane5.setViewportView(jTextArea2);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 1510, 160));

        jTabbedPane1.addTab("Manual", jPanel1);

        ManualPanel.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 1570, 940));

        jLabel36.setText("Documentacion");
        ManualPanel.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, -1, -1));

        getContentPane().add(ManualPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 1570, 1080));

        LateralLoginPanel.setBackground(new java.awt.Color(177, 203, 192));
        LateralLoginPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AboutB1.setBackground(new java.awt.Color(73, 114, 116));
        AboutB1.setForeground(new java.awt.Color(255, 255, 255));
        AboutB1.setText("Acerca de");
        LateralLoginPanel.add(AboutB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 260, -1));
        LateralLoginPanel.add(LogoLateralPanelL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 260, 260));

        DocumentationB1.setBackground(new java.awt.Color(73, 114, 116));
        DocumentationB1.setForeground(new java.awt.Color(255, 255, 255));
        DocumentationB1.setText("Documentacion");
        LateralLoginPanel.add(DocumentationB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 260, -1));

        getContentPane().add(LateralLoginPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 1080));

        LoginPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setText("Iniciar Sesion");
        LoginPanel.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 250, -1, -1));

        jLabel38.setText("CI");
        LoginPanel.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 310, -1, -1));

        jLabel39.setText("Contraseña");
        LoginPanel.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 360, -1, -1));
        LoginPanel.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 360, 590, -1));
        LoginPanel.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 310, 590, -1));

        jButton37.setText("Iniciar sesion");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        LoginPanel.add(jButton37, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 420, -1, -1));

        getContentPane().add(LoginPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 1570, 1080));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RoomsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomsButtonActionPerformed
        // TODO add your handling code here:
        button = 1;
        selectedButton(button);
    }//GEN-LAST:event_RoomsButtonActionPerformed

    private void InformationBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InformationBActionPerformed
        // TODO add your handling code here:
        button = 2;
        selectedButton(button);
    }//GEN-LAST:event_InformationBActionPerformed

    private void CleanningBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CleanningBActionPerformed
        // TODO add your handling code here:
        button = 3;
        selectedButton(button);
    }//GEN-LAST:event_CleanningBActionPerformed

    private void SellsBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SellsBActionPerformed
        // TODO add your handling code here:
        button = 4;
        selectedButton(button);
    }//GEN-LAST:event_SellsBActionPerformed

    private void AdditionalsBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdditionalsBActionPerformed
        // TODO add your handling code here:
        button = 5;
        selectedButton(button);
    }//GEN-LAST:event_AdditionalsBActionPerformed

    private void SwitchAccountBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SwitchAccountBActionPerformed
        // TODO add your handling code here:
        button = 8;
        selectedButton(button);
    }//GEN-LAST:event_SwitchAccountBActionPerformed

    private void AboutBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutBActionPerformed
        // TODO add your handling code here:
        button = 6;
        selectedButton(button);
    }//GEN-LAST:event_AboutBActionPerformed

    private void DocumentationBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DocumentationBActionPerformed
        // TODO add your handling code here:
        button = 7;
        selectedButton(button);
    }//GEN-LAST:event_DocumentationBActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
        //codigo para validar credenciales
        
        //dirigir al menu
        button = 0;
        selectedButton(button);
    }//GEN-LAST:event_jButton37ActionPerformed
    
    private void RentBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RentBActionPerformed
        // TODO add your handling code here:
        selectedButton(1);
    }//GEN-LAST:event_RentBActionPerformed
    private void setFontForComponents(Container container, Font font) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton || component instanceof JLabel) {
                component.setFont(font);
            } else if (component instanceof Container) {
                setFontForComponents((Container) component, font);  // Recursión
            }
        }
    }
    public void setDefaultGUI(){
        RoomsPanel.setVisible(false);
        InformationPanel.setVisible(false);
        FacturationPanel.setVisible(false);
        AdditionalsPanel.setVisible(false);
        CleaningPanel.setVisible(false);
        AboutPanel.setVisible(false);
        MainPanel.setVisible(false);
        LateralPanel.setVisible(false);
        AboutPanel.setVisible(false);
        ManualPanel.setVisible(false);
        LateralLoginPanel.setVisible(true);
        LoginPanel.setVisible(true);
    }
    
    public void selectedButton(int boton) {
        // Ocultar todos los paneles primero
        hideAllPanels();

        switch(boton) {
            case 0:
                // main
                MainPanel.setVisible(true);
                LateralPanel.setVisible(true);
                break;

            case 1:
                // alquilar
                LateralPanel.setVisible(true);
                RoomsPanel.setVisible(true);
                break;

            case 2:
                // informacion
                LateralPanel.setVisible(true);
                InformationPanel.setVisible(true);
                break;

            case 3:
                // limpieza
                LateralPanel.setVisible(true);
                CleaningPanel.setVisible(true);
                break;

            case 4:
                // facturacion
                LateralPanel.setVisible(true);
                FacturationPanel.setVisible(true);
                break;

            case 5:
                // adicionales
                LateralPanel.setVisible(true);
                AdditionalsPanel.setVisible(true);
                break;

            case 6:
                // acerca de
                LateralPanel.setVisible(true);
                AboutPanel.setVisible(true);
                break;

            case 7:
                // documentacion
                LateralPanel.setVisible(true);
                MainPanel.setVisible(true);
                break;

            case 8:
                // cambiar de cuenta
                LateralLoginPanel.setVisible(true);
                LoginPanel.setVisible(true);
                break;

            case 9:
                // No hace nada
                break;

            default:
                // No hace nada
                break;
        }
    }

    private void hideAllPanels() {
        MainPanel.setVisible(false);
        LateralPanel.setVisible(false);
        RoomsPanel.setVisible(false);
        InformationPanel.setVisible(false);
        FacturationPanel.setVisible(false);
        CleaningPanel.setVisible(false);
        AdditionalsPanel.setVisible(false);
        AboutPanel.setVisible(false);
        LateralLoginPanel.setVisible(false);
        LoginPanel.setVisible(false);
    }
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
            java.util.logging.Logger.getLogger(Principal_Hotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal_Hotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal_Hotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal_Hotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal_Hotel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AboutB;
    private javax.swing.JButton AboutB1;
    private javax.swing.JPanel AboutPanel;
    private javax.swing.JButton AdditionalsB;
    private javax.swing.JPanel AdditionalsPanel;
    private javax.swing.JPanel CleaningPanel;
    private javax.swing.JButton CleanningB;
    private javax.swing.JButton DocumentationB;
    private javax.swing.JButton DocumentationB1;
    private javax.swing.JPanel FacturationPanel;
    private javax.swing.JButton FormB;
    private javax.swing.JButton InformationB;
    private javax.swing.JPanel InformationPanel;
    private javax.swing.JPanel LateralLoginPanel;
    private javax.swing.JPanel LateralPanel;
    private javax.swing.JPanel LoginPanel;
    private javax.swing.JLabel LogoL;
    private javax.swing.JLabel LogoLateralPanelL;
    private javax.swing.JLabel LogoLateralPanelL1;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel ManualPanel;
    private javax.swing.JPanel PrintRoomsPanel;
    private javax.swing.JButton RentB;
    private javax.swing.JButton RoomsButton;
    private javax.swing.JPanel RoomsPanel;
    private javax.swing.JButton SellsB;
    private javax.swing.JLabel SimpleRoomL;
    private javax.swing.JLabel SimpleRoomL1;
    private javax.swing.JLabel SimpleRoomL2;
    private javax.swing.JButton SwitchAccountB;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
