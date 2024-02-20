/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


/**
 *
 * @author Keth Dominic
 */
public class AdminIFrame extends javax.swing.JInternalFrame {
     DefaultTableModel model, branchModel, historyModel, userModel;
    Object tablerow [][];
    String tablecol []= {"Branch","Location","Room Number","Room Type","Availability","Price/Night"};
    String tablecolBranch []= {"Branch Number","Branch Name","Manager","Location","Branch Type"};
    String tablecolHistory []= {"<html><left>Customer<br>Id","Branch","Location","<html><left>Reservation<br>Date","Chek-in","Check-out","<html><left>Room<br>No.","<html><left>Room<br>Type","<html><left>Days<br>Occupied","<html><left>Price<br>/Night","<html><left>Price<br>Payed"};
    String tablecolUser[] = {"User Id","First Name","Last Name","Gender","Address","B-Day","Age","Email"};
    
    ResultSet result;
    PreparedStatement statement;
    Connection connect;
    Color defaultColor = new Color(0, 0, 110);
    String checkIntNum , checkIntPrice;
    Boolean editOn = false;
    Boolean editBranchOn = false;
    Boolean isNotDigit = false;
    char c;
           
    /**
     * Creates new form AdminFrame
     */
    QueryProcessor dbprocess;
    public AdminIFrame() {
        initComponents();
        dbprocess = new QueryProcessor ();
        connect = DBConnection.connect;
         
        setBookingsTable();
        setBranchTable();
        setHistoryTable();
        setUsersTable();
        
    }
 // <editor-fold defaultstate="collapsed" desc="Codes for Manage Bookings Tab">
// codes for Manage Bookings Tab   
public void setBookingsTable(){
        tablerow = dbprocess.getAllRecord("SELECT `branch_table`.`branch_name`,`branch_table`.`branch_location`,`booking_table`.`booking_room_num`,`booking_table`.`booking_type`,\n" +
        "`booking_table`.`booking_avail`,`booking_table`.`booking_price` FROM `branch_table`,`booking_table` WHERE `branch_table`.`branch_name` = `booking_table`.`booking_branch` ORDER BY `branch_table`.`branch_name` ASC");
        model = new DefaultTableModel(tablerow,tablecol);
        bookingsTable.setModel(model);
        bookingsTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        bookingsTable.getColumnModel().getColumn(1).setPreferredWidth(150);
}
public void addInfo(){
    dbprocess.executeUpdate("Insert into `booking_table` values('"+comboAvail.getSelectedItem()
            +"','"+comboBranch.getSelectedItem()+"','"+roomNumTF.getText()
            +"','"+comboRoomType.getSelectedItem()+"','"+roomPriceTF.getText()+"') ");
    setBookingsTable();
}
public int getRoomNumber(){
    return Integer.parseInt(model.getValueAt(bookingsTable.getSelectedRow(),2).toString());
}
public String getBranchName(){
    return model.getValueAt(bookingsTable.getSelectedRow(),0).toString();
}
public void saveData(){
    dbprocess.executeUpdate("Update `booking_table` SET `booking_branch` ='"+comboBranch.getSelectedItem()
            +"', `booking_type` ='"+comboRoomType.getSelectedItem()
            +"',`booking_avail` ='"+comboAvail.getSelectedItem()+"',`booking_room_num` ='"+roomNumTF.getText()
            +"',`booking_price` ='"+roomPriceTF.getText()+"' where `booking_table`.`booking_room_num`='"+getRoomNumber()+"'");
            
}
public void setDataToFields(){
    roomNumTF.setForeground(defaultColor);
    roomPriceTF.setForeground(defaultColor);
    comboBranch.setSelectedItem(dbprocess.getSpecificField("Select `booking_branch` from `booking_table` where `booking_room_num` ='"+getRoomNumber()+"' and `booking_branch` = '"+getBranchName()+"'"));
    comboLocation.setSelectedItem(dbprocess.getSpecificField("Select `branch_location` from `branch_table` where `branch_name` = '"+getBranchName()+"'"));
    comboRoomType.setSelectedItem(dbprocess.getSpecificField("Select `booking_type` from `booking_table` where `booking_room_num` ='"+getRoomNumber()+"' and `booking_branch` = '"+getBranchName()+"'"));
    comboAvail.setSelectedItem(dbprocess.getSpecificField("Select `booking_avail` from `booking_table` where `booking_room_num` ='"+getRoomNumber()+"' and `booking_branch` = '"+getBranchName()+"'"));
    roomNumTF.setText(dbprocess.getSpecificField("Select `booking_room_num` from `booking_table` where `booking_room_num` ='"+getRoomNumber()+"' and `booking_branch` = '"+getBranchName()+"'"));
    roomPriceTF.setText(dbprocess.getSpecificField("Select `booking_price` from `booking_table` where `booking_room_num` ='"+getRoomNumber()+"' and `booking_branch` = '"+getBranchName()+"'"));
}
public void deleteData(){
     dbprocess.executeUpdate("delete from `booking_table` where `booking_table`.`booking_room_num`='"+getRoomNumber()+"'");
}
public void insertComboItems(){
    enableTF();
    try {
    statement = connect.prepareStatement("Select * FROM `branch_table`");
    result = statement.executeQuery();
    while(result.next()){
        comboBranch.addItem(result.getString("branch_name"));
        comboLocation.addItem(result.getString("branch_location"));
    }
    } catch (Exception e){
        System.out.println(e); 
    }
}
public void resetPanelFields(){
    roomNumTF.setText("Room Number");
    roomNumTF.setForeground(Color.gray);
    roomPriceTF.setText("Room Price");
    roomPriceTF.setForeground(Color.gray);
    roomNumTF.setEnabled(false);
    roomPriceTF.setEnabled(false);
    editOn = false;
    bookingsTable.clearSelection();
    saveBtn.setEnabled(false);
    addBtn.setEnabled(false);
    errorMsg.setText("");
    comboBranch.removeAllItems();
    comboLocation.removeAllItems();
    saveBtn.hide();
    addBtn.hide();
    deleteBtn.setEnabled(false);
    
}

public void enableTF(){
    roomNumTF.setEnabled(true);
    roomPriceTF.setEnabled(true);
}

public void checkCheckoutFinish(){
    
}

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Codes for Manage Branches Tab">
// codes for Manage Branches Tab
public void setBranchTable(){
    tablerow = dbprocess.getAllRecord("Select `branch_number`, `branch_name`, `branch_manager`, `branch_location`,`branch_type` from `branch_table` ORDER BY `branch_number` ASC");
    branchModel = new DefaultTableModel(tablerow,tablecolBranch);
    branchesTable.setModel(branchModel);
}

public void addBranchInfo(){
    dbprocess.executeUpdate("Insert into `branch_table` values ('"+branchNameTF.getText()+"','"+managerTF.getText()+"','"+branchNumTF.getText()+"','"+locationNTF.getText()+"','"+comboBranchType.getSelectedItem()+"')");
}
public int getBranchNumber(){
    return Integer.parseInt(branchModel.getValueAt(branchesTable.getSelectedRow(),0 ).toString());
}
public void setDataToBranchTable(){
    delBranchBtn.setEnabled(true);
    branchNumTF.setText(dbprocess.getSpecificField("Select `branch_number` from `branch_table` where `branch_number` ='"+getBranchNumber()+"'"));
    branchNameTF.setText(dbprocess.getSpecificField("Select `branch_name` from `branch_table` where `branch_number` ='"+getBranchNumber()+"'"));
    managerTF.setText(dbprocess.getSpecificField("Select `branch_manager` from `branch_table` where `branch_number` ='"+getBranchNumber()+"'"));
    locationNTF.setText(dbprocess.getSpecificField("Select `branch_location` from `branch_table` where `branch_number` ='"+getBranchNumber()+"'"));
    comboBranchType.setSelectedItem(dbprocess.getSpecificField("Select `branch_type` from `branch_table` where `branch_number` ='"+getBranchNumber()+"'"));
    
}
public void saveBranchData(){
    String query = "Update `branch_table` SET `branch_name` ='"+branchNameTF.getText()+"', `branch_manager` ='"+managerTF.getText()+"',`branch_location` ='"+locationNTF.getText()+"',`branch_type` ='"+comboBranchType.getSelectedItem()+"' where `branch_table`.`branch_number`='"+getBranchNumber()+"'";
    dbprocess.executeUpdate(query);
}
public void deleteBranchData(){
    dbprocess.executeUpdate("delete from `branch_table` where `branch_number`='"+getBranchNumber()+"'");
}
public void resetBranchOptions(){
    branchNumTF.setEnabled(false);
    branchNumTF.setText("");
    branchNameTF.setEnabled(false);
    branchNameTF.setText("");
    managerTF.setEnabled(false);
    managerTF.setText("");
    locationNTF.setEnabled(false);
    locationNTF.setText("");
    comboBranchType.setEnabled(false);
    comboBranchType.setSelectedIndex(1);
    addBranchBtn.setEnabled(false);
    saveBranchBtn.setEnabled(false);
    delBranchBtn.setEnabled(false);
    branchesTable.clearSelection();
    editBranchOn = false;
    
}
public void enableBranchOpt(){
    
    branchNumTF.setEnabled(true);
    branchNameTF.setEnabled(true);
    managerTF.setEnabled(true);
    locationNTF.setEnabled(true);
    comboBranchType.setEnabled(true);
}
public void newBranch(){
    addBranchBtn.show();
    Integer nextBranchNum = Integer.parseInt(dbprocess.getSpecificField("Select MAX(`branch_number`) from `branch_table`"))+1;
    branchNumTF.setText(nextBranchNum.toString());
    branchNumTF.setEnabled(false);
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Codes for History Tab">
//codes for Histories Tab
public void setHistoryTable(){
    tablerow = dbprocess.getAllRecord("Select `customer_id`,`branch`,`location`,`reservation_date`,`check_in`,`check_out`,`room_number`,`room_type`,`days_occupied`,`room_price`,`price_payed` from `recent_bookings_table`");
    historyModel = new DefaultTableModel(tablerow,tablecolHistory);
    historyTable.setModel(historyModel);
    historyTable.getColumnModel().getColumn(0).setPreferredWidth(50);
    historyTable.getColumnModel().getColumn(1).setPreferredWidth(120);
    historyTable.getColumnModel().getColumn(2).setPreferredWidth(125);
    historyTable.getColumnModel().getColumn(3).setPreferredWidth(80);
    historyTable.getColumnModel().getColumn(6).setPreferredWidth(30);
    historyTable.getColumnModel().getColumn(7).setPreferredWidth(30);
    historyTable.getColumnModel().getColumn(9).setPreferredWidth(30);
    historyTable.getColumnModel().getColumn(10).setPreferredWidth(40);
    resultCount.setText(dbprocess.getSpecificField("SELECT COUNT(*) FROM `recent_bookings_table`"));
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Codes for Users Table Tab">
public void setUsersTable(){
    tablerow = dbprocess.getAllRecord("SELECT `user_id`,`user_fname`,`user_lname`,`user_gender`,`user_address`,`user_bday`,`user_age`,`user_email` FROM `users_info` ORDER BY `user_id` ASC");
    userModel = new DefaultTableModel(tablerow,tablecolUser);
    usersTable.setModel(userModel);
    usersTable.getColumnModel().getColumn(0).setPreferredWidth(10);
    usersTable.getColumnModel().getColumn(3).setPreferredWidth(10);
    usersTable.getColumnModel().getColumn(4).setPreferredWidth(150);
    usersTable.getColumnModel().getColumn(6).setPreferredWidth(5);
    usersTable.getColumnModel().getColumn(7).setPreferredWidth(210);
    resultCountUser.setText(dbprocess.getSpecificField("SELECT COUNT(*) FROM `users_info`"));
}

// </editor-fold>

public void checkIntTF(){
    if (Character.isDigit(c)){   
            errorMsg.setText("");
            isNotDigit = false;
            addBtn.setEnabled(true);
            saveBtn.setEnabled(true);
        }
        else{
            isNotDigit = true;
            errorMsg.setText("Input must be numbers only");  
            addBtn.setEnabled(false);
            saveBtn.setEnabled(false);
        }
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
        bookingsTab = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        userInfoPanel = new javax.swing.JPanel();
        comboBranch = new javax.swing.JComboBox<>();
        comboLocation = new javax.swing.JComboBox<>();
        roomNumTF = new javax.swing.JTextField();
        comboRoomType = new javax.swing.JComboBox<>();
        comboAvail = new javax.swing.JComboBox<>();
        roomPriceTF = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        deleteBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        bookingsTable = new javax.swing.JTable();
        newBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        errorMsg = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        userInfoPanel1 = new javax.swing.JPanel();
        branchNumTF = new javax.swing.JTextField();
        branchNameTF = new javax.swing.JTextField();
        managerTF = new javax.swing.JTextField();
        locationNTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        comboBranchType = new javax.swing.JComboBox<>();
        delBranchBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        branchesTable = new javax.swing.JTable();
        newBranchBtn = new javax.swing.JButton();
        addBranchBtn = new javax.swing.JButton();
        saveBranchBtn = new javax.swing.JButton();
        editBranchBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        resultCount = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersTable = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        resultCountUser = new javax.swing.JLabel();

        jToggleButton1.setText("jToggleButton1");

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(0, 0, 0));
        setTitle("Administrator Area");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/bookingsystem/images/gblogo.png"))); // NOI18N
        setPreferredSize(new java.awt.Dimension(1060, 440));

        bookingsTab.setForeground(new java.awt.Color(0, 0, 110));
        bookingsTab.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        bookingsTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookingsTabMouseClicked(evt);
            }
        });

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userInfoPanel.setOpaque(false);
        userInfoPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboBranch.setForeground(new java.awt.Color(0, 0, 110));
        comboBranch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBranchActionPerformed(evt);
            }
        });
        userInfoPanel.add(comboBranch, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 220, 30));

        comboLocation.setForeground(new java.awt.Color(0, 0, 110));
        comboLocation.setToolTipText("");
        comboLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLocationActionPerformed(evt);
            }
        });
        userInfoPanel.add(comboLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 220, 30));

        roomNumTF.setForeground(new java.awt.Color(153, 153, 153));
        roomNumTF.setText("Room Number");
        roomNumTF.setEnabled(false);
        roomNumTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roomNumTFMouseClicked(evt);
            }
        });
        roomNumTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                roomNumTFKeyTyped(evt);
            }
        });
        userInfoPanel.add(roomNumTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 140, 30));

        comboRoomType.setForeground(new java.awt.Color(0, 0, 110));
        comboRoomType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single", "Partner", "Family" }));
        comboRoomType.setSelectedIndex(-1);
        userInfoPanel.add(comboRoomType, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 140, 30));

        comboAvail.setForeground(new java.awt.Color(0, 0, 110));
        comboAvail.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vacant", "Reserved" }));
        comboAvail.setSelectedIndex(-1);
        userInfoPanel.add(comboAvail, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 140, 30));

        roomPriceTF.setForeground(new java.awt.Color(102, 102, 102));
        roomPriceTF.setText("Room Price");
        roomPriceTF.setEnabled(false);
        roomPriceTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roomPriceTFMouseClicked(evt);
            }
        });
        roomPriceTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                roomPriceTFKeyTyped(evt);
            }
        });
        userInfoPanel.add(roomPriceTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, 140, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Branch");
        userInfoPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Location");
        userInfoPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Room Number");
        userInfoPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Availability");
        userInfoPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 0, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Room Type");
        userInfoPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Price per Night");
        userInfoPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, -1, -1));

        jPanel4.add(userInfoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 1020, 50));

        deleteBtn.setForeground(new java.awt.Color(0, 0, 110));
        deleteBtn.setText("Delete");
        deleteBtn.setEnabled(false);
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel4.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 80, 30));

        bookingsTable.setForeground(new java.awt.Color(0, 0, 110));
        bookingsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        bookingsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookingsTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(bookingsTable);
        if (bookingsTable.getColumnModel().getColumnCount() > 0) {
            bookingsTable.getColumnModel().getColumn(4).setResizable(false);
            bookingsTable.getColumnModel().getColumn(5).setHeaderValue("Title 6");
        }

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1030, 220));

        newBtn.setForeground(new java.awt.Color(0, 0, 110));
        newBtn.setText("New");
        newBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBtnActionPerformed(evt);
            }
        });
        jPanel4.add(newBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 80, -1));

        addBtn.setForeground(new java.awt.Color(0, 0, 110));
        addBtn.setText("Add");
        addBtn.setEnabled(false);
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel4.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 330, 100, -1));

        saveBtn.setForeground(new java.awt.Color(0, 0, 110));
        saveBtn.setText("Save");
        saveBtn.setEnabled(false);
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        jPanel4.add(saveBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 330, 100, -1));

        editBtn.setForeground(new java.awt.Color(0, 0, 110));
        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel4.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 80, -1));

        errorMsg.setForeground(new java.awt.Color(255, 0, 0));
        errorMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(errorMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 330, 220, 20));

        bookingsTab.addTab("Manage Bookings", jPanel4);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userInfoPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        branchNumTF.setEnabled(false);
        branchNumTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                branchNumTFActionPerformed(evt);
            }
        });
        userInfoPanel1.add(branchNumTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 210, 30));

        branchNameTF.setEnabled(false);
        userInfoPanel1.add(branchNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 200, 30));

        managerTF.setEnabled(false);
        userInfoPanel1.add(managerTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 210, 30));

        locationNTF.setEnabled(false);
        userInfoPanel1.add(locationNTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 200, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Branch Type");
        userInfoPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 0, 140, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Branch Name");
        userInfoPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 140, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Branch Number");
        userInfoPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Manager");
        userInfoPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 140, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Location");
        userInfoPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 140, -1));

        comboBranchType.setForeground(new java.awt.Color(0, 0, 110));
        comboBranchType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Main Branch", "Sub Branch" }));
        comboBranchType.setSelectedIndex(1);
        comboBranchType.setEnabled(false);
        userInfoPanel1.add(comboBranchType, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, 200, 30));

        jPanel5.add(userInfoPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 1020, 60));

        delBranchBtn.setForeground(new java.awt.Color(0, 0, 110));
        delBranchBtn.setText("Delete");
        delBranchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBranchBtnActionPerformed(evt);
            }
        });
        jPanel5.add(delBranchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 80, 30));

        branchesTable.setForeground(new java.awt.Color(0, 0, 110));
        branchesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Branch ID", "Branch Name", "Manager", "Location", "Branch Type"
            }
        ));
        branchesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                branchesTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(branchesTable);
        if (branchesTable.getColumnModel().getColumnCount() > 0) {
            branchesTable.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel5.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1030, 210));

        newBranchBtn.setForeground(new java.awt.Color(0, 0, 110));
        newBranchBtn.setText("New");
        newBranchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBranchBtnActionPerformed(evt);
            }
        });
        jPanel5.add(newBranchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 80, -1));

        addBranchBtn.setForeground(new java.awt.Color(0, 0, 110));
        addBranchBtn.setText("Add");
        addBranchBtn.setEnabled(false);
        addBranchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBranchBtnActionPerformed(evt);
            }
        });
        jPanel5.add(addBranchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 330, 100, -1));

        saveBranchBtn.setForeground(new java.awt.Color(0, 0, 110));
        saveBranchBtn.setText("Save");
        saveBranchBtn.setEnabled(false);
        saveBranchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBranchBtnActionPerformed(evt);
            }
        });
        jPanel5.add(saveBranchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 330, 100, -1));

        editBranchBtn.setForeground(new java.awt.Color(0, 0, 110));
        editBranchBtn.setText("Edit");
        editBranchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBranchBtnActionPerformed(evt);
            }
        });
        jPanel5.add(editBranchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 80, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1055, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1055, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 381, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        bookingsTab.addTab("Manage Branches", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        historyTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        historyTable.setForeground(new java.awt.Color(0, 0, 110));
        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        jScrollPane4.setViewportView(historyTable);
        if (historyTable.getColumnModel().getColumnCount() > 0) {
            historyTable.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 330));

        jLabel10.setForeground(new java.awt.Color(0, 0, 110));
        jLabel10.setText("Results:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 340, -1, -1));

        resultCount.setForeground(new java.awt.Color(0, 0, 110));
        jPanel2.add(resultCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 340, 30, 20));

        bookingsTab.addTab("Booking History", jPanel2);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        usersTable.setForeground(new java.awt.Color(0, 0, 110));
        usersTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(usersTable);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 320));

        jLabel13.setForeground(new java.awt.Color(0, 0, 110));
        jLabel13.setText("Results:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 340, -1, -1));

        resultCountUser.setForeground(new java.awt.Color(0, 0, 110));
        jPanel3.add(resultCountUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 340, 30, 20));

        bookingsTab.addTab("Users Table", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bookingsTab, javax.swing.GroupLayout.PREFERRED_SIZE, 1022, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(bookingsTab, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBtnActionPerformed
        // TODO add your handling code here:
        resetPanelFields();
        insertComboItems();
        addBtn.show();
        editOn = true;
        enableTF();
    }//GEN-LAST:event_newBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // TODO add your handling code here:         
        
        resetPanelFields();
        insertComboItems();
        saveBtn.show();
        bookingsTable.setRowSelectionAllowed(true);
        editOn = true;
    }//GEN-LAST:event_editBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:

        int confirm = JOptionPane.showConfirmDialog(null, "Confirm delete this row?", "GBHotel",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,  new ImageIcon("C:\\Users\\Keth Dominic\\Documents\\NetBeansProjects\\BookingSystem\\src\\bookingsystem\\images\\icons8_delete_document_30px.png"));
        if(confirm == JOptionPane.YES_OPTION){
            deleteData();
            setBookingsTable();
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
        addInfo();
    }//GEN-LAST:event_addBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        // TODO add your handling code here:
         saveData();
         setBookingsTable();

    }//GEN-LAST:event_saveBtnActionPerformed

    private void comboLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLocationActionPerformed
        // TODO add your handling code here:
        comboBranch.setSelectedItem(dbprocess.getSpecificField("Select `branch_name` from `branch_table` where `branch_location` ='"+comboLocation.getSelectedItem()+"'"));
    }//GEN-LAST:event_comboLocationActionPerformed

    private void comboBranchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBranchActionPerformed
        // TODO add your handling code here:
        comboLocation.setSelectedItem(dbprocess.getSpecificField("Select `branch_location` from `branch_table` where `branch_name` ='"+comboBranch.getSelectedItem()+"'"));
    }//GEN-LAST:event_comboBranchActionPerformed

    private void roomNumTFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomNumTFMouseClicked
        // TODO add your handling code here:
        roomNumTF.setText("");
        roomNumTF.setForeground(defaultColor);
    }//GEN-LAST:event_roomNumTFMouseClicked

    private void roomPriceTFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomPriceTFMouseClicked
        // TODO add your handling code here:
        roomPriceTF.setText("");
        roomPriceTF.setForeground(defaultColor);
    }//GEN-LAST:event_roomPriceTFMouseClicked

    private void bookingsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookingsTableMouseClicked
        // TODO add your handling code here:
        getRoomNumber();
        getBranchName();
        deleteBtn.setEnabled(true);
        if (editOn.equals(true)){
            setDataToFields();
            saveBtn.setEnabled(true);
            errorMsg.setText("");
        }
    }//GEN-LAST:event_bookingsTableMouseClicked

    private void branchNumTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_branchNumTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_branchNumTFActionPerformed

    private void addBranchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBranchBtnActionPerformed
        // TODO add your handling code here:
        addBranchInfo();
        setBranchTable();
    }//GEN-LAST:event_addBranchBtnActionPerformed

    private void saveBranchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBranchBtnActionPerformed
        // TODO add your handling code here:
        saveBranchData();
        setBranchTable();
        setBookingsTable();
    }//GEN-LAST:event_saveBranchBtnActionPerformed

    private void branchesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_branchesTableMouseClicked
        // TODO add your handling code here:
        getBranchNumber();
        delBranchBtn.setEnabled(true);
        if (editBranchOn.equals(true)){
            enableBranchOpt();
            setDataToBranchTable();
            saveBranchBtn.setEnabled(true);
        }
    }//GEN-LAST:event_branchesTableMouseClicked

    private void delBranchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBranchBtnActionPerformed
        // TODO add your handling code here:
        
        int confirm = JOptionPane.showConfirmDialog(null, "Confirm delete this row?\nAll records from Bookings Table will also be deleted", "GBHotel",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,  new ImageIcon("C:\\Users\\Keth Dominic\\Documents\\NetBeansProjects\\BookingSystem\\src\\bookingsystem\\images\\icons8_delete_document_30px.png"));
        if(confirm == JOptionPane.YES_OPTION){
            deleteBranchData();
            setBranchTable();
            setBookingsTable();
            resetBranchOptions();
        }
    }//GEN-LAST:event_delBranchBtnActionPerformed

    private void newBranchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBranchBtnActionPerformed
        // TODO add your handling code here:
        resetBranchOptions(); 
        addBranchBtn.setEnabled(true);
        saveBranchBtn.hide();
        addBranchBtn.show();
        
        enableBranchOpt();
        newBranch();
    }//GEN-LAST:event_newBranchBtnActionPerformed

    private void editBranchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBranchBtnActionPerformed
        // TODO add your handling code here:
        
        resetBranchOptions();
        addBranchBtn.hide();
        saveBranchBtn.show();
        
        editBranchOn = true;
    }//GEN-LAST:event_editBranchBtnActionPerformed

    private void roomNumTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_roomNumTFKeyTyped
        // TODO add your handling code here:
        c = evt.getKeyChar();
        checkIntTF();
        if(isNotDigit){
            evt.consume();
        }
    }//GEN-LAST:event_roomNumTFKeyTyped

    private void roomPriceTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_roomPriceTFKeyTyped
        // TODO add your handling code here:
        c = evt.getKeyChar();
        checkIntTF();
        if(isNotDigit){
            evt.consume();
        }
    }//GEN-LAST:event_roomPriceTFKeyTyped

    private void bookingsTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookingsTabMouseClicked
        // TODO add your handling code here:
        setBookingsTable();
        setBranchTable();
        setHistoryTable();
        setUsersTable();
    }//GEN-LAST:event_bookingsTabMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBranchBtn;
    private javax.swing.JButton addBtn;
    private javax.swing.JTabbedPane bookingsTab;
    private javax.swing.JTable bookingsTable;
    private javax.swing.JTextField branchNameTF;
    private javax.swing.JTextField branchNumTF;
    private javax.swing.JTable branchesTable;
    private javax.swing.JComboBox<String> comboAvail;
    private javax.swing.JComboBox<String> comboBranch;
    private javax.swing.JComboBox<String> comboBranchType;
    private javax.swing.JComboBox<String> comboLocation;
    private javax.swing.JComboBox<String> comboRoomType;
    private javax.swing.JButton delBranchBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBranchBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JLabel errorMsg;
    private javax.swing.JTable historyTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField locationNTF;
    private javax.swing.JTextField managerTF;
    private javax.swing.JButton newBranchBtn;
    private javax.swing.JButton newBtn;
    private javax.swing.JLabel resultCount;
    private javax.swing.JLabel resultCountUser;
    private javax.swing.JTextField roomNumTF;
    private javax.swing.JTextField roomPriceTF;
    private javax.swing.JButton saveBranchBtn;
    private javax.swing.JButton saveBtn;
    private javax.swing.JPanel userInfoPanel;
    private javax.swing.JPanel userInfoPanel1;
    private javax.swing.JTable usersTable;
    // End of variables declaration//GEN-END:variables
}
