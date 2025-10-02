/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package f1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
 * @author tanishj52
 */
public class Login extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Login.class.getName());

    private JLabel titleLabel;
    private JLabel nameLabel, passwordLabel;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton submitButton;

    /**
     * Creates new form Login
     */
    public Login() {
        setupUI();
    }
    
    private void setupUI() {
        setTitle("Fitness Tracker - Login");
        setSize(500, 350); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

        imagePanel backgroundPanel = new imagePanel("/f1/images/bg1.jpg"); 
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new GridBagLayout()); 

        final Color textColor = Color.WHITE; 
        final Color fieldTextColor = Color.BLACK; 
        final Color caretColor = Color.BLACK; 

        titleLabel = new JLabel("LOGIN"); 
        nameLabel = new JLabel("Name :");
        passwordLabel = new JLabel("Password :");
        nameField = new JTextField(20); 
        passwordField = new JPasswordField(20);
        submitButton = new JButton("SUBMIT");
       
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 40));
        titleLabel.setForeground(textColor); 
        
        Font labelFont = new Font("SansSerif", Font.BOLD, 18); 
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(textColor);
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(textColor);
        
        Font fieldFont = new Font("SansSerif", Font.BOLD, 18);
        
        nameField.setFont(fieldFont);
        
        nameField.setOpaque(false);
        nameField.setForeground(fieldTextColor);
        nameField.setCaretColor(caretColor);
        
        passwordField.setFont(fieldFont);
        passwordField.setOpaque(false);
        passwordField.setForeground(fieldTextColor);
        passwordField.setCaretColor(caretColor);
        
        submitButton.setPreferredSize(new Dimension(150, 40));
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));

        GridBagConstraints gbc = new GridBagConstraints();
        
        // Title 
        gbc.gridx = 0; gbc.gridy = 0; 
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 10, 30, 10); 
        backgroundPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1; 
        gbc.anchor = GridBagConstraints.WEST; 
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); 

        // Name
        gbc.gridx = 0; gbc.gridy = 1; backgroundPanel.add(nameLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1; backgroundPanel.add(nameField, gbc);

        // Password 
        gbc.gridx = 0; gbc.gridy = 2; backgroundPanel.add(passwordLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2; backgroundPanel.add(passwordField, gbc);

        // Submit
        gbc.gridx = 0; gbc.gridy = 3; 
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.fill = GridBagConstraints.NONE; 
        gbc.insets = new Insets(40, 10, 10, 10); 
        backgroundPanel.add(submitButton, gbc);
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get data from fields
                String name = nameField.getText();
                String password = new String(passwordField.getPassword());
                
                System.out.println("Attempting to login for: " + name);
                
                // Add your actual login logic (validation, check credentials) here.
                
                // Example: Open main application window upon successful login
                // if (loginSuccess) {
                     mainWindow mainApp = new mainWindow(); // Assuming you have a main window
                     mainApp.setVisible(true);
                     dispose(); 
                // }
            }
        });

        setVisible(true);
        revalidate();
        repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel2.setText("Name :");

        jLabel3.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel3.setText("Password :");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("Login");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setText("Name :");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel5.setText("Password :");

        jButton1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jButton1.setText("Submit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44))
            .addGroup(layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(152, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(153, 153, 153))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
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
