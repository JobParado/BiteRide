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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author job parado
 */
public class UserTransactionPage extends javax.swing.JFrame {

    DefaultTableModel model;
    DefaultTableModel model2;
    
       /**
     * Creates new form HomePage
     */
    public UserTransactionPage() {
        initComponents();
        model = (DefaultTableModel)bookingList.getModel();
        model2 = (DefaultTableModel)foodOrderList.getModel();
    }

     // fullname displayer for the homepage

    private String phoneNumber,firstname,surname,password;

    // one setter for account

    
    public void SetAccount(String phoneNumber,String firstname , String surname,String password) {
        this.phoneNumber=phoneNumber;
        this.firstname=firstname;
        this.surname=surname;
        this.password=password;
    }

    Dotenv dotenv = Dotenv.load();
    String url = dotenv.get("Database_URL");
    String user = dotenv.get("Admin_Name");
    String pass = dotenv.get("Admin_Password");
    
    public void displayUserBooking() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            Connection connection = DriverManager.getConnection(url, user, pass);
    
            String query = "SELECT * FROM booking";
            PreparedStatement statement = connection.prepareStatement(query);
    
            ResultSet resultSet = statement.executeQuery();
    
            model.setRowCount(0);

    
            while (resultSet.next()) {

                String fullname = resultSet.getString("userfullname");
                String userphoneNumber = resultSet.getString("userphonenumber");
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
                
                if(currentFullName.equals(fullname) && currentPhoneNumber.equals(userphoneNumber)) {
                    model.insertRow(model.getRowCount(),new String[] {fullname,userphoneNumber,pickUp,dropOff,orderfood,cost,vehicle,bookingId,driverName,driverNumber});
                }
                


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
                String totalcost = resultSet.getString("totalcost");
                String usersFullName = resultSet.getString("userfullname");
                String usersPhoneNumber = resultSet.getString("userphonenumber");
                String bookingId = resultSet.getString("bookingid");
                String variant= resultSet.getString("variant");
                String merchantName= resultSet.getString("merchantfullname");
                String merchantNumber = resultSet.getString("merchantnumber");
                String driverName= resultSet.getString("driverfullname");
                String driverNumber = resultSet.getString("drivernumber");
                
                String currentFullName = firstname + " " + surname;
                String currentPhoneNumber = phoneNumber;


                if(currentFullName.equals(usersFullName) && currentPhoneNumber.equals(usersPhoneNumber)) {
                    model2.insertRow(model2.getRowCount(),new String[] {usersFullName,usersPhoneNumber,foodname,variant,orderAmount,totalcost,bookingId,merchantName,merchantNumber,driverName,driverNumber});
                }
                
            }
    
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
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
        btnBack = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Transaction Page");
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
        jLabel5.setText("Booking's List");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Food Order's List");

        foodOrderList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        foodOrderList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User's Fullname", "User's Number", "Foodname", "Variant", "Orderamount", "Totalcost", "Booking ID", "Merchant FullName", "Merchant Number", "Driver FullName", "Driver Number"
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

        btnBack.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(userbookingtable, javax.swing.GroupLayout.DEFAULT_SIZE, 1216, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefresh)
                .addContainerGap())
            .addComponent(userfoodordertable, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 945, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
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
                .addComponent(userfoodordertable, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Transaction", jPanel1);

        javax.swing.GroupLayout whiteLayout = new javax.swing.GroupLayout(white);
        white.setLayout(whiteLayout);
        whiteLayout.setHorizontalGroup(
            whiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        whiteLayout.setVerticalGroup(
            whiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, whiteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Transaction Page");

        javax.swing.GroupLayout blackLayout = new javax.swing.GroupLayout(black);
        black.setLayout(blackLayout);
        blackLayout.setHorizontalGroup(
            blackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(white, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(blackLayout.createSequentialGroup()
                .addGap(411, 411, 411)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        blackLayout.setVerticalGroup(
            blackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(blackLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(white, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(black, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:

        displayUserBooking();
        displayUserFoodOrderList();

    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:

        this.dispose();

        UserHomePage userHomePage = new UserHomePage();
        userHomePage.SetAccount(phoneNumber, firstname, surname, password);
        userHomePage.DisplayFullName(firstname, surname);
        userHomePage.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

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
            java.util.logging.Logger.getLogger(UserTransactionPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserTransactionPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserTransactionPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserTransactionPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserTransactionPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel black;
    private javax.swing.JTable bookingList;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JTable foodOrderList;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JScrollPane userbookingtable;
    private javax.swing.JScrollPane userfoodordertable;
    private javax.swing.JPanel white;
    // End of variables declaration//GEN-END:variables
}
