/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Keth Dominic
 */
public class Loginpanel extends javax.swing.JFrame {
    
    int mouseX, mouseY;
    String gender;
    String birth;
    String username, userId;
    /**
     * Creates new form Loginpanel
     */
    QueryProcessor dbprocess;
    //fdDWASD
    //asd
    public Loginpanel() {
        initComponents();
        
        dbprocess = new QueryProcessor ();
        
        loginInfo();
    }
    
    public void loginInfo(){
        regPanel.hide();
        usrTF.setText(dbprocess.getSpecificField("Select `username` from `remember_table` where `rem_id` = 1"));
        passTF.setText(dbprocess.getSpecificField("Select `pass` from `remember_table` where `rem_id` = 1"));
    } 
    public void loginAttempt(){
       String dbUser;
       String dbPass;
       dbUser = dbprocess.getSpecificField("Select `user_user` from `users_info` where `user_user` ='"+usrTF.getText()+"' ");
       dbPass = dbprocess.getSpecificField("Select `user_pass` from `users_info` where `user_pass` ='"+passTF.getText()+"'");
       
       if (usrTF.getText().equals(dbUser) || passTF.getText().equals(dbPass)){
           dbUser = dbprocess.getSpecificField("Select `user_user` from `users_info` where `user_user` ='"+usrTF.getText()+"' AND `user_pass` ='"+passTF.getText()+"'");
           dbPass = dbprocess.getSpecificField("Select `user_pass` from `users_info` where `user_user` ='"+usrTF.getText()+"' AND `user_pass` ='"+passTF.getText()+"'");
           if (usrTF.getText().equals(dbUser) && passTF.getText().equals(dbPass)){
              username = usrTF.getText();
              passToHome();
           }
           else{
               loginRslt.setText("Incorrect username or password!");
           }
       }     
       else{
           loginRslt.setText("Please register first!");
       }
       
       if (remCheckBox.isSelected()){
          String checkrem = dbprocess.getSpecificField("Select `rem_id` from `remember_table` where `rem_id` = 1");
          if(checkrem == null){
               dbprocess.executeUpdate("Insert into `remember_table` (`rem_id`, `username`, `pass`) values( 1 ,'"+usrTF.getText()+"','"+passTF.getText()+"') "); 
           }
           else{
               dbprocess.executeUpdate("Update `remember_table` SET `username` ='"+usrTF.getText()+"', `pass` ='"+passTF.getText()+"' where `rem_id` = 1");  
           }        
       }
    }
    public void regUser(){ 
        birth = ""+monthCBox.getSelectedItem()+"/"+dayCBox.getSelectedItem()+"/"+yearCBox.getSelectedItem()+"";
        
       dbprocess.executeUpdate("Insert into `users_info` (`user_id`, `user_fname`, `user_lname`, `user_gender`, `user_address`, `user_bday`, `user_age`, `user_user`, `user_email`,`user_pass`) values(NULL, '"
         +fnTF1.getText()+"','"+lnTF.getText()+"','"+gender+"','"+addTF.getText()
         +"','"+birth+"','"+ageTF1.getText()+"','"+unTF.getText()+"','"+emailTF.getText()
         +"','"+passTF1.getText()+"')");
       username = unTF.getText();
       passToHome();
       
       
    }
    public String getUserId(){
        return dbprocess.getSpecificField("Select `user_id` from `users_info` where `user_user` ='"+username+"' ");
    }
    public void passToHome(){
        Homepanel passHome = new Homepanel();
        passHome.userId = getUserId();
        passHome.setVisible(true);
        dispose();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        genderGroup = new javax.swing.ButtonGroup();
        closeIcn = new javax.swing.JLabel();
        minICn = new javax.swing.JLabel();
        dragLabel = new javax.swing.JLabel();
        regPanel = new javax.swing.JPanel();
        reg2TF = new javax.swing.JLabel();
        jbTF = new javax.swing.JLabel();
        lnTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        reg3Btn = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        unTF = new javax.swing.JTextField();
        passTF1 = new javax.swing.JPasswordField();
        rpassTF = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        emailTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        maleBtn = new javax.swing.JRadioButton();
        femaleBtn = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        addTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        login2Btn = new javax.swing.JLabel();
        fnTF1 = new javax.swing.JTextField();
        rpassLabel = new javax.swing.JLabel();
        monthCBox = new javax.swing.JComboBox<>();
        dayCBox = new javax.swing.JComboBox<>();
        yearCBox = new javax.swing.JComboBox<>();
        ageTF1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        regErrorLabel = new javax.swing.JLabel();
        loginPanel = new javax.swing.JPanel();
        labelLogo = new javax.swing.JLabel();
        loginTitle = new javax.swing.JLabel();
        usrTF = new javax.swing.JTextField();
        usrIcn = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        usrnmeLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        passTF = new javax.swing.JPasswordField();
        jTextField3 = new javax.swing.JTextField();
        passLabel = new javax.swing.JLabel();
        loginBtn = new javax.swing.JButton();
        regBtn = new javax.swing.JLabel();
        remCheckBox = new javax.swing.JCheckBox();
        showpassBox = new javax.swing.JCheckBox();
        loginRslt = new javax.swing.JLabel();
        Jlabelbackground = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JBHotel");
        setBackground(new java.awt.Color(82, 181, 235));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(133, 169, 228));
        setIconImage(new ImageIcon("C:\\Users\\Keth Dominic\\Documents\\NetBeansProjects\\BookingSystem\\src\\bookingsystem\\images\\gblogo.png").getImage());
        setLocationByPlatform(true);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeIcn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookingsystem/images/49158 (1).png"))); // NOI18N
        closeIcn.setToolTipText("Close the window");
        closeIcn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeIcn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeIcnMouseClicked(evt);
            }
        });
        getContentPane().add(closeIcn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

        minICn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookingsystem/images/minimize.png"))); // NOI18N
        minICn.setToolTipText("Minimize window");
        minICn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minICn.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        minICn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minICnMouseClicked(evt);
            }
        });
        getContentPane().add(minICn, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 30, 40));

        dragLabel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                dragLabelMouseDragged(evt);
            }
        });
        dragLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dragLabelMousePressed(evt);
            }
        });
        getContentPane().add(dragLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 40));

        regPanel.setBackground(new java.awt.Color(255, 255, 255));
        regPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        regPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        reg2TF.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        reg2TF.setForeground(new java.awt.Color(0, 101, 153));
        reg2TF.setText("Register");
        regPanel.add(reg2TF, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 41, -1, 30));

        jbTF.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jbTF.setForeground(new java.awt.Color(0, 101, 153));
        jbTF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookingsystem/images/glogo1.png"))); // NOI18N
        jbTF.setText("JBHotel");
        regPanel.add(jbTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 110, 80));

        lnTF.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lnTF.setForeground(new java.awt.Color(0, 101, 153));
        regPanel.add(lnTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 187, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 101, 153));
        jLabel3.setText("First name");
        regPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 95, -1, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 101, 153));
        jLabel4.setText("Last name");
        regPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 155, -1, 30));

        reg3Btn.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        reg3Btn.setForeground(new java.awt.Color(0, 101, 153));
        reg3Btn.setText("Register");
        reg3Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reg3Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reg3BtnActionPerformed(evt);
            }
        });
        regPanel.add(reg3Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, -1, -1));

        userLabel.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        userLabel.setForeground(new java.awt.Color(0, 101, 153));
        userLabel.setText("Username");
        regPanel.add(userLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 225, -1, 30));

        unTF.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        unTF.setForeground(new java.awt.Color(0, 101, 153));
        regPanel.add(unTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 187, -1));

        passTF1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        passTF1.setForeground(new java.awt.Color(0, 101, 153));
        passTF1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passTF1KeyTyped(evt);
            }
        });
        regPanel.add(passTF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 187, -1));

        rpassTF.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        rpassTF.setForeground(new java.awt.Color(0, 101, 153));
        rpassTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                rpassTFKeyReleased(evt);
            }
        });
        regPanel.add(rpassTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 187, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 101, 153));
        jLabel7.setText("Age");
        regPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 155, -1, 30));

        emailTF.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        emailTF.setForeground(new java.awt.Color(0, 101, 153));
        regPanel.add(emailTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 170, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 101, 153));
        jLabel8.setText("Gender");
        regPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 285, -1, 30));

        genderGroup.add(maleBtn);
        maleBtn.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        maleBtn.setForeground(new java.awt.Color(0, 101, 153));
        maleBtn.setText("Male");
        maleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maleBtnActionPerformed(evt);
            }
        });
        regPanel.add(maleBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, -1, -1));

        genderGroup.add(femaleBtn);
        femaleBtn.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        femaleBtn.setForeground(new java.awt.Color(0, 101, 153));
        femaleBtn.setText("Female");
        femaleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femaleBtnActionPerformed(evt);
            }
        });
        regPanel.add(femaleBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 101, 153));
        jLabel9.setText("Password");
        regPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 285, -1, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 101, 153));
        jLabel10.setText("Re-type password");
        regPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 345, -1, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 101, 153));
        jLabel11.setText("Birthdate");
        regPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 345, -1, 30));

        addTF.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        addTF.setForeground(new java.awt.Color(0, 101, 153));
        regPanel.add(addTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 170, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 101, 153));
        jLabel12.setText("Address");
        regPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 95, -1, 30));

        login2Btn.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        login2Btn.setForeground(new java.awt.Color(0, 101, 153));
        login2Btn.setText("Login instead");
        login2Btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        login2Btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                login2BtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                login2BtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                login2BtnMouseExited(evt);
            }
        });
        regPanel.add(login2Btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 460, 130, 30));

        fnTF1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        fnTF1.setForeground(new java.awt.Color(0, 101, 153));
        regPanel.add(fnTF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 187, -1));

        rpassLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rpassLabel.setForeground(new java.awt.Color(255, 0, 0));
        regPanel.add(rpassLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 280, 20));

        monthCBox.setForeground(new java.awt.Color(0, 101, 153));
        monthCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        monthCBox.setSelectedItem("Jun");
        regPanel.add(monthCBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 60, -1));

        dayCBox.setForeground(new java.awt.Color(0, 101, 153));
        dayCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", " " }));
        regPanel.add(dayCBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 370, 50, -1));

        yearCBox.setForeground(new java.awt.Color(0, 101, 153));
        yearCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", " " }));
        yearCBox.setSelectedItem("2000");
        regPanel.add(yearCBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 370, 70, -1));

        ageTF1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        ageTF1.setForeground(new java.awt.Color(0, 101, 153));
        regPanel.add(ageTF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 170, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 101, 153));
        jLabel2.setText("Email");
        regPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 225, -1, 30));

        regErrorLabel.setForeground(new java.awt.Color(255, 0, 0));
        regPanel.add(regErrorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 400, 170, 30));

        getContentPane().add(regPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 479, 500));

        loginPanel.setBackground(new java.awt.Color(255, 255, 255));
        loginPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        loginPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelLogo.setBackground(new java.awt.Color(63, 170, 235));
        labelLogo.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        labelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookingsystem/images/glogo1.png"))); // NOI18N
        loginPanel.add(labelLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 150, 70));

        loginTitle.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        loginTitle.setForeground(new java.awt.Color(0, 101, 153));
        loginTitle.setText("Login");
        loginPanel.add(loginTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, -1, -1));

        usrTF.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        usrTF.setForeground(new java.awt.Color(0, 101, 153));
        usrTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usrTFActionPerformed(evt);
            }
        });
        loginPanel.add(usrTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 180, -1));

        usrIcn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usrIcn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookingsystem/images/man-silhouette-profile-11.png"))); // NOI18N
        usrIcn.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        usrIcn.setMaximumSize(new java.awt.Dimension(10, 10));
        usrIcn.setMinimumSize(new java.awt.Dimension(10, 10));
        loginPanel.add(usrIcn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 40, 30));

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jTextField2.setFocusable(false);
        loginPanel.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 50, -1));

        usrnmeLabel.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        usrnmeLabel.setForeground(new java.awt.Color(0, 101, 153));
        usrnmeLabel.setText("Username");
        loginPanel.add(usrnmeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookingsystem/images/1434538.png"))); // NOI18N
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        loginPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 40, 30));

        passTF.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        passTF.setForeground(new java.awt.Color(0, 101, 153));
        loginPanel.add(passTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 180, -1));

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jTextField3.setFocusable(false);
        loginPanel.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 50, -1));

        passLabel.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        passLabel.setForeground(new java.awt.Color(0, 101, 153));
        passLabel.setText("Password");
        loginPanel.add(passLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        loginBtn.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        loginBtn.setForeground(new java.awt.Color(0, 101, 153));
        loginBtn.setText("Login");
        loginBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        loginPanel.add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, -1, -1));

        regBtn.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        regBtn.setForeground(new java.awt.Color(0, 101, 153));
        regBtn.setText("Register");
        regBtn.setToolTipText("Create a new account");
        regBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        regBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                regBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                regBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                regBtnMouseExited(evt);
            }
        });
        loginPanel.add(regBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 460, -1, -1));

        remCheckBox.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        remCheckBox.setForeground(new java.awt.Color(0, 101, 153));
        remCheckBox.setText("Remember me");
        remCheckBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginPanel.add(remCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        showpassBox.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        showpassBox.setForeground(new java.awt.Color(0, 101, 153));
        showpassBox.setText("Show");
        showpassBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showpassBoxActionPerformed(evt);
            }
        });
        loginPanel.add(showpassBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, -1, -1));

        loginRslt.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        loginRslt.setForeground(new java.awt.Color(255, 0, 0));
        loginRslt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginRslt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        loginPanel.add(loginRslt, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 250, 30));

        getContentPane().add(loginPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 270, 500));

        Jlabelbackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookingsystem/images/pexels-vincent-gerbouin-1179156.jpg"))); // NOI18N
        Jlabelbackground.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(Jlabelbackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 540));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        // TODO add your handling code here:
        loginAttempt();
    }//GEN-LAST:event_loginBtnActionPerformed

    private void closeIcnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeIcnMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_closeIcnMouseClicked

    private void minICnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minICnMouseClicked
        // TODO add your handling code here:
        setState(ICONIFIED);
    }//GEN-LAST:event_minICnMouseClicked

    private void dragLabelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dragLabelMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - mouseX ,y - mouseY);
    }//GEN-LAST:event_dragLabelMouseDragged

    private void dragLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dragLabelMousePressed
        // TODO add your handling code here:
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_dragLabelMousePressed

    private void reg3BtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reg3BtnActionPerformed
        // TODO add your handling code here:
        if(fnTF1.getText().equals("") || lnTF.getText().equals("") || addTF.getText().equals("") || 
                unTF.getText().equals("") || ageTF1.getText().equals("") || emailTF.getText().equals("") || passTF1.getText().equals("") || 
                rpassTF.getText().equals("") || (maleBtn.isSelected() == false && femaleBtn.isSelected() == false)){
            JOptionPane.showMessageDialog(null,"Incomplete information","GBHotel",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("C:\\Users\\Keth Dominic\\Documents\\NetBeansProjects\\BookingSystem\\src\\bookingsystem\\images\\icons8_error_30px.png"));
            }
        else{
            regUser();
        }
        
    }//GEN-LAST:event_reg3BtnActionPerformed

    private void login2BtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login2BtnMouseClicked
        // TODO add your handling code here:
        regPanel.hide();
        loginPanel.show(); 
    }//GEN-LAST:event_login2BtnMouseClicked

    private void regBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regBtnMouseClicked
        // TODO add your handling code here:
        regPanel.show();
        loginPanel.hide();
    }//GEN-LAST:event_regBtnMouseClicked

    private void rpassTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rpassTFKeyReleased
        // TODO add your handling code here:
       if (passTF1.getText().length() < 5){
            rpassLabel.setText("Password must be at least 6 characters!");
        }
        else{
            if (!passTF1.getText().equals(rpassTF.getText())){
                rpassLabel.setText("Password do not match!");
                reg3Btn.setEnabled(false);
            }
            else{
            rpassLabel.setText("");
            reg3Btn.setEnabled(true);
            }
        }
    }//GEN-LAST:event_rpassTFKeyReleased

    private void showpassBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showpassBoxActionPerformed
        // TODO add your handling code here:
        if(showpassBox.isSelected()){
        passTF.setEchoChar((char)0);
        }
        else{
            passTF.setEchoChar('*');
        }
    }//GEN-LAST:event_showpassBoxActionPerformed

    private void regBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regBtnMouseEntered
        // TODO add your handling code here:
        regBtn.setForeground(Color.black);
    }//GEN-LAST:event_regBtnMouseEntered

    private void regBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regBtnMouseExited
        // TODO add your handling code here:
         regBtn.setForeground(new Color(0, 101, 153));
    }//GEN-LAST:event_regBtnMouseExited

    private void login2BtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login2BtnMouseEntered
        // TODO add your handling code here:
        login2Btn.setForeground(Color.black);
    }//GEN-LAST:event_login2BtnMouseEntered

    private void login2BtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login2BtnMouseExited
        // TODO add your handling code here:
        login2Btn.setForeground(new Color(0, 101, 153));
    }//GEN-LAST:event_login2BtnMouseExited

    private void maleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maleBtnActionPerformed
        // TODO add your handling code here:
        gender = maleBtn.getText();
    }//GEN-LAST:event_maleBtnActionPerformed

    private void femaleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femaleBtnActionPerformed
        // TODO add your handling code here:
        gender = femaleBtn.getText();
    }//GEN-LAST:event_femaleBtnActionPerformed

    private void usrTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usrTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usrTFActionPerformed

    private void passTF1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passTF1KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
       if (passTF1.getText().length() < 5){
            rpassLabel.setText("Password must be at least 6 characters!");
        }
        else{
            if (!passTF1.getText().equals(rpassTF.getText()) && !rpassTF.getText().equals("")){
                rpassLabel.setText("Password do not match!");
                reg3Btn.setEnabled(false);
            }
            else{
            rpassLabel.setText("");
            reg3Btn.setEnabled(true);
            }
        }
    }//GEN-LAST:event_passTF1KeyTyped

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
            java.util.logging.Logger.getLogger(Loginpanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loginpanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loginpanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loginpanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Loginpanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jlabelbackground;
    private javax.swing.JTextField addTF;
    private javax.swing.JTextField ageTF1;
    private javax.swing.JLabel closeIcn;
    private javax.swing.JComboBox<String> dayCBox;
    private javax.swing.JLabel dragLabel;
    private javax.swing.JTextField emailTF;
    private javax.swing.JRadioButton femaleBtn;
    private javax.swing.JTextField fnTF1;
    private javax.swing.ButtonGroup genderGroup;
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
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel jbTF;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JTextField lnTF;
    private javax.swing.JLabel login2Btn;
    private javax.swing.JButton loginBtn;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JLabel loginRslt;
    private javax.swing.JLabel loginTitle;
    private javax.swing.JRadioButton maleBtn;
    private javax.swing.JLabel minICn;
    private javax.swing.JComboBox<String> monthCBox;
    private javax.swing.JLabel passLabel;
    private javax.swing.JPasswordField passTF;
    private javax.swing.JPasswordField passTF1;
    private javax.swing.JLabel reg2TF;
    private javax.swing.JButton reg3Btn;
    private javax.swing.JLabel regBtn;
    private javax.swing.JLabel regErrorLabel;
    private javax.swing.JPanel regPanel;
    private javax.swing.JCheckBox remCheckBox;
    private javax.swing.JLabel rpassLabel;
    private javax.swing.JPasswordField rpassTF;
    private javax.swing.JCheckBox showpassBox;
    private javax.swing.JTextField unTF;
    private javax.swing.JLabel userLabel;
    private javax.swing.JLabel usrIcn;
    private javax.swing.JTextField usrTF;
    private javax.swing.JLabel usrnmeLabel;
    private javax.swing.JComboBox<String> yearCBox;
    // End of variables declaration//GEN-END:variables
}
