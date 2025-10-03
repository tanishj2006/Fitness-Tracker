/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package f1;

import java.sql.Connection;
import db.DBConnection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 *
 * @author pearl
 */
public class Register extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Register.class.getName());

    private JLabel titleLabel;
    private JLabel nameLabel, passwordLabel, weightLabel, heightLabel;
    private JTextField nameField, weightField, heightField;
    private JPasswordField passwordField;
    private JButton submitButton;
    /**
     * Creates new form Login
     */
    public Register() {
        setupUI();
    }
    
    private void setupUI() {
        setTitle("Fitness Tracker - Register");
        setSize(500, 500); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

        imagePanel backgroundPanel = new imagePanel("/f1/images/bg1.jpg"); 
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new GridBagLayout()); 

        final Color textColor = Color.WHITE; 
        final Color fieldTextColor = Color.BLACK;
        final Color caretColor = Color.BLACK; 

        titleLabel = new JLabel("REGISTER");
        nameLabel = new JLabel("Name :");
        passwordLabel = new JLabel("Password :");
        weightLabel = new JLabel("Weight :");
        heightLabel = new JLabel("Height :");
        nameField = new JTextField(20); 
        passwordField = new JPasswordField(20);
        weightField = new JTextField(20);
        heightField = new JTextField(20);
        submitButton = new JButton("SUBMIT");

        titleLabel.setFont(new Font("SanSerif", Font.BOLD, 40));
        titleLabel.setForeground(textColor); 

        Font labelFont = new Font("SanSerif", Font.BOLD, 18);
        nameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
        weightLabel.setFont(labelFont);
        heightLabel.setFont(labelFont);
        nameLabel.setForeground(textColor);
        passwordLabel.setForeground(textColor);
        weightLabel.setForeground(textColor);
        heightLabel.setForeground(textColor);
        
        Font fieldFont = new Font("SanSerif", Font.BOLD, 18);
        nameField.setFont(fieldFont);
        passwordField.setFont(fieldFont);
        weightField.setFont(fieldFont);
        heightField.setFont(fieldFont);
        nameField.setOpaque(false);
        nameField.setForeground(fieldTextColor);
        nameField.setCaretColor(caretColor);
        
        passwordField.setOpaque(false);
        passwordField.setForeground(fieldTextColor);
        passwordField.setCaretColor(caretColor);
        
        weightField.setOpaque(false);
        weightField.setForeground(fieldTextColor);
        weightField.setCaretColor(caretColor);
        
        heightField.setOpaque(false);
        heightField.setForeground(fieldTextColor);
        heightField.setCaretColor(caretColor);

        submitButton.setPreferredSize(new Dimension(150, 40));
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        
        //Title 
        gbc.gridx = 0; gbc.gridy = 0; 
        gbc.gridwidth = 2; // Span 2 columns
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 10, 30, 10);
        backgroundPanel.add(titleLabel, gbc);
        
        gbc.gridwidth = 1; 
        gbc.anchor = GridBagConstraints.WEST; 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        //  Name 
        gbc.gridx = 0; gbc.gridy = 1; backgroundPanel.add(nameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1; backgroundPanel.add(nameField, gbc);

        // Password 
        gbc.gridx = 0; gbc.gridy = 2; backgroundPanel.add(passwordLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2; backgroundPanel.add(passwordField, gbc);

        // Weight
        gbc.gridx = 0; gbc.gridy = 3; backgroundPanel.add(weightLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 3; backgroundPanel.add(weightField, gbc);

        // Height 
        gbc.gridx = 0; gbc.gridy = 4; backgroundPanel.add(heightLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 4; backgroundPanel.add(heightField, gbc);

        //Submit
        gbc.gridx = 0; gbc.gridy = 5; 
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.fill = GridBagConstraints.NONE; 
        gbc.insets = new Insets(40, 10, 10, 10);
        backgroundPanel.add(submitButton, gbc);
        
        submitButton.addActionListener((ActionEvent e) -> {
            try {
                String name1 = nameField.getText();
                String password = new String(passwordField.getPassword());
                double weight = Double.parseDouble(weightField.getText());
                double height1 = Double.parseDouble(heightField.getText());
                if (name1.isEmpty() || password.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Name and password cannot be empty!");
                    return;
                }
                int userId = registerUser(name1, password, weight, height1);
                if (userId != -1) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Registration successful! User ID: " + userId);
                    
                    // After successful registration -> move to login
                    Login loginPage = new Login();
                    loginPage.setVisible(true);
                    dispose();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Registration failed! Maybe username already exists.");
                }
            }catch (NumberFormatException ex) {
                javax.swing.JOptionPane.showMessageDialog(null, "Weight and height must be numbers.");
            }catch (HeadlessException ex) {
                ex.printStackTrace();
            }
        });

        setVisible(true);
        revalidate();
        repaint();
    }
    
  public static int registerUser(String name, String password, double weight, double height) {
    int userId = -1;
    String sql = "INSERT INTO users (name, password, weight, height) VALUES (?, ?, ?, ?)";
    try (Connection conn = db.DBConnection.getConnection();
         java.sql.PreparedStatement ps = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

        ps.setString(1, name);
        ps.setString(2, password);
        ps.setDouble(3, weight);
        ps.setDouble(4, height);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            try (var rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    userId = rs.getInt(1); // Auto-generated user id (unique per user)
                }
            }
        }

        System.out.println("User registered with ID: " + userId);

    } catch (Exception e) {
        e.printStackTrace();
    }
    return userId;
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel1.setText("Name :");

        jLabel2.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel2.setText("Password :");

        jLabel3.setFont(new java.awt.Font("Book Antiqua", 1, 24)); // NOI18N
        jLabel3.setText("REGISTER");

        jLabel4.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel4.setText("Weight :");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel5.setText("Height :");

        jButton1.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jButton1.setText("SUBMIT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                .addComponent(jTextField3)
                                .addComponent(jTextField4))
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(56, 56, 56))
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jButton1)
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            String name = jTextField1.getText(); 
            String password = jTextField2.getText(); 
            double weight = Double.parseDouble(jTextField3.getText());
            double height = Double.parseDouble(jTextField4.getText()); 
        } catch(Exception e) {}
        mainWindow main = new mainWindow();
        main.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Register().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
