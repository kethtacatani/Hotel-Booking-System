/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Keth Dominic
 */
public class EditProfileIFrame extends javax.swing.JInternalFrame {
    
    String birth, gender;
    String id;

    /**
     * Creates new form EditProfileFrame
     */
    QueryProcessor dbprocess;
    
    public EditProfileIFrame() {
        initComponents();
        dbprocess = new QueryProcessor ();
    }
    
    public void setInfoToTextField(String userId){
        id = userId;
        fnTF1.setText(dbprocess.getSpecificField("Select `user_fname` from `users_info` where `user_id` ='"+id+"'"));
        lnTF.setText(dbprocess.getSpecificField("Select `user_lname` from `users_info` where `user_id` ='"+id+"'"));
        addTF.setText(dbprocess.getSpecificField("Select `user_address` from `users_info` where `user_id` ='"+id+"'"));
        ageTF1.setText(dbprocess.getSpecificField("Select `user_age` from `users_info` where `user_id` ='"+id+"'"));
        unTF.setText(dbprocess.getSpecificField("Select `user_user` from `users_info` where `user_id` ='"+id+"'"));
        passTF1.setText(dbprocess.getSpecificField("Select `user_pass` from `users_info` where `user_id` ='"+id+"'"));
        rpassTF.setText(dbprocess.getSpecificField("Select `user_pass` from `users_info` where `user_id` ='"+id+"'"));
        emailTF.setText(dbprocess.getSpecificField("Select `user_email` from `users_info` where `user_id` ='"+id+"'"));
        
        if (maleBtn.getText().equals(dbprocess.getSpecificField("Select `user_gender` from `users_info` where `user_id` ='"+id+"'"))){
            maleBtn.setSelected(true);
            gender = maleBtn.getText();
        }
        else{
            femaleBtn.setSelected(true);
            gender = femaleBtn.getText();
        }
        
        monthCBox.setSelectedItem(dbprocess.getSpecificField("SELECT SUBSTRING(`user_bday`, 1, 3) FROM `users_info` WHERE `user_id` = '"+id+"'"));
        dayCBox.setSelectedItem(dbprocess.getSpecificField("SELECT SUBSTRING(`user_bday`, 5, 2) FROM `users_info` WHERE `user_id` = '"+id+"'"));
        yearCBox.setSelectedItem(dbprocess.getSpecificField("SELECT SUBSTRING(`user_bday`, 8, 4) FROM `users_info` WHERE `user_id` = '"+id+"'"));
    }
    public void saveInfo(){ 
        birth = ""+monthCBox.getSelectedItem()+"/"+dayCBox.getSelectedItem()+"/"+yearCBox.getSelectedItem()+"";
       String query = "Update `users_info` SET `user_fname` = '"+fnTF1.getText()+"' , `user_lname` = '"+lnTF.getText()
         +"', `user_gender` = '"+gender+"', `user_address` = '"+addTF.getText()+"', `user_bday` = '"+birth
         +"', `user_age` = '"+ageTF1.getText()+"', `user_user` = '"+unTF.getText()+"', `user_email` = '"+emailTF.getText()
         +"',`user_pass` = '"+passTF1.getText()+"' where `user_id` = '"+id+"' ";
         
        dbprocess.executeUpdate(query);
        JOptionPane.showMessageDialog(null,"Profile information has been saved","GBHotel",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("C:\\Users\\Keth Dominic\\Documents\\NetBeansProjects\\BookingSystem\\src\\bookingsystem\\images\\icons8_checked_radio_button_48px.png"));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        unTF = new javax.swing.JTextField();
        yearCBox = new javax.swing.JComboBox<>();
        rpassTF = new javax.swing.JPasswordField();
        dayCBox = new javax.swing.JComboBox<>();
        emailTF = new javax.swing.JTextField();
        monthCBox = new javax.swing.JComboBox<>();
        passTF1 = new javax.swing.JPasswordField();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rpassLabel = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        addTF = new javax.swing.JTextField();
        maleBtn = new javax.swing.JRadioButton();
        fnTF1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lnTF = new javax.swing.JTextField();
        ageTF1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        femaleBtn = new javax.swing.JRadioButton();
        saveBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jToggleButton1.setText("jToggleButton1");

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Profile Information");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/bookingsystem/images/gblogo.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(1060, 440));

        jInternalFrame1.setBackground(new java.awt.Color(255, 255, 255));
        jInternalFrame1.setTitle("Profile Information");
        jInternalFrame1.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/bookingsystem/images/gblogo.png"))); // NOI18N

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 464));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 101, 153));
        jLabel10.setText("Re-type password");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, 30));

        unTF.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        unTF.setForeground(new java.awt.Color(0, 101, 153));
        jPanel1.add(unTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 220, -1));

        yearCBox.setForeground(new java.awt.Color(0, 101, 153));
        yearCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", " " }));
        yearCBox.setSelectedItem("2000");
        jPanel1.add(yearCBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 320, 70, -1));

        rpassTF.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        rpassTF.setForeground(new java.awt.Color(0, 101, 153));
        rpassTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rpassTFKeyReleased(evt);
            }
        });
        jPanel1.add(rpassTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 220, -1));

        dayCBox.setForeground(new java.awt.Color(0, 101, 153));
        dayCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", " " }));
        jPanel1.add(dayCBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 320, 50, -1));

        emailTF.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        emailTF.setForeground(new java.awt.Color(0, 101, 153));
        jPanel1.add(emailTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 200, 30));

        monthCBox.setForeground(new java.awt.Color(0, 101, 153));
        monthCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        monthCBox.setSelectedItem("Jun");
        jPanel1.add(monthCBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 320, -1, -1));

        passTF1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        passTF1.setForeground(new java.awt.Color(0, 101, 153));
        passTF1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passTF1KeyTyped(evt);
            }
        });
        jPanel1.add(passTF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 220, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 101, 153));
        jLabel12.setText("Address");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, -1, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 101, 153));
        jLabel9.setText("New Password");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 101, 153));
        jLabel11.setText("Birthdate");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, -1, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 101, 153));
        jLabel8.setText("Gender");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, -1, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 101, 153));
        jLabel4.setText("Last name");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 30));

        rpassLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rpassLabel.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(rpassLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 250, 20));

        userLabel.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        userLabel.setForeground(new java.awt.Color(0, 101, 153));
        userLabel.setText("Username");
        jPanel1.add(userLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 30));

        addTF.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        addTF.setForeground(new java.awt.Color(0, 101, 153));
        jPanel1.add(addTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 200, -1));

        buttonGroup1.add(maleBtn);
        maleBtn.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        maleBtn.setForeground(new java.awt.Color(0, 101, 153));
        maleBtn.setText("Male");
        maleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maleBtnActionPerformed(evt);
            }
        });
        jPanel1.add(maleBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, -1, -1));

        fnTF1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        fnTF1.setForeground(new java.awt.Color(0, 101, 153));
        jPanel1.add(fnTF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 220, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 101, 153));
        jLabel2.setText("Email");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 170, -1, 30));

        lnTF.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lnTF.setForeground(new java.awt.Color(0, 101, 153));
        jPanel1.add(lnTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 220, -1));

        ageTF1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        ageTF1.setForeground(new java.awt.Color(0, 101, 153));
        jPanel1.add(ageTF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 200, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 101, 153));
        jLabel7.setText("Age");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, -1, 30));

        buttonGroup1.add(femaleBtn);
        femaleBtn.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        femaleBtn.setForeground(new java.awt.Color(0, 101, 153));
        femaleBtn.setText("Female");
        femaleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femaleBtnActionPerformed(evt);
            }
        });
        jPanel1.add(femaleBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, -1, -1));

        saveBtn.setBackground(new java.awt.Color(255, 255, 255));
        saveBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        saveBtn.setForeground(new java.awt.Color(0, 0, 110));
        saveBtn.setText("Save Changes");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        jPanel1.add(saveBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 170, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 101, 153));
        jLabel5.setText("First name");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 110));
        jLabel3.setText("Edit Profile");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        // TODO add your handling code here:
        if(fnTF1.getText().equals("") || lnTF.getText().equals("") || addTF.getText().equals("") || 
                unTF.getText().equals("") || ageTF1.getText().equals("") || emailTF.getText().equals("") || passTF1.getText().equals("") || 
                rpassTF.getText().equals("") || (maleBtn.isSelected() == false && femaleBtn.isSelected() == false)){
            JOptionPane.showMessageDialog(null,"Incomplete information","GBHotel",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("C:\\Users\\Keth Dominic\\Documents\\NetBeansProjects\\BookingSystem\\src\\bookingsystem\\images\\icons8_error_30px.png"));
            }
        else{
            saveInfo();
        }
        
    }//GEN-LAST:event_saveBtnActionPerformed

    private void maleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maleBtnActionPerformed
        // TODO add your handling code here:
        gender = maleBtn.getText();
    }//GEN-LAST:event_maleBtnActionPerformed

    private void femaleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femaleBtnActionPerformed
        // TODO add your handling code here:
        gender = femaleBtn.getText();
    }//GEN-LAST:event_femaleBtnActionPerformed

    private void rpassTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rpassTFKeyReleased
        // TODO add your handling code here:
       if (passTF1.getText().length() < 5){
            rpassLabel.setText("Password must be at least 6 characters!");
        }
        else{
            if (!passTF1.getText().equals(rpassTF.getText())){
                rpassLabel.setText("Password do not match!");
                saveBtn.setEnabled(false);
            }
            else{
            rpassLabel.setText("");
            saveBtn.setEnabled(true);
            }
        }
    }//GEN-LAST:event_rpassTFKeyReleased

    private void passTF1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passTF1KeyTyped
        // TODO add your handling code here:
                char c = evt.getKeyChar();
       if (passTF1.getText().length() < 5){
            rpassLabel.setText("Password must be at least 6 characters!");
        }
        else{
            if (!passTF1.getText().equals(rpassTF.getText()) && !rpassTF.getText().equals("")){
                rpassLabel.setText("Password do not match!");
                saveBtn.setEnabled(false);
            }
            else{
            rpassLabel.setText("");
            saveBtn.setEnabled(true);
            }
        }
    }//GEN-LAST:event_passTF1KeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addTF;
    private javax.swing.JTextField ageTF1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> dayCBox;
    private javax.swing.JTextField emailTF;
    private javax.swing.JRadioButton femaleBtn;
    private javax.swing.JTextField fnTF1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField lnTF;
    private javax.swing.JRadioButton maleBtn;
    private javax.swing.JComboBox<String> monthCBox;
    private javax.swing.JPasswordField passTF1;
    private javax.swing.JLabel rpassLabel;
    private javax.swing.JPasswordField rpassTF;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField unTF;
    private javax.swing.JLabel userLabel;
    private javax.swing.JComboBox<String> yearCBox;
    // End of variables declaration//GEN-END:variables
}
