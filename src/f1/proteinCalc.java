/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package f1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author tanishj52
 */
public class proteinCalc extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(proteinCalc.class.getName());

    private JLabel titleLabel;
    private JLabel ageLabel, ageRangeLabel, genderLabel, heightLabel, weightLabel, exerciseTypeLabel;
    private JTextField ageField, heightField, weightField;
    private JRadioButton maleRadio, femaleRadio;
    private ButtonGroup genderGroup;
    private JComboBox<String> exerciseTypeCombo;
    private JButton calculateButton;

    private JLabel resultTitleLabel;
    private JLabel sedentaryProteinLabel;
    private JLabel activeProteinLabel;
    private JLabel intenseProteinLabel;
    private final int currentUserId;

    /**
     * Creates new form proteinCalc
     */
    public proteinCalc(int currentUserId) {
        this.currentUserId = currentUserId;
        UI();
    }

    private void UI() {
        setTitle("Protein Calculator");
        setSize(550, 680); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        imagePanel backgroundPanel = new imagePanel("/f1/images/bg3.jpg");
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new GridBagLayout());

        final Color textColor = Color.WHITE;
        final Color fieldTextColor = Color.BLACK;
        final Font formFont = new Font("SansSerif", Font.PLAIN, 16);
        final Font buttonFont = new Font("SansSerif", Font.BOLD, 14);

        titleLabel = new JLabel("Protein Calculator");
        ageLabel = new JLabel("Age :");
        ageRangeLabel = new JLabel("ages 15 - 80");
        genderLabel = new JLabel("Gender :");
        heightLabel = new JLabel("Height :");
        weightLabel = new JLabel("Weight :");
        exerciseTypeLabel = new JLabel("Exercise Type :");

        ageField = new JTextField(15);
        heightField = new JTextField(15);
        weightField = new JTextField(15);

        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        maleRadio.setSelected(true);

        String[] exerciseLevels = {"Sedentary", "Active", "Intense"};
        exerciseTypeCombo = new JComboBox<>(exerciseLevels);

        calculateButton = new JButton("Calculate");

        final Font resultFont = new Font("SansSerif", Font.BOLD, 18);

        resultTitleLabel = new JLabel("Protein Targets (Grams)");
        resultTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        resultTitleLabel.setForeground(Color.CYAN);

        sedentaryProteinLabel = new JLabel("Sedentary: ");
        activeProteinLabel = new JLabel("Active: ");
        intenseProteinLabel = new JLabel("Intense: ");

        sedentaryProteinLabel.setFont(resultFont);
        activeProteinLabel.setFont(resultFont);
        intenseProteinLabel.setFont(resultFont);
        sedentaryProteinLabel.setForeground(textColor);
        activeProteinLabel.setForeground(textColor);
        intenseProteinLabel.setForeground(textColor);

        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        titleLabel.setForeground(textColor);

        JLabel[] labels = {ageLabel, genderLabel, heightLabel, weightLabel, exerciseTypeLabel};
        for (JLabel label : labels) {
            label.setFont(formFont);
            label.setForeground(textColor);
        }
        ageRangeLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        ageRangeLabel.setForeground(textColor);

        JTextField[] fields = {ageField, heightField, weightField};
        for (JTextField field : fields) {
            field.setFont(formFont);
            field.setOpaque(false);
            field.setForeground(fieldTextColor);
            field.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, textColor));
        }

        JRadioButton[] radios = {maleRadio, femaleRadio};
        for (JRadioButton radio : radios) {
            radio.setFont(formFont);
            radio.setForeground(textColor);
            radio.setOpaque(false);
        }

        exerciseTypeCombo.setFont(formFont);
        exerciseTypeCombo.setForeground(fieldTextColor);

        calculateButton.setFont(buttonFont);
        calculateButton.setPreferredSize(new Dimension(150, 40));
        calculateButton.setBackground(new Color(60, 179, 113));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        //  Title 
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 10, 30, 10);
        backgroundPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 10, 8, 5);

        // Age 
        gbc.gridx = 0;
        gbc.gridy = 1;
        backgroundPanel.add(ageLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        JPanel agePanel = new JPanel(new GridBagLayout());
        agePanel.setOpaque(false);
        GridBagConstraints apc = new GridBagConstraints();
        apc.insets = new Insets(0, 0, 0, 10);
        apc.anchor = GridBagConstraints.WEST;
        apc.gridx = 0;
        apc.gridy = 0;
        apc.weightx = 0.7;
        apc.fill = GridBagConstraints.HORIZONTAL;
        agePanel.add(ageField, apc);
        apc.gridx = 1;
        apc.gridy = 0;
        apc.weightx = 0.3;
        apc.fill = GridBagConstraints.NONE;
        agePanel.add(ageRangeLabel, apc);
        backgroundPanel.add(agePanel, gbc);

        //Gender
        gbc.gridx = 0;
        gbc.gridy = 2;
        backgroundPanel.add(genderLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        genderPanel.setOpaque(false);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        backgroundPanel.add(genderPanel, gbc);

        // Height
        gbc.gridx = 0;
        gbc.gridy = 3;
        backgroundPanel.add(heightLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        JPanel heightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        heightPanel.setOpaque(false);
        heightPanel.add(heightField);
        JLabel cmLabel = new JLabel("cm");
        cmLabel.setFont(formFont);
        cmLabel.setForeground(textColor);
        heightPanel.add(cmLabel);
        backgroundPanel.add(heightPanel, gbc);

        // Weight
        gbc.gridx = 0;
        gbc.gridy = 4;
        backgroundPanel.add(weightLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        JPanel weightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        weightPanel.setOpaque(false);
        weightPanel.add(weightField);
        JLabel kgLabel = new JLabel("kg");
        kgLabel.setFont(formFont);
        kgLabel.setForeground(textColor);
        weightPanel.add(kgLabel);
        backgroundPanel.add(weightPanel, gbc);

        // Exercise Type 
        gbc.gridx = 0;
        gbc.gridy = 5;
        backgroundPanel.add(exerciseTypeLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        backgroundPanel.add(exerciseTypeCombo, gbc);

        //Calculate
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(30, 10, 30, 10); 
        backgroundPanel.add(calculateButton, gbc);

        // Result Title
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.insets = new Insets(15, 10, 15, 10);
        backgroundPanel.add(resultTitleLabel, gbc);

        // Sedentary
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST; 
        gbc.insets = new Insets(5, 50, 5, 10);
        backgroundPanel.add(sedentaryProteinLabel, gbc);

        // Active
        gbc.gridy = 9;
        backgroundPanel.add(activeProteinLabel, gbc);

        // Intense
        gbc.gridy = 10;
        backgroundPanel.add(intenseProteinLabel, gbc);

        calculateButton.addActionListener(e -> {
            try {
                
                double weight = Double.parseDouble(weightField.getText()); 

                if (weight <= 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid weight.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                final double SEDENTARY_MULTIPLIER = 0.8; // General population (RDA)
                final double ACTIVE_MULTIPLIER = 1.2;    // Regular exercise, endurance athletes
                final double INTENSE_MULTIPLIER = 1.8;   // Heavy strength training, bodybuilders

                int sedentaryProtein = (int) Math.round(weight * SEDENTARY_MULTIPLIER);
                int activeProtein = (int) Math.round(weight * ACTIVE_MULTIPLIER);
                int intenseProtein = (int) Math.round(weight * INTENSE_MULTIPLIER);

                sedentaryProteinLabel.setText("Sedentary: " + sedentaryProtein + " g/day (RDA)");
                activeProteinLabel.setText("Active: " + activeProtein + " g/day (General Fitness)");
                intenseProteinLabel.setText("Intense: " + intenseProtein + " g/day (Muscle Building)");
                
                // Goal nikaalo jo user ne comboBox se select kiya hai
        String goal = (String) exerciseTypeCombo.getSelectedItem();
        double proteinTarget = 0;

        if ("Sedentary".equals(goal)) proteinTarget = sedentaryProtein;
        else if ("Active".equals(goal)) proteinTarget = activeProtein;
        else if ("Intense".equals(goal)) proteinTarget = intenseProtein;

        try (Connection conn = db.DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO protein_records (user_id, weight, goal, protein_target) VALUES (?, ?, ?, ?)")) {

            ps.setInt(1, currentUserId);   // current login user ka id
            ps.setDouble(2, weight);       // entered weight
            ps.setString(3, goal);         // goal type
            ps.setDouble(4, proteinTarget);// calculated protein

            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Protein record saved successfully!");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving protein record.", "DB Error", JOptionPane.ERROR_MESSAGE);
        }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for Weight.", "Input Error", JOptionPane.ERROR_MESSAGE);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel6.setText("Exercise Type : ");

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel9.setText("kg");

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jButton1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jButton1.setText("Calculate");

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setToolTipText("feet");

        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel7.setText("ages 15 - 80");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jRadioButton1.setText("Male");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jRadioButton2.setText("Female");

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel8.setText("cm");

        jComboBox2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Exercise: 15-30mins", "Intense exercise: 45-120mins", "Very Intense exercise: 2+ hours" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("Protein Calculator");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setText("Age : ");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setText("Gender : ");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setText("Height : ");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel5.setText("Weight : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(148, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(156, 156, 156))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new proteinCalc(1).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
