/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookingsystem;

import java.awt.Color;
import java.sql.*;
import java.text.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.time.LocalDate;


/**
 *
 * @author Keth Dominic
 */
public class Homepanel extends javax.swing.JFrame {
    DefaultTableModel model;
    Object tablerow [][];
    String tablecol []= {"Branch","Location","Room Number","Room Type","Room Price/Night (Php)"};
    
    
    int mouseX;
    int mouseY;
    ResultSet result;
    PreparedStatement statement;
    Connection connect;
    Color defaultColor = new Color(0, 0, 110);
    DateFormat currentDateFormat = new SimpleDateFormat("MMMM d, yyyy");
    DateFormat reserveDateFormat = new SimpleDateFormat("MMM/dd/yyyy");
    DateFormat nextDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date currentDate = new Date();
    String userId;
    String checkInReserve,checkOutReserve;
    Long daysOccupied;
    char c;
    Boolean isNotDigit;
    String idReserve, branchReserve,locationReserve,roomTypeReserve,roomPriceReserve,dateReserve;
    Integer roomNumReserve,  daysOccupiedReserve;
    double pricePayed;
    
    
    
    /**
     * Creates new form Homepanel
     */
    QueryProcessor dbprocess;
    
    public Homepanel() {
        initComponents();
        dbprocess = new QueryProcessor ();
        connect = DBConnection.connect;
        comboBoxItems();
        searchTable();

        currentDateLabel.setText(currentDateFormat.format(currentDate));

    }

public void comboBoxItems(){
// Insert Database contents to comboBox 
    try {
     
    statement = connect.prepareStatement("Select DISTINCT `booking_type` FROM `booking_table`");
    result = statement.executeQuery();
    while(result.next()){
        comboType.addItem(result.getString("booking_type"));
    }
    } catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
    minPriceTF.setText(dbprocess.getSpecificField("Select MIN(`booking_price`) FROM `booking_table`"));
    maxPriceTF.setText(dbprocess.getSpecificField("Select MAX(`booking_price`) FROM `booking_table`"));
    
    String dateToday = reserveDateFormat.format(currentDate);
    String today[] = dateToday.split("/");
    monthCBox.setSelectedItem(today[0]);
    dayCBox.setSelectedItem(today[1]);
    yearCBox.setSelectedItem(today[2]);

    LocalDate dateTomm = LocalDate.parse(nextDateFormat.format(currentDate)).plusDays(1);
    String tomm[] = dateToday.split("/");
    String tommDay[] = dateTomm.toString().split("-");
    monthCBox1.setSelectedItem(tomm[0]);
    dayCBox1.setSelectedItem(tommDay[2]);
    yearCBox1.setSelectedItem(tomm[2]); 
}
public void searchTable(){
//Insert query results to searchTable
    String branch = branchTF.getText();
    String roomType = comboType.getSelectedItem().toString();
    String minPrice = minPriceTF.getText();
    String maxPrice = maxPriceTF.getText();
    String sort;
        //check if user has not inputted on branchTF
        if (branchTF.getText().equals("Search City/Municipality")){
            branch = "";
        }
        //check if user has not selected Room Type
        if (comboType.getSelectedItem().equals("Room Type")){
            roomType = "";
        }
        //check if user has no Price Range
        if (maxPriceTF.getText().equals("")){
            maxPrice = dbprocess.getSpecificField("Select MAX(`booking_price`) FROM `booking_table`");
        }
        if (minPriceTF.getText().equals("")){
            minPrice = dbprocess.getSpecificField("Select MIN(`booking_price`) FROM `booking_table`");
        }
        //check users sort prefference and enable sort
        comboSort.setEnabled(true);
        if(comboSort.getSelectedIndex() == 0){
            sort = "ASC";
        }
        else{
            sort = "DESC";
        }
        
        String searchQuery = "SELECT `branch_table`.`branch_name`,`branch_table`.`branch_location`"
                +",`booking_table`.`booking_room_num`,`booking_table`.`booking_type`,`booking_table`.`booking_price`"
                +"FROM `branch_table`,`booking_table` WHERE `branch_table`.`branch_name` = `booking_table`.`booking_branch`"
                +"AND `branch_table`.`branch_name` LIKE '%"+branch+"%'  AND "
                +"`booking_table`.`booking_price` BETWEEN '"+minPrice+"' AND '"+maxPrice
                +"'  AND `booking_table`.`booking_type` LIKE '%"+roomType
                +"%' AND `booking_table`.`booking_avail` = 'Vacant' ORDER BY `booking_price` "+sort+""; 
        
                //System.out.println(searchQuery);
        tablerow = dbprocess.getAllRecord(searchQuery);
        model = new DefaultTableModel(tablerow,tablecol);
        searchTable.setModel(model);
        searchTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        searchTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        if (tablerow == null){
            tableResultLabel.setText("No Results Found");
        }
        else{ 
            tableResultLabel.setText("");
        }
}
public int getRoomNumber(){
    return Integer.parseInt(model.getValueAt(searchTable.getSelectedRow(),2).toString());

}
public String getBranchName(){
    return model.getValueAt(searchTable.getSelectedRow(),0).toString();
}
public void getSelection(){
        idReserve = userId;
        branchReserve = getBranchName();
        locationReserve = dbprocess.getSpecificField("Select `branch_location` from `branch_table` where `branch_name` ='"+getBranchName()+"'");
        dateReserve = reserveDateFormat.format(currentDate);
        roomNumReserve = getRoomNumber();
        roomTypeReserve = dbprocess.getSpecificField("Select `booking_type` from `booking_table` where `booking_room_num` ='"+getRoomNumber()+"' AND `booking_branch` = '"+getBranchName()+"'");           
        daysOccupiedReserve = (Integer.parseInt(getDayDiff())* -1);
        roomPriceReserve = dbprocess.getSpecificField("Select `booking_price` from `booking_table` where `booking_room_num` ='"+getRoomNumber()+"' AND `booking_branch` = '"+getBranchName()+"'");
        pricePayed = Double.parseDouble(roomPriceReserve) * ((Double.parseDouble(getDayDiff()) * -1));

}
public void reserve(){
    try{        
        String ReserveQuery = "Insert into `recent_bookings_table` (`customer_id`, `branch`, `location`, `reservation_date`, `check_in`, `check_out`, `room_number`, `room_type`,`days_occupied`,`room_price`, `price_payed`)"
                    +"VALUES ('"+idReserve+"','"+branchReserve+"','"+locationReserve+"','"+dateReserve+"','"+checkInReserve
                    +"','"+checkOutReserve+"','"+roomNumReserve+"','"+roomTypeReserve+"','"+daysOccupiedReserve
                    +"','"+roomPriceReserve+"','"+pricePayed+"')";
        String availabilityQuery = "Update `booking_table` SET `booking_avail` ='Reserved' where `booking_room_num` ='"+getRoomNumber()+"' AND `booking_branch` = '"+getBranchName()+"'"; 

            dbprocess.executeUpdate(ReserveQuery);
            dbprocess.executeUpdate(availabilityQuery);
            JOptionPane.showMessageDialog(null,"Thank you for booking with us, enjoy your stay!","GBHotel",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("C:\\Users\\Keth Dominic\\Documents\\NetBeansProjects\\BookingSystem\\src\\bookingsystem\\images\\icons8_check_file_30px.png"));
            searchTable();
    }
    catch(Exception e){
        searchTable();
        System.out.println(e);
    }
    
}
public String getDayDiff() {
    //getting the difference between time
        try {
        checkInReserve  = ""+monthCBox.getSelectedItem()+"/"+dayCBox.getSelectedItem()+"/"+yearCBox.getSelectedItem()+"";
        checkOutReserve = ""+monthCBox1.getSelectedItem()+"/"+dayCBox1.getSelectedItem()+"/"+yearCBox1.getSelectedItem()+"";

                Date checkin,checkout;
                checkin = reserveDateFormat.parse(checkInReserve);
                checkout = reserveDateFormat.parse(checkOutReserve);
          long difference = checkin.getTime() - checkout.getTime();
            TimeUnit time = TimeUnit.DAYS; 
            long dayDifference = time.convert(difference, TimeUnit.MILLISECONDS);
            daysOccupied = dayDifference;
            
        } catch (ParseException e) {

        }
     
    return daysOccupied.toString();
}
public void checkCheckoutDate(){
        Integer checkOutDate = (Integer.parseInt(getDayDiff())* -1);
            if( checkOutDate < 0){
                jLabel7.setText("Invalid Date");
                reserveLabel.setEnabled(false);
            }
            else{
                reserveLabel.setEnabled(true);
                jLabel7.setText("");
            }
}
public void checkIntTF(){
    if (Character.isDigit(c)){   
            priceErrorMsg.setText("");
            isNotDigit = false;
        }
        else{
            isNotDigit = true;
            priceErrorMsg.setText("Input must be numbers only");       
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

        jSeparator1 = new javax.swing.JSeparator();
        tablePanel = new javax.swing.JPanel();
        comboSort = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        searchTable = new javax.swing.JTable();
        reserveLabel = new javax.swing.JLabel();
        yearCBox = new javax.swing.JComboBox<>();
        monthCBox = new javax.swing.JComboBox<>();
        dayCBox = new javax.swing.JComboBox<>();
        monthCBox1 = new javax.swing.JComboBox<>();
        yearCBox1 = new javax.swing.JComboBox<>();
        dayCBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tableResultLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTitlePanel = new javax.swing.JPanel();
        profileLabel = new javax.swing.JLabel();
        logoutLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        searchPanel = new javax.swing.JPanel();
        searchLabel = new javax.swing.JLabel();
        findaccomoLabel = new javax.swing.JLabel();
        comboType = new javax.swing.JComboBox<>();
        minPriceTF = new javax.swing.JTextField();
        maxPriceTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        branchTF = new javax.swing.JTextField();
        priceErrorMsg = new javax.swing.JLabel();
        currentDateLabel = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GBHotel");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(new ImageIcon("C:\\Users\\Keth Dominic\\Documents\\NetBeansProjects\\BookingSystem\\src\\bookingsystem\\images\\gblogo.png").getImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, -1));

        tablePanel.setBackground(new java.awt.Color(255, 255, 255));
        tablePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Price Lowest to Highest", "Price Highest to Lowest" }));
        comboSort.setEnabled(false);
        comboSort.setOpaque(false);
        comboSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSortActionPerformed(evt);
            }
        });
        tablePanel.add(comboSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 200, -1));

        searchTable.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        searchTable.setForeground(new java.awt.Color(0, 0, 255));
        searchTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Branch", "Location", "Room Number", "Room Type", "Room Price/Night (Php)"
            }
        ));
        searchTable.setGridColor(new java.awt.Color(204, 204, 204));
        searchTable.setRowHeight(30);
        searchTable.setShowVerticalLines(false);
        searchTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(searchTable);
        if (searchTable.getColumnModel().getColumnCount() > 0) {
            searchTable.getColumnModel().getColumn(0).setPreferredWidth(200);
            searchTable.getColumnModel().getColumn(1).setPreferredWidth(120);
            searchTable.getColumnModel().getColumn(2).setPreferredWidth(30);
            searchTable.getColumnModel().getColumn(3).setPreferredWidth(30);
            searchTable.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        tablePanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 1050, 230));

        reserveLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        reserveLabel.setForeground(new java.awt.Color(0, 0, 255));
        reserveLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookingsystem/images/icons8_reserve_30px_1.png"))); // NOI18N
        reserveLabel.setText("Reserve");
        reserveLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        reserveLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reserveLabelMouseClicked(evt);
            }
        });
        tablePanel.add(reserveLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 290, 120, 40));

        yearCBox.setForeground(new java.awt.Color(0, 0, 110));
        yearCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2022", "2023", "2024", "2025", " ", " " }));
        tablePanel.add(yearCBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, 110, 40));

        monthCBox.setForeground(new java.awt.Color(0, 0, 110));
        monthCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        tablePanel.add(monthCBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 100, 40));

        dayCBox.setForeground(new java.awt.Color(0, 0, 110));
        dayCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", " " }));
        tablePanel.add(dayCBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 60, 40));

        monthCBox1.setForeground(new java.awt.Color(0, 0, 110));
        monthCBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        tablePanel.add(monthCBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 290, 100, 40));

        yearCBox1.setForeground(new java.awt.Color(0, 0, 110));
        yearCBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2022", "2023", "2024", "2025", " ", " " }));
        tablePanel.add(yearCBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 290, 110, 40));

        dayCBox1.setForeground(new java.awt.Color(0, 0, 110));
        dayCBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", " " }));
        dayCBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayCBox1ActionPerformed(evt);
            }
        });
        tablePanel.add(dayCBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 290, 60, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 110));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookingsystem/images/icons8_right_25px.png"))); // NOI18N
        jLabel4.setText("Check-out");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        tablePanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, 140, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 110));
        jLabel5.setText("Check-in");
        tablePanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 90, 40));

        tableResultLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        tableResultLabel.setForeground(new java.awt.Color(0, 0, 110));
        tableResultLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tablePanel.add(tableResultLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 480, 40));

        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tablePanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 330, 190, 20));

        getContentPane().add(tablePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 1110, 350));

        jTitlePanel.setBackground(new java.awt.Color(255, 255, 255));
        jTitlePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTitlePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        profileLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        profileLabel.setForeground(new java.awt.Color(0, 0, 255));
        profileLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookingsystem/images/icons8_male_user_40px.png"))); // NOI18N
        profileLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileLabelMouseClicked(evt);
            }
        });
        jTitlePanel.add(profileLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 40));

        logoutLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        logoutLabel.setForeground(new java.awt.Color(0, 0, 255));
        logoutLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookingsystem/images/icons8_shutdown_40px.png"))); // NOI18N
        logoutLabel.setText("Logout");
        logoutLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutLabelMouseClicked(evt);
            }
        });
        jTitlePanel.add(logoutLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 10, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookingsystem/images/gblogotext.png"))); // NOI18N
        jTitlePanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 170, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookingsystem/images/gblogo_1.png"))); // NOI18N
        jTitlePanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 40, 60));

        getContentPane().add(jTitlePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 60));

        searchPanel.setBackground(new java.awt.Color(255, 255, 255));
        searchPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        searchLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        searchLabel.setForeground(new java.awt.Color(0, 0, 255));
        searchLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookingsystem/images/icons8_search_40px.png"))); // NOI18N
        searchLabel.setText("Search");
        searchLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchLabelMouseClicked(evt);
            }
        });
        searchPanel.add(searchLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 90, 140, 50));

        findaccomoLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        findaccomoLabel.setForeground(new java.awt.Color(0, 0, 110));
        findaccomoLabel.setText("Find Accomodations");
        searchPanel.add(findaccomoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 440, 50));

        comboType.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        comboType.setForeground(new java.awt.Color(153, 153, 153));
        comboType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Room Type" }));
        comboType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTypeActionPerformed(evt);
            }
        });
        searchPanel.add(comboType, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, 220, 80));

        minPriceTF.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBox.light"));
        minPriceTF.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        minPriceTF.setForeground(new java.awt.Color(153, 153, 153));
        minPriceTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minPriceTFMouseClicked(evt);
            }
        });
        minPriceTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                minPriceTFKeyTyped(evt);
            }
        });
        searchPanel.add(minPriceTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 120, 80));

        maxPriceTF.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBox.light"));
        maxPriceTF.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        maxPriceTF.setForeground(new java.awt.Color(153, 153, 153));
        maxPriceTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maxPriceTFMouseClicked(evt);
            }
        });
        maxPriceTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                maxPriceTFKeyTyped(evt);
            }
        });
        searchPanel.add(maxPriceTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 120, 80));

        jLabel3.setForeground(new java.awt.Color(0, 0, 110));
        jLabel3.setText("Price range in Pesos");
        searchPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 240, -1));

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 110));
        jLabel6.setText("-");
        searchPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 30, -1));

        branchTF.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBox.light"));
        branchTF.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        branchTF.setForeground(new java.awt.Color(153, 153, 153));
        branchTF.setText("Search City/Municipality");
        branchTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                branchTFMouseClicked(evt);
            }
        });
        searchPanel.add(branchTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 400, 80));

        priceErrorMsg.setForeground(new java.awt.Color(255, 0, 0));
        searchPanel.add(priceErrorMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 270, 30));

        currentDateLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        currentDateLabel.setForeground(new java.awt.Color(51, 51, 51));
        currentDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        searchPanel.add(currentDateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 360, 40));

        getContentPane().add(searchPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 1110, 190));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookingsystem/images/pexels-pixabay-210604.jpg"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(-320, -260, 1490, 990));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logoutLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutLabelMouseClicked
        // TODO add your handling code here:
        Loginpanel login = new Loginpanel();
        login.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_logoutLabelMouseClicked

    private void profileLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileLabelMouseClicked
        // TODO add your handling code here:
        Settingspanel settings = new Settingspanel();
        settings.displayAccInformation(userId);
        settings.recentFrame(userId);
        settings.setVisible(true);
        
    }//GEN-LAST:event_profileLabelMouseClicked

    private void minPriceTFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minPriceTFMouseClicked
        // TODO add your handling code here:
        minPriceTF.setText("");
        minPriceTF.setForeground(defaultColor);
    }//GEN-LAST:event_minPriceTFMouseClicked

    private void maxPriceTFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maxPriceTFMouseClicked
        // TODO add your handling code here:
        
        maxPriceTF.setText("");
        maxPriceTF.setForeground(defaultColor);
    }//GEN-LAST:event_maxPriceTFMouseClicked

    private void comboTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTypeActionPerformed
        // TODO add your handling code here:
        if(comboType.getSelectedIndex() != 0){
                comboType.setForeground(defaultColor);
        }
        else{
            comboType.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_comboTypeActionPerformed

    private void branchTFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_branchTFMouseClicked
        // TODO add your handling code here:
        branchTF.setText("");
        branchTF.setForeground(defaultColor);
    }//GEN-LAST:event_branchTFMouseClicked

    private void searchLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchLabelMouseClicked
        // TODO add your handling code here:
        searchTable();
    }//GEN-LAST:event_searchLabelMouseClicked

    private void comboSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSortActionPerformed
        // TODO add your handling code here:
        searchTable();
    }//GEN-LAST:event_comboSortActionPerformed

    private void reserveLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reserveLabelMouseClicked
        // TODO add your handling code here:
        if(reserveLabel.isEnabled()){
            if (searchTable.getSelectionModel().isSelectionEmpty()){
                tableResultLabel.setText("Please select accomodations first" );
            }
            else{
                getSelection();
                tableResultLabel.setText("" );
                int confirm = JOptionPane.showConfirmDialog(null, "<html><b>Please confirm that your reservation is correct.      </b>\nBranch: "
                        +branchReserve+"\nLocation: "+locationReserve+"\nRoom Number: "+roomNumReserve+"\nRoom Type: "+roomTypeReserve
                        +"\nPrice/Night: P"+roomPriceReserve+"\nNo. of Days: "+daysOccupiedReserve+"\nTotal Payable: P"+pricePayed+"0", "GBHotel",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,  new ImageIcon("C:\\Users\\Keth Dominic\\Documents\\NetBeansProjects\\BookingSystem\\src\\bookingsystem\\images\\icons8_checked_radio_button_48px.png"));
                if(confirm == JOptionPane.YES_OPTION){
                    
                    reserve();
                }
            }
        }
    }//GEN-LAST:event_reserveLabelMouseClicked

    private void searchTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchTableMouseClicked
        // TODO add your handling code here:
        getRoomNumber();
        getSelection();
    }//GEN-LAST:event_searchTableMouseClicked

    private void dayCBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayCBox1ActionPerformed
        // TODO add your handling code here:
          checkCheckoutDate();
    }//GEN-LAST:event_dayCBox1ActionPerformed

    private void minPriceTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_minPriceTFKeyTyped
        // TODO add your handling code here:
        c = evt.getKeyChar();
        checkIntTF();
        if(isNotDigit){
            evt.consume();
        }
    }//GEN-LAST:event_minPriceTFKeyTyped

    private void maxPriceTFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_maxPriceTFKeyTyped
        // TODO add your handling code here:
        c = evt.getKeyChar();
        checkIntTF();
        if(isNotDigit){
            evt.consume();
        }
    }//GEN-LAST:event_maxPriceTFKeyTyped

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
            java.util.logging.Logger.getLogger(Homepanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Homepanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Homepanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Homepanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Homepanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JTextField branchTF;
    private javax.swing.JComboBox<String> comboSort;
    private javax.swing.JComboBox<String> comboType;
    private javax.swing.JLabel currentDateLabel;
    private javax.swing.JComboBox<String> dayCBox;
    private javax.swing.JComboBox<String> dayCBox1;
    private javax.swing.JLabel findaccomoLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jTitlePanel;
    private javax.swing.JLabel logoutLabel;
    private javax.swing.JTextField maxPriceTF;
    private javax.swing.JTextField minPriceTF;
    private javax.swing.JComboBox<String> monthCBox;
    private javax.swing.JComboBox<String> monthCBox1;
    private javax.swing.JLabel priceErrorMsg;
    private javax.swing.JLabel profileLabel;
    private javax.swing.JLabel reserveLabel;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTable searchTable;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JLabel tableResultLabel;
    private javax.swing.JComboBox<String> yearCBox;
    private javax.swing.JComboBox<String> yearCBox1;
    // End of variables declaration//GEN-END:variables
}
