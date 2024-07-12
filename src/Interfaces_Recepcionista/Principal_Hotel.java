package Interfaces_Recepcionista;

import Actors.Worker;
import BD_Facturas.crearPDF;
import BD_Users.Conection;
import Clases.agregarDatosFa;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//NOTA: IMPLEMENTAR CAMBIO DE RESOLUCIONES AUTOMATICA, actual 1366,768
//NOTA: Los labels de las habitaciones en el panel RoomsPanel se nombran de la siguiente manera S,M,F+R+T,C,D+N. Donde SMF significa: Simple, Matrimonial, Familiar. Y R significa Room. Y TCD significa: Tipo, Codigo, Disponibilidad, y N es el numero de habitacion
public class Principal_Hotel extends javax.swing.JFrame {
    
    ImageIcon icon = new ImageIcon("src/Imagenes/HotelSantanderIcon.png");
    int userID;
    Worker recepcionista;
    public Principal_Hotel() {
        initComponents();        
        setDefaultGUI();
        loadCredentials();
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("Hotel Santander");
        rsscalelabel.RSScaleLabel.setScaleLabel(LogoLateralPanelL, "src/imagenes/HotelSantanderIcon.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(LogoL, "src/imagenes/HotelSantanderIcon.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(LogoLateralPanelL1, "src/imagenes/HotelSantanderIcon.png");
        un_lockPanel(DataUsersPanel,false);
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

        QuoteDialog = new javax.swing.JDialog();
        jLabel37 = new javax.swing.JLabel();
        ImagePreviewProform = new javax.swing.JLabel();
        RoomNameProform = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        RoomTypeProform = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        Days = new javax.swing.JSlider();
        SliderDaysInfo = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        OutDate = new com.toedter.calendar.JDateChooser();
        jLabel47 = new javax.swing.JLabel();
        BeginingDate = new com.toedter.calendar.JDateChooser();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        Nights = new javax.swing.JTextField();
        FinalPrice = new javax.swing.JTextField();
        RoomPrice = new javax.swing.JTextField();
        jButton34 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        D = new javax.swing.JTextPane();
        AsignCleaningDialog = new javax.swing.JDialog();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel54 = new javax.swing.JLabel();
        HourComponent = new javax.swing.JPanel();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jSpinner3 = new javax.swing.JSpinner();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        LoadRoomCount = new javax.swing.JDialog();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        RoomDataDialog = new javax.swing.JDialog();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        SendDataToBill = new javax.swing.JButton();
        RoomCodeTF = new javax.swing.JTextField();
        DaysSliderRoom = new javax.swing.JSlider();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        DaysRoom = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        RoomTypeCB = new javax.swing.JComboBox<>();
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
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jScrollBar1 = new javax.swing.JScrollBar();
        jLabel45 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        FRT1 = new javax.swing.JLabel();
        SRD1 = new javax.swing.JLabel();
        MRT1 = new javax.swing.JLabel();
        SRT1 = new javax.swing.JLabel();
        FRD1 = new javax.swing.JLabel();
        SRC1 = new javax.swing.JLabel();
        MRC1 = new javax.swing.JLabel();
        MRD1 = new javax.swing.JLabel();
        FRC1 = new javax.swing.JLabel();
        InformationPanel = new javax.swing.JPanel();
        SimpleRoomL = new javax.swing.JLabel();
        SimpleRoomL1 = new javax.swing.JLabel();
        SimpleRoomL2 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        SI5 = new javax.swing.JLabel();
        PersonalRoomL = new javax.swing.JLabel();
        SI1 = new javax.swing.JLabel();
        SI2 = new javax.swing.JLabel();
        SI3 = new javax.swing.JLabel();
        SI4 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        FI6 = new javax.swing.JLabel();
        FamiliarRoomL = new javax.swing.JLabel();
        FI1 = new javax.swing.JLabel();
        FI2 = new javax.swing.JLabel();
        FI3 = new javax.swing.JLabel();
        FI4 = new javax.swing.JLabel();
        FI5 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        MI6 = new javax.swing.JLabel();
        MatrimonialRoomL = new javax.swing.JLabel();
        MI1 = new javax.swing.JLabel();
        MI2 = new javax.swing.JLabel();
        MI3 = new javax.swing.JLabel();
        MI4 = new javax.swing.JLabel();
        MI5 = new javax.swing.JLabel();
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
        SearchTF = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        BillDescriptionTable = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        FacturationSearchValidation = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        TotalTF = new javax.swing.JTextField();
        SubtotalTF = new javax.swing.JTextField();
        IVATF = new javax.swing.JTextField();
        DataUsersPanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        PhoneTF = new javax.swing.JTextField();
        NameTF = new javax.swing.JTextField();
        IDTF = new javax.swing.JTextField();
        EmailTF = new javax.swing.JTextField();
        jComboBox4 = new javax.swing.JComboBox<>();
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
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
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
        LoginButon = new javax.swing.JButton();
        LogoLateralPanelL1 = new javax.swing.JLabel();
        DocumentationB1 = new javax.swing.JButton();
        AboutB2 = new javax.swing.JButton();
        LoginPanel = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        LoginPasswordTF = new javax.swing.JTextField();
        LoginUserTF = new javax.swing.JTextField();
        SingInB = new javax.swing.JButton();
        LoginValidation1 = new javax.swing.JLabel();
        LoginValidation2 = new javax.swing.JLabel();
        KeepLogin = new javax.swing.JCheckBox();

        QuoteDialog.setAlwaysOnTop(true);
        QuoteDialog.setMinimumSize(new java.awt.Dimension(797, 490));
        QuoteDialog.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel37.setText("Cotizar");
        jLabel37.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        QuoteDialog.getContentPane().add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, 60, -1));

        ImagePreviewProform.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        ImagePreviewProform.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        QuoteDialog.getContentPane().add(ImagePreviewProform, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 140, 140));

        RoomNameProform.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomNameProform.setText("Room Name");
        QuoteDialog.getContentPane().add(RoomNameProform, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 250, 160, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        QuoteDialog.getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, -7, 10, 500));

        RoomTypeProform.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Simple", "Familiar", "Matrimonial" }));
        RoomTypeProform.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomTypeProformActionPerformed(evt);
            }
        });
        QuoteDialog.getContentPane().add(RoomTypeProform, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 220, -1));

        jLabel43.setText("Tipo de habitacion");
        QuoteDialog.getContentPane().add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, -1, -1));

        jLabel44.setText("Duracion de la Estancia");
        QuoteDialog.getContentPane().add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, -1, -1));

        Days.setValue(0);
        Days.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                DaysStateChanged(evt);
            }
        });
        QuoteDialog.getContentPane().add(Days, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, -1, -1));

        SliderDaysInfo.setText("0");
        QuoteDialog.getContentPane().add(SliderDaysInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 210, -1, -1));

        jLabel46.setText("Fecha de salida");
        QuoteDialog.getContentPane().add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, -1, -1));
        QuoteDialog.getContentPane().add(OutDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, 220, -1));

        jLabel47.setText("Fecha de ingreso");
        QuoteDialog.getContentPane().add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 250, -1, -1));
        QuoteDialog.getContentPane().add(BeginingDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 220, -1));

        jLabel48.setText("Total de noches");
        QuoteDialog.getContentPane().add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 90, -1, -1));

        jLabel49.setText("Precio de la habitacion");
        QuoteDialog.getContentPane().add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, -1, -1));

        jLabel50.setText("Precio final");
        QuoteDialog.getContentPane().add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, -1, -1));

        Nights.setEnabled(false);
        QuoteDialog.getContentPane().add(Nights, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 120, 120, -1));

        FinalPrice.setEnabled(false);
        FinalPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinalPriceActionPerformed(evt);
            }
        });
        QuoteDialog.getContentPane().add(FinalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, 120, -1));

        RoomPrice.setEnabled(false);
        RoomPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomPriceActionPerformed(evt);
            }
        });
        QuoteDialog.getContentPane().add(RoomPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 120, -1));

        jButton34.setText("Generar Cotizacion");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        QuoteDialog.getContentPane().add(jButton34, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 280, 140, -1));

        jButton36.setText("Generar Reservacion");
        QuoteDialog.getContentPane().add(jButton36, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 320, 140, -1));

        jButton38.setText("Cancelar");
        QuoteDialog.getContentPane().add(jButton38, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 400, 140, -1));

        jButton39.setText("Limpiar");
        QuoteDialog.getContentPane().add(jButton39, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 360, 140, -1));

        D.setEditable(false);
        jScrollPane7.setViewportView(D);

        QuoteDialog.getContentPane().add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 130, 120));

        AsignCleaningDialog.setMinimumSize(new java.awt.Dimension(475, 306));
        AsignCleaningDialog.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel51.setText("Asignar Limpieza");
        AsignCleaningDialog.getContentPane().add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        jLabel52.setText("Trabajador Disponible");
        AsignCleaningDialog.getContentPane().add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        AsignCleaningDialog.getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 180, -1));

        jLabel53.setText("Habitacion a limpiar");
        AsignCleaningDialog.getContentPane().add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, -1, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        AsignCleaningDialog.getContentPane().add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 190, -1));

        jLabel54.setText("Asignar hora");
        AsignCleaningDialog.getContentPane().add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        HourComponent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));
        HourComponent.add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        jSpinner2.setModel(new javax.swing.SpinnerListModel(new String[] {"A.M", "P.M"}));
        HourComponent.add(jSpinner2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        jSpinner3.setModel(new javax.swing.SpinnerNumberModel(0, 0, 12, 1));
        HourComponent.add(jSpinner3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel55.setText("Minutos");
        HourComponent.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        jLabel56.setText("Hora");
        HourComponent.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        AsignCleaningDialog.getContentPane().add(HourComponent, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 220, 70));

        LoadRoomCount.setTitle("Cargar cuenta de habitacion");
        LoadRoomCount.setAlwaysOnTop(true);
        LoadRoomCount.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LoadRoomCount.setMinimumSize(new java.awt.Dimension(466, 303));
        LoadRoomCount.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel57.setText("Cargar cuenta de habitacion");
        LoadRoomCount.getContentPane().add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 150, -1));

        jLabel58.setText("Buscar por codigo");
        LoadRoomCount.getContentPane().add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));
        LoadRoomCount.getContentPane().add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 420, -1));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(jTable4);

        LoadRoomCount.getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 420, 120));

        jButton40.setText("Cargar");
        LoadRoomCount.getContentPane().add(jButton40, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, -1, -1));

        jButton41.setText("Cancelar");
        LoadRoomCount.getContentPane().add(jButton41, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, -1, -1));

        jLabel59.setText("Info message");
        LoadRoomCount.getContentPane().add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 420, 20));

        RoomDataDialog.setMinimumSize(new java.awt.Dimension(400, 300));
        RoomDataDialog.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setText("Datos de la habitacion");
        RoomDataDialog.getContentPane().add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, -1));

        jLabel72.setText("Fecha de salida");
        RoomDataDialog.getContentPane().add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, 20));

        jLabel73.setText("Tipo de habitacion");
        RoomDataDialog.getContentPane().add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));

        jLabel74.setText("Duracion de la estadía");
        RoomDataDialog.getContentPane().add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, 20));

        jLabel75.setText("Fecha de inicio");
        RoomDataDialog.getContentPane().add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, 20));

        jButton11.setText("Cancelar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        RoomDataDialog.getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, -1, -1));

        SendDataToBill.setText("Finalizar");
        SendDataToBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendDataToBillActionPerformed(evt);
            }
        });
        RoomDataDialog.getContentPane().add(SendDataToBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, -1, -1));
        RoomDataDialog.getContentPane().add(RoomCodeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 210, -1));

        DaysSliderRoom.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                DaysSliderRoomStateChanged(evt);
            }
        });
        RoomDataDialog.getContentPane().add(DaysSliderRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 210, -1));
        RoomDataDialog.getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 210, -1));
        RoomDataDialog.getContentPane().add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 210, -1));

        DaysRoom.setText("0 dias");
        RoomDataDialog.getContentPane().add(DaysRoom, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, -1, -1));

        jLabel76.setText("Codigo de habitacion");
        RoomDataDialog.getContentPane().add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, 20));

        RoomTypeCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Simple", "Matrimonial", "Familiar" }));
        RoomTypeCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomTypeCBActionPerformed(evt);
            }
        });
        RoomDataDialog.getContentPane().add(RoomTypeCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 210, -1));

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
        LateralPanel.add(AboutB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, 140, -1));
        LateralPanel.add(LogoLateralPanelL, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 260, 260));

        RoomsButton.setBackground(new java.awt.Color(73, 114, 116));
        RoomsButton.setForeground(new java.awt.Color(255, 255, 255));
        RoomsButton.setText("Alquilar");
        RoomsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomsButtonActionPerformed(evt);
            }
        });
        LateralPanel.add(RoomsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 290, -1));

        CleanningB.setBackground(new java.awt.Color(73, 114, 116));
        CleanningB.setForeground(new java.awt.Color(255, 255, 255));
        CleanningB.setText("Limpieza");
        CleanningB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CleanningBActionPerformed(evt);
            }
        });
        LateralPanel.add(CleanningB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 290, -1));

        SellsB.setBackground(new java.awt.Color(73, 114, 116));
        SellsB.setForeground(new java.awt.Color(255, 255, 255));
        SellsB.setText("Facturacion");
        SellsB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SellsBActionPerformed(evt);
            }
        });
        LateralPanel.add(SellsB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 290, -1));

        FormB.setBackground(new java.awt.Color(73, 114, 116));
        FormB.setForeground(new java.awt.Color(255, 255, 255));
        FormB.setText("Proforma");
        LateralPanel.add(FormB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 290, -1));

        AdditionalsB.setBackground(new java.awt.Color(73, 114, 116));
        AdditionalsB.setForeground(new java.awt.Color(255, 255, 255));
        AdditionalsB.setText("Productos adicionales");
        AdditionalsB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdditionalsBActionPerformed(evt);
            }
        });
        LateralPanel.add(AdditionalsB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, 290, -1));

        SwitchAccountB.setBackground(new java.awt.Color(73, 114, 116));
        SwitchAccountB.setForeground(new java.awt.Color(255, 255, 255));
        SwitchAccountB.setText("Logout");
        SwitchAccountB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SwitchAccountBActionPerformed(evt);
            }
        });
        LateralPanel.add(SwitchAccountB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 290, -1));

        DocumentationB.setBackground(new java.awt.Color(73, 114, 116));
        DocumentationB.setForeground(new java.awt.Color(255, 255, 255));
        DocumentationB.setText("Documentacion");
        DocumentationB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DocumentationBActionPerformed(evt);
            }
        });
        LateralPanel.add(DocumentationB, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 620, 120, -1));

        InformationB.setBackground(new java.awt.Color(73, 114, 116));
        InformationB.setForeground(new java.awt.Color(255, 255, 255));
        InformationB.setText("Informacion");
        InformationB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InformationBActionPerformed(evt);
            }
        });
        LateralPanel.add(InformationB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 290, -1));

        getContentPane().add(LateralPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 1080));

        MainPanel.setMinimumSize(new java.awt.Dimension(1016, 698));
        MainPanel.setPreferredSize(new java.awt.Dimension(1016, 698));
        MainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        MainPanel.add(LogoL, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 350, 350));

        RentB.setBackground(new java.awt.Color(73, 114, 116));
        RentB.setForeground(new java.awt.Color(255, 255, 255));
        RentB.setText("Alquilar");
        RentB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RentBActionPerformed(evt);
            }
        });
        MainPanel.add(RentB, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 410, 290, 100));

        getContentPane().add(MainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, -1, -1));

        RoomsPanel.setMaximumSize(new java.awt.Dimension(1016, 698));
        RoomsPanel.setMinimumSize(new java.awt.Dimension(1016, 698));
        RoomsPanel.setPreferredSize(new java.awt.Dimension(1016, 698));
        RoomsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setText("Habitaciones");
        RoomsPanel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, -1, -1));
        RoomsPanel.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 760, -1));

        jButton10.setText("Buscar");
        RoomsPanel.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 70, -1, -1));

        jLabel21.setText("Buscar Habitacion");
        RoomsPanel.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 20));

        PrintRoomsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton12.setText("Rentar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        PrintRoomsPanel.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 90, 20));

        jButton13.setText("Rentar");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        PrintRoomsPanel.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, 90, 20));

        jButton14.setText("Rentar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        PrintRoomsPanel.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 230, 90, 20));
        PrintRoomsPanel.add(jScrollBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, -2, 20, 950));

        jLabel45.setText("Disponibilidad");
        PrintRoomsPanel.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, -1, -1));

        jLabel60.setText("Tipo");
        PrintRoomsPanel.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 140, -1, -1));

        jLabel61.setText("Codigo");
        PrintRoomsPanel.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, -1, -1));

        jLabel62.setText("Image Reference");
        PrintRoomsPanel.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, 170, 100));

        jLabel63.setText("Image Reference");
        PrintRoomsPanel.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 170, 100));

        jLabel64.setText("Image Reference");
        PrintRoomsPanel.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 170, 100));

        jLabel65.setText("Tipo");
        PrintRoomsPanel.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 140, 30, -1));

        jLabel66.setText("Tipo");
        PrintRoomsPanel.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, -1, -1));

        jLabel67.setText("Codigo");
        PrintRoomsPanel.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, -1, -1));

        jLabel68.setText("Codigo");
        PrintRoomsPanel.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, -1, -1));

        jLabel69.setText("Disponibilidad");
        PrintRoomsPanel.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));

        jLabel70.setText("Disponibilidad");
        PrintRoomsPanel.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, -1, -1));

        FRT1.setText("Familiar");
        PrintRoomsPanel.add(FRT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 140, -1, -1));

        SRD1.setText("Disponible");
        PrintRoomsPanel.add(SRD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, -1, -1));

        MRT1.setText("Matrimonial");
        PrintRoomsPanel.add(MRT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, -1, -1));

        SRT1.setText("Simple");
        PrintRoomsPanel.add(SRT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, -1, -1));

        FRD1.setText("Diponible");
        PrintRoomsPanel.add(FRD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 200, -1, -1));

        SRC1.setText("a123");
        PrintRoomsPanel.add(SRC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, -1, -1));

        MRC1.setText("b123");
        PrintRoomsPanel.add(MRC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, -1, -1));

        MRD1.setText("Disponible");
        PrintRoomsPanel.add(MRD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, -1, -1));

        FRC1.setText("c123");
        PrintRoomsPanel.add(FRC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 170, -1, -1));

        RoomsPanel.add(PrintRoomsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 880, 530));

        getContentPane().add(RoomsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 1570, 1080));

        InformationPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SimpleRoomL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Matrimonial.png"))); // NOI18N
        SimpleRoomL.setText("exampleimage3");
        InformationPanel.add(SimpleRoomL, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, 220, 220));

        SimpleRoomL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Personal.png"))); // NOI18N
        SimpleRoomL1.setText("exampleimage1");
        InformationPanel.add(SimpleRoomL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 220, 220));

        SimpleRoomL2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Familiar.png"))); // NOI18N
        SimpleRoomL2.setText("exampleimage2");
        InformationPanel.add(SimpleRoomL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 220, 220));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("30$ - 40$ USD/noche");
        InformationPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, -1, -1));

        jButton1.setText("Cotizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        InformationPanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 520, -1, -1));

        jButton2.setText("Cotizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        InformationPanel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 520, -1, -1));

        jButton3.setText("Cotizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        InformationPanel.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 520, -1, -1));

        SI5.setText("-Television por cable");
        InformationPanel.add(SI5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 470, -1, -1));

        PersonalRoomL.setText("Habitacion Simple - Personal");
        InformationPanel.add(PersonalRoomL, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, -1, -1));

        SI1.setText("-1 Cama individual");
        InformationPanel.add(SI1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, -1, -1));

        SI2.setText("-Balcon con vista al mar");
        InformationPanel.add(SI2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, -1, -1));

        SI3.setText("-Wifi");
        InformationPanel.add(SI3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 430, -1, -1));

        SI4.setText("-Agua caliente");
        InformationPanel.add(SI4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, -1, -1));

        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel84.setText("80$ - 100$ USD/noche");
        InformationPanel.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 350, -1, -1));

        FI6.setText("-Opcion a cama adicional");
        InformationPanel.add(FI6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 490, -1, -1));

        FamiliarRoomL.setText("Habitacion Familiar");
        InformationPanel.add(FamiliarRoomL, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 320, -1, -1));

        FI1.setText("-1 Cama matrimonial y 2 camas personales");
        InformationPanel.add(FI1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 390, -1, -1));

        FI2.setText("-Balcon con vista al mar");
        InformationPanel.add(FI2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 410, -1, -1));

        FI3.setText("-Wifi");
        InformationPanel.add(FI3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 430, -1, -1));

        FI4.setText("-Agua caliente");
        InformationPanel.add(FI4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 450, -1, -1));

        FI5.setText("-Television por cable");
        InformationPanel.add(FI5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 470, -1, -1));

        jLabel92.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel92.setText("60$ - 70$ USD/noche");
        InformationPanel.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, -1, -1));

        MI6.setText("-Area de estar");
        InformationPanel.add(MI6, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 490, -1, -1));

        MatrimonialRoomL.setText("Habitacion Matrimonial");
        InformationPanel.add(MatrimonialRoomL, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 320, -1, -1));

        MI1.setText("-1 Cama matrimonial");
        InformationPanel.add(MI1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 390, -1, -1));

        MI2.setText("-Balcon con vista al mar");
        InformationPanel.add(MI2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 410, -1, -1));

        MI3.setText("-Wifi");
        InformationPanel.add(MI3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 430, -1, -1));

        MI4.setText("-Agua caliente");
        InformationPanel.add(MI4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 450, -1, -1));

        MI5.setText("-Television por cable");
        InformationPanel.add(MI5, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 470, -1, -1));

        getContentPane().add(InformationPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 1570, 1080));

        CleaningPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("Limpieza");
        CleaningPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, -1));
        CleaningPanel.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 760, -1));

        jButton4.setText("Buscar");
        CleaningPanel.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 60, -1, -1));

        jLabel8.setText("Busqueda");
        CleaningPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 20));

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

        CleaningPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 960, -1));

        jButton33.setText("Asignar limpieza");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        CleaningPanel.add(jButton33, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 590, -1, -1));

        getContentPane().add(CleaningPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 1570, 1080));

        FacturationPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setText("Factura");
        FacturationPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, -1, -1));

        SearchTF.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                SearchTFCaretUpdate(evt);
            }
        });
        SearchTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchTFActionPerformed(evt);
            }
        });
        FacturationPanel.add(SearchTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 580, -1));

        jButton5.setText("Buscar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        FacturationPanel.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 70, -1, -1));

        jLabel10.setText("Busqueda");
        FacturationPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, -1, 20));

        BillDescriptionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Tipo de habitacion", "Tiempo de estadia", "Precio por noche", "Precio total"
            }
        ));
        jScrollPane2.setViewportView(BillDescriptionTable);

        FacturationPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 950, 310));

        jButton6.setText("Añadir");
        FacturationPanel.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 240, -1, -1));

        jButton7.setText("Cancelar");
        FacturationPanel.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 700, -1, -1));

        jButton8.setText("Registrar factura");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        FacturationPanel.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 700, -1, -1));

        jButton9.setText("Editar");
        FacturationPanel.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 240, -1, -1));

        jLabel16.setText("IVA 15%");
        FacturationPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 630, -1, 20));

        jLabel17.setText("Subtotal");
        FacturationPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 600, -1, 20));
        FacturationPanel.add(FacturationSearchValidation, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 580, 20));

        jLabel77.setText("Total");
        FacturationPanel.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 660, -1, 20));

        TotalTF.setEditable(false);
        FacturationPanel.add(TotalTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 660, 40, -1));

        SubtotalTF.setEditable(false);
        FacturationPanel.add(SubtotalTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 600, 40, -1));

        IVATF.setEditable(false);
        FacturationPanel.add(IVATF, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 630, 40, -1));

        DataUsersPanel.setBackground(new java.awt.Color(255, 255, 255));
        DataUsersPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setText("Cliente");
        DataUsersPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel12.setText("Cedula");
        DataUsersPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        jLabel13.setText("Telefono");
        DataUsersPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, -1, -1));

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Correo electronico");
        DataUsersPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, -1));

        jLabel15.setText("Forma de pago");
        DataUsersPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));
        DataUsersPanel.add(PhoneTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 280, 20));
        DataUsersPanel.add(NameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 310, 20));
        DataUsersPanel.add(IDTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 310, 20));

        EmailTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailTFActionPerformed(evt);
            }
        });
        DataUsersPanel.add(EmailTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 280, 20));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Transferencia Bancaria", "Tarjeta de credito-debito" }));
        DataUsersPanel.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 270, -1));

        FacturationPanel.add(DataUsersPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 840, 110));

        getContentPane().add(FacturationPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 1570, 1080));

        AdditionalsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setText("Agregar productos a habitaciones");
        AdditionalsPanel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));
        AdditionalsPanel.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 760, -1));

        jButton31.setText("Buscar");
        AdditionalsPanel.add(jButton31, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 80, -1, -1));

        jLabel23.setText("Busqueda");
        AdditionalsPanel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, 20));

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

        AdditionalsPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 930, 310));

        jButton32.setText("Añadir producto");
        AdditionalsPanel.add(jButton32, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 510, -1, -1));

        getContentPane().add(AdditionalsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 1570, 1080));

        AboutPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setText("Acerca de");
        AboutPanel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, -1, -1));

        jLabel28.setText("Developed by;");
        AboutPanel.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, -1, -1));

        jLabel29.setText("Designed by:");
        AboutPanel.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 210, -1, -1));

        jLabel30.setText("Version");
        AboutPanel.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 240, -1, -1));

        jLabel31.setText("Contact & Support ");
        AboutPanel.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, -1, -1));

        jLabel32.setText("099999999/0222222222");
        AboutPanel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 270, -1, -1));

        jLabel33.setText("Edith Chuico, Adonis Alegria, Kennet Risco");
        AboutPanel.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, -1, -1));

        jLabel34.setText("Kennet Risco");
        AboutPanel.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, -1, -1));

        jLabel35.setText("1.1.1.1");
        AboutPanel.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 240, -1, -1));

        jLabel78.setText("Directed by:");
        AboutPanel.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, -1, -1));

        jLabel79.setText("Edith Chuico");
        AboutPanel.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, -1, -1));

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

        ManualPanel.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 1020, 560));

        jLabel36.setText("Documentacion");
        ManualPanel.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, -1));

        getContentPane().add(ManualPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 1570, 1080));

        LateralLoginPanel.setBackground(new java.awt.Color(177, 203, 192));
        LateralLoginPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LoginButon.setBackground(new java.awt.Color(73, 114, 116));
        LoginButon.setForeground(new java.awt.Color(255, 255, 255));
        LoginButon.setText("Login");
        LoginButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButonActionPerformed(evt);
            }
        });
        LateralLoginPanel.add(LoginButon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 260, -1));
        LateralLoginPanel.add(LogoLateralPanelL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 260, 260));

        DocumentationB1.setBackground(new java.awt.Color(73, 114, 116));
        DocumentationB1.setForeground(new java.awt.Color(255, 255, 255));
        DocumentationB1.setText("Documentacion");
        DocumentationB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DocumentationB1ActionPerformed(evt);
            }
        });
        LateralLoginPanel.add(DocumentationB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 260, -1));

        AboutB2.setBackground(new java.awt.Color(73, 114, 116));
        AboutB2.setForeground(new java.awt.Color(255, 255, 255));
        AboutB2.setText("Acerca de");
        AboutB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutB2ActionPerformed(evt);
            }
        });
        LateralLoginPanel.add(AboutB2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 260, -1));

        getContentPane().add(LateralLoginPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 698));

        LoginPanel.setMaximumSize(new java.awt.Dimension(1016, 698));
        LoginPanel.setMinimumSize(new java.awt.Dimension(1016, 698));
        LoginPanel.setPreferredSize(new java.awt.Dimension(1016, 698));
        LoginPanel.setVerifyInputWhenFocusTarget(false);
        LoginPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setText("Iniciar Sesion");
        LoginPanel.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, -1, -1));

        jLabel38.setText("CI");
        LoginPanel.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, -1, -1));

        jLabel39.setText("Contraseña");
        LoginPanel.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, -1, -1));

        LoginPasswordTF.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                LoginPasswordTFCaretUpdate(evt);
            }
        });
        LoginPanel.add(LoginPasswordTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 590, -1));

        LoginUserTF.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                LoginUserTFCaretUpdate(evt);
            }
        });
        LoginUserTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginUserTFActionPerformed(evt);
            }
        });
        LoginPanel.add(LoginUserTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 590, -1));

        SingInB.setText("Iniciar sesion");
        SingInB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SingInBActionPerformed(evt);
            }
        });
        LoginPanel.add(SingInB, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 370, -1, -1));
        LoginPanel.add(LoginValidation1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 590, 20));
        LoginPanel.add(LoginValidation2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 590, 20));

        KeepLogin.setText("Mantener sesion iniciada");
        KeepLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeepLoginActionPerformed(evt);
            }
        });
        LoginPanel.add(KeepLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 350, 160, -1));

        getContentPane().add(LoginPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 1016, 698));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RoomsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomsButtonActionPerformed
        // TODO add your handling code here:
        selectedButton(1);
    }//GEN-LAST:event_RoomsButtonActionPerformed

    private void InformationBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InformationBActionPerformed
        // TODO add your handling code here:
        selectedButton(2);
    }//GEN-LAST:event_InformationBActionPerformed

    private void CleanningBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CleanningBActionPerformed
        // TODO add your handling code here:
        selectedButton(3);
    }//GEN-LAST:event_CleanningBActionPerformed

    private void SellsBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SellsBActionPerformed
        // TODO add your handling code here:
        selectedButton(4);
        //LoadRoomCount = new JDialog(parent, "Modal Dialog", true);
        LoadRoomCount.setLocationRelativeTo(null);
        LoadRoomCount.setVisible(true);
    }//GEN-LAST:event_SellsBActionPerformed

    private void AdditionalsBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdditionalsBActionPerformed
        // TODO add your handling code here:
        selectedButton(5);
    }//GEN-LAST:event_AdditionalsBActionPerformed

    private void SwitchAccountBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SwitchAccountBActionPerformed
        int seleccion = JOptionPane.showConfirmDialog(
        this, // Componente padre, usualmente el JFrame o JDialog actual
        "¿Estás seguro que deseas cerrar la sesión actual?", 
        "Confirmación", 
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE // Icono de pregunta para indicar una pregunta al usuario
        );

        if (seleccion == JOptionPane.YES_OPTION) {
            // Borrar archivo de credenciales si existe
            File file = new File("credentials.json");
            if (file.exists()) {
                file.delete();
            }

            // Limpiar campos de texto y restaurar GUI por defecto
            LoginUserTF.setText("");
            LoginPasswordTF.setText("");
            setDefaultGUI(); // Supongo que esta función restaura la GUI por defecto

        } else {
            // Ocultar la ventana actual en lugar de cerrarla completamente
            this.setVisible(false);
        }       
    }//GEN-LAST:event_SwitchAccountBActionPerformed

    private void AboutBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutBActionPerformed
        // TODO add your handling code here
        selectedButton(6);
    }//GEN-LAST:event_AboutBActionPerformed

    private void DocumentationBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DocumentationBActionPerformed
        // TODO add your handling code here:
        selectedButton(7);
    }//GEN-LAST:event_DocumentationBActionPerformed

    private void SingInBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SingInBActionPerformed
        // código para validar credenciales
    Conection conection = new Conection();
    conection.conexion("usuarios");
    String userID = LoginUserTF.getText();
    String password = LoginPasswordTF.getText();
    String userVal = LoginValidation1.getText();
    String passwordVal = LoginValidation2.getText();

    if(userID.isEmpty() && password.isEmpty()){
        JOptionPane.showMessageDialog(null, "¡Los campos están vacíos!", "Campos vacíos", JOptionPane.ERROR_MESSAGE);
    } else {
        if(userVal.isEmpty() || passwordVal.isEmpty()){
            Document query = new Document("Cedula", userID);
            Document queryD = new Document("CódigoUsuario:", password);
            long count = conection.collection.countDocuments(query);
            long countD = conection.collection.countDocuments(queryD);

            if(count <= 0) {
                JOptionPane.showMessageDialog(null, "La cédula ingresada no está registrada", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                if(countD <= 0) {
                    JOptionPane.showMessageDialog(null, "La contraseña ingresada es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    // Recuperar datos del recepcionista
                    Document recepcionistaData = conection.collection.find(query).first();
                    if(recepcionistaData != null) {
                        String id = recepcionistaData.getString("Cedula");
                        String pass = recepcionistaData.getString("CódigoUsuario:");
                        String name = recepcionistaData.getString("Usuario:");

                        // Asignar datos al objeto recepcionista
                        recepcionista = new Worker(id, pass, name);
                        // dirigir al menú
                        
                        selectedButton(0);
                        checkKeepIn();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al recuperar los datos del recepcionista", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else {    
            JOptionPane.showMessageDialog(null, "Los campos tienen errores, corrígelos y vuelve a intentarlo", "Campos erróneos", JOptionPane.ERROR_MESSAGE);
        }
    }                            
    }//GEN-LAST:event_SingInBActionPerformed
    public boolean checkKeepIn(){
        if(KeepLogin.isSelected()){
            //generar archivo de credenciales
            generateJSONTempCredentials();
            return true;
        }else{
            // Borrar el archivo de credenciales si existe
                File file = new File("credentials.json");
                if (file.exists()) {
                    file.delete();
                }
            return false;
        }
    }
    private void RentBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RentBActionPerformed
        // TODO add your handling code here:
        selectedButton(1);
    }//GEN-LAST:event_RentBActionPerformed

    private void LoginButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButonActionPerformed
        // TODO add your handling code here:
        selectedButton(8);
    }//GEN-LAST:event_LoginButonActionPerformed

    private void DocumentationB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DocumentationB1ActionPerformed
        // TODO add your handling code here:
        selectedButton(10);
    }//GEN-LAST:event_DocumentationB1ActionPerformed

    private void AboutB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutB2ActionPerformed
        // TODO add your handling code here:
        selectedButton(9);
    }//GEN-LAST:event_AboutB2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:}
        
        QuoteDialog.setLocationRelativeTo(null);
        
        QuoteDialog.setVisible(true);
        setDataOnQuoute(1);
        System.out.println("boton1 presionado");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // TODO add your handling code here:
        AsignCleaningDialog.setLocationRelativeTo(null);
        AsignCleaningDialog.setVisible(true);
    }//GEN-LAST:event_jButton33ActionPerformed

    private void EmailTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailTFActionPerformed

    private void LoginUserTFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_LoginUserTFCaretUpdate
        String valueOfUser = LoginUserTF.getText();
        genericTextFieldValidationNumber(LoginValidation1,LoginUserTF,"¡La cedula esta vacia!","¡La cedula ingresada no es un numero","La cedula debe tener 10 digitos");
        
    }//GEN-LAST:event_LoginUserTFCaretUpdate

    private void LoginPasswordTFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_LoginPasswordTFCaretUpdate
        // TODO add your handling code here:
        String valueOfPassword = LoginPasswordTF.getText();
        genericTextFieldValidationString(LoginValidation2,LoginPasswordTF,"¡Ingresa tu contraseña!");
    }//GEN-LAST:event_LoginPasswordTFCaretUpdate

    private void SearchTFCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_SearchTFCaretUpdate
        // TODO add your handling code here:
        String valueOfFind = SearchTF.getText();
        genericTextFieldValidationNumber(FacturationSearchValidation,SearchTF,"¡No hay nada para buscar!","¡La cedula ingresada no es un numero","La cedula debe tener 10 digitos");
        FacturationSearchValidation.setText("Mostrando resultados para "+valueOfFind);
    }//GEN-LAST:event_SearchTFCaretUpdate

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        //NOTA: Implementar la busqueda por correo y nombre
        Conection conection = new Conection();
        conection.conexion("RegistroClientes");
        String userID = SearchTF.getText();
        String val = FacturationSearchValidation.getText();
        org.bson.Document query = new  org.bson.Document("Cedula", userID);
        long count= conection.collection.countDocuments(query);
        if(userID.length()!=10 ){
            FacturationSearchValidation.setText("La cedula debe tener 10 digitos!");
            return;
        }else{
            if(count<=0){
                JOptionPane.showMessageDialog(null, "La cedula ingresada no esta registrada", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else{
                FindIterable< org.bson.Document> documents = conection.collection.find(query);
                if (documents.iterator().hasNext()) {
                    for (org.bson.Document document : documents) {
                        NameTF.setText(document.getString("Nombre"));
                        PhoneTF.setText(document.getString("Teléfono"));
                        IDTF.setText(document.getString("Cedula"));
                    }
                    SearchTF.setText("Mostrando resultados para "+userID);
                }
            }}  // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        
    }//GEN-LAST:event_jButton8ActionPerformed
    
    public void genericRoomData(){
        String nombre= NameTF.getText();
        String telefono= PhoneTF.getText();
        String cedu= IDTF.getText();
        String correo= EmailTF.getText();
        crearPDF c=new crearPDF();
        c.guardarFacBD(nombre, telefono, cedu, correo);
        c.crearPDF(RoomCodeTF.getText(), RoomTypeCB.getSelectedItem().toString(),  DaysSliderRoom.getValue(), 0.0,1, BillDescriptionTable);
        JOptionPane.showMessageDialog(this, "Agregado", "Exito", JOptionPane.INFORMATION_MESSAGE);
    }
    private void DaysStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_DaysStateChanged
        // TODO add your handling code here:
        int actualValue = Days.getValue();       
        String valueS = actualValue+"";
        SliderDaysInfo.setText(valueS);
        Nights.setText(valueS);
        int roomprice = Integer.parseInt(RoomPrice.getText());
        int nights = Integer.parseInt(Nights.getText());
        int finalValue = roomprice*nights;
        FinalPrice.setText(finalValue+"");
    }//GEN-LAST:event_DaysStateChanged

    private void DaysSliderRoomStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_DaysSliderRoomStateChanged
        // TODO add your handling code here:
        int actualValue = DaysSliderRoom.getValue();
        String valueS = actualValue+"";
        DaysRoom.setText(valueS+" dias");
    }//GEN-LAST:event_DaysSliderRoomStateChanged

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        RoomDataDialog.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        invoqueRoomDataDialog();
        setSelectedRoomData(SRT1,SRC1);
        genericRoomData();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void SendDataToBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendDataToBillActionPerformed
        // TODO add your handling code here:
        String roomCode= RoomCodeTF.getText();
        int days = DaysSliderRoom.getValue();   
        String roomType = RoomTypeCB.getSelectedItem().toString();
        agregarDatosFa agg=new agregarDatosFa();
        agg.agregarDatosTabla(roomCode,roomType, days,BillDescriptionTable, SubtotalTF, TotalTF,IVATF);
    }//GEN-LAST:event_SendDataToBillActionPerformed

    private void LoginUserTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginUserTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LoginUserTFActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        invoqueRoomDataDialog();
        setSelectedRoomData(MRT1,MRC1);
        genericRoomData();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        invoqueRoomDataDialog();
        setSelectedRoomData(FRT1,FRC1);
        genericRoomData();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void SearchTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchTFActionPerformed

    private void KeepLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeepLoginActionPerformed

    }//GEN-LAST:event_KeepLoginActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        QuoteDialog.setLocationRelativeTo(null);
        QuoteDialog.setVisible(true);
        setDataOnQuoute(2);
        System.out.println("boton2 presionado");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void RoomTypeProformActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomTypeProformActionPerformed
        // TODO add your handling code here:
        if(RoomTypeProform.getSelectedIndex()==0){
            setDataOnQuoute(1);
        }else if(RoomTypeProform.getSelectedIndex()==1){
            setDataOnQuoute(2);
        }else if(RoomTypeProform.getSelectedIndex()==2){
            setDataOnQuoute(3);
        }
    }//GEN-LAST:event_RoomTypeProformActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        QuoteDialog.setLocationRelativeTo(null);
        QuoteDialog.setVisible(true);
        setDataOnQuoute(3);
        System.out.println("boton1 presionado");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void RoomTypeCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomTypeCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RoomTypeCBActionPerformed

    private void RoomPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RoomPriceActionPerformed

    private void FinalPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinalPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FinalPriceActionPerformed
    
    public void generateJSONTempCredentials(){
        // Crear un objeto JSON para almacenar las credenciales
        JSONObject credentials = new JSONObject();
        credentials.put("ID", recepcionista.getID());
        credentials.put("password", recepcionista.getPassword());
        credentials.put("name", recepcionista.getName());

        // Guardar las credenciales en un archivo JSON
        try (FileWriter file = new FileWriter("credentials.json")) {
            file.write(credentials.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadCredentials() {
        JSONParser parser = new JSONParser();
        File file = new File("credentials.json");

        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                JSONObject credentials = (JSONObject) parser.parse(reader);
                String ID = (String) credentials.get("ID");
                String password = (String) credentials.get("password");
                String name = (String) credentials.get("name");

                // Crear el objeto Recepcionista con las credenciales guardadas
                recepcionista = new Worker(ID, password, name);

                // Rellenar los campos de inicio de sesión con las credenciales guardadas
                LoginUserTF.setText(ID);
                LoginPasswordTF.setText(password);
                KeepLogin.setSelected(true);

                // Simular clic en el botón de inicio de sesión
                SingInB.doClick();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
    }
    public void setDataOnQuoute(int caso){
        //establecer informacion en los campos
        switch(caso){
            case 1:
                RoomTypeProform.setSelectedIndex(0);
                rsscalelabel.RSScaleLabel.setScaleLabel(ImagePreviewProform, "src/imagenes/Personal.png");
                RoomNameProform.setText(PersonalRoomL.getText());
                D.setText(SI1.getText()+"\n"+SI2.getText()+"\n"+SI3.getText()+"\n"+SI4.getText()+"\n"+SI5.getText());
                RoomPrice.setText("30");
                break;
            case 2:
                RoomTypeProform.setSelectedIndex(1);
                rsscalelabel.RSScaleLabel.setScaleLabel(ImagePreviewProform, "src/imagenes/Familiar.png");
                RoomNameProform.setText(FamiliarRoomL.getText());
                D.setText(FI1.getText()+"\n"+FI2.getText()+"\n"+FI3.getText()+"\n"+FI4.getText()+"\n"+FI5.getText()+"\n"+FI6.getText());
                RoomPrice.setText("80");
                break;             
            case 3:
                RoomTypeProform.setSelectedIndex(2);
                rsscalelabel.RSScaleLabel.setScaleLabel(ImagePreviewProform, "src/imagenes/Matrimonial.png");
                RoomNameProform.setText(MatrimonialRoomL.getText());
                D.setText(MI1.getText()+"\n"+MI2.getText()+"\n"+MI3.getText()+"\n"+MI4.getText()+"\n"+MI5.getText()+"\n"+MI6.getText());
                RoomPrice.setText("60");
                break;
            default:
        }
    }
    public void invoqueRoomDataDialog(){
        RoomDataDialog.setVisible(true);
        RoomDataDialog.setLocationRelativeTo(null);
    }
    
    public void genericTextFieldValidationNumber(JLabel label,JTextField textField,String empyfield,String notNumber,String outoflenght){
        String valueOfTextField = textField.getText();
        long number;
        if (valueOfTextField.isEmpty()){
            label.setText(empyfield);
        }else if(!isNumber(valueOfTextField)){
            label.setText(notNumber);           
        } else {
            if(valueOfTextField.length()!=10){
                label.setText(outoflenght);
            }else{
                try {
                    number = Long.parseLong(valueOfTextField);
                    label.setText("");
                } catch (NumberFormatException e) {
                    label.setText("¡Error al convertir a número!");
                }
            }           
        }
    }
    
    public void genericTextFieldValidationString(JLabel label,JTextField textField,String empyfield){
        String valueOfTextField = textField.getText();
        if (valueOfTextField.isEmpty()) {
            label.setText(empyfield);
        } else {
            label.setText("");
        }
    }
    
    public boolean isNumber(String arg){
        try{
            Integer.parseInt(arg);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    
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
                ManualPanel.setVisible(false);
                break;

            case 7:
                // documentacion
                LateralPanel.setVisible(true);
                ManualPanel.setVisible(true);
                break;

            case 8:
                // cambiar de cuenta
                LateralLoginPanel.setVisible(true);
                LoginPanel.setVisible(true);
                ManualPanel.setVisible(false);
                break;

            case 9:
                // ir al panel de about y volver al login
                LateralLoginPanel.setVisible(true);
                AboutPanel.setVisible(true);
                ManualPanel.setVisible(false);
                break;
                
            case 10:
                // ir al panel de manual y volver al login
                LateralLoginPanel.setVisible(true);
                ManualPanel.setVisible(true);
                AboutPanel.setVisible(false);
                LoginPanel.setVisible(false);
                break;
                
            case 11:
                ManualPanel.setVisible(true);
                AboutPanel.setVisible(false);
                
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
    
    public void setSelectedRoomData(JLabel roomtypelabel,JLabel roomcode){
        String roomtype = roomtypelabel.getText();
        if(roomtype.equals("Simple")){
            RoomTypeCB.setSelectedIndex(0);
        }else if(roomtype.equals("Matrimonial")){
            RoomTypeCB.setSelectedIndex(1);
        }else if(roomtype.equals("Familiar")){
            RoomTypeCB.setSelectedIndex(2);
        }
    }
    
    public void un_lockPanel(JPanel panel,boolean bloquear){
        Component[] components = panel.getComponents();
        if(bloquear){
            for (Component component : components) {
                component.setEnabled(true);
            }
        }else{
            for (Component component : components) {
                component.setEnabled(false);
            }
        }
        
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
    private javax.swing.JButton AboutB2;
    private javax.swing.JPanel AboutPanel;
    private javax.swing.JButton AdditionalsB;
    private javax.swing.JPanel AdditionalsPanel;
    private javax.swing.JDialog AsignCleaningDialog;
    private com.toedter.calendar.JDateChooser BeginingDate;
    private javax.swing.JTable BillDescriptionTable;
    private javax.swing.JPanel CleaningPanel;
    private javax.swing.JButton CleanningB;
    private javax.swing.JTextPane D;
    private javax.swing.JPanel DataUsersPanel;
    private javax.swing.JSlider Days;
    private javax.swing.JLabel DaysRoom;
    private javax.swing.JSlider DaysSliderRoom;
    private javax.swing.JButton DocumentationB;
    private javax.swing.JButton DocumentationB1;
    private javax.swing.JTextField EmailTF;
    private javax.swing.JLabel FI1;
    private javax.swing.JLabel FI2;
    private javax.swing.JLabel FI3;
    private javax.swing.JLabel FI4;
    private javax.swing.JLabel FI5;
    private javax.swing.JLabel FI6;
    private javax.swing.JLabel FRC1;
    private javax.swing.JLabel FRD1;
    private javax.swing.JLabel FRT1;
    private javax.swing.JPanel FacturationPanel;
    private javax.swing.JLabel FacturationSearchValidation;
    private javax.swing.JLabel FamiliarRoomL;
    private javax.swing.JTextField FinalPrice;
    private javax.swing.JButton FormB;
    private javax.swing.JPanel HourComponent;
    private javax.swing.JTextField IDTF;
    private javax.swing.JTextField IVATF;
    private javax.swing.JLabel ImagePreviewProform;
    private javax.swing.JButton InformationB;
    private javax.swing.JPanel InformationPanel;
    private javax.swing.JCheckBox KeepLogin;
    private javax.swing.JPanel LateralLoginPanel;
    private javax.swing.JPanel LateralPanel;
    private javax.swing.JDialog LoadRoomCount;
    private javax.swing.JButton LoginButon;
    private javax.swing.JPanel LoginPanel;
    private javax.swing.JTextField LoginPasswordTF;
    private javax.swing.JTextField LoginUserTF;
    private javax.swing.JLabel LoginValidation1;
    private javax.swing.JLabel LoginValidation2;
    private javax.swing.JLabel LogoL;
    private javax.swing.JLabel LogoLateralPanelL;
    private javax.swing.JLabel LogoLateralPanelL1;
    private javax.swing.JLabel MI1;
    private javax.swing.JLabel MI2;
    private javax.swing.JLabel MI3;
    private javax.swing.JLabel MI4;
    private javax.swing.JLabel MI5;
    private javax.swing.JLabel MI6;
    private javax.swing.JLabel MRC1;
    private javax.swing.JLabel MRD1;
    private javax.swing.JLabel MRT1;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel ManualPanel;
    private javax.swing.JLabel MatrimonialRoomL;
    private javax.swing.JTextField NameTF;
    private javax.swing.JTextField Nights;
    private com.toedter.calendar.JDateChooser OutDate;
    private javax.swing.JLabel PersonalRoomL;
    private javax.swing.JTextField PhoneTF;
    private javax.swing.JPanel PrintRoomsPanel;
    private javax.swing.JDialog QuoteDialog;
    private javax.swing.JButton RentB;
    private javax.swing.JTextField RoomCodeTF;
    private javax.swing.JDialog RoomDataDialog;
    private javax.swing.JLabel RoomNameProform;
    private javax.swing.JTextField RoomPrice;
    private javax.swing.JComboBox<String> RoomTypeCB;
    private javax.swing.JComboBox<String> RoomTypeProform;
    private javax.swing.JButton RoomsButton;
    private javax.swing.JPanel RoomsPanel;
    private javax.swing.JLabel SI1;
    private javax.swing.JLabel SI2;
    private javax.swing.JLabel SI3;
    private javax.swing.JLabel SI4;
    private javax.swing.JLabel SI5;
    private javax.swing.JLabel SRC1;
    private javax.swing.JLabel SRD1;
    private javax.swing.JLabel SRT1;
    private javax.swing.JTextField SearchTF;
    private javax.swing.JButton SellsB;
    private javax.swing.JButton SendDataToBill;
    private javax.swing.JLabel SimpleRoomL;
    private javax.swing.JLabel SimpleRoomL1;
    private javax.swing.JLabel SimpleRoomL2;
    private javax.swing.JButton SingInB;
    private javax.swing.JLabel SliderDaysInfo;
    private javax.swing.JTextField SubtotalTF;
    private javax.swing.JButton SwitchAccountB;
    private javax.swing.JTextField TotalTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
