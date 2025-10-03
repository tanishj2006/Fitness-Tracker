/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package f1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author tanishj52
 */
public class workoutTracker extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(workoutTracker.class.getName());
    private JLabel titleLabel;
    private JLabel workoutLabel, timeLabel, caloriesBurnedLabel;
    private JComboBox<String> workoutComboBox, timeComboBox;
    private JTextField caloriesBurnedField;
    private JButton saveButton, viewWorkoutsButton, closeButton;
    private final int currentUserId; 
    /**
     * Creates new form workoutTracker
     * @param userId
     */


    public workoutTracker(int userId) {
        this.currentUserId = userId; // store the logged-in user's ID
        UI();
    }

    private workoutTracker() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    private void UI() {
        setTitle("Workout Tracker");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

        imagePanel backgroundPanel = new imagePanel("/f1/images/bg6.jpg"); 
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout(20, 20)); 
        
        final Color textColor = Color.WHITE; 
        final Color fieldTextColor = Color.BLACK; 
        final Font formFont = new Font("SansSerif", Font.PLAIN, 16);
        final Font buttonFont = new Font("SansSerif", Font.BOLD, 14);

        titleLabel = new JLabel("Add Workout", SwingConstants.CENTER);
        workoutLabel = new JLabel("Workout :");
        timeLabel = new JLabel("Time :");
        caloriesBurnedLabel = new JLabel("Calories burned :");
        
        String[] workouts = {"Select","Running", "Weight Lifting", "Cycling", "Yoga"};
        String[] times = {"Select","30", "45", "60", "90"};
        workoutComboBox = new JComboBox<>(workouts);
        timeComboBox = new JComboBox<>(times);
        
        caloriesBurnedField = new JTextField(15); 
        
        saveButton = new JButton("Save");
        viewWorkoutsButton = new JButton("View Workouts");
        closeButton = new JButton("Close");
        
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        titleLabel.setForeground(textColor); 
        
        JLabel[] labels = {workoutLabel, timeLabel, caloriesBurnedLabel};
        for (JLabel label : labels) {
            label.setFont(formFont);
            label.setForeground(textColor);
        }
        
        JComboBox<?>[] combo_boxes = {workoutComboBox, timeComboBox};
        for (JComboBox<?> combo : combo_boxes) {
            combo.setFont(formFont);
            combo.setForeground(fieldTextColor); 
        }
        
        caloriesBurnedField.setFont(formFont);
        caloriesBurnedField.setOpaque(false);
        caloriesBurnedField.setForeground(fieldTextColor);
        caloriesBurnedField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE)); 
        
        Dimension buttonSize = new Dimension(140, 35);
        
        saveButton.setFont(buttonFont);
        saveButton.setPreferredSize(buttonSize);
        saveButton.setBackground(new Color(60, 179, 113));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        
        viewWorkoutsButton.setFont(buttonFont);
        viewWorkoutsButton.setPreferredSize(new Dimension(150, 35)); 
        viewWorkoutsButton.setBackground(new Color(70, 130, 180));
        viewWorkoutsButton.setForeground(Color.WHITE);
        viewWorkoutsButton.setFocusPainted(false);

        closeButton.setFont(buttonFont);
        closeButton.setPreferredSize(new Dimension(100, 35));
        closeButton.setBackground(new Color(220, 20, 60)); 
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);

        // Top Buttons 
        JPanel topButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15)); // Right aligned
        topButtonPanel.setOpaque(false);
        topButtonPanel.add(viewWorkoutsButton);
        topButtonPanel.add(closeButton);
        
        backgroundPanel.add(topButtonPanel, BorderLayout.NORTH); 

        //Central Form
        JPanel formContentPanel = new JPanel(new GridBagLayout());
        formContentPanel.setOpaque(false); 
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        
        gbc.gridwidth = 1; 
        gbc.anchor = GridBagConstraints.EAST; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.insets = new Insets(10, 5, 10, 5); 

        // Workout 
        gbc.gridx = 0; gbc.gridy = 1; formContentPanel.add(workoutLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1; formContentPanel.add(workoutComboBox, gbc);

        // Time
        gbc.gridx = 0; gbc.gridy = 2; formContentPanel.add(timeLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 2; formContentPanel.add(timeComboBox, gbc);

        // Calories Burned 
        gbc.gridx = 0; gbc.gridy = 3; formContentPanel.add(caloriesBurnedLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 3; formContentPanel.add(caloriesBurnedField, gbc);

        // Save Button
        gbc.gridx = 0; gbc.gridy = 4; 
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.fill = GridBagConstraints.NONE; 
        gbc.insets = new Insets(40, 5, 5, 5); 
        formContentPanel.add(saveButton, gbc);

        backgroundPanel.add(formContentPanel, BorderLayout.CENTER);
        
        saveButton.addActionListener(e -> {
            String workout = (String) workoutComboBox.getSelectedItem();
            String time = (String) timeComboBox.getSelectedItem();
            String calories = caloriesBurnedField.getText();
            System.out.println("Saving Workout: " + workout + " for " + time + ", Burned: " + calories);
            if(workout.equals("Select") || time.equals("Select") || calories.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            try {
                int timeInt = Integer.parseInt(time);
                int caloriesInt = Integer.parseInt(calories);

                String sql = "INSERT INTO workouts (user_id, workout_name, duration_mins, calories_burned) VALUES (?, ?, ?, ?)";
                try (Connection conn = db.DBConnection.getConnection();
                     PreparedStatement ps = conn.prepareStatement(sql)) {

                    ps.setInt(1, currentUserId);
                    ps.setString(2, workout);
                    ps.setInt(3, timeInt);
                    ps.setInt(4, caloriesInt);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Workout saved successfully!");

                    workoutComboBox.setSelectedIndex(0);
                    timeComboBox.setSelectedIndex(0);
                    caloriesBurnedField.setText("");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Time and Calories must be numbers!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error saving workout!");
            }
});
     
        viewWorkoutsButton.addActionListener(e -> {
        System.out.println("Opening View Workouts Window for user: " + currentUserId);
        viewWorkout dialog = new viewWorkout(this, currentUserId);  // ✅ pass userId
    dialog.setVisible(true);
});        
        closeButton.addActionListener(e -> {
            dispose();
        });

        setVisible(true);
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

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        jButton1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton2.setText("View Workouts");

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("Workout : ");

        jComboBox1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Chest", "Back", "Shoulders", "Biceps", "Triceps", "Abs", "Legs" }));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setText("Time : ");

        jComboBox2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Exercise: 15-30mins", "Intense exercise: 45-120mins", "Very Intense exercise: 2+ hours" }));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setText("Calories burned : ");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox2, 0, 240, Short.MAX_VALUE)
                    .addComponent(jTextField1))
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(220, 220, 220))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(53, 53, 53))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        java.awt.EventQueue.invokeLater(() -> new workoutTracker(1).setVisible(true));
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
         java.awt.EventQueue.invokeLater(() -> new workoutTracker(1).setVisible(true));

        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
