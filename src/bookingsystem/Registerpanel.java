/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

/**
 *
 * @author Keth Dominic
 */
public class Registerpanel extends javax.swing.JFrame {

    /**
     * Creates new form Loginpanel
     */
    public Registerpanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        regPanel = new javax.swing.JPanel();
        reg2TF = new javax.swing.JLabel();
        jbTF = new javax.swing.JLabel();
        fnTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lnTF = new javax.swing.JPasswordField();
        reg3Btn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        unTF = new javax.swing.JTextField();
        passTF = new javax.swing.JPasswordField();
        rpassTF = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        ageTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        maleTF = new javax.swing.JRadioButton();
        femaleTF = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        birthTF = new javax.swing.JTextField();
        addTF = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        login2Btn = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 10)); // NOI18N
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        regPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        reg2TF.setText("Register");
        regPanel.add(reg2TF, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, -1, 21));

        jbTF.setText("JBHotel");
        regPanel.add(jbTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 57, 30));

        fnTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnTFActionPerformed(evt);
            }
        });
        regPanel.add(fnTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 187, -1));

        jLabel3.setText("first name");
        regPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        jLabel4.setText("Last name");
        regPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));
        regPanel.add(lnTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 187, -1));

        reg3Btn.setText("Register");
        reg3Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reg3Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reg3BtnActionPerformed(evt);
            }
        });
        regPanel.add(reg3Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 490, -1, -1));

        jLabel5.setText("Username");
        regPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));
        regPanel.add(unTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 187, -1));
        regPanel.add(passTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 187, -1));
        regPanel.add(rpassTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 187, -1));

        jLabel6.setText("Age");
        regPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, -1, -1));
        regPanel.add(ageTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 163, -1));

        jLabel7.setText("Gender");
        regPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, -1, -1));

        maleTF.setText("Male");
        regPanel.add(maleTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, -1, -1));

        femaleTF.setText("Female");
        regPanel.add(femaleTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, -1, -1));

        jLabel8.setText("Password");
        regPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        jLabel9.setText("Re-type password");
        regPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, -1, -1));

        jLabel10.setText("Birthdate");
        regPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, -1, -1));

        birthTF.setToolTipText("mm/dd/yyyy");
        birthTF.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        regPanel.add(birthTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 163, -1));
        regPanel.add(addTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 163, -1));

        jLabel11.setText("Address");
        regPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, -1, -1));

        login2Btn.setText("Login instead");
        login2Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        login2Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                login2BtnMouseClicked(evt);
            }
        });
        regPanel.add(login2Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 530, -1, -1));

        getContentPane().add(regPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 479, 570));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void fnTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fnTFActionPerformed

    private void reg3BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reg3BtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reg3BtnActionPerformed

    private void login2BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login2BtnMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new Loginpanel().setVisible(true); // Main Form to show after the Login Form..
    }//GEN-LAST:event_login2BtnMouseClicked

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registerpanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registerpanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registerpanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registerpanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registerpanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addTF;
    private javax.swing.JTextField ageTF;
    private javax.swing.JTextField birthTF;
    private javax.swing.JRadioButton femaleTF;
    private javax.swing.JTextField fnTF;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jbTF;
    private javax.swing.JPasswordField lnTF;
    private javax.swing.JLabel login2Btn;
    private javax.swing.JRadioButton maleTF;
    private javax.swing.JPasswordField passTF;
    private javax.swing.JLabel reg2TF;
    private javax.swing.JButton reg3Btn;
    private javax.swing.JPanel regPanel;
    private javax.swing.JPasswordField rpassTF;
    private javax.swing.JTextField unTF;
    // End of variables declaration//GEN-END:variables
}
