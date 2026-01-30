/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bite_Ride;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author job Parado
 */
public class UserCompleteBookingWithFood extends javax.swing.JFrame {
    
    DefaultTableModel model,model2;
    
    /**
     * Creates new form UserCompleteBooking
     */
    
    
    public UserCompleteBookingWithFood() {
        initComponents();
        model = (DefaultTableModel)orderFoodList.getModel();
        model2 = (DefaultTableModel)currentOrderList.getModel();
        tab.setEnabledAt(1, false);
    }

    Dotenv dotenv = Dotenv.load();
    String url = dotenv.get("Database_URL");
    String user = dotenv.get("Admin_Name");
    String pass = dotenv.get("Admin_Password");

    private String phoneNumber,firstname,surname,password;
    private String vehicle,pickUp,dropOff;
    private int transportationCost;
    private int overAllCost = 0;
    private String generatedID;

    

    // one setter for account
    public void SetAccount(String phoneNumber,String firstname , String surname,String password) {
        this.phoneNumber=phoneNumber;
        this.firstname=firstname;
        this.surname=surname;
        this.password=password;
    }
    public void SetCost(String vehicle) {
        if(vehicle.equals("MotoDash")) {
            transportationCost+=20;
        } else if(vehicle.equals("CarDash")) {
            transportationCost+=60;
        }
    }
    
    public void setBookingWithFood(String vehicle,String pickUp,String dropOff,int transportationCost) {
        this.vehicle = vehicle;
        this.pickUp=pickUp;
        this.dropOff=dropOff;
        this.transportationCost=transportationCost;
    }
    public void setText() {
        txtVehicle.setText(vehicle);
        txtDropOff.setText(dropOff);
        txtPickUp.setText(pickUp);
    }


    int limitOrderFood =0;
    int currentOrdered;


    

    public void setOrderLimit(String vehicle) {
        if("MotoDash".equals(vehicle)) {
            limitOrderFood=20;
        } else if("CarDash".equals(vehicle)) {
            limitOrderFood=40;
        }
    }

    public void giveUsFeedBack(String feedback) {
        try {
            if (feedback.length() >= 1) {
                Class.forName("com.mysql.cj.jdbc.Driver");
    
                try (Connection connection = DriverManager.getConnection(url, user, pass)) {
                    String query = "INSERT INTO feedback (fullname, phonenumber, message) VALUES (?, ?, ?)";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setString(1, firstname + " " + surname);
                        statement.setString(2, phoneNumber);
                        statement.setString(3, feedback);
    
                        int rowsInserted = statement.executeUpdate();
                        if (rowsInserted > 0) {
                            JOptionPane.showMessageDialog(null, "Thanks for your feedback!");
                        }
                    }
                } catch (SQLException e) {
                }
            }
        } catch (Exception e) {
        }
    }

    public void insertFoodOrderToDataBase(JTable table) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, user, pass)) {
                String query = "INSERT INTO foodorder (foodname, orderamount, totalcost, userfullname, userphonenumber, bookingid, variant) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int rowCount = model.getRowCount();
                    for (int row = 0; row < rowCount; row++) {
                        String foodName = (String) model.getValueAt(row, 0);
                        String orderAmount = (String) model.getValueAt(row, 2);
                        String totalCost = (String) model.getValueAt(row, 3);
                        String variant = (String) model.getValueAt(row, 1);

                        statement.setString(1, foodName);
                        statement.setString(2, orderAmount);
                        
                        statement.setString(3, totalCost);
                        statement.setString(4, firstname + " " + surname);
                        statement.setString(5, phoneNumber);
                        statement.setString(6, generatedID);
                        statement.setString(7, variant);

                        statement.addBatch();
                    }
                    statement.executeBatch();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void generatedID() {

        Random random = new Random();
        int num1 = random.nextInt(10);
        int num2 = random.nextInt(10);
        int num3 = random.nextInt(10);
        int num4 = random.nextInt(10);
        int num5 = random.nextInt(10);
        int num6 = random.nextInt(10);

        String generated= num1 + "" + num2 + "" + num3 + "" + num4 + "" + num5 + "" + num6;
        generatedID=generated;
    }
    
    public void insertBookingToDataBase() {

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            String query = "INSERT INTO booking (userfullname, userphonenumber, pickuplocation, dropofflocation, orderfood, cost, vehicle,bookingid) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, firstname + " " + surname);
                statement.setString(2, phoneNumber);
                statement.setString(3, pickUp);
                statement.setString(4, dropOff);
                statement.setString(5, "yes");
                statement.setString(6, Integer.toString(transportationCost));
                statement.setString(7, vehicle);
                statement.setString(8, generatedID);
                    
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        black = new javax.swing.JPanel();
        tab = new javax.swing.JTabbedPane();
        OrderFoodTab = new javax.swing.JPanel();
        foodTabs = new javax.swing.JTabbedPane();
        jollibee = new javax.swing.JPanel();
        icon2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnFood1 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        b1 = new javax.swing.JSpinner();
        a1 = new javax.swing.JSpinner();
        jLabel26 = new javax.swing.JLabel();
        b2 = new javax.swing.JSpinner();
        a2 = new javax.swing.JSpinner();
        jLabel23 = new javax.swing.JLabel();
        btnFood2 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        icon10 = new javax.swing.JLabel();
        icon11 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        btnFood3 = new javax.swing.JButton();
        a3 = new javax.swing.JSpinner();
        b3 = new javax.swing.JSpinner();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        icon12 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        btnFood4 = new javax.swing.JButton();
        a4 = new javax.swing.JSpinner();
        b4 = new javax.swing.JSpinner();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        KFC = new javax.swing.JPanel();
        icon3 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        b5 = new javax.swing.JSpinner();
        a5 = new javax.swing.JSpinner();
        btnFood5 = new javax.swing.JButton();
        icon13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        b6 = new javax.swing.JSpinner();
        a6 = new javax.swing.JSpinner();
        btnFood6 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        icon14 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        b8 = new javax.swing.JSpinner();
        jLabel50 = new javax.swing.JLabel();
        a8 = new javax.swing.JSpinner();
        btnFood8 = new javax.swing.JButton();
        b7 = new javax.swing.JSpinner();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        icon15 = new javax.swing.JLabel();
        btnFood7 = new javax.swing.JButton();
        a7 = new javax.swing.JSpinner();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        currentOrderTab = new javax.swing.JPanel();
        scrollBarForCurrentOrder = new javax.swing.JScrollPane();
        currentOrderList = new javax.swing.JTable();
        btnCancel2 = new javax.swing.JButton();
        btnConfirmOrder = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        ConfirmBookingTab = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderFoodList = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        btnConfirmBooking = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtPickUp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDropOff = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtVehicle = new javax.swing.JTextField();
        btnCancel1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtDropOff1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTransportationCost = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtFoodCost = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtOverAllCost = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Complete Your Booking With Package");
        setFocusable(false);
        setResizable(false);

        black.setBackground(new java.awt.Color(0, 0, 0));

        tab.setBackground(new java.awt.Color(255, 255, 255));
        tab.setFocusable(false);
        tab.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        OrderFoodTab.setBackground(new java.awt.Color(255, 255, 255));

        foodTabs.setFocusable(false);
        foodTabs.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jollibee.setBackground(new java.awt.Color(255, 255, 255));
        jollibee.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        icon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tunaPie.jpg"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setText("Light Foods");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setText("Dessert And Drinks");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Variants:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Original ₱45");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Spicy    ₱49");

        btnFood1.setText("Add");
        btnFood1.setFocusable(false);
        btnFood1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFood1ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("Add Order     Variant           Amount");

        b1.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        a1.setModel(new javax.swing.SpinnerListModel(new String[] {"Original", "Spicy"}));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Name:      Tuna Pie");

        b2.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        a2.setModel(new javax.swing.SpinnerListModel(new String[] {"Regular", "Large"}));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Add Order     Variant           Amount");

        btnFood2.setText("Add");
        btnFood2.setFocusable(false);
        btnFood2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFood2ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Large    ₱64");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Regular ₱48");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Name: Peach Mango Pie");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Variants:");

        icon10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/peachMangoPie.jpg"))); // NOI18N

        icon11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/pineappleJuice.jpg"))); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("Add Order     Variant           Amount");

        btnFood3.setText("Add");
        btnFood3.setFocusable(false);
        btnFood3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFood3ActionPerformed(evt);
            }
        });

        a3.setModel(new javax.swing.SpinnerListModel(new String[] {"Medium", "Large"}));

        b3.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Large    ₱75");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("Medium ₱64");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Variants:");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setText("Name: Pineapple Juice");

        icon12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/iceCreamSunDae.jpg"))); // NOI18N

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("Add Order     Variant           Amount");

        btnFood4.setText("Add");
        btnFood4.setFocusable(false);
        btnFood4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFood4ActionPerformed(evt);
            }
        });

        a4.setModel(new javax.swing.SpinnerListModel(new String[] {"Mini", "Regular"}));

        b4.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText("Regular   ₱40");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setText("Mini        ₱27");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel36.setText("Variants:");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel37.setText("Name: Ice cream Sundae");

        javax.swing.GroupLayout jollibeeLayout = new javax.swing.GroupLayout(jollibee);
        jollibee.setLayout(jollibeeLayout);
        jollibeeLayout.setHorizontalGroup(
            jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jollibeeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jollibeeLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel13)
                        .addGap(216, 216, 216))
                    .addGroup(jollibeeLayout.createSequentialGroup()
                        .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jollibeeLayout.createSequentialGroup()
                                .addGap(307, 307, 307)
                                .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jollibeeLayout.createSequentialGroup()
                                        .addComponent(btnFood4)
                                        .addGap(18, 18, 18)
                                        .addComponent(a4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jollibeeLayout.createSequentialGroup()
                                        .addComponent(icon12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jollibeeLayout.createSequentialGroup()
                                                .addComponent(jLabel36)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel34)
                                                    .addComponent(jLabel35)))
                                            .addComponent(jLabel37)))))
                            .addGroup(jollibeeLayout.createSequentialGroup()
                                .addComponent(icon11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jollibeeLayout.createSequentialGroup()
                                        .addComponent(jLabel31)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel30)))
                                    .addComponent(jLabel32)))
                            .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jollibeeLayout.createSequentialGroup()
                                .addComponent(btnFood3)
                                .addGap(18, 18, 18)
                                .addComponent(a3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jollibeeLayout.createSequentialGroup()
                                .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jollibeeLayout.createSequentialGroup()
                                        .addComponent(btnFood1)
                                        .addGap(18, 18, 18)
                                        .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jollibeeLayout.createSequentialGroup()
                                            .addComponent(icon2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jollibeeLayout.createSequentialGroup()
                                                    .addComponent(jLabel15)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel17)))
                                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(b1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jollibeeLayout.createSequentialGroup()
                                            .addComponent(icon10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(jollibeeLayout.createSequentialGroup()
                                                        .addComponent(jLabel25)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel20)))
                                                    .addComponent(b2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLabel27))))
                                    .addGroup(jollibeeLayout.createSequentialGroup()
                                        .addComponent(btnFood2)
                                        .addGap(18, 18, 18)
                                        .addComponent(a2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(40, 40, 40))))
            .addGroup(jollibeeLayout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(jLabel12)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jollibeeLayout.setVerticalGroup(
            jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jollibeeLayout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(29, 29, 29)
                .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jollibeeLayout.createSequentialGroup()
                        .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(icon2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jollibeeLayout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFood1)
                            .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jollibeeLayout.createSequentialGroup()
                        .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(icon10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jollibeeLayout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFood2)
                            .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jollibeeLayout.createSequentialGroup()
                        .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(icon11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jollibeeLayout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFood3)
                            .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jollibeeLayout.createSequentialGroup()
                        .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(icon12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jollibeeLayout.createSequentialGroup()
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jollibeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFood4)
                            .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        foodTabs.addTab("Jollibee", jollibee);

        KFC.setBackground(new java.awt.Color(255, 255, 255));

        icon3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/mashedPotato.jpg"))); // NOI18N

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("Name: Mashed Potato");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Regular ₱60");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Variants:");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("Large    ₱85");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setText("Add Order     Variant           Amount");

        b5.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        a5.setModel(new javax.swing.SpinnerListModel(new String[] {"Regular", "Large"}));

        btnFood5.setText("Add");
        btnFood5.setFocusable(false);
        btnFood5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFood5ActionPerformed(evt);
            }
        });

        icon13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/crispyFries.jpg"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setText("Light Foods");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("Name:       Crispy Fries");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel42.setText("Variants:");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("Regular ₱60");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Large    ₱95");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setText("Add Order     Variant           Amount");

        b6.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        a6.setModel(new javax.swing.SpinnerListModel(new String[] {"Regular", "Large", "Junior bucket"}));

        btnFood6.setText("Add");
        btnFood6.setFocusable(false);
        btnFood6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFood6ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel19.setText("Dessert And Drinks");

        icon14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icedBlackCoffee.jpg"))); // NOI18N

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setText("Name: Iced Black Coffe");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setText("Variant:");

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel49.setText("Regular   ₱60");

        b8.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setText("Add Order     Variant           Amount");

        a8.setModel(new javax.swing.SpinnerListModel(new String[] {"Regular"}));

        btnFood8.setText("Add");
        btnFood8.setFocusable(false);
        btnFood8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFood8ActionPerformed(evt);
            }
        });

        b7.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setText("Add Order     Variant           Amount");

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel52.setText("         Medium   ₱75");

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setText("Regular ₱64");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setText("Name: Unsweetened Ice tea");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setText("Variant:");

        icon15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/unsweetened iced tea.jpg"))); // NOI18N

        btnFood7.setText("Add");
        btnFood7.setFocusable(false);
        btnFood7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFood7ActionPerformed(evt);
            }
        });

        a7.setModel(new javax.swing.SpinnerListModel(new String[] {"Regular", "Medium", "Large"}));

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setText("    Junior bucket ₱130");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setText("Large    ₱75");

        javax.swing.GroupLayout KFCLayout = new javax.swing.GroupLayout(KFC);
        KFC.setLayout(KFCLayout);
        KFCLayout.setHorizontalGroup(
            KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KFCLayout.createSequentialGroup()
                .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(KFCLayout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jLabel14))
                    .addGroup(KFCLayout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(jLabel19))
                    .addGroup(KFCLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(KFCLayout.createSequentialGroup()
                                .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(KFCLayout.createSequentialGroup()
                                        .addComponent(icon15, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(KFCLayout.createSequentialGroup()
                                                .addComponent(jLabel55)
                                                .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(KFCLayout.createSequentialGroup()
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel53))
                                                    .addGroup(KFCLayout.createSequentialGroup()
                                                        .addGap(6, 6, 6)
                                                        .addComponent(jLabel57))))
                                            .addComponent(jLabel54)
                                            .addGroup(KFCLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(jLabel52))))
                                    .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(KFCLayout.createSequentialGroup()
                                        .addComponent(btnFood7)
                                        .addGap(18, 18, 18)
                                        .addComponent(a7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(125, 125, 125)
                                .addComponent(a6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(KFCLayout.createSequentialGroup()
                                .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(KFCLayout.createSequentialGroup()
                                        .addComponent(btnFood5)
                                        .addGap(18, 18, 18)
                                        .addComponent(a5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(KFCLayout.createSequentialGroup()
                                            .addComponent(icon3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(KFCLayout.createSequentialGroup()
                                                        .addComponent(jLabel16)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel18)))
                                                    .addComponent(b5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLabel38)))))
                                .addGap(76, 76, 76)
                                .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnFood6)
                                    .addGroup(KFCLayout.createSequentialGroup()
                                        .addComponent(icon13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel56)
                                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, KFCLayout.createSequentialGroup()
                                                .addComponent(jLabel42)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel43))
                                            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KFCLayout.createSequentialGroup()
                                .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(KFCLayout.createSequentialGroup()
                                        .addComponent(icon14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(KFCLayout.createSequentialGroup()
                                                .addComponent(jLabel47)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel49))
                                            .addComponent(jLabel46)))
                                    .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(KFCLayout.createSequentialGroup()
                                                .addGap(193, 193, 193)
                                                .addComponent(b8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(KFCLayout.createSequentialGroup()
                                                .addComponent(btnFood8)
                                                .addGap(18, 18, 18)
                                                .addComponent(a8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(KFCLayout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(11, 11, 11)))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        KFCLayout.setVerticalGroup(
            KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KFCLayout.createSequentialGroup()
                .addComponent(jLabel14)
                .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(KFCLayout.createSequentialGroup()
                        .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(KFCLayout.createSequentialGroup()
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(icon13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFood6)
                            .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(KFCLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(icon3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(KFCLayout.createSequentialGroup()
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFood5)
                            .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(KFCLayout.createSequentialGroup()
                        .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(icon15, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(KFCLayout.createSequentialGroup()
                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFood7)
                            .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(KFCLayout.createSequentialGroup()
                        .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(icon14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(KFCLayout.createSequentialGroup()
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(KFCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFood8)
                            .addComponent(b8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(a8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        foodTabs.addTab("KFC", KFC);

        currentOrderTab.setBackground(new java.awt.Color(255, 255, 255));

        currentOrderList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Food Name", "Variant", "Order Amount", "Total Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollBarForCurrentOrder.setViewportView(currentOrderList);

        btnCancel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancel2.setText("Remove Selected");
        btnCancel2.setFocusable(false);
        btnCancel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout currentOrderTabLayout = new javax.swing.GroupLayout(currentOrderTab);
        currentOrderTab.setLayout(currentOrderTabLayout);
        currentOrderTabLayout.setHorizontalGroup(
            currentOrderTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentOrderTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(currentOrderTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollBarForCurrentOrder, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, currentOrderTabLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancel2)))
                .addContainerGap())
        );
        currentOrderTabLayout.setVerticalGroup(
            currentOrderTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentOrderTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollBarForCurrentOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancel2)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        foodTabs.addTab("My Current Order Food List", currentOrderTab);

        btnConfirmOrder.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnConfirmOrder.setText("Confirm Food Order");
        btnConfirmOrder.setFocusable(false);
        btnConfirmOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmOrderActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.setFocusable(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OrderFoodTabLayout = new javax.swing.GroupLayout(OrderFoodTab);
        OrderFoodTab.setLayout(OrderFoodTabLayout);
        OrderFoodTabLayout.setHorizontalGroup(
            OrderFoodTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(foodTabs)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OrderFoodTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConfirmOrder)
                .addContainerGap())
        );
        OrderFoodTabLayout.setVerticalGroup(
            OrderFoodTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OrderFoodTabLayout.createSequentialGroup()
                .addComponent(foodTabs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OrderFoodTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmOrder)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        tab.addTab("Order Food", OrderFoodTab);

        ConfirmBookingTab.setBackground(new java.awt.Color(255, 255, 255));

        orderFoodList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Food name", "Variant", "Order Amount", "Total Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orderFoodList.getTableHeader().setResizingAllowed(false);
        orderFoodList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(orderFoodList);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Pickup Location");

        btnConfirmBooking.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnConfirmBooking.setText("Confirm Booking");
        btnConfirmBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmBookingActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Food Order List");

        txtPickUp.setEditable(false);
        txtPickUp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Dropoff Location");

        txtDropOff.setEditable(false);
        txtDropOff.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Mode of Transportation");

        txtVehicle.setEditable(false);
        txtVehicle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        btnCancel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancel1.setText("Cancel");
        btnCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Payment Method:");

        txtDropOff1.setEditable(false);
        txtDropOff1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtDropOff1.setText("Cash On Delivery");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Transportation:");

        txtTransportationCost.setEditable(false);
        txtTransportationCost.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTransportationCost.setText("Cost");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("All Food Order:");

        txtFoodCost.setEditable(false);
        txtFoodCost.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtFoodCost.setText("Cost");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("       Total Cost:");

        txtOverAllCost.setEditable(false);
        txtOverAllCost.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtOverAllCost.setText("Cost");

        javax.swing.GroupLayout ConfirmBookingTabLayout = new javax.swing.GroupLayout(ConfirmBookingTab);
        ConfirmBookingTab.setLayout(ConfirmBookingTabLayout);
        ConfirmBookingTabLayout.setHorizontalGroup(
            ConfirmBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfirmBookingTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ConfirmBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ConfirmBookingTabLayout.createSequentialGroup()
                        .addGroup(ConfirmBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ConfirmBookingTabLayout.createSequentialGroup()
                                .addComponent(btnCancel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnConfirmBooking))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                            .addComponent(txtPickUp)
                            .addComponent(txtDropOff)
                            .addComponent(txtVehicle)
                            .addGroup(ConfirmBookingTabLayout.createSequentialGroup()
                                .addGroup(ConfirmBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addGroup(ConfirmBookingTabLayout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtOverAllCost, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(ConfirmBookingTabLayout.createSequentialGroup()
                        .addGroup(ConfirmBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addGroup(ConfirmBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ConfirmBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDropOff1)
                            .addComponent(txtTransportationCost)
                            .addComponent(txtFoodCost, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 329, Short.MAX_VALUE))))
        );
        ConfirmBookingTabLayout.setVerticalGroup(
            ConfirmBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfirmBookingTabLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPickUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDropOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(ConfirmBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDropOff1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ConfirmBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTransportationCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ConfirmBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtFoodCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ConfirmBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtOverAllCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(ConfirmBookingTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmBooking)
                    .addComponent(btnCancel1))
                .addContainerGap())
        );

        tab.addTab("Confirm Your Booking", ConfirmBookingTab);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Order Your Food And Confirm Your Booking");

        javax.swing.GroupLayout blackLayout = new javax.swing.GroupLayout(black);
        black.setLayout(blackLayout);
        blackLayout.setHorizontalGroup(
            blackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, blackLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(29, 29, 29))
        );
        blackLayout.setVerticalGroup(
            blackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, blackLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(black, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(black, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
        // TODO add your handling code here:

        this.dispose();
        UserHomePage userHomePage = new UserHomePage();
        userHomePage.SetAccount(phoneNumber, firstname, surname, password);
        userHomePage.DisplayFullName(firstname, surname);
        userHomePage.setVisible(true);
    }//GEN-LAST:event_btnCancel1ActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        
          
        this.dispose();
        UserHomePage userHomePage = new UserHomePage();
        userHomePage.SetAccount(phoneNumber, firstname, surname, password);
        userHomePage.DisplayFullName(firstname, surname);
        userHomePage.setVisible(true);
        
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnConfirmOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmOrderActionPerformed
        // TODO add your handling code here:

        int rowCount = currentOrderList.getRowCount();
        

        if(!(rowCount > 0)) {
            JOptionPane.showMessageDialog(null,"No Food Order  Order Something");
            
        } else {
             DefaultTableModel model = (DefaultTableModel) currentOrderList.getModel();
            
        List<String> columnValues1 = new ArrayList<>();
            
        for (int i = 0; i < model.getRowCount(); i++) {
            Object value = model.getValueAt(i, 2);
            columnValues1.add(String.valueOf(value));
        }


        int totalOrder= 0;

        for (String value : columnValues1) {
            totalOrder += Integer.parseInt(value);
        }

        currentOrdered=totalOrder;

        if(currentOrdered > limitOrderFood) {
            JOptionPane.showMessageDialog(null, "Limit Exceeded : " + limitOrderFood);

        } else if(currentOrdered <= limitOrderFood) {

        DefaultTableModel sModel = (DefaultTableModel) currentOrderList.getModel();
        DefaultTableModel destinationModel = (DefaultTableModel) orderFoodList.getModel();

        destinationModel.setRowCount(0);

        for (int i = 0; i < sModel.getRowCount(); i++) {
            Object[] rowData = new Object[sModel.getColumnCount()];
            for (int j = 0; j < sModel.getColumnCount(); j++) {
                rowData[j] = sModel.getValueAt(i, j);
            }
            destinationModel.addRow(rowData);
        }
        tab.setSelectedIndex(1);
        tab.setEnabledAt(0, false);
        tab.setEnabledAt(1, true); 
        txtTransportationCost.setText(Integer.toString(transportationCost));
        
        List<String> columnValues = new ArrayList<>();
            
        for (int i = 0; i < model.getRowCount(); i++) {
            Object value = model.getValueAt(i, 3);
            columnValues.add(String.valueOf(value));
        }

        int sum= 0;

        for (String value : columnValues) {
            sum += Integer.parseInt(value);
        }
        
        txtFoodCost.setText(Integer.toString(sum));
        overAllCost+=sum;
        overAllCost+=transportationCost;
        txtOverAllCost.setText(Integer.toString(overAllCost));
        }
        }    
    }//GEN-LAST:event_btnConfirmOrderActionPerformed

    private void btnConfirmBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmBookingActionPerformed
        // TODO add your handling code here:
        int confirmation = JOptionPane.showConfirmDialog(null,"Confirm Booking ?");


        if(confirmation == 0) {
            generatedID();

            try {
                insertBookingToDataBase();
                insertFoodOrderToDataBase(orderFoodList);
                String  feedback = JOptionPane.showInputDialog("Order Submitted Consider Giving us Feedback ?");
                giveUsFeedBack(feedback);
            } catch (Exception e) {
            }

            this.dispose();
            UserHomePage userHomePage = new UserHomePage();
            userHomePage.SetAccount(phoneNumber, firstname, surname, password);
            userHomePage.DisplayFullName(firstname, surname);
            userHomePage.setVisible(true);  
        }
    }//GEN-LAST:event_btnConfirmBookingActionPerformed

    private void btnCancel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel2ActionPerformed
        // TODO add your handling code here:
        try {
            int choosed = currentOrderList.getSelectedRow();
            model2.removeRow(choosed);
        } catch(Exception e){
        }
    }//GEN-LAST:event_btnCancel2ActionPerformed

    private void btnFood1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFood1ActionPerformed
        // TODO add your handling code here:

        int foodCost = 0;

        if(a1.getValue().toString().equals("Original")) { foodCost=45; }
        else if(a1.getValue().toString().equals("Spicy")) { foodCost=49; };

        int amountOfOrder = Integer.parseInt(b1.getValue().toString());
        int totalCost = foodCost * amountOfOrder;

        model2.insertRow(model2.getRowCount(), new String[]{"Tuna Pie",a1.getValue().toString(),b1.getValue().toString(),Integer.toString(totalCost)});
        
        
    }//GEN-LAST:event_btnFood1ActionPerformed

    private void btnFood2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFood2ActionPerformed
        // TODO add your handling code here:

        int foodCost = 0;

        if(a2.getValue().toString().equals("Regular")) { foodCost=48; }
        else if(a2.getValue().toString().equals("Large")) { foodCost=64; };

        int amountOfOrder = Integer.parseInt(b2.getValue().toString());
        int totalCost = foodCost * amountOfOrder;

        model2.insertRow(model2.getRowCount(), new String[]{"Peach Mango Pie",a2.getValue().toString(),b2.getValue().toString(),Integer.toString(totalCost)});

    }//GEN-LAST:event_btnFood2ActionPerformed

    private void btnFood3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFood3ActionPerformed
        // TODO add your handling code here:

        int foodCost = 0;

        if(a3.getValue().toString().equals("Medium")) { foodCost=64; }
        else if(a3.getValue().toString().equals("Large")) { foodCost=75; };

        int amountOfOrder = Integer.parseInt(b3.getValue().toString());
        int totalCost = foodCost * amountOfOrder;

        model2.insertRow(model2.getRowCount(), new String[]{"Pineapple Juice",a3.getValue().toString(),b3.getValue().toString(),Integer.toString(totalCost)});

    }//GEN-LAST:event_btnFood3ActionPerformed

    private void btnFood4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFood4ActionPerformed
        // TODO add your handling code here:

        int foodCost = 0;

        if(a4.getValue().toString().equals("Mini")) { foodCost=27; }
        else if(a4.getValue().toString().equals("Regular")) { foodCost=40; };

        int amountOfOrder = Integer.parseInt(b4.getValue().toString());
        int totalCost = foodCost * amountOfOrder;

        model2.insertRow(model2.getRowCount(), new String[]{"Ice cream Sundae",a4.getValue().toString(),b4.getValue().toString(),Integer.toString(totalCost)});

    }//GEN-LAST:event_btnFood4ActionPerformed

    private void btnFood5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFood5ActionPerformed
        // TODO add your handling code here:

        int foodCost = 0;

        if(a5.getValue().toString().equals("Regular")) { foodCost=60; }
        else if(a5.getValue().toString().equals("Large")) { foodCost=85; };

        int amountOfOrder = Integer.parseInt(b5.getValue().toString());
        int totalCost = foodCost * amountOfOrder;

        model2.insertRow(model2.getRowCount(), new String[]{"Mashed Potato",a5.getValue().toString(),b5.getValue().toString(),Integer.toString(totalCost)});

    }//GEN-LAST:event_btnFood5ActionPerformed

    private void btnFood6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFood6ActionPerformed
        // TODO add your handling code here:

        int foodCost = 0;

        if(a6.getValue().toString().equals("Regular")) { foodCost=60; }
        else if(a6.getValue().toString().equals("Large")) { foodCost=95; }
        else if(a6.getValue().toString().equals("Junior bucket")) { foodCost=130; };

        int amountOfOrder = Integer.parseInt(b6.getValue().toString());
        int totalCost = foodCost * amountOfOrder;

        model2.insertRow(model2.getRowCount(), new String[]{"Crispy Fries",a6.getValue().toString(),b6.getValue().toString(),Integer.toString(totalCost)});

    }//GEN-LAST:event_btnFood6ActionPerformed

    private void btnFood7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFood7ActionPerformed
        // TODO add your handling code here:

        
        int foodCost = 0;

        if(a7.getValue().toString().equals("Regular")) { foodCost=70; }
        else if(a7.getValue().toString().equals("Medium")) { foodCost=80; }
        else if(a7.getValue().toString().equals("Large")) { foodCost=85; };

        int amountOfOrder = Integer.parseInt(b7.getValue().toString());
        int totalCost = foodCost * amountOfOrder;

        model2.insertRow(model2.getRowCount(), new String[]{"Unsweetened Ice tea",a7.getValue().toString(),b7.getValue().toString(),Integer.toString(totalCost)});

    }//GEN-LAST:event_btnFood7ActionPerformed

    private void btnFood8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFood8ActionPerformed
        // TODO add your handling code here:

        
        int foodCost = 0;

        if(a8.getValue().toString().equals("Regular")) { foodCost=60; }


        int amountOfOrder = Integer.parseInt(b8.getValue().toString());
        int totalCost = foodCost * amountOfOrder;

        model2.insertRow(model2.getRowCount(), new String[]{"Iced Black Coffe",a8.getValue().toString(),b8.getValue().toString(),Integer.toString(totalCost)});

    }//GEN-LAST:event_btnFood8ActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserCompleteBookingWithFood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserCompleteBookingWithFood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserCompleteBookingWithFood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserCompleteBookingWithFood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserCompleteBookingWithFood().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ConfirmBookingTab;
    private javax.swing.JPanel KFC;
    private javax.swing.JPanel OrderFoodTab;
    private javax.swing.JSpinner a1;
    private javax.swing.JSpinner a2;
    private javax.swing.JSpinner a3;
    private javax.swing.JSpinner a4;
    private javax.swing.JSpinner a5;
    private javax.swing.JSpinner a6;
    private javax.swing.JSpinner a7;
    private javax.swing.JSpinner a8;
    private javax.swing.JSpinner b1;
    private javax.swing.JSpinner b2;
    private javax.swing.JSpinner b3;
    private javax.swing.JSpinner b4;
    private javax.swing.JSpinner b5;
    private javax.swing.JSpinner b6;
    private javax.swing.JSpinner b7;
    private javax.swing.JSpinner b8;
    private javax.swing.JPanel black;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCancel1;
    private javax.swing.JButton btnCancel2;
    private javax.swing.JButton btnConfirmBooking;
    private javax.swing.JButton btnConfirmOrder;
    private javax.swing.JButton btnFood1;
    private javax.swing.JButton btnFood2;
    private javax.swing.JButton btnFood3;
    private javax.swing.JButton btnFood4;
    private javax.swing.JButton btnFood5;
    private javax.swing.JButton btnFood6;
    private javax.swing.JButton btnFood7;
    private javax.swing.JButton btnFood8;
    private javax.swing.JTable currentOrderList;
    private javax.swing.JPanel currentOrderTab;
    private javax.swing.JTabbedPane foodTabs;
    private javax.swing.JLabel icon10;
    private javax.swing.JLabel icon11;
    private javax.swing.JLabel icon12;
    private javax.swing.JLabel icon13;
    private javax.swing.JLabel icon14;
    private javax.swing.JLabel icon15;
    private javax.swing.JLabel icon2;
    private javax.swing.JLabel icon3;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jollibee;
    private javax.swing.JTable orderFoodList;
    private javax.swing.JScrollPane scrollBarForCurrentOrder;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTextField txtDropOff;
    private javax.swing.JTextField txtDropOff1;
    private javax.swing.JTextField txtFoodCost;
    private javax.swing.JTextField txtOverAllCost;
    private javax.swing.JTextField txtPickUp;
    private javax.swing.JTextField txtTransportationCost;
    private javax.swing.JTextField txtVehicle;
    // End of variables declaration//GEN-END:variables
}
