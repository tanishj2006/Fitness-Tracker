/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package f1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 *
 * @author pearl
 */
public class mainWindow extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(mainWindow.class.getName());

    private JButton profileBtn, logoutBtn;
    private JButton mealTrackerBtn, workoutTrackerBtn, calorieCalcBtn, proteinCalcBtn;
    private JPanel dashboardPanel;
    private JLabel consumedLabel;
    private JLabel burnedLabel;
    private JLabel netLabel;
    private JLabel proteinLabel;   

    /**
     * Creates new form mainWindow
     */
    private final int currentUserId;  
    public mainWindow(int userId) {
        this.currentUserId = userId;  
        setupUI();
    }
     public mainWindow() {
        this(-1); 
    }
    private void setupUI() {
        setTitle("Fitness Tracker - Dashboard");
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        imagePanel backgroundPanel = new imagePanel("/f1/images/bg4.jpg");
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout());

        JPanel navPanel = createNavigationPanel();
        navPanel.setOpaque(false); 
        backgroundPanel.add(navPanel, BorderLayout.NORTH);

        JPanel contentPanel = createContentPanel();
        contentPanel.setOpaque(false); 
        backgroundPanel.add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createNavigationPanel() {
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10)); // Align right

        Font navFont = new Font("SansSerif", Font.BOLD, 14);
        Dimension navSize = new Dimension(100, 30);
        Color textColor = Color.WHITE;

        profileBtn = new JButton("Profile");
        profileBtn.setFont(navFont);
        profileBtn.setPreferredSize(navSize);
        profileBtn.setForeground(textColor);
        profileBtn.setOpaque(false);
        profileBtn.setContentAreaFilled(false); // Transparent background for a sleek look
        profileBtn.setBorder(BorderFactory.createLineBorder(textColor));

        logoutBtn = new JButton("Logout");
        logoutBtn.setFont(navFont);
        logoutBtn.setPreferredSize(navSize);
        logoutBtn.setForeground(textColor);
        logoutBtn.setOpaque(false);
        logoutBtn.setContentAreaFilled(false);
        logoutBtn.setBorder(BorderFactory.createLineBorder(textColor));

//    profileBtn.addActionListener(e -> {
//            try (Connection conn = db.DBConnection.getConnection();
//                 PreparedStatement ps = conn.prepareStatement("SELECT name, height, weight FROM users WHERE id = ?")) {
//
//                ps.setInt(1, currentUserId);
//                ResultSet rs = ps.executeQuery();
//                if (rs.next()) {
//                    String name = rs.getString("name");
//                    String height = rs.getDouble("height") + " cm";
//                    String weight = rs.getDouble("weight") + " kg";
//
//                    Profile pf = new Profile(this, name, height, weight);
//                    pf.setVisible(true);
//                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        });   
profileBtn.addActionListener(e -> {
    // Open Profile dialog; Profile will fetch user data itself using userId
    // Use mainWindow.this to pass the outer JFrame as owner (not the ActionListener)
    Profile pf = new Profile(mainWindow.this, currentUserId);
    pf.setVisible(true);
});

         logoutBtn.addActionListener(e -> {
    this.dispose(); 
    new Login().setVisible(true); 
});
     navPanel.add(profileBtn);
    navPanel.add(logoutBtn);
        return navPanel;
 }
   
    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new GridBagLayout());

        Font buttonFont = new Font("SansSerif", Font.BOLD, 18);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.weightx = 1.0; 
        gbc.weighty = 0.1; 

        mealTrackerBtn = new JButton("Meal Tracker");
        workoutTrackerBtn = new JButton("Workout Tracker");
        calorieCalcBtn = new JButton("Calorie Calculator");
        proteinCalcBtn = new JButton("Protein Calculator");

        JButton[] buttons = {mealTrackerBtn, workoutTrackerBtn, calorieCalcBtn, proteinCalcBtn};

for (int i = 0; i < buttons.length; i++) {
    JButton btn = buttons[i];
    btn.setFont(buttonFont);

    
    btn.setBackground(Color.BLACK);                        
    btn.setForeground(Color.WHITE);                        
    btn.setOpaque(true);                                  
    btn.setContentAreaFilled(true);                       
    btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); 
    btn.setFocusPainted(false);                            
    btn.setFocusable(false);                               

    gbc.gridx = i % 2;
    gbc.gridy = i / 2;
    contentPanel.add(btn, gbc);

    btn.addActionListener(e -> {
        String buttonText = btn.getText();
        JFrame targetFrame = null;

        switch (buttonText) {
            case "Meal Tracker":
                targetFrame = new mealTracker(currentUserId);
                break;
            case "Workout Tracker":
                targetFrame = new workoutTracker(currentUserId);
                break;
            case "Calorie Calculator":
                targetFrame = new calorieCalc(currentUserId);
                break;
            case "Protein Calculator":
                targetFrame = new proteinCalc(currentUserId);
                break;
        }

        if (targetFrame != null) {
            targetFrame.setVisible(true);
        }
    });
}


        dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding inside panel
        dashboardPanel.setBackground(new Color(255, 255, 255, 220)); // Semi-transparent white

        JPanel dataPanel = new JPanel(new GridBagLayout());
        dataPanel.setOpaque(false);

        Font titleFont = new Font("SansSerif", Font.BOLD, 22);
        Font dataFont = new Font("SansSerif", Font.BOLD, 18);

        GridBagConstraints dgc = new GridBagConstraints();
        dgc.insets = new Insets(10, 10, 10, 10);
        dgc.fill = GridBagConstraints.HORIZONTAL;
        dgc.anchor = GridBagConstraints.WEST;

        JLabel dashboardTitle = new JLabel("Dashboard Summary", SwingConstants.CENTER);
        dashboardTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        dashboardPanel.add(dashboardTitle, BorderLayout.NORTH);

        consumedLabel = new JLabel("Consumed: ---");
        burnedLabel = new JLabel("Burned: ---");
        netLabel = new JLabel("Net: ---");
        proteinLabel = new JLabel("Protein: ---");

        consumedLabel.setFont(dataFont);
        burnedLabel.setFont(dataFont);
        netLabel.setFont(dataFont);
        proteinLabel.setFont(dataFont);
           
        proteinLabel.setForeground(Color.BLACK);
     
        dataPanel.add(proteinLabel, dgc);
        dgc.gridy = 3;
        dgc.gridx = 0;
        dgc.gridy = 0;
        dataPanel.add(consumedLabel, dgc);
        dgc.gridx = 0;
        dgc.gridy = 1;
        dataPanel.add(burnedLabel, dgc);

        dgc.gridy = 2; 
        dgc.insets = new Insets(20, 10, 10, 10); 
        dataPanel.add(netLabel, dgc);

        dashboardPanel.add(dataPanel, BorderLayout.CENTER);

        updateDashboardSummary();

        
        gbc.gridx = 0;
        gbc.gridy = 2; 
        gbc.gridwidth = 2; 
        gbc.weighty = 0.9; 

        contentPanel.add(dashboardPanel, gbc);

        return contentPanel;
    }

    public void updateDashboardSummary() {
    int totalConsumed = 0;
    int totalBurned = 0;
    String proteinSummary = "---";

    try (Connection conn = db.DBConnection.getConnection()) {

        // Meals → Calories Consumed
        String mealSql = "SELECT COALESCE(SUM(calories), 0) FROM meals WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(mealSql)) {
            ps.setInt(1, currentUserId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalConsumed = rs.getInt(1);
            }
        }

        // Workouts → Calories Burned
        String workoutSql = "SELECT COALESCE(SUM(calories_burned), 0) FROM workouts WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(workoutSql)) {
            ps.setInt(1, currentUserId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalBurned = rs.getInt(1);
            }
        }

        // Protein Records → Latest Protein Calculation
        String proteinSql = "SELECT sedentary_protein, active_protein, intense_protein " +
                            "FROM protein_records WHERE user_id = ? ORDER BY id DESC LIMIT 1";
        try (PreparedStatement ps = conn.prepareStatement(proteinSql)) {
            ps.setInt(1, currentUserId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                proteinSummary = rs.getInt("sedentary_protein") + " - " +
                                 rs.getInt("intense_protein") + " g/day";
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    int netCalories = totalConsumed - totalBurned;
    Color netColor = (netCalories >= 0) ? new Color(220, 20, 60) : new Color(60, 179, 113);

    consumedLabel.setText("Consumed: " + totalConsumed + " kcal");
    burnedLabel.setText("Burned: " + totalBurned + " kcal");
    netLabel.setText("Net Calories: " + netCalories + " kcal");
    netLabel.setForeground(netColor);
    proteinLabel.setText("Protein Target: " + proteinSummary);
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jFrame1 = new javax.swing.JFrame();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 386, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jTextField1.setText("jTextField1");

        jLabel1.setText("HELLO ");

        jPanel1.setBackground(java.awt.Color.white);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel3.setText("jLabel3");

        jLabel2.setText("jLabel2");

        jInternalFrame2.setVisible(true);

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 214, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Profile");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jButton3.setText("Calorie Calculator");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jButton4.setText("Protein Calculator");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jButton5.setText("Meal Tracker");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jButton6.setText("Workout Tracker");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(java.awt.Color.white);

        jLabel4.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        jLabel4.setText("Dashboard");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        new calorieCalc(currentUserId).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
         new workoutTracker(currentUserId).setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        new mealTracker(currentUserId).setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         new proteinCalc(currentUserId).setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         Profile pf = new Profile(this, currentUserId);
        pf.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            /* silently ignore */ }
        /* Create and display the form */
      java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
