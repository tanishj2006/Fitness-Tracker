/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package f1;

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
import javax.swing.JTextField;

/**
 *
 * @author pearl
 */
public class mealTracker extends javax.swing.JFrame {

    private JLabel titleLabel;
    private JLabel mealLabel, caloriesLabel, dateLabel;
    private JTextField mealField, caloriesField, dateField;
    private JButton saveButton, viewMealsButton, closeButton;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(mealTracker.class.getName());

    /**
     * Creates new form mealTracker
     */
    public mealTracker() {
        UI();
    }

    private void UI() {
        setTitle("Meal Tracker");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        imagePanel backgroundPanel = new imagePanel("/f1/images/bg5.jpg");
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout(20, 20));

        final Color textColor = Color.BLACK;
        final Color fieldTextColor = Color.BLACK;
        final Color caretColor = Color.BLACK;
        final Font formFont = new Font("SansSerif", Font.PLAIN, 16);
        final Font buttonFont = new Font("SansSerif", Font.BOLD, 14);

        titleLabel = new JLabel("Add Meal");
        mealLabel = new JLabel("Meal :");
        caloriesLabel = new JLabel("Calories :");
        dateLabel = new JLabel("Date (DD/MM/YYYY) :");

        mealField = new JTextField(20);
        caloriesField = new JTextField(20);
        dateField = new JTextField(20);

        saveButton = new JButton("Save");
        viewMealsButton = new JButton("View Meals");
        closeButton = new JButton("Close");

        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        titleLabel.setForeground(textColor);

        JLabel[] labels = {mealLabel, caloriesLabel, dateLabel};
        JTextField[] fields = {mealField, caloriesField, dateField};

        for (JLabel label : labels) {
            label.setFont(formFont);
            label.setForeground(textColor);
        }

        for (JTextField field : fields) {
            field.setFont(formFont);
            field.setOpaque(false);
            field.setForeground(fieldTextColor);
            field.setCaretColor(caretColor);
            field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        }

        Dimension buttonSize = new Dimension(120, 35);

        saveButton.setFont(buttonFont);
        saveButton.setPreferredSize(buttonSize);
        saveButton.setBackground(new Color(60, 179, 113));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);

        viewMealsButton.setFont(buttonFont);
        viewMealsButton.setPreferredSize(buttonSize);
        viewMealsButton.setBackground(new Color(70, 130, 180));
        viewMealsButton.setForeground(Color.WHITE);
        viewMealsButton.setFocusPainted(false);

        closeButton.setFont(buttonFont);
        closeButton.setPreferredSize(buttonSize);
        closeButton.setBackground(new Color(220, 20, 60));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);

        JPanel topButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        topButtonPanel.setOpaque(false);
        topButtonPanel.add(viewMealsButton);
        topButtonPanel.add(closeButton);

        backgroundPanel.add(topButtonPanel, BorderLayout.NORTH);

        JPanel formContentPanel = new JPanel(new GridBagLayout());
        formContentPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 30, 0);
        formContentPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 10, 5);

        // Meal 
        gbc.gridx = 0;
        gbc.gridy = 1;
        formContentPanel.add(mealLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formContentPanel.add(mealField, gbc);

        // Calories
        gbc.gridx = 0;
        gbc.gridy = 2;
        formContentPanel.add(caloriesLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formContentPanel.add(caloriesField, gbc);

        // Date 
        gbc.gridx = 0;
        gbc.gridy = 3;
        formContentPanel.add(dateLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formContentPanel.add(dateField, gbc);

        // Save Button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(40, 5, 5, 5);
        formContentPanel.add(saveButton, gbc);

        backgroundPanel.add(formContentPanel, BorderLayout.CENTER);

        saveButton.addActionListener(e -> {
            System.out.println("Saving Meal: " + mealField.getText() + ", Calories: " + caloriesField.getText() + ", Date: " + dateField.getText());
            // Add your meal saving logic here (validation, database write)
        });

        viewMealsButton.addActionListener(e -> {
            System.out.println("Opening View Meals Window...");
            viewMeals dialog = new viewMeals(this);
            dialog.setVisible(true);
        });

        closeButton.addActionListener(e -> {
            dispose(); // Close the current window
            // If mainWindoe is the parent, you might want to show it again if it was hidden:
             if (getOwner() instanceof mainWindow) {
                 getOwner().setVisible(true);
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        jPopupMenu1.setToolTipText("Meal Added");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton2.setText("View Meals");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Add Meal : ");

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Calories : ");

        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("Date : ");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jButton3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jButton3.setText("Save");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(jButton3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(16, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                        .addComponent(jTextField1)))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(65, 65, 65)
                .addComponent(jButton3)
                .addGap(51, 51, 51))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
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
        java.awt.EventQueue.invokeLater(() -> new mealTracker().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
