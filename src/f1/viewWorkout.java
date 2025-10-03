/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package f1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tanishj52
 */
public class viewWorkout extends JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(viewWorkout.class.getName());
    private JTable workoutTable;
    private DefaultTableModel tableModel;
    private JButton closeButton;
    private final String[] COLUMN_NAMES = {"Workout", "Time", "Calories Burned"};
    /**
     * Creates new form viewWorkout
     * @param owner
     */
    private final int currentUserId;

  public viewWorkout(JFrame owner, int userId) {
    super(owner, "View Recorded Workouts", true);
    this.currentUserId = userId;
    setupDialogUI();
    loadDataFromDatabase();
}

    
    private void setupDialogUI() {
        setSize(650, 450); 
        setLocationRelativeTo(getOwner()); 
        setLayout(new BorderLayout(10, 10));

        Font sansSerifBold = new Font("SansSerif", Font.BOLD, 14);
        
        tableModel = new DefaultTableModel(COLUMN_NAMES, 0); 
        workoutTable = new JTable(tableModel);
        
        workoutTable.getTableHeader().setFont(sansSerifBold);
        workoutTable.getTableHeader().setBackground(new Color(70, 130, 180));
        workoutTable.getTableHeader().setForeground(Color.WHITE);
        workoutTable.setFont(new Font("SansSerif", Font.PLAIN, 12));
        workoutTable.setRowHeight(25);
        
        JScrollPane scrollPane = new JScrollPane(workoutTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10)); 
        
        add(scrollPane, BorderLayout.CENTER);

        closeButton = new JButton("Close");
        closeButton.setFont(sansSerifBold);
        closeButton.setPreferredSize(new Dimension(100, 30));
        closeButton.setBackground(new Color(220, 20, 60));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        
        closeButton.addActionListener(e -> dispose()); 

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        southPanel.add(closeButton);
        add(southPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    /**
     * This method simulates loading data. In a real app, this would query a database.
     */
    private void loadDataFromDatabase() {
    tableModel.setRowCount(0); // Clear previous data

    String sql = "SELECT workout_name, duration_mins, calories_burned FROM workouts WHERE user_id = ? ORDER BY id DESC";

    try (Connection conn = db.DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, currentUserId);   // filter by logged-in user
        ResultSet rs = ps.executeQuery();

        boolean hasData = false;
        while (rs.next()) {
            hasData = true;
            String workout = rs.getString("workout_name");
            int duration = rs.getInt("duration_mins");
            String time = duration + " mins";
            int calories = rs.getInt("calories_burned");

            tableModel.addRow(new Object[]{workout, time, calories});
        }

        if (!hasData) {
            JOptionPane.showMessageDialog(this, "No workouts found for this user!");
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading workouts from database!");
    }
}
//    private void loadDataFromDatabase() {
//   tableModel.setRowCount(0); // Clear previous data
//
//    // Use exact column names from your database
//    String sql = "SELECT workout_name, duration_mins, calories_burned FROM workouts ORDER BY id DESC";
//
//    try (Connection conn = db.DBConnection.getConnection();
//         Statement stmt = conn.createStatement();
//         ResultSet rs = stmt.executeQuery(sql)) {
//
//        while (rs.next()) {
//            String workout = rs.getString("workout_name");
//            int timeMinutes = rs.getInt("duration_mins");
//            String time = timeMinutes + " mins";
//            int calories = rs.getInt("calories_burned");           
//
//            Object[] row = {workout, time, calories};
//            tableModel.addRow(row);
//        }
//
//    } catch (SQLException ex) {
//        ex.printStackTrace();
//        JOptionPane.showMessageDialog(this, "Error loading workouts from database!");
//    }
//    try (Connection conn = db.DBConnection.getConnection();
//     Statement stmt = conn.createStatement();
//     ResultSet rs = stmt.executeQuery(sql)) {
//
//    boolean hasData = false;
//    while (rs.next()) {
//        hasData = true;
//        String workout = rs.getString("workout_name");
//        int duration = rs.getInt("duration_mins");
//        String time = duration + " mins";
//        int calories = rs.getInt("calories_burned");
//
//        System.out.println(workout + " | " + time + " | " + calories); // debug output
//        tableModel.addRow(new Object[]{workout, time, calories});
//    }
//
//    if (!hasData) {
//        JOptionPane.showMessageDialog(this, "No data found in the database!");
//    }
//
//} catch (SQLException ex) {
//    ex.printStackTrace();
//    JOptionPane.showMessageDialog(this, "Error loading workouts from database!");
//}
//}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // </editor-fold>
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
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
       int testUserId = 1; 
        new viewWorkout(null, testUserId).setVisible(true);  
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
