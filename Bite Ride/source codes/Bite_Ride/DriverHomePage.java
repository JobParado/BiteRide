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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author job parado
 */
public class DriverHomePage extends javax.swing.JFrame {

    DefaultTableModel model;
    DefaultTableModel model2;
    
    
    Dotenv dotenv = Dotenv.load();
    String url = dotenv.get("Database_URL");
    String user = dotenv.get("Admin_Name");
    String pass = dotenv.get("Admin_Password");
    
       /**
     * Creates new form HomePage
     */
    public DriverHomePage() {
        initComponents();
        model = (DefaultTableModel)bookingList.getModel();
        model2 = (DefaultTableModel)foodOrderList.getModel();
    }

     // fullname displayer for the homepage
    public void DisplayFullName(String firstname,String surname) {
        txtFullName.setText(firstname + " " + surname);
    }

    private String phoneNumber,firstname,surname,password,role;

    // one setter for account

    
    public void SetAccount(String phoneNumber,String firstname , String surname,String password,String role) {
        this.phoneNumber=phoneNumber;
        this.firstname=firstname;
        this.surname=surname;
        this.password=password;
        this.role=role;
    }

    
    public void displayUserBooking() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            Connection connection = DriverManager.getConnection(url, user, pass);
    
            String query = "SELECT * FROM booking";
            PreparedStatement statement = connection.prepareStatement(query);
    
            ResultSet resultSet = statement.executeQuery();
    
            model.setRowCount(0);
            cmbBookingList.removeAllItems();
            Set<String> uniqueBookingIds = new HashSet<>();
    
            while (resultSet.next()) {


                String fullname = resultSet.getString("userfullname");
                String UserphoneNumber = resultSet.getString("userphonenumber");
                String pickUp = resultSet.getString("pickuplocation");
                String dropOff = resultSet.getString("dropofflocation");
                String orderfood = resultSet.getString("orderfood");
                String cost = resultSet.getString("cost");
                String vehicle = resultSet.getString("vehicle");
                String bookingId = resultSet.getString("bookingid");
                String driverName= resultSet.getString("driverfullname");
                String driverNumber = resultSet.getString("drivernumber");
                
                String currentFullName = firstname + " " + surname;
                String currentPhoneNumber = phoneNumber;

                if(!(currentFullName.equals(fullname) && currentPhoneNumber.equals(UserphoneNumber))) {
                    model.insertRow(model.getRowCount(),new String[] {fullname,UserphoneNumber,pickUp,dropOff,orderfood,cost,vehicle,bookingId,driverName,driverNumber});
                    if (driverName == null && driverNumber == null) {
                        uniqueBookingIds.add(bookingId);
                    }             
                }
                

            }

            for (String uniqueBookingId : uniqueBookingIds) {
                cmbBookingList.addItem(uniqueBookingId);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public void displayUserFoodOrderList() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            Connection connection = DriverManager.getConnection(url, user, pass);
    
            String query = "SELECT * FROM foodorder";
            PreparedStatement statement = connection.prepareStatement(query);
    
            ResultSet resultSet = statement.executeQuery();
    
            model2.setRowCount(0);
    
            while (resultSet.next()) {
                String foodname = resultSet.getString("foodname");
                String orderAmount = resultSet.getString("orderamount");
                String variant = resultSet.getString("variant");
                String totalcost = resultSet.getString("totalcost");
                String usersFullName = resultSet.getString("userfullname");
                String usersPhoneNumber = resultSet.getString("userphonenumber");
                String bookingId = resultSet.getString("bookingid");
                String merchantName= resultSet.getString("merchantfullname");
                String merchantNumber = resultSet.getString("merchantnumber");
                String driverName= resultSet.getString("driverfullname");
                String driverNumber = resultSet.getString("drivernumber");


                String currentFullName = firstname + " " + surname;
                String currentPhoneNumber = phoneNumber;

                if(!(currentFullName.equals(usersFullName) && currentPhoneNumber.equals(usersPhoneNumber))) {
                    model2.insertRow(model2.getRowCount(),new String[] {usersFullName,usersPhoneNumber,foodname,variant,orderAmount,totalcost,bookingId,merchantName,merchantNumber,driverName,driverNumber});
                }
                
            }
    
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }


    public void insertAcceptedToBooking(String fullname,String phoneNumber) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, user, pass)) {
                String query = "UPDATE booking SET driverfullname = ?, drivernumber = ? WHERE bookingid = ?";
                
                try (PreparedStatement statement = connection.prepareStatement(query)) {

                    statement.setString(1, fullname);
                    statement.setString(2, phoneNumber);
                    statement.setString(3, cmbBookingList.getSelectedItem().toString());
                    
                    statement.executeUpdate();
                    
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAcceptedToOrder(String fullname,String phoneNumber) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, user, pass)) {
                String query = "UPDATE foodorder SET driverfullname = ?, drivernumber = ? WHERE bookingid = ?";
                
                try (PreparedStatement statement = connection.prepareStatement(query)) {

                    statement.setString(1, fullname);
                    statement.setString(2, phoneNumber);
                    statement.setString(3, cmbBookingList.getSelectedItem().toString());
                    
                    statement.executeUpdate();

                }
            }
        } catch (ClassNotFoundException | SQLException e) {
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
        white = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnRefresh = new javax.swing.JButton();
        userbookingtable = new javax.swing.JScrollPane();
        bookingList = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        userfoodordertable = new javax.swing.JScrollPane();
        foodOrderList = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        cmbBookingList = new javax.swing.JComboBox<>();
        btnAcceptBooking = new javax.swing.JButton();
        txtFullName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        userIcon = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnSettings = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Driver Home Page");
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        black.setBackground(new java.awt.Color(0, 0, 0));

        white.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnRefresh.setText("Refresh");
        btnRefresh.setFocusable(false);
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        bookingList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bookingList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User's Fullname", "User's Number", "Pickup", "Dropoff", "OrderFood", "Transportation Cost", "Vehicle", "Booking ID", "Driver fullname", "Driver Number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bookingList.getTableHeader().setResizingAllowed(false);
        bookingList.getTableHeader().setReorderingAllowed(false);
        userbookingtable.setViewportView(bookingList);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("User's Booking");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("User's Food Order");

        foodOrderList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        foodOrderList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User's Fullname", "User's Number", "Foodname", "Variant", "Orderamount", "Total Food Cost", "Booking ID", "Merchant FullName", "Merchant Number", "Driver FullName", "Driver Number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        foodOrderList.getTableHeader().setResizingAllowed(false);
        foodOrderList.getTableHeader().setReorderingAllowed(false);
        userfoodordertable.setViewportView(foodOrderList);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Choose Booking ID to Accept");

        btnAcceptBooking.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnAcceptBooking.setText("Accept Booking");
        btnAcceptBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptBookingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(userbookingtable, javax.swing.GroupLayout.DEFAULT_SIZE, 1295, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefresh)
                .addContainerGap())
            .addComponent(userfoodordertable, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1024, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbBookingList, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(btnAcceptBooking)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRefresh)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userbookingtable, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userfoodordertable, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbBookingList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAcceptBooking)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Jobs For You As Driver", jPanel1);

        javax.swing.GroupLayout whiteLayout = new javax.swing.GroupLayout(white);
        white.setLayout(whiteLayout);
        whiteLayout.setHorizontalGroup(
            whiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        whiteLayout.setVerticalGroup(
            whiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        txtFullName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtFullName.setForeground(new java.awt.Color(255, 255, 255));
        txtFullName.setText("firstname surname");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Feed Page");

        userIcon.setForeground(new java.awt.Color(255, 255, 255));
        userIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/userIcon.png"))); // NOI18N
        userIcon.setFocusable(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ready to assist our Customer?");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Check their booking now");

        btnSettings.setBackground(new java.awt.Color(0, 0, 0));
        btnSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/hiclipart.com.png"))); // NOI18N
        btnSettings.setBorderPainted(false);
        btnSettings.setContentAreaFilled(false);
        btnSettings.setFocusable(false);
        btnSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout blackLayout = new javax.swing.GroupLayout(black);
        black.setLayout(blackLayout);
        blackLayout.setHorizontalGroup(
            blackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(white, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(blackLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(blackLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(userIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(blackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFullName)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(115, 115, 115)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        blackLayout.setVerticalGroup(
            blackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blackLayout.createSequentialGroup()
                .addGroup(blackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(blackLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(blackLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(blackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(userIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(blackLayout.createSequentialGroup()
                                .addComponent(txtFullName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(white, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingsActionPerformed
        // TODO add your handling code here:
        
               
        this.dispose();
        SettingsPage settingsPage = new SettingsPage();
        
        settingsPage.SetAccount(phoneNumber, firstname, surname, password);
        settingsPage.DisplayAccountInformation();
        settingsPage.setRole(role);
        settingsPage.setVisible(true);
        txtFullName.setText("");
        
    }//GEN-LAST:event_btnSettingsActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:

        displayUserBooking();
        displayUserFoodOrderList();

    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnAcceptBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptBookingActionPerformed
        // TODO add your handling code here:
        try {
            int confirmation =JOptionPane.showConfirmDialog(null,"Accept This Booking ID ? " + cmbBookingList.getSelectedItem().toString());

        if(confirmation == 0) {
            insertAcceptedToBooking(firstname + " " + surname, phoneNumber);
            insertAcceptedToOrder(firstname + " ", phoneNumber);
            JOptionPane.showMessageDialog(null, "Booking Accepted!");
            displayUserBooking();
            displayUserFoodOrderList();
        }
        } catch (Exception e) {
        }
        

    }//GEN-LAST:event_btnAcceptBookingActionPerformed

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
            java.util.logging.Logger.getLogger(DriverHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DriverHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DriverHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DriverHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DriverHomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel black;
    private javax.swing.JTable bookingList;
    private javax.swing.JButton btnAcceptBooking;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSettings;
    private javax.swing.JComboBox<String> cmbBookingList;
    private javax.swing.JTable foodOrderList;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel txtFullName;
    private javax.swing.JButton userIcon;
    private javax.swing.JScrollPane userbookingtable;
    private javax.swing.JScrollPane userfoodordertable;
    private javax.swing.JPanel white;
    // End of variables declaration//GEN-END:variables
}
