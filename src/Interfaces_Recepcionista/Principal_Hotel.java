package Interfaces_Recepcionista;

import Actors.Client;
import Actors.Habitacion;
import Actors.Worker;
import BD_Facturas.crearPDF;
import BD_Facturas.crearPDFProforma;
import BD_Rooms.Conection;
import BD_Rooms.Create;
import BD_Rooms.LoadData;
import BD_Rooms.Update;
import BD_Users.ConectionUsers;
import BD_Users.LoadClient;
import BD_Users.Regist;
import Clases.agregarDatosFa;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.awt.Color;
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
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

//NOTA: IMPLEMENTAR CAMBIO DE RESOLUCIONES AUTOMATICA, actual 1366,768
//NOTA: Los labels de las habitaciones en el panel RoomsPanel se nombran de la siguiente manera S,M,F+R+T,C,D+N. Donde SMF significa: Simple, Matrimonial, Familiar. Y R significa Room. Y TCD significa: Tipo, Codigo, Disponibilidad, y N es el numero de habitacion
public class Principal_Hotel extends javax.swing.JFrame {
    
    ImageIcon icon = new ImageIcon("src/Imagenes/HotelSantanderIcon.png");
    int userID;
    Worker recepcionista;
    List <Habitacion> habitaciones;
    List <Habitacion> habitacionesSeleccionadas;
    List <Client> clientes;
    DefaultTableModel modelFactura = new DefaultTableModel();
    DefaultTableModel ModelProforma = new DefaultTableModel();
    DefaultTableModel modelAllRoomData = new DefaultTableModel();
    String valueOfFindFilter;
    
    public Principal_Hotel() {
        initComponents();        
        setDefaultGUI();
        loadCredentials();
        initializeDateChoosers();
        //PrintAvailableRoomsOnList();
        habitacionesSeleccionadas = new ArrayList<>();
        modelFactura.addColumn("Codigo");
        modelFactura.addColumn("Tipo de habitacion");
        modelFactura.addColumn("Tiempo de estadia");
        modelFactura.addColumn("Precio por noche");
        modelFactura.addColumn("Precio total");
        
        ModelProforma.addColumn("Codigo");
        ModelProforma.addColumn("Tipo de habitacion");
        ModelProforma.addColumn("Tiempo de estadia");
        ModelProforma.addColumn("Precio por noche");
        ModelProforma.addColumn("Precio total");
        
        ProformTable.setModel(ModelProforma);
        
        
        modelAllRoomData.addColumn("Codigo");
        modelAllRoomData.addColumn("Tipo");
        modelAllRoomData.addColumn("Disponibilidad");
        modelAllRoomData.addColumn("Precio");
        modelAllRoomData.addColumn("Limpieza");
        modelAllRoomData.addColumn("Fecha de entrada");
        modelAllRoomData.addColumn("Fecha de salida");
        modelAllRoomData.addColumn("Dias totales rentados");
        
        AvaliableRoomsTable.setModel(modelAllRoomData);
        
        BillDescriptionTable.setModel(modelFactura);
        
        Conection Conexion = new Conection();
        Conexion.conexion("Habitaciones");
        //555555555555555555555555555555555555555555555555555555555555555555
        habitaciones = new ArrayList<>();
        LoadData cargarHabitaciones = new LoadData();
        List<Habitacion> listaHabitaciones = cargarHabitaciones.loadData(Conexion);
        habitaciones.addAll(listaHabitaciones);
        Create crear = new Create();
        crear.CreateDefaultRoomsCode(Conexion);
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("Hotel Santander");
        rsscalelabel.RSScaleLabel.setScaleLabel(LogoLateralPanelL, "src/imagenes/HotelSantanderIcon.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(LogoL, "src/imagenes/HotelSantanderIcon.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(LogoLateralPanelL1, "src/imagenes/HotelSantanderIcon.png");
        un_lockPanel(DataUsersPanel,false);
        un_lockPanel(DataUsersPanel1,false);
        this.setIconImage(icon.getImage());
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Fuentes/more-sugar.regular.ttf")).deriveFont(14f);
            setFontForComponents(getContentPane(), customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        setCodeOnRooms(1);
        setDataOnRoomPanels(1);
        updateRoomRentButtons();
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
    
    private void initializeDateChoosers() {
        // Añade un listener para DateBegin
        DateBegin.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    Date selectedDate = DateBegin.getDate();
                    if (selectedDate != null) {
                        // Establece la fecha mínima en DateEnd
                        DateEnd.setMinSelectableDate(selectedDate);

                        // Opcional: Si la fecha de finalización actual es anterior a la nueva fecha de inicio, ajusta la fecha de finalización
                        if (DateEnd.getDate() != null && DateEnd.getDate().before(selectedDate)) {
                            DateEnd.setDate(selectedDate);
                        }
                    }
                }
            }
        });

        // Inicialmente, establece la fecha mínima en DateEnd
        if (DateBegin.getDate() != null) {
            DateEnd.setMinSelectableDate(DateBegin.getDate());
        }

        // Añade un listener para ProformaINDATE
        ProformaINDATE.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    Date selectedDate = ProformaINDATE.getDate();
                    if (selectedDate != null) {
                        // Establece la fecha mínima en ProformaOUTDATE
                        ProformaOUTDATE.setMinSelectableDate(selectedDate);

                        // Opcional: Si la fecha de salida proforma actual es anterior a la nueva fecha de entrada proforma, ajusta la fecha de salida proforma
                        if (ProformaOUTDATE.getDate() != null && ProformaOUTDATE.getDate().before(selectedDate)) {
                            ProformaOUTDATE.setDate(selectedDate);
                        }
                    }
                }
            }
        });

        // Inicialmente, establece la fecha mínima en ProformaOUTDATE
        if (ProformaINDATE.getDate() != null) {
            ProformaOUTDATE.setMinSelectableDate(ProformaINDATE.getDate());
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
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        Nights = new javax.swing.JTextField();
        FinalPrice = new javax.swing.JTextField();
        RoomPrice = new javax.swing.JTextField();
        jButton36 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        D = new javax.swing.JTextPane();
        jLabel61 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        Name = new javax.swing.JTextField();
        Phone = new javax.swing.JTextField();
        ID = new javax.swing.JTextField();
        Mail = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        ProformaINDATE = new com.toedter.calendar.JDateChooser();
        ProformaOUTDATE = new com.toedter.calendar.JDateChooser();
        jScrollPane8 = new javax.swing.JScrollPane();
        RoomList = new javax.swing.JList<>();
        jButton13 = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
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
        searchRoomsField = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        AvaliableRoomsTable = new javax.swing.JTable();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        RoomDataDialog = new javax.swing.JDialog();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        SendDataToBill = new javax.swing.JButton();
        RoomTypeTF = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        DateEnd = new com.toedter.calendar.JDateChooser();
        DateBegin = new com.toedter.calendar.JDateChooser();
        RoomCodeTF2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton21 = new javax.swing.JButton();
        RoomInfoDialog = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        OutDate = new javax.swing.JTextField();
        Type = new javax.swing.JTextField();
        Code = new javax.swing.JTextField();
        Avaliability = new javax.swing.JTextField();
        TotalDays = new javax.swing.JTextField();
        EntryDate = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jButton22 = new javax.swing.JButton();
        jLabel80 = new javax.swing.JLabel();
        Price = new javax.swing.JTextField();
        LateralPanel = new javax.swing.JPanel();
        AboutB = new javax.swing.JButton();
        LogoLateralPanelL = new javax.swing.JLabel();
        RoomsButton = new javax.swing.JButton();
        CleanningB = new javax.swing.JButton();
        SellsB = new javax.swing.JButton();
        ProformaB = new javax.swing.JButton();
        SwitchAccountB = new javax.swing.JButton();
        DocumentationB = new javax.swing.JButton();
        InformationB = new javax.swing.JButton();
        MainPanel = new javax.swing.JPanel();
        LogoL = new javax.swing.JLabel();
        RentB = new javax.swing.JButton();
        RoomsPanel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        Room5Panel = new test.PanelRound();
        Room5Image = new javax.swing.JLabel();
        RoomType5 = new javax.swing.JLabel();
        RoomCode5 = new javax.swing.JLabel();
        RoomAvaliability5 = new javax.swing.JLabel();
        RoomRent5 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        Room1Panel = new test.PanelRound();
        Room1Image = new javax.swing.JLabel();
        RoomType1 = new javax.swing.JLabel();
        RoomCode1 = new javax.swing.JLabel();
        RoomAvaliability1 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        RoomRent1 = new javax.swing.JButton();
        Room2Panel = new test.PanelRound();
        Room2Image = new javax.swing.JLabel();
        RoomType2 = new javax.swing.JLabel();
        RoomCode2 = new javax.swing.JLabel();
        RoomAvaliability2 = new javax.swing.JLabel();
        RoomRent2 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        Room3Panel = new test.PanelRound();
        Room3Image = new javax.swing.JLabel();
        RoomType3 = new javax.swing.JLabel();
        RoomCode3 = new javax.swing.JLabel();
        RoomAvaliability3 = new javax.swing.JLabel();
        RoomRent3 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        Room4Panel = new test.PanelRound();
        Room4Image = new javax.swing.JLabel();
        RoomType4 = new javax.swing.JLabel();
        RoomCode4 = new javax.swing.JLabel();
        RoomAvaliability4 = new javax.swing.JLabel();
        RoomRent4 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        Room10Panel = new test.PanelRound();
        Room10Image = new javax.swing.JLabel();
        RoomType10 = new javax.swing.JLabel();
        RoomCode10 = new javax.swing.JLabel();
        RoomAvaliability10 = new javax.swing.JLabel();
        RoomRent10 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        Room6Panel = new test.PanelRound();
        Room6Image = new javax.swing.JLabel();
        RoomType6 = new javax.swing.JLabel();
        RoomCode6 = new javax.swing.JLabel();
        RoomAvaliability6 = new javax.swing.JLabel();
        RoomRent6 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        Room7Panel = new test.PanelRound();
        Room7Image = new javax.swing.JLabel();
        RoomType7 = new javax.swing.JLabel();
        RoomCode7 = new javax.swing.JLabel();
        RoomAvaliability7 = new javax.swing.JLabel();
        RoomRent7 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        Room8Panel = new test.PanelRound();
        Room8Image = new javax.swing.JLabel();
        RoomType8 = new javax.swing.JLabel();
        RoomCode8 = new javax.swing.JLabel();
        RoomAvaliability8 = new javax.swing.JLabel();
        RoomRent8 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        Room9Panel = new test.PanelRound();
        Room9Image = new javax.swing.JLabel();
        RoomType9 = new javax.swing.JLabel();
        RoomCode9 = new javax.swing.JLabel();
        RoomAvaliability9 = new javax.swing.JLabel();
        RoomRent9 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        RoomsTypeFilter = new javax.swing.JComboBox<>();
        jLabel60 = new javax.swing.JLabel();
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
        ProformPanel = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        SearchTF1 = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jLabel66 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ProformTable = new javax.swing.JTable();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        FacturationSearchValidation1 = new javax.swing.JLabel();
        DataUsersPanel1 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        PhoneTF1 = new javax.swing.JTextField();
        NameTF1 = new javax.swing.JTextField();
        IDTF1 = new javax.swing.JTextField();
        EmailTF1 = new javax.swing.JTextField();
        clientRegistVal = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        TotalTF1 = new javax.swing.JTextField();
        SubtotalTF1 = new javax.swing.JTextField();
        IVATF1 = new javax.swing.JTextField();
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
        QuoteDialog.setMaximumSize(new java.awt.Dimension(800, 530));
        QuoteDialog.setMinimumSize(new java.awt.Dimension(800, 530));
        QuoteDialog.setPreferredSize(new java.awt.Dimension(800, 530));
        QuoteDialog.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel37.setText("Proforma");
        jLabel37.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        QuoteDialog.getContentPane().add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 80, -1));

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
        QuoteDialog.getContentPane().add(RoomTypeProform, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 280, -1));

        jLabel43.setText("Datos de la habitacion");
        QuoteDialog.getContentPane().add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));

        jLabel46.setText("Fecha de salida");
        QuoteDialog.getContentPane().add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, -1, -1));

        jLabel47.setText("Fecha de ingreso");
        QuoteDialog.getContentPane().add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, -1, -1));

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

        jButton36.setText("Generar Proforma");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        QuoteDialog.getContentPane().add(jButton36, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 320, 140, -1));

        jButton38.setText("Cancelar");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        QuoteDialog.getContentPane().add(jButton38, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 400, 140, -1));

        jButton39.setText("Limpiar");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });
        QuoteDialog.getContentPane().add(jButton39, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 360, 140, -1));

        D.setEditable(false);
        jScrollPane7.setViewportView(D);

        QuoteDialog.getContentPane().add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 130, 120));

        jLabel61.setText("Correo");
        QuoteDialog.getContentPane().add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 60, 20));

        jLabel44.setText("Datos del cliente");
        QuoteDialog.getContentPane().add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, -1));

        Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameActionPerformed(evt);
            }
        });
        QuoteDialog.getContentPane().add(Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 150, -1));

        Phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhoneActionPerformed(evt);
            }
        });
        QuoteDialog.getContentPane().add(Phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 150, -1));

        ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDActionPerformed(evt);
            }
        });
        QuoteDialog.getContentPane().add(ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 150, -1));

        Mail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MailActionPerformed(evt);
            }
        });
        QuoteDialog.getContentPane().add(Mail, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 150, -1));

        jLabel62.setText("Habitaciones disponibles");
        QuoteDialog.getContentPane().add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, -1, 20));

        jLabel63.setText("Nombre");
        QuoteDialog.getContentPane().add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 60, 20));

        jLabel64.setText("Telefono");
        QuoteDialog.getContentPane().add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 60, 20));

        jLabel65.setText("Cedula");
        QuoteDialog.getContentPane().add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 60, 20));
        QuoteDialog.getContentPane().add(ProformaINDATE, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, 280, -1));
        QuoteDialog.getContentPane().add(ProformaOUTDATE, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 450, 280, -1));

        RoomList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane8.setViewportView(RoomList);

        QuoteDialog.getContentPane().add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 280, 60));

        jButton13.setText("jButton13");
        QuoteDialog.getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, 30, -1));

        jLabel67.setText("Tipo de habitacion");
        QuoteDialog.getContentPane().add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, -1, -1));

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
        LoadRoomCount.setMinimumSize(new java.awt.Dimension(602, 315));
        LoadRoomCount.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel57.setText("Añadir habitacion a la factura");
        LoadRoomCount.getContentPane().add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 180, -1));

        jLabel58.setText("Buscar por codigo");
        LoadRoomCount.getContentPane().add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        searchRoomsField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                searchRoomsFieldCaretUpdate(evt);
            }
        });
        searchRoomsField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchRoomsFieldActionPerformed(evt);
            }
        });
        LoadRoomCount.getContentPane().add(searchRoomsField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 550, -1));

        AvaliableRoomsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(AvaliableRoomsTable);

        LoadRoomCount.getContentPane().add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 550, 120));

        jButton40.setText("Añadir");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });
        LoadRoomCount.getContentPane().add(jButton40, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, -1, -1));

        jButton41.setText("Cancelar");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });
        LoadRoomCount.getContentPane().add(jButton41, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 250, -1, -1));
        LoadRoomCount.getContentPane().add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 550, 20));

        RoomDataDialog.setMinimumSize(new java.awt.Dimension(400, 300));
        RoomDataDialog.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setText("Datos de la habitacion");
        RoomDataDialog.getContentPane().add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, -1));

        jLabel72.setText("Fecha de salida");
        RoomDataDialog.getContentPane().add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 20));

        jLabel73.setText("Tipo de habitacion");
        RoomDataDialog.getContentPane().add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, 20));

        jLabel75.setText("Fecha de inicio");
        RoomDataDialog.getContentPane().add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 20));

        jButton11.setText("Cancelar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        RoomDataDialog.getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, -1, -1));

        SendDataToBill.setText("Rentar");
        SendDataToBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendDataToBillActionPerformed(evt);
            }
        });
        RoomDataDialog.getContentPane().add(SendDataToBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, -1));

        RoomTypeTF.setEditable(false);
        RoomDataDialog.getContentPane().add(RoomTypeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 210, -1));

        jLabel76.setText("Codigo de habitacion");
        RoomDataDialog.getContentPane().add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));

        DateEnd.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                DateEndHierarchyChanged(evt);
            }
        });
        DateEnd.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                DateEndAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        RoomDataDialog.getContentPane().add(DateEnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 210, -1));

        DateBegin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DateBeginMouseClicked(evt);
            }
        });
        RoomDataDialog.getContentPane().add(DateBegin, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 210, -1));

        RoomCodeTF2.setEditable(false);
        RoomDataDialog.getContentPane().add(RoomCodeTF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 210, -1));
        RoomDataDialog.getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 210, 20));
        RoomDataDialog.getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 210, 20));

        jButton21.setText("Proformar");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        RoomDataDialog.getContentPane().add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 90, -1));

        RoomInfoDialog.setMaximumSize(new java.awt.Dimension(400, 357));
        RoomInfoDialog.setMinimumSize(new java.awt.Dimension(400, 357));
        RoomInfoDialog.setPreferredSize(new java.awt.Dimension(400, 357));
        RoomInfoDialog.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Informacion de la habitacion seleccionada");
        RoomInfoDialog.getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        jLabel5.setText("Tipo");
        RoomInfoDialog.getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 20));

        jLabel18.setText("Codigo");
        RoomInfoDialog.getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, 20));

        jLabel19.setText("Disponibilidad");
        RoomInfoDialog.getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, 20));

        jLabel40.setText("Total de dias rentado");
        RoomInfoDialog.getContentPane().add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, 20));

        jLabel41.setText("Fecha de entrada");
        RoomInfoDialog.getContentPane().add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, 20));

        jLabel42.setText("Precio");
        RoomInfoDialog.getContentPane().add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, 20));

        jLabel45.setText("Acciones");
        RoomInfoDialog.getContentPane().add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, 20));

        jButton12.setForeground(new java.awt.Color(0, 0, 0));
        jButton12.setText("Proformar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        RoomInfoDialog.getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, -1, -1));

        jButton15.setForeground(new java.awt.Color(0, 0, 0));
        jButton15.setText("Cancelar estadia");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        RoomInfoDialog.getContentPane().add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, -1, -1));

        jButton43.setBackground(new java.awt.Color(73, 114, 116));
        jButton43.setForeground(new java.awt.Color(255, 255, 255));
        jButton43.setText("Cerrar");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });
        RoomInfoDialog.getContentPane().add(jButton43, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 290, 80, -1));

        OutDate.setEditable(false);
        RoomInfoDialog.getContentPane().add(OutDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 250, -1));

        Type.setEditable(false);
        RoomInfoDialog.getContentPane().add(Type, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 250, -1));

        Code.setEditable(false);
        RoomInfoDialog.getContentPane().add(Code, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 250, -1));

        Avaliability.setEditable(false);
        RoomInfoDialog.getContentPane().add(Avaliability, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 250, -1));

        TotalDays.setEditable(false);
        RoomInfoDialog.getContentPane().add(TotalDays, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 250, -1));

        EntryDate.setEditable(false);
        RoomInfoDialog.getContentPane().add(EntryDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 250, -1));
        RoomInfoDialog.getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 400, -1));

        jButton22.setForeground(new java.awt.Color(0, 0, 0));
        jButton22.setText("Facturar");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        RoomInfoDialog.getContentPane().add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, -1, -1));

        jLabel80.setText("Fecha de salida");
        RoomInfoDialog.getContentPane().add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, 20));

        Price.setEditable(false);
        RoomInfoDialog.getContentPane().add(Price, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 250, -1));

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

        ProformaB.setBackground(new java.awt.Color(73, 114, 116));
        ProformaB.setForeground(new java.awt.Color(255, 255, 255));
        ProformaB.setText("Proforma");
        ProformaB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProformaBActionPerformed(evt);
            }
        });
        LateralPanel.add(ProformaB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 290, -1));

        SwitchAccountB.setBackground(new java.awt.Color(73, 114, 116));
        SwitchAccountB.setForeground(new java.awt.Color(255, 255, 255));
        SwitchAccountB.setText("Logout");
        SwitchAccountB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SwitchAccountBActionPerformed(evt);
            }
        });
        LateralPanel.add(SwitchAccountB, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, 290, -1));

        DocumentationB.setBackground(new java.awt.Color(73, 114, 116));
        DocumentationB.setForeground(new java.awt.Color(255, 255, 255));
        DocumentationB.setText("Documentacion");
        DocumentationB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DocumentationBActionPerformed(evt);
            }
        });
        LateralPanel.add(DocumentationB, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 620, 140, -1));

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

        searchField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                searchFieldCaretUpdate(evt);
            }
        });
        RoomsPanel.add(searchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 610, -1));

        jButton10.setText("Buscar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        RoomsPanel.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 70, -1, -1));

        jLabel21.setText("Buscar Habitacion");
        RoomsPanel.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 30));

        Room5Panel.setBackground(new java.awt.Color(177, 213, 200));
        Room5Panel.setForeground(new java.awt.Color(255, 255, 255));
        Room5Panel.setRoundBottomLeft(30);
        Room5Panel.setRoundBottomRight(30);
        Room5Panel.setRoundTopLeft(30);
        Room5Panel.setRoundTopRight(30);
        Room5Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Room5Image.setText("Image Reference");
        Room5Panel.add(Room5Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 140, 140));

        RoomType5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomType5.setText("Simple");
        Room5Panel.add(RoomType5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 160, 170, -1));

        RoomCode5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomCode5.setText("a123");
        RoomCode5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Room5Panel.add(RoomCode5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 160, -1));

        RoomAvaliability5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomAvaliability5.setText("Disponible");
        Room5Panel.add(RoomAvaliability5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 200, 150, -1));

        RoomRent5.setBackground(new java.awt.Color(73, 114, 116));
        RoomRent5.setForeground(new java.awt.Color(255, 255, 255));
        RoomRent5.setText("Rentar");
        RoomRent5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomRent5ActionPerformed(evt);
            }
        });
        Room5Panel.add(RoomRent5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 80, 20));

        jButton28.setBackground(new java.awt.Color(73, 114, 116));
        jButton28.setForeground(new java.awt.Color(255, 255, 255));
        jButton28.setText("Ver");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        Room5Panel.add(jButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 70, 20));

        RoomsPanel.add(Room5Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 150, 180, 250));

        Room1Panel.setBackground(new java.awt.Color(177, 213, 200));
        Room1Panel.setForeground(new java.awt.Color(255, 255, 255));
        Room1Panel.setRoundBottomLeft(30);
        Room1Panel.setRoundBottomRight(30);
        Room1Panel.setRoundTopLeft(30);
        Room1Panel.setRoundTopRight(30);
        Room1Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Room1Image.setText("Image Reference");
        Room1Panel.add(Room1Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 140, 140));

        RoomType1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomType1.setText("Simple");
        RoomType1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Room1Panel.add(RoomType1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 160, 170, -1));

        RoomCode1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomCode1.setText("a123");
        RoomCode1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Room1Panel.add(RoomCode1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 160, -1));

        RoomAvaliability1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomAvaliability1.setText("Disponible");
        Room1Panel.add(RoomAvaliability1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 200, 170, -1));

        jButton16.setBackground(new java.awt.Color(73, 114, 116));
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setText("Ver");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        Room1Panel.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 70, 20));

        RoomRent1.setBackground(new java.awt.Color(73, 114, 116));
        RoomRent1.setForeground(new java.awt.Color(255, 255, 255));
        RoomRent1.setText("Rentar");
        RoomRent1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomRent1ActionPerformed(evt);
            }
        });
        Room1Panel.add(RoomRent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 80, 20));

        RoomsPanel.add(Room1Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 180, 250));

        Room2Panel.setBackground(new java.awt.Color(177, 213, 200));
        Room2Panel.setForeground(new java.awt.Color(255, 255, 255));
        Room2Panel.setRoundBottomLeft(30);
        Room2Panel.setRoundBottomRight(30);
        Room2Panel.setRoundTopLeft(30);
        Room2Panel.setRoundTopRight(30);
        Room2Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Room2Image.setText("Image Reference");
        Room2Panel.add(Room2Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 140, 140));

        RoomType2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomType2.setText("Simple");
        Room2Panel.add(RoomType2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 160, 170, -1));

        RoomCode2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomCode2.setText("a123");
        RoomCode2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Room2Panel.add(RoomCode2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 160, -1));

        RoomAvaliability2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomAvaliability2.setText("Disponible");
        Room2Panel.add(RoomAvaliability2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 200, 160, -1));

        RoomRent2.setBackground(new java.awt.Color(73, 114, 116));
        RoomRent2.setForeground(new java.awt.Color(255, 255, 255));
        RoomRent2.setText("Rentar");
        RoomRent2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomRent2ActionPerformed(evt);
            }
        });
        Room2Panel.add(RoomRent2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 80, 20));

        jButton25.setBackground(new java.awt.Color(73, 114, 116));
        jButton25.setForeground(new java.awt.Color(255, 255, 255));
        jButton25.setText("Ver");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        Room2Panel.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 70, 20));

        RoomsPanel.add(Room2Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 180, 250));

        Room3Panel.setBackground(new java.awt.Color(177, 213, 200));
        Room3Panel.setForeground(new java.awt.Color(255, 255, 255));
        Room3Panel.setRoundBottomLeft(30);
        Room3Panel.setRoundBottomRight(30);
        Room3Panel.setRoundTopLeft(30);
        Room3Panel.setRoundTopRight(30);
        Room3Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Room3Image.setText("Image Reference");
        Room3Panel.add(Room3Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 140, 140));

        RoomType3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomType3.setText("Simple");
        Room3Panel.add(RoomType3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 160, 170, -1));

        RoomCode3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomCode3.setText("a123");
        RoomCode3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Room3Panel.add(RoomCode3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 160, -1));

        RoomAvaliability3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomAvaliability3.setText("Disponible");
        Room3Panel.add(RoomAvaliability3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 200, 170, -1));

        RoomRent3.setBackground(new java.awt.Color(73, 114, 116));
        RoomRent3.setForeground(new java.awt.Color(255, 255, 255));
        RoomRent3.setText("Rentar");
        RoomRent3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomRent3ActionPerformed(evt);
            }
        });
        Room3Panel.add(RoomRent3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 80, 20));

        jButton26.setBackground(new java.awt.Color(73, 114, 116));
        jButton26.setForeground(new java.awt.Color(255, 255, 255));
        jButton26.setText("Ver");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        Room3Panel.add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 70, 20));

        RoomsPanel.add(Room3Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 180, 250));

        Room4Panel.setBackground(new java.awt.Color(177, 213, 200));
        Room4Panel.setForeground(new java.awt.Color(255, 255, 255));
        Room4Panel.setRoundBottomLeft(30);
        Room4Panel.setRoundBottomRight(30);
        Room4Panel.setRoundTopLeft(30);
        Room4Panel.setRoundTopRight(30);
        Room4Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Room4Image.setText("Image Reference");
        Room4Panel.add(Room4Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 140, 140));

        RoomType4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomType4.setText("Simple");
        Room4Panel.add(RoomType4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 160, 170, -1));

        RoomCode4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomCode4.setText("a123");
        RoomCode4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Room4Panel.add(RoomCode4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 160, -1));

        RoomAvaliability4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomAvaliability4.setText("Disponible");
        Room4Panel.add(RoomAvaliability4, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 200, 150, -1));

        RoomRent4.setBackground(new java.awt.Color(73, 114, 116));
        RoomRent4.setForeground(new java.awt.Color(255, 255, 255));
        RoomRent4.setText("Rentar");
        RoomRent4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomRent4ActionPerformed(evt);
            }
        });
        Room4Panel.add(RoomRent4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 80, 20));

        jButton27.setBackground(new java.awt.Color(73, 114, 116));
        jButton27.setForeground(new java.awt.Color(255, 255, 255));
        jButton27.setText("Ver");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        Room4Panel.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 70, 20));

        RoomsPanel.add(Room4Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 150, 180, 250));

        Room10Panel.setBackground(new java.awt.Color(177, 213, 200));
        Room10Panel.setForeground(new java.awt.Color(255, 255, 255));
        Room10Panel.setRoundBottomLeft(30);
        Room10Panel.setRoundBottomRight(30);
        Room10Panel.setRoundTopLeft(30);
        Room10Panel.setRoundTopRight(30);
        Room10Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Room10Image.setText("Image Reference");
        Room10Panel.add(Room10Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 140, 140));

        RoomType10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomType10.setText("Simple");
        Room10Panel.add(RoomType10, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 160, 150, -1));

        RoomCode10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomCode10.setText("a123");
        RoomCode10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Room10Panel.add(RoomCode10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 160, -1));

        RoomAvaliability10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomAvaliability10.setText("Disponible");
        Room10Panel.add(RoomAvaliability10, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 200, 150, -1));

        RoomRent10.setBackground(new java.awt.Color(73, 114, 116));
        RoomRent10.setForeground(new java.awt.Color(255, 255, 255));
        RoomRent10.setText("Rentar");
        RoomRent10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomRent10ActionPerformed(evt);
            }
        });
        Room10Panel.add(RoomRent10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 80, 20));

        jButton42.setBackground(new java.awt.Color(73, 114, 116));
        jButton42.setForeground(new java.awt.Color(255, 255, 255));
        jButton42.setText("Ver");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });
        Room10Panel.add(jButton42, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 70, 20));

        RoomsPanel.add(Room10Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 420, 180, 250));

        Room6Panel.setBackground(new java.awt.Color(177, 213, 200));
        Room6Panel.setForeground(new java.awt.Color(255, 255, 255));
        Room6Panel.setRoundBottomLeft(30);
        Room6Panel.setRoundBottomRight(30);
        Room6Panel.setRoundTopLeft(30);
        Room6Panel.setRoundTopRight(30);
        Room6Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Room6Image.setText("Image Reference");
        Room6Panel.add(Room6Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 140, 140));

        RoomType6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomType6.setText("Simple");
        Room6Panel.add(RoomType6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 160, 170, -1));

        RoomCode6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomCode6.setText("a123");
        RoomCode6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Room6Panel.add(RoomCode6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 160, -1));

        RoomAvaliability6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomAvaliability6.setText("Disponible");
        Room6Panel.add(RoomAvaliability6, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 200, 150, -1));

        RoomRent6.setBackground(new java.awt.Color(73, 114, 116));
        RoomRent6.setForeground(new java.awt.Color(255, 255, 255));
        RoomRent6.setText("Rentar");
        RoomRent6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomRent6ActionPerformed(evt);
            }
        });
        Room6Panel.add(RoomRent6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 80, 20));

        jButton29.setBackground(new java.awt.Color(73, 114, 116));
        jButton29.setForeground(new java.awt.Color(255, 255, 255));
        jButton29.setText("Ver");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        Room6Panel.add(jButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 70, 20));

        RoomsPanel.add(Room6Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 180, 250));

        Room7Panel.setBackground(new java.awt.Color(177, 213, 200));
        Room7Panel.setForeground(new java.awt.Color(255, 255, 255));
        Room7Panel.setRoundBottomLeft(30);
        Room7Panel.setRoundBottomRight(30);
        Room7Panel.setRoundTopLeft(30);
        Room7Panel.setRoundTopRight(30);
        Room7Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Room7Image.setText("Image Reference");
        Room7Panel.add(Room7Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 140, 140));

        RoomType7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomType7.setText("Simple");
        Room7Panel.add(RoomType7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 160, 170, -1));

        RoomCode7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomCode7.setText("a123");
        RoomCode7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Room7Panel.add(RoomCode7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 160, -1));

        RoomAvaliability7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomAvaliability7.setText("Disponible");
        Room7Panel.add(RoomAvaliability7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 200, 170, -1));

        RoomRent7.setBackground(new java.awt.Color(73, 114, 116));
        RoomRent7.setForeground(new java.awt.Color(255, 255, 255));
        RoomRent7.setText("Rentar");
        RoomRent7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomRent7ActionPerformed(evt);
            }
        });
        Room7Panel.add(RoomRent7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 80, 20));

        jButton30.setBackground(new java.awt.Color(73, 114, 116));
        jButton30.setForeground(new java.awt.Color(255, 255, 255));
        jButton30.setText("Ver");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        Room7Panel.add(jButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 70, 20));

        RoomsPanel.add(Room7Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 420, 180, 250));

        Room8Panel.setBackground(new java.awt.Color(177, 213, 200));
        Room8Panel.setForeground(new java.awt.Color(255, 255, 255));
        Room8Panel.setRoundBottomLeft(30);
        Room8Panel.setRoundBottomRight(30);
        Room8Panel.setRoundTopLeft(30);
        Room8Panel.setRoundTopRight(30);
        Room8Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Room8Image.setText("Image Reference");
        Room8Panel.add(Room8Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 140, 140));

        RoomType8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomType8.setText("Simple");
        Room8Panel.add(RoomType8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 160, 170, -1));

        RoomCode8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomCode8.setText("a123");
        RoomCode8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Room8Panel.add(RoomCode8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 160, -1));

        RoomAvaliability8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomAvaliability8.setText("Disponible");
        Room8Panel.add(RoomAvaliability8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 200, 170, -1));

        RoomRent8.setBackground(new java.awt.Color(73, 114, 116));
        RoomRent8.setForeground(new java.awt.Color(255, 255, 255));
        RoomRent8.setText("Rentar");
        RoomRent8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomRent8ActionPerformed(evt);
            }
        });
        Room8Panel.add(RoomRent8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 80, 20));

        jButton35.setBackground(new java.awt.Color(73, 114, 116));
        jButton35.setForeground(new java.awt.Color(255, 255, 255));
        jButton35.setText("Ver");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        Room8Panel.add(jButton35, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 70, 20));

        RoomsPanel.add(Room8Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, 180, 250));

        Room9Panel.setBackground(new java.awt.Color(177, 213, 200));
        Room9Panel.setForeground(new java.awt.Color(255, 255, 255));
        Room9Panel.setRoundBottomLeft(30);
        Room9Panel.setRoundBottomRight(30);
        Room9Panel.setRoundTopLeft(30);
        Room9Panel.setRoundTopRight(30);
        Room9Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Room9Image.setText("Image Reference");
        Room9Panel.add(Room9Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 140, 140));

        RoomType9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomType9.setText("Simple");
        Room9Panel.add(RoomType9, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 160, 170, -1));

        RoomCode9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomCode9.setText("a123");
        RoomCode9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Room9Panel.add(RoomCode9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 160, -1));

        RoomAvaliability9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RoomAvaliability9.setText("Disponible");
        Room9Panel.add(RoomAvaliability9, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 200, 150, -1));

        RoomRent9.setBackground(new java.awt.Color(73, 114, 116));
        RoomRent9.setForeground(new java.awt.Color(255, 255, 255));
        RoomRent9.setText("Rentar");
        RoomRent9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomRent9ActionPerformed(evt);
            }
        });
        Room9Panel.add(RoomRent9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 80, 20));

        jButton37.setBackground(new java.awt.Color(73, 114, 116));
        jButton37.setForeground(new java.awt.Color(255, 255, 255));
        jButton37.setText("Ver");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        Room9Panel.add(jButton37, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 70, 20));

        RoomsPanel.add(Room9Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 420, 180, 250));

        jLabel1.setText("Filtrar");
        RoomsPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 70, 50, 30));

        RoomsTypeFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Simple", "Familiar", "Matrimonial" }));
        RoomsTypeFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomsTypeFilterActionPerformed(evt);
            }
        });
        RoomsPanel.add(RoomsTypeFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 70, 100, -1));
        RoomsPanel.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 610, 20));

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
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
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

        ProformPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setText("Proforma");
        ProformPanel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, -1));

        SearchTF1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                SearchTF1CaretUpdate(evt);
            }
        });
        SearchTF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchTF1ActionPerformed(evt);
            }
        });
        ProformPanel.add(SearchTF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 580, -1));

        jButton14.setText("Buscar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        ProformPanel.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 70, -1, -1));

        jLabel66.setText("Busqueda");
        ProformPanel.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, -1, 20));

        ProformTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(ProformTable);

        ProformPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 950, 310));

        jButton17.setText("Añadir");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        ProformPanel.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 240, -1, -1));

        jButton18.setText("Quitar");
        ProformPanel.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 240, -1, -1));
        ProformPanel.add(FacturationSearchValidation1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 580, 20));

        DataUsersPanel1.setBackground(new java.awt.Color(255, 255, 255));
        DataUsersPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel68.setText("Cliente");
        DataUsersPanel1.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel69.setText("Cedula");
        DataUsersPanel1.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        jLabel70.setText("Telefono");
        DataUsersPanel1.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, -1, -1));

        jLabel74.setBackground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Correo electronico");
        DataUsersPanel1.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, -1, -1));
        DataUsersPanel1.add(PhoneTF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 280, 20));
        DataUsersPanel1.add(NameTF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 310, 20));
        DataUsersPanel1.add(IDTF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 310, 20));

        EmailTF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailTF1ActionPerformed(evt);
            }
        });
        DataUsersPanel1.add(EmailTF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 280, 20));
        DataUsersPanel1.add(clientRegistVal, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 350, 20));

        ProformPanel.add(DataUsersPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 840, 80));

        jButton19.setText("Cancelar");
        ProformPanel.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 670, -1, -1));

        jButton20.setText("Registrar Proforma");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        ProformPanel.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 670, -1, -1));

        jLabel23.setText("IVA 15%");
        ProformPanel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 600, -1, 20));

        jLabel81.setText("Subtotal");
        ProformPanel.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 600, -1, 20));

        jLabel82.setText("Total");
        ProformPanel.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 630, -1, 20));

        TotalTF1.setEditable(false);
        ProformPanel.add(TotalTF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 630, 90, -1));

        SubtotalTF1.setEditable(false);
        ProformPanel.add(SubtotalTF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 600, 90, -1));

        IVATF1.setEditable(false);
        ProformPanel.add(IVATF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 600, 90, -1));

        getContentPane().add(ProformPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 1570, 1080));

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
        
    }//GEN-LAST:event_SellsBActionPerformed

    private void ProformaBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProformaBActionPerformed
        // TODO add your handling code here:
        selectedButton(5);

        // Definir y calcular valores en TextFields
        double subtotal = 0.0;
        double ivaValue = 0.0;

        // Verificar si hay datos en la tabla
        if (ProformTable.getRowCount() > 0) {
            // Iterar sobre cada fila de la tabla para sumar el subtotal
            for (int i = 0; i < ProformTable.getRowCount(); i++) {
                // Obtener el valor de la columna 4 (total) de cada fila
                Object totalObject = ProformTable.getValueAt(i, 4);
                if (totalObject != null) {
                    try {
                        // Convertir el valor a double y agregarlo al subtotal
                        subtotal += Double.parseDouble(totalObject.toString());
                    } catch (NumberFormatException e) {
                        // Manejar el error de conversión si el valor no es un número válido
                        System.err.println("Error al convertir el valor de la tabla en la fila " + i + ": " + e.getMessage());
                        SubtotalTF1.setText("Error");
                        IVATF1.setText("Error");
                        TotalTF1.setText("Error");
                        return; // Salir del método si ocurre un error
                    }
                }
            }

            // Calcular el IVA y el total
            ivaValue = subtotal * 0.15;
            double totalConIVA = subtotal + ivaValue;

            // Establecer valores en los TextFields con formato
            SubtotalTF1.setText(String.format("%.2f", subtotal));
            IVATF1.setText(String.format("%.2f", ivaValue));
            TotalTF1.setText(String.format("%.2f", totalConIVA)); // Asumiendo que también necesitas mostrar el total
        } else {
            // Si no hay filas en la tabla, establecer valores predeterminados
            SubtotalTF1.setText("0.00");
            IVATF1.setText("0.00");
            TotalTF1.setText("0.00");
        }
    }//GEN-LAST:event_ProformaBActionPerformed

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
        PrintAvailableRoomsOnList();

        System.out.println("boton1 presionado");
    }//GEN-LAST:event_jButton1ActionPerformed

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
        String cedulaBuscada = SearchTF.getText();
        
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
            }
        }  // TODO add your handling code here:
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
        JOptionPane.showMessageDialog(this, "Agregado", "Exito", JOptionPane.INFORMATION_MESSAGE);
    }
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        RoomDataDialog.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void SendDataToBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendDataToBillActionPerformed
        Conection conection = new Conection();
        conection.conexion("Habitaciones");
        Update actualizar = new Update();

        // Obtener el código de la habitación
        String roomCode = RoomCodeTF2.getText();

        // Obtener las fechas desde JDateChooser
        Date dateBegin = DateBegin.getDate();
        Date dateEnd = DateEnd.getDate();

        // Verificar que las fechas no sean nulas
        if (dateBegin != null && dateEnd != null) {
            // Convertir java.util.Date a java.time.LocalDate
            LocalDate fechaEntrada = dateBegin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fechaSalida = dateEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // Actualizar la habitación con las nuevas fechas y cambiar el estado a 'Ocupado'
            actualizar.actualizarHabitacion(conection, roomCode, fechaEntrada, fechaSalida,"Ocupado");

            // Recargar la lista de habitaciones y actualizar la interfaz de usuario
            reloadHabitaciones();
            setCodeOnRooms(RoomsTypeFilter.getSelectedIndex() + 1); // Usar el tipo de habitación seleccionado
            setDataOnRoomPanels(RoomsTypeFilter.getSelectedIndex() + 1); // Usar el tipo de habitación seleccionado
            setColorRoomPanels();

            // Forzar la actualización de la interfaz
            this.revalidate();
            this.repaint();

            // Pasar el objeto a la tabla de facturación.
            for (Habitacion habitacion : habitaciones) {
                if (habitacion.getCodigo().equals(roomCode)) {
                    // Utilizar el método para actualizar la tabla de facturación
                    updateTableWithRoomData(modelFactura, habitacion);
                    break;
                }
            }
            updateRoomRentButtons();
            RoomDataDialog.dispose();
        } else {
            jLabel6.setText("Por favor, seleccione las fechas de entrada");
            jLabel4.setText("Por favor, seleccione las fechas de salida");
        }        
    }//GEN-LAST:event_SendDataToBillActionPerformed
       
    public void updateTableWithRoomData(DefaultTableModel tableModel, Habitacion habitacion) {
        // Obtener los datos necesarios de la habitación
        String tipo = habitacion.getTipo();
        int diasRentados = habitacion.getDiasRentados();
        int precio = habitacion.getPrecio();
        int total = diasRentados * precio;

        // Calcular el índice
        String codigo = habitacion.getCodigo();

        // Agregar una nueva fila a la tabla
        tableModel.addRow(new Object[]{codigo, tipo, diasRentados, precio, total});               
    }

    public void loadAllRoomOnTable(DefaultTableModel modeloTabla, String filtro) {
        // Limpiar la tabla antes de cargar los datos
        modeloTabla.setRowCount(0);

        // Iterar sobre cada habitación en la lista de habitaciones
        for (Habitacion habitacion : habitaciones) {
            // Verificar si la habitación cumple con el filtro especificado
            String disponibilidad = habitacion.getDisponibilidad();
            boolean mostrarHabitacion = false;

            // Aplicar el filtro basado en el estado de la habitación
            if ("Ocupado".equalsIgnoreCase(filtro) && "Ocupado".equalsIgnoreCase(disponibilidad)) {
                mostrarHabitacion = true;
            } else if ("Reservado".equalsIgnoreCase(filtro) && "Reservado".equalsIgnoreCase(disponibilidad)) {
                mostrarHabitacion = true;
            }

            if (mostrarHabitacion && !habitacionesSeleccionadas.contains(habitacion)) {
                // Obtener los atributos de cada habitación
                String codigo = habitacion.getCodigo();
                String tipo = habitacion.getTipo();
                int precio = habitacion.getPrecio();
                String limpieza = habitacion.getLimpieza();

                // Obtener las fechas de entrada y salida
                LocalDate fechaEntrada = habitacion.getFechaEntrada();
                LocalDate fechaSalida = habitacion.getFechaSalida();

                // Formatear las fechas para mostrarlas como texto
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String fechaEntradaStr = (fechaEntrada != null) ? fechaEntrada.format(formatter) : "No definido";
                String fechaSalidaStr = (fechaSalida != null) ? fechaSalida.format(formatter) : "No definido";

                // Obtener los días rentados
                int diasRentados = habitacion.getDiasRentados();

                // Agregar una nueva fila al modelo de la tabla con los datos de la habitación
                modeloTabla.addRow(new Object[]{
                    codigo, tipo, disponibilidad, precio, limpieza, fechaEntradaStr, fechaSalidaStr, diasRentados
                });
            }
        }
    }

    
    private Habitacion obtenerHabitacionPorCodigo(String codigo) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getCodigo().equals(codigo)) {
                return habitacion;
            }
        }
        return null;
    }
    
    public void PrintAvailableRoomsOnList() {
        System.out.println("Iniciando impresión de habitaciones disponibles.");

        // Verifica que RoomList esté inicializado
        if (RoomList == null) {
            System.out.println("RoomList no está inicializado.");
            return;
        }

        // Verifica que la lista de habitaciones no esté vacía
        if (habitaciones == null || habitaciones.isEmpty()) {
            System.out.println("La lista de habitaciones está vacía.");
            return;
        }

        // Obtener el índice seleccionado y determinar el tipo de habitación
        int selectedIndex = RoomTypeProform.getSelectedIndex();
        String tipoHabitacion = "";

        switch (selectedIndex) {
            case 0:
                tipoHabitacion = "Personal";
                break;
            case 1:
                tipoHabitacion = "Familiar";
                break;
            case 2:
                tipoHabitacion = "Matrimonial";
                break;
            default:
                System.out.println("Seleccione un tipo de habitación válido.");
                return;
        }

        // Modelo para almacenar las habitaciones disponibles
        DefaultListModel<String> listModel = new DefaultListModel<>();

        // Filtra e imprime las habitaciones disponibles del tipo seleccionado
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getTipo().equals(tipoHabitacion) && habitacion.getDisponibilidad().equals("Libre")) {
                // Calcula el total más el 15% de IVA

                // Formatea la información de la habitación
                String infoHabitacion = String.format(
                    "Código: %s, Tipo: %s, Precio: %d",
                    habitacion.getCodigo(),
                    habitacion.getTipo(),
                    habitacion.getPrecio()
                );

                // Añade la información al modelo de lista
                listModel.addElement(infoHabitacion);

                // Imprime la información de la habitación para debugging
                System.out.println("Añadiendo habitación: " + infoHabitacion);
            }
        }

        // Verifica si hay habitaciones que se han añadido
        if (listModel.isEmpty()) {
            System.out.println("No hay habitaciones disponibles para el tipo seleccionado.");
        }

        // Establece el modelo de lista en RoomList
        RoomList.setModel(listModel);
        System.out.println("Configuración de RoomList completada.");
    }

    private void LoginUserTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginUserTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LoginUserTFActionPerformed

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
            PrintAvailableRoomsOnList();
        }else if(RoomTypeProform.getSelectedIndex()==1){
            setDataOnQuoute(2);
            PrintAvailableRoomsOnList();
        }else if(RoomTypeProform.getSelectedIndex()==2){
            setDataOnQuoute(3);
            PrintAvailableRoomsOnList();
        }
    }//GEN-LAST:event_RoomTypeProformActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        QuoteDialog.setLocationRelativeTo(null);
        QuoteDialog.setVisible(true);
        setDataOnQuoute(3);
        System.out.println("boton1 presionado");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void RoomPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RoomPriceActionPerformed

    private void FinalPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinalPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FinalPriceActionPerformed

    private void RoomRent5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomRent5ActionPerformed
        // TODO add your handling code here:
        setSelectedRoomData(RoomCode5,RoomType5);
    }//GEN-LAST:event_RoomRent5ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        showRoomInfo(RoomCode1);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void RoomRent2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomRent2ActionPerformed
        // TODO add your handling code here:
        setSelectedRoomData(RoomCode2,RoomType2);
    }//GEN-LAST:event_RoomRent2ActionPerformed

    private void RoomRent3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomRent3ActionPerformed
        // TODO add your handling code here:
        setSelectedRoomData(RoomCode3,RoomType3);
    }//GEN-LAST:event_RoomRent3ActionPerformed

    private void RoomRent4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomRent4ActionPerformed
        // TODO add your handling code here:
        setSelectedRoomData(RoomCode4,RoomType4);
    }//GEN-LAST:event_RoomRent4ActionPerformed

    private void RoomRent10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomRent10ActionPerformed
        // TODO add your handling code here:
        setSelectedRoomData(RoomCode10,RoomType10);
    }//GEN-LAST:event_RoomRent10ActionPerformed

    private void RoomRent6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomRent6ActionPerformed
        // TODO add your handling code here:
        setSelectedRoomData(RoomCode6,RoomType6);
    }//GEN-LAST:event_RoomRent6ActionPerformed

    private void RoomRent7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomRent7ActionPerformed
        // TODO add your handling code here:
        setSelectedRoomData(RoomCode7,RoomType7);
    }//GEN-LAST:event_RoomRent7ActionPerformed

    private void RoomRent8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomRent8ActionPerformed
        // TODO add your handling code here:
        setSelectedRoomData(RoomCode8,RoomType8);
    }//GEN-LAST:event_RoomRent8ActionPerformed

    private void RoomRent9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomRent9ActionPerformed
        // TODO add your handling code here:
        setSelectedRoomData(RoomCode9,RoomType9);
    }//GEN-LAST:event_RoomRent9ActionPerformed

    private void RoomsTypeFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomsTypeFilterActionPerformed
        // TODO add your handling code here:
        reloadHabitaciones();
        if (RoomsTypeFilter.getSelectedIndex() == 0) {
            setCodeOnRooms(1);
            setDataOnRoomPanels(1);
            setColorRoomPanels();
            updateRoomRentButtons(); // Actualiza los botones después de configurar los datos y colores
        } else if (RoomsTypeFilter.getSelectedIndex() == 1) {
            setCodeOnRooms(2);
            setDataOnRoomPanels(2);
            setColorRoomPanels();
            updateRoomRentButtons(); // Actualiza los botones después de configurar los datos y colores
        } else if (RoomsTypeFilter.getSelectedIndex() == 2) {
            setCodeOnRooms(3);
            setDataOnRoomPanels(3);
            setColorRoomPanels();
            updateRoomRentButtons();
            this.repaint();// Actualiza los botones después de configurar los datos y colores
        }        
    }//GEN-LAST:event_RoomsTypeFilterActionPerformed

    private void RoomRent1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomRent1ActionPerformed
        // TODO add your handling code here:
        setSelectedRoomData(RoomCode1,RoomType1);
    }//GEN-LAST:event_RoomRent1ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        showRoomInfo(RoomCode2);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
        showRoomInfo(RoomCode3);
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
        showRoomInfo(RoomCode4);
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
        showRoomInfo(RoomCode5);
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
        showRoomInfo(RoomCode6);
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
        showRoomInfo(RoomCode7);
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // TODO add your handling code here:
        showRoomInfo(RoomCode8);
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
        showRoomInfo(RoomCode9);
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        // TODO add your handling code here:
        showRoomInfo(RoomCode10);
    }//GEN-LAST:event_jButton42ActionPerformed

    private void DateEndAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_DateEndAncestorAdded
        // TODO add your handling code here:
        
    }//GEN-LAST:event_DateEndAncestorAdded

    private void DateEndHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_DateEndHierarchyChanged
        // TODO add your handling code here:
        if (DateBegin.getDate() != null) {
            DateEnd.setMinSelectableDate(DateBegin.getDate());
        }
    }//GEN-LAST:event_DateEndHierarchyChanged

    private void DateBeginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DateBeginMouseClicked
        // TODO add your handling code here:
        Date selectedDate = DateBegin.getDate();
        if (selectedDate != null) {
            // Establece la fecha mínima en DateEnd
            DateEnd.setMinSelectableDate(selectedDate);
        }
    }//GEN-LAST:event_DateBeginMouseClicked

    private void searchFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_searchFieldCaretUpdate
        // TODO add your handling code here:
        if(searchField.getText().isEmpty()){
            jLabel60.setText("¡Ingresa algo para buscar!");
        }else{
            searchAndCompare(searchField.getText());
        }        
    }//GEN-LAST:event_searchFieldCaretUpdate

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        ocultarPaneles();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        loadAllRoomOnTable(modelAllRoomData,"Ocupado");
        LoadRoomCount.setLocationRelativeTo(null);
        LoadRoomCount.setVisible(true);
        valueOfFindFilter = "Facturacion";
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        // TODO add your handling code here:
        LoadRoomCount.dispose();
    }//GEN-LAST:event_jButton41ActionPerformed

    private void searchRoomsFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchRoomsFieldActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_searchRoomsFieldActionPerformed

    private void searchRoomsFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_searchRoomsFieldCaretUpdate
        // Obtener el valor de búsqueda del campo de texto
        String valorBusqueda = searchRoomsField.getText().trim();

        // Filtrar las habitaciones basadas en el código
        filterRoomsByCode(valorBusqueda);
    }//GEN-LAST:event_searchRoomsFieldCaretUpdate

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // Obtener el índice de la fila seleccionada
        int selectedRow = AvaliableRoomsTable.getSelectedRow();

        // Verificar que se haya seleccionado una fila
        if (selectedRow != -1) {
            // Obtener los datos de la fila seleccionada
            String codigo = (String) modelAllRoomData.getValueAt(selectedRow, 0);
            String tipo = (String) modelAllRoomData.getValueAt(selectedRow, 1);
            int precioPorNoche = (Integer) modelAllRoomData.getValueAt(selectedRow, 3);
            int diasRentados = (Integer) modelAllRoomData.getValueAt(selectedRow, 7);

            // Calcular el precio total
            int precioTotal = precioPorNoche * diasRentados;

            // Determinar el número de ítem (índice) para la nueva fila
            int numeroDeItem = modelFactura.getRowCount() + 1;

            // Insertar los datos en la tabla de facturación
            modelFactura.addRow(new Object[]{
                numeroDeItem,  // Número de ítem
                tipo,          // Tipo de habitación
                diasRentados,  // Tiempo de estadía
                precioPorNoche, // Precio por noche
                precioTotal    // Precio total
            });

            // Añadir la habitación seleccionada a la lista temporal
            Habitacion habitacionSeleccionada = obtenerHabitacionPorCodigo(codigo);
            if (habitacionSeleccionada != null) {
                habitacionesSeleccionadas.add(habitacionSeleccionada);
            }

            // Mensaje opcional de confirmación
            jLabel59.setText("Habitación añadida a la factura con éxito.");

            // Eliminar la habitación de la tabla de habitaciones disponibles
            ((DefaultTableModel) AvaliableRoomsTable.getModel()).removeRow(selectedRow);

        } else {
            // Mensaje en caso de que no se haya seleccionado ninguna fila
            jLabel59.setText("Por favor, seleccione una habitación para añadir a la factura.");
        }
    }//GEN-LAST:event_jButton40ActionPerformed

    private void NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameActionPerformed

    private void PhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PhoneActionPerformed

    private void IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDActionPerformed

    private void MailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MailActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
        QuoteDialog.dispose();
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // TODO add your handling code here:
        //variables
        String nombre = Name.getText();
        String telefono = Phone.getText();
        String cedula = ID.getText();
        String correo = Mail.getText();
        
        
    }//GEN-LAST:event_jButton36ActionPerformed

    private void SearchTF1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_SearchTF1CaretUpdate
        String cedulaBuscada = SearchTF1.getText().trim();

        // Verifica si el campo de texto de búsqueda está vacío
        if (cedulaBuscada.isEmpty()) {
            FacturationSearchValidation1.setText("¡Ingresa una cédula para buscar!");
            FacturationSearchValidation1.setForeground(new java.awt.Color(255, 0, 0)); // Rojo para indicar error
            un_lockPanel(DataUsersPanel1, false); // Desbloquea el panel ya que no hay búsqueda
        } else {
            ConectionUsers CU = new ConectionUsers();
            CU.conexionUsuarios(); // Asegúrate de que esto inicialice correctamente la conexión
            LoadClient LC = new LoadClient();

            // Carga el cliente registrado
            LC.cargarClienteRegistrado(CU, cedulaBuscada, NameTF1, IDTF1, PhoneTF1, EmailTF1, FacturationSearchValidation1);

            // Comprueba si el cliente fue encontrado o no
            if (FacturationSearchValidation1.getText().equals("Cliente no encontrado.")) {
                // Si el cliente no es encontrado, desbloquea el panel para permitir el registro
                un_lockPanel(DataUsersPanel1, true);
            } else {
                // Si el cliente es encontrado, bloquea el panel para impedir modificaciones
                un_lockPanel(DataUsersPanel1, false);
            }
        }
    }//GEN-LAST:event_SearchTF1CaretUpdate

    private void SearchTF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchTF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchTF1ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        loadAllRoomOnTable(modelAllRoomData,"Reservado");
        LoadRoomCount.setLocationRelativeTo(null);
        LoadRoomCount.setVisible(true);
        valueOfFindFilter = "Proforma";
    }//GEN-LAST:event_jButton17ActionPerformed

    private void EmailTF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailTF1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailTF1ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        boolean hasError = false; // Inicialmente no hay errores

        // Obtener los datos del cliente de los campos de texto
        String nombre = NameTF1.getText();
        String cedulaStr = IDTF1.getText();
        String correo = EmailTF1.getText();
        String telefono = PhoneTF1.getText();
        int cuentaDeLaTabla = ProformTable.getRowCount();

        // Validar si los campos del cliente están vacíos
        if (nombre.isEmpty() || cedulaStr.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los datos del cliente están vacíos, busca al cliente o ingresa sus datos para registrarlo", "Cliente vacío", JOptionPane.WARNING_MESSAGE);
            hasError = true;
        } else {
            // Validar la cédula
            Long cedula = null;
            try {
                cedula = Long.parseLong(cedulaStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "La cédula debe ser un número válido.", "Error de cédula", JOptionPane.ERROR_MESSAGE);
                hasError = true;
            }

            // Validar el correo electrónico
            try {
                Client cliente = new Client(nombre, cedula, telefono, correo); // Intentar crear el cliente
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error de correo", JOptionPane.ERROR_MESSAGE);
                hasError = true;
            }
        }

        // Validar si hay datos en la tabla
        if (cuentaDeLaTabla <= 0) {
            JOptionPane.showMessageDialog(this, "No hay datos que agregar a la factura", "Sin datos que proformar", JOptionPane.WARNING_MESSAGE);
            hasError = true;
        }

        // Si no hay errores, proceder con el registro
        if (!hasError) {
            // Crear y guardar el cliente en la base de datos
            ConectionUsers conexionU = new ConectionUsers();
            conexionU.conexionUsuarios();
            Regist regist = new Regist();
            regist.registrarCliente(conexionU, nombre, cedulaStr, telefono, correo, clientRegistVal); // Registrar al cliente

            // Guardar datos en el objeto y crear PDF
            crearPDFProforma c = new crearPDFProforma();
            c.guardarProfBD(nombre, telefono, cedulaStr, correo);

            // Obtener y convertir el valor del subtotal correctamente
            String subtotalStr = SubtotalTF1.getText().replace(",", "."); // Reemplazar coma por punto
            double subtotal = Double.parseDouble(subtotalStr); // Convertir subtotal

            // Obtener y convertir el valor total correctamente
            String totalStr = TotalTF1.getText().replace(",", "."); // Reemplazar coma por punto
            double total = Double.parseDouble(totalStr); // Convertir total

            // Iterar sobre las filas de la tabla
            for (int i = 0; i < ProformTable.getRowCount(); i++) {
                String codigo = (String) ProformTable.getValueAt(i, 0);
                String tipo = (String) ProformTable.getValueAt(i, 1);
                int tiempoEstadia = (int) ProformTable.getValueAt(i, 2);

                // Convertir el precio por noche correctamente
                double precioPorNoche = Double.parseDouble(ProformTable.getValueAt(i, 3).toString().replace(",", "."));
                // Convertir el precio final correctamente
                double precioFinal = Double.parseDouble(ProformTable.getValueAt(i, 4).toString().replace(",", "."));

                // Crear PDF usando los valores correctos
                c.crearPDFProf(codigo, tipo, tiempoEstadia, precioPorNoche, total, ProformTable);
            }

            JOptionPane.showMessageDialog(this, "Agregado", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Restablecer la habitación a default y cambiar la limpieza
            Conection conection = new Conection();
            conection.conexion("Habitaciones");
            Update updater = new Update();

            // Iterar sobre las habitaciones y restablecer cada una
            for (int i = 0; i < ProformTable.getRowCount(); i++) {
                String codigoHabitacion = (String) ProformTable.getValueAt(i, 0);
                updater.restablecerHabitacion(conection, codigoHabitacion);
            }

            // Recargar las habitaciones y actualizar la interfaz
            reloadHabitaciones();
            setCodeOnRooms(RoomsTypeFilter.getSelectedIndex() + 1); // Usar el tipo de habitación seleccionado
            setDataOnRoomPanels(RoomsTypeFilter.getSelectedIndex() + 1); // Usar el tipo de habitación seleccionado
            setColorRoomPanels();
            updateRoomRentButtons();
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // Conectar a la base de datos
        Conection conection = new Conection();
        conection.conexion("Habitaciones");
        Update actualizar = new Update();

        // Obtener el código de la habitación
        String roomCode = RoomCodeTF2.getText();

        // Obtener las fechas desde JDateChooser
        Date dateBegin = DateBegin.getDate();
        Date dateEnd = DateEnd.getDate();

        // Verificar que las fechas no sean nulas
        if (dateBegin != null && dateEnd != null) {
            // Convertir java.util.Date a java.time.LocalDate
            LocalDate fechaEntrada = dateBegin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fechaSalida = dateEnd.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // Actualizar la habitación con las nuevas fechas y cambiar el estado a 'Reservado'
            actualizar.actualizarHabitacion(conection, roomCode, fechaEntrada, fechaSalida, "Reservado");

            // Recargar la lista de habitaciones y actualizar la interfaz de usuario
            reloadHabitaciones();
            setCodeOnRooms(RoomsTypeFilter.getSelectedIndex() + 1); // Usar el tipo de habitación seleccionado
            setDataOnRoomPanels(RoomsTypeFilter.getSelectedIndex() + 1); // Usar el tipo de habitación seleccionado
            setColorRoomPanels();

            // Forzar la actualización de la interfaz
            this.revalidate();
            this.repaint();

            // Pasar el objeto a la tabla de modelo 'modelProforma'.
            for (Habitacion habitacion : habitaciones) {
                if (habitacion.getCodigo().equals(roomCode)) {
                    // Utilizar el método para actualizar la tabla de proforma
                    updateTableWithRoomData(ModelProforma, habitacion);
                    break;
                }
            }
            updateRoomRentButtons();
        } else {
            jLabel6.setText("Por favor, seleccione las fechas de entrada");
            jLabel4.setText("Por favor, seleccione las fechas de salida");
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // Mostrar un diálogo de confirmación al usuario
        String codigoHabitacion = Code.getText();
        int respuesta = JOptionPane.showConfirmDialog(
            this, // Componente padre (puede ser null si no se necesita)
            "¿Desea cancelar todos los estados de la habitación?", // Mensaje
            "Confirmar Cancelación", // Título del diálogo
            JOptionPane.YES_NO_OPTION, // Tipo de opciones
            JOptionPane.QUESTION_MESSAGE // Tipo de mensaje
        );

        // Comprobar la respuesta del usuario
        if (respuesta == JOptionPane.YES_OPTION) {
            // Crear una instancia de la clase Update
            Conection conexion = new Conection();
            conexion.conexion("Habitaciones");// Asegúrate de que la conexión esté abierta
            Update updater = new Update();

            // Restablecer la habitación
            updater.restablecerHabitacion(conexion, codigoHabitacion);
            reloadHabitaciones();
            setCodeOnRooms(RoomsTypeFilter.getSelectedIndex() + 1); // Usar el tipo de habitación seleccionado
            setDataOnRoomPanels(RoomsTypeFilter.getSelectedIndex() + 1); // Usar el tipo de habitación seleccionado
            setColorRoomPanels();
            updateRoomRentButtons();
            
        } else {
            // El usuario ha elegido "No" o ha cerrado el diálogo
            JOptionPane.showMessageDialog(this, "Cancelación de estados de habitación abortada.", "Acción Cancelada", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        SendDataToTable(ModelProforma,"proforma");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        SendDataToTable(modelFactura,"factura");
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        // TODO add your handling code here:
        RoomInfoDialog.dispose();
    }//GEN-LAST:event_jButton43ActionPerformed
    public void SendDataToTable(DefaultTableModel model,String facturaOProforma){
        
        String codigo = Code.getText();
        String tipo = Type.getText();
        String tiempo = TotalDays.getText();
        String precio = Price.getText();
        int tiempoInt = Integer.parseInt(tiempo);
        int precioDouble = Integer.parseInt(precio);
        int total = tiempoInt * precioDouble;
        if(Avaliability.getText().equals("Libre")){
            JOptionPane.showMessageDialog(this,"La habitacion no tiene datos de facturacion, por favor selecciona una diferente","Habitacion no rentable",JOptionPane.WARNING_MESSAGE);
        }else{
            model.addRow(new Object[]{codigo, tipo, tiempoInt, precioDouble,total});
            JOptionPane.showMessageDialog(this, "La habitacion ha sido añadida a la "+facturaOProforma,"Habitacion añadida",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void filterRoomsByCode(String valorBusqueda) {
        //limpiar el modelo de la tabla de las habitaciones buscadas
        modelAllRoomData.setRowCount(0);
    }

  
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
    
    public void setColorRoomPanels() {
        System.out.println("Actualizando colores de los paneles.");
        // Definir colores para los estados de disponibilidad
        Color colorDisponible = Color.decode("#B1D5C8");
        Color colorReservado = Color.decode("#FEED90");
        Color colorOcupado = Color.decode("#FF5C39");
        Color colorDefault = Color.decode("#C1C5DE"); // Color hexadecimal predeterminado

        for (int i = 1; i <= 10; i++) {
            try {
                Field fieldPanel = getClass().getDeclaredField("Room" + i + "Panel");
                JPanel roomPanel = (JPanel) fieldPanel.get(this);
                Field fieldAvailability = getClass().getDeclaredField("RoomAvaliability" + i);
                JLabel roomAvailability = (JLabel) fieldAvailability.get(this);

                // Verificar que el JPanel y JLabel no sean null
                if (roomPanel != null && roomAvailability != null) {
                    String availabilityText = roomAvailability.getText();
                    System.out.println("Room" + i + " disponibilidad: " + availabilityText);
                    switch (availabilityText) {
                        case "Libre":
                            roomPanel.setBackground(colorDisponible);
                            break;
                        case "Reservado":
                            roomPanel.setBackground(colorReservado);
                            break;
                        case "Ocupado":
                            roomPanel.setBackground(colorOcupado);
                            break;
                        default:
                            roomPanel.setBackground(colorDefault); // Color predeterminado para estados desconocidos
                            break;
                    }
                } else {
                    System.out.println("Error: El JPanel o JLabel es null para Room" + i);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

   
    public void setDataOnRoomPanels(int caso){
        reloadHabitaciones();
        switch(caso){
            case 1:
                for (int i = 1; i <= 10; i++) {
                    try {
                        Field fieldImage = getClass().getDeclaredField("Room" + i + "Image");
                        Field fieldType = getClass().getDeclaredField("RoomType" + i);
                        JLabel roomLabel = (JLabel) fieldImage.get(this);
                        JLabel roomType = (JLabel) fieldType.get(this);
                        // Verificar que el JLabel no sea null
                        if (roomLabel != null) {
                            rsscalelabel.RSScaleLabel.setScaleLabel(roomLabel, "src/imagenes/Personal.png");
                            roomType.setText("Simple");
                        } else {
                            System.out.println("Error: El JLabel es null para Room" + i);
                        }
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 2:
                for (int i = 1; i <= 10; i++) {
                    try {
                        Field fieldImage = getClass().getDeclaredField("Room" + i + "Image");
                        Field fieldType = getClass().getDeclaredField("RoomType" + i);
                        JLabel roomLabel = (JLabel) fieldImage.get(this);
                        JLabel roomType = (JLabel) fieldType.get(this);
                        // Verificar que el JLabel no sea null
                        if (roomLabel != null) {
                            rsscalelabel.RSScaleLabel.setScaleLabel(roomLabel, "src/imagenes/Familiar.png");
                            roomType.setText("Familiar");
                        } else {
                            System.out.println("Error: El JLabel es null para Room" + i);
                        }
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                break;             
            case 3:
                for (int i = 1; i <= 10; i++) {
                    try {
                        Field fieldImage = getClass().getDeclaredField("Room" + i + "Image");
                        Field fieldType = getClass().getDeclaredField("RoomType" + i);
                        JLabel roomLabel = (JLabel) fieldImage.get(this);
                        JLabel roomType = (JLabel) fieldType.get(this);
                        // Verificar que el JLabel no sea null
                        if (roomLabel != null) {
                            rsscalelabel.RSScaleLabel.setScaleLabel(roomLabel, "src/imagenes/Matrimonial.png");
                            roomType.setText("Matrimonial");
                        } else {
                            System.out.println("Error: El JLabel es null para Room" + i);
                        }
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
        }
        setColorRoomPanels();
    }
    
    public void setCodeOnRooms(int caso) {
        SwingUtilities.invokeLater(() -> {
            reloadHabitaciones(); // Asegúrate de cargar los datos más recientes
            System.out.println("Habitaciones recargadas.");

            String tipoHabitacion;
            switch (caso) {
                case 1:
                    tipoHabitacion = "Personal";
                    break;
                case 2:
                    tipoHabitacion = "Familiar";
                    break;
                case 3:
                    tipoHabitacion = "Matrimonial";
                    break;
                default:
                    System.out.println("Caso no reconocido.");
                    return;
            }

            int index = 0;
            for (Habitacion habitacion : habitaciones) {
                if (habitacion.getTipo().equalsIgnoreCase(tipoHabitacion)) {
                    if (index >= 10) {
                        break; // No asignar más de 10 habitaciones
                    }

                    try {
                        // Usa reflexión para obtener los campos dinámicamente
                        Field fieldCode = getClass().getDeclaredField("RoomCode" + (index + 1));
                        Field fieldAvailability = getClass().getDeclaredField("RoomAvaliability" + (index + 1));

                        // Obtener los JLabels
                        JLabel roomCode = (JLabel) fieldCode.get(this);
                        JLabel roomAvailability = (JLabel) fieldAvailability.get(this);

                        // Verificar que el JLabel no sea null
                        if (roomCode != null && roomAvailability != null) {
                            // Asigna el código de la habitación al JLabel
                            roomCode.setText(habitacion.getCodigo());
                            // Asigna la disponibilidad de la habitación al JLabel
                            roomAvailability.setText(habitacion.getDisponibilidad());
                            System.out.println("Actualizado Room" + (index + 1) + ": Código = " + habitacion.getCodigo() + ", Disponibilidad = " + habitacion.getDisponibilidad());
                        } else {
                            System.out.println("Error: El JLabel es null para Room" + (index + 1));
                        }

                        index++;
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            // Aplicar los colores a los paneles basados en la disponibilidad actual
            setColorRoomPanels();

            // Forzar la actualización de la interfaz
            this.revalidate();
            this.repaint();
            System.out.println("Paneles actualizados y repintados.");
        });
    }

    public void updateRoomRentButtons() {
        SwingUtilities.invokeLater(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    // Usa reflexión para obtener el campo del botón correspondiente
                    Field fieldButton = getClass().getDeclaredField("RoomRent" + i);
                    JButton rentButton = (JButton) fieldButton.get(this);

                    // Usa reflexión para obtener el campo del JLabel de disponibilidad correspondiente
                    Field fieldAvailability = getClass().getDeclaredField("RoomAvaliability" + i);
                    JLabel roomAvailability = (JLabel) fieldAvailability.get(this);

                    // Verificar que el botón y JLabel no sean null
                    if (rentButton != null && roomAvailability != null) {
                        String availabilityText = roomAvailability.getText();

                        // Habilitar o deshabilitar el botón basado en el estado de disponibilidad
                        if ("Ocupado".equalsIgnoreCase(availabilityText)) {
                            rentButton.setEnabled(false); // Deshabilita el botón
                            System.out.println("RoomRent" + i + " deshabilitado. Estado: Ocupado");
                        } else if ("Libre".equalsIgnoreCase(availabilityText)) {
                            rentButton.setEnabled(true); // Habilita el botón
                            System.out.println("RoomRent" + i + " habilitado. Estado: Libre");
                        } else {
                            // Opcional: manejar otros estados o establecer un estado predeterminado
                            rentButton.setEnabled(false); // O habilitar en caso de estado desconocido
                            System.out.println("RoomRent" + i + " deshabilitado. Estado desconocido.");
                        }
                    } else {
                        System.out.println("Error: El botón o JLabel es null para Room" + i);
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            // Forzar la actualización visual de los botones
            this.revalidate();
            this.repaint();
            System.out.println("Botones de alquiler actualizados.");
        });
    }

    public void showRoomInfo(JLabel codigoLabel) {
        String codigo = codigoLabel.getText();

        // Formateador para convertir LocalDate a String
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getCodigo().equals(codigo)) {
                Type.setText(habitacion.getTipo());
                Code.setText(habitacion.getCodigo());
                Avaliability.setText(habitacion.getDisponibilidad());
                Price.setText(habitacion.getPrecio()+"");

                // Usa el método getDiasRentados() para obtener el número de días rentados
                TotalDays.setText(String.valueOf(habitacion.getDiasRentados()));

                // Formatea y muestra las fechas de entrada y salida
                LocalDate fechaEntrada = habitacion.getFechaEntrada();
                LocalDate fechaSalida = habitacion.getFechaSalida();

                // Formatear las fechas, manejando nulos
                EntryDate.setText(fechaEntrada != null ? fechaEntrada.format(dateFormatter) : "No definido");
                OutDate.setText(fechaSalida != null ? fechaSalida.format(dateFormatter) : "No definido");
                
            }
            //System.out.println(habitacion.toString());
        }
        RoomInfoDialog.setLocationRelativeTo(null);
        RoomInfoDialog.setVisible(true);
    }
    
    private void reloadHabitaciones() {
        Conection conexion = new Conection();
        conexion.conexion("Habitaciones");
        LoadData cargarHabitaciones = new LoadData();
        habitaciones = cargarHabitaciones.loadData(conexion);
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
        ProformPanel.setVisible(false);
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
                ProformPanel.setVisible(true);
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
        ProformPanel.setVisible(false);
        AboutPanel.setVisible(false);
        LateralLoginPanel.setVisible(false);
        LoginPanel.setVisible(false);
    }
    //Metodo de busqueda de habitaciones
    public void searchAndCompare(String valorBusqueda) {
        boolean encontrado = false;

        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getCodigo().equals(valorBusqueda)) {
                // Ocultar paneles y dejar solo el primero
                ocultarPaneles();

                // Establecer datos, imagen, color de la habitación
                String tipoHabitacion = habitacion.getTipo();
                int tipo = 0;
                if (tipoHabitacion.equals("Personal")) {
                    RoomsTypeFilter.setSelectedIndex(0);
                    tipo = 1;
                } else if (tipoHabitacion.equals("Familiar")) {
                    RoomsTypeFilter.setSelectedIndex(1);
                    tipo = 2;
                } else if (tipoHabitacion.equals("Matrimonial")) {
                    RoomsTypeFilter.setSelectedIndex(2);
                    tipo = 3;
                }

                if (tipo > 0) {
                    setCodeOnRooms(tipo);
                    setDataOnRoomPanels(tipo);
                    setColorRoomPanels();
                    updateRoomRentButtons();
                }

                encontrado = true;
                jLabel60.setText("Mostrando resultados para el codigo "+valorBusqueda);
                break; // Salir del bucle una vez encontrada la habitación
            }
        }

        if (!encontrado) {
            jLabel60.setText("No se encontró una habitación asociada al código");
            mostrarPaneles();
            
        }
    }

    public void ocultarPaneles() {
        for (int i = 1; i <= 10; i++) {
            try {
                Field fieldPanel = getClass().getDeclaredField("Room" + i + "Panel");
                JPanel panel = (JPanel) fieldPanel.get(this);

                if (panel != null) {
                    // Mostrar el primer panel y ocultar los demás
                    if (i == 1) {
                        panel.setVisible(true); // Asegúrate de que el primer panel esté visible
                        System.out.println("Panel 1 visible");
                    } else {
                        panel.setVisible(false); // Ocultar todos los demás paneles
                        System.out.println("Panel " + i + " oculto");
                    }
                } else {
                    System.out.println("Error: El JPanel es null para Room" + i);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // Forzar actualización visual
        this.revalidate();
        this.repaint();
        System.out.println("Paneles ocultos actualizados.");
    }
    public void mostrarPaneles() {
        for (int i = 1; i <= 10; i++) {
            try {
                Field fieldPanel = getClass().getDeclaredField("Room" + i + "Panel");
                JPanel panel = (JPanel) fieldPanel.get(this);

                if (panel != null) {
                    panel.setVisible(true); // Mostrar todos los paneles
                    System.out.println("Panel " + i + " visible");
                } else {
                    System.out.println("Error: El JPanel es null para Room" + i);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // Forzar actualización visual
        this.revalidate();
        this.repaint();
        System.out.println("Paneles mostrados actualizados.");
    }
    //
    //setear informacion en las habitaciones seleccionadas
    public void setSelectedRoomData(JLabel codigo,JLabel tipo){
        RoomCodeTF2.setText(codigo.getText());
        RoomTypeTF.setText(tipo.getText());
        RoomDataDialog.setLocationRelativeTo(null);
        RoomDataDialog.setVisible(true);
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
    private javax.swing.JDialog AsignCleaningDialog;
    private javax.swing.JTextField Avaliability;
    private javax.swing.JTable AvaliableRoomsTable;
    private javax.swing.JTable BillDescriptionTable;
    private javax.swing.JPanel CleaningPanel;
    private javax.swing.JButton CleanningB;
    private javax.swing.JTextField Code;
    private javax.swing.JTextPane D;
    private javax.swing.JPanel DataUsersPanel;
    private javax.swing.JPanel DataUsersPanel1;
    private com.toedter.calendar.JDateChooser DateBegin;
    private com.toedter.calendar.JDateChooser DateEnd;
    private javax.swing.JButton DocumentationB;
    private javax.swing.JButton DocumentationB1;
    private javax.swing.JTextField EmailTF;
    private javax.swing.JTextField EmailTF1;
    private javax.swing.JTextField EntryDate;
    private javax.swing.JLabel FI1;
    private javax.swing.JLabel FI2;
    private javax.swing.JLabel FI3;
    private javax.swing.JLabel FI4;
    private javax.swing.JLabel FI5;
    private javax.swing.JLabel FI6;
    private javax.swing.JPanel FacturationPanel;
    private javax.swing.JLabel FacturationSearchValidation;
    private javax.swing.JLabel FacturationSearchValidation1;
    private javax.swing.JLabel FamiliarRoomL;
    private javax.swing.JTextField FinalPrice;
    private javax.swing.JPanel HourComponent;
    private javax.swing.JTextField ID;
    private javax.swing.JTextField IDTF;
    private javax.swing.JTextField IDTF1;
    private javax.swing.JTextField IVATF;
    private javax.swing.JTextField IVATF1;
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
    private javax.swing.JTextField Mail;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel ManualPanel;
    private javax.swing.JLabel MatrimonialRoomL;
    private javax.swing.JTextField Name;
    private javax.swing.JTextField NameTF;
    private javax.swing.JTextField NameTF1;
    private javax.swing.JTextField Nights;
    private javax.swing.JTextField OutDate;
    private javax.swing.JLabel PersonalRoomL;
    private javax.swing.JTextField Phone;
    private javax.swing.JTextField PhoneTF;
    private javax.swing.JTextField PhoneTF1;
    private javax.swing.JTextField Price;
    private javax.swing.JPanel ProformPanel;
    private javax.swing.JTable ProformTable;
    private javax.swing.JButton ProformaB;
    private com.toedter.calendar.JDateChooser ProformaINDATE;
    private com.toedter.calendar.JDateChooser ProformaOUTDATE;
    private javax.swing.JDialog QuoteDialog;
    private javax.swing.JButton RentB;
    private javax.swing.JLabel Room10Image;
    private test.PanelRound Room10Panel;
    private javax.swing.JLabel Room1Image;
    private test.PanelRound Room1Panel;
    private javax.swing.JLabel Room2Image;
    private test.PanelRound Room2Panel;
    private javax.swing.JLabel Room3Image;
    private test.PanelRound Room3Panel;
    private javax.swing.JLabel Room4Image;
    private test.PanelRound Room4Panel;
    private javax.swing.JLabel Room5Image;
    private test.PanelRound Room5Panel;
    private javax.swing.JLabel Room6Image;
    private test.PanelRound Room6Panel;
    private javax.swing.JLabel Room7Image;
    private test.PanelRound Room7Panel;
    private javax.swing.JLabel Room8Image;
    private test.PanelRound Room8Panel;
    private javax.swing.JLabel Room9Image;
    private test.PanelRound Room9Panel;
    private javax.swing.JLabel RoomAvaliability1;
    private javax.swing.JLabel RoomAvaliability10;
    private javax.swing.JLabel RoomAvaliability2;
    private javax.swing.JLabel RoomAvaliability3;
    private javax.swing.JLabel RoomAvaliability4;
    private javax.swing.JLabel RoomAvaliability5;
    private javax.swing.JLabel RoomAvaliability6;
    private javax.swing.JLabel RoomAvaliability7;
    private javax.swing.JLabel RoomAvaliability8;
    private javax.swing.JLabel RoomAvaliability9;
    private javax.swing.JLabel RoomCode1;
    private javax.swing.JLabel RoomCode10;
    private javax.swing.JLabel RoomCode2;
    private javax.swing.JLabel RoomCode3;
    private javax.swing.JLabel RoomCode4;
    private javax.swing.JLabel RoomCode5;
    private javax.swing.JLabel RoomCode6;
    private javax.swing.JLabel RoomCode7;
    private javax.swing.JLabel RoomCode8;
    private javax.swing.JLabel RoomCode9;
    private javax.swing.JTextField RoomCodeTF2;
    private javax.swing.JDialog RoomDataDialog;
    private javax.swing.JDialog RoomInfoDialog;
    private javax.swing.JList<String> RoomList;
    private javax.swing.JLabel RoomNameProform;
    private javax.swing.JTextField RoomPrice;
    private javax.swing.JButton RoomRent1;
    private javax.swing.JButton RoomRent10;
    private javax.swing.JButton RoomRent2;
    private javax.swing.JButton RoomRent3;
    private javax.swing.JButton RoomRent4;
    private javax.swing.JButton RoomRent5;
    private javax.swing.JButton RoomRent6;
    private javax.swing.JButton RoomRent7;
    private javax.swing.JButton RoomRent8;
    private javax.swing.JButton RoomRent9;
    private javax.swing.JLabel RoomType1;
    private javax.swing.JLabel RoomType10;
    private javax.swing.JLabel RoomType2;
    private javax.swing.JLabel RoomType3;
    private javax.swing.JLabel RoomType4;
    private javax.swing.JLabel RoomType5;
    private javax.swing.JLabel RoomType6;
    private javax.swing.JLabel RoomType7;
    private javax.swing.JLabel RoomType8;
    private javax.swing.JLabel RoomType9;
    private javax.swing.JComboBox<String> RoomTypeProform;
    private javax.swing.JTextField RoomTypeTF;
    private javax.swing.JButton RoomsButton;
    private javax.swing.JPanel RoomsPanel;
    private javax.swing.JComboBox<String> RoomsTypeFilter;
    private javax.swing.JLabel SI1;
    private javax.swing.JLabel SI2;
    private javax.swing.JLabel SI3;
    private javax.swing.JLabel SI4;
    private javax.swing.JLabel SI5;
    private javax.swing.JTextField SearchTF;
    private javax.swing.JTextField SearchTF1;
    private javax.swing.JButton SellsB;
    private javax.swing.JButton SendDataToBill;
    private javax.swing.JLabel SimpleRoomL;
    private javax.swing.JLabel SimpleRoomL1;
    private javax.swing.JLabel SimpleRoomL2;
    private javax.swing.JButton SingInB;
    private javax.swing.JTextField SubtotalTF;
    private javax.swing.JTextField SubtotalTF1;
    private javax.swing.JButton SwitchAccountB;
    private javax.swing.JTextField TotalDays;
    private javax.swing.JTextField TotalTF;
    private javax.swing.JTextField TotalTF1;
    private javax.swing.JTextField Type;
    private javax.swing.JLabel clientRegistVal;
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
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
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
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField searchField;
    private javax.swing.JTextField searchRoomsField;
    // End of variables declaration//GEN-END:variables
}
