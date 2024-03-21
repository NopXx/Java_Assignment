import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.util.*;
import java.util.jar.Attributes.Name;

public class BorrowPopup extends JFrame implements ActionListener {

    database db = new database();
    private JPanel contentPane;
    JButton logout, btnSave, btnCancel;
    JLabel AddLabel, UserLabel, AccessoryLabel, CountLabel, DateLabel;
    JTextField UserField, AccessoryField, CountField, DateField;
    JTable listTable;

    public BorrowPopup() {
        // staff staffData = StaffData;
        // System.out.println(staffData.toString());
        // if (staffData.getSUser() == "" || staffData.getSUser() == null) {
        // dispose();
        // loginFrom login = new loginFrom();
        // login.setVisible(true);
        // // JOptionPane.showMessageDialog(null, "Please Login");
        // }

        setTitle("Accessory Management System");
        
        

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Hello Manager waka");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(700, 20, 273, 15);
        contentPane.add(lblNewLabel);

        logout = new JButton("Logout");
        logout.addActionListener(this);
        logout.setFont(new Font("Tahoma", Font.PLAIN, 16));
        logout.setBounds(890, 10, 100, 30);
        contentPane.add(logout);
        // Button Add Data
        AddLabel = new JLabel("Borrow");
        AddLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        AddLabel.setBounds(150, 50, 100, 30);
        contentPane.add(AddLabel);

        // Name Label Field 
        UserLabel = new JLabel("Username : ");
        UserLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        UserLabel.setBounds(125, 100, 100, 30);
        contentPane.add(UserLabel);
        UserField = new JTextField();
        UserField.setBounds(125, 125, 300, 30);
        contentPane.add(UserField);

        // Accessory Label Field
        AccessoryLabel = new JLabel("Accessory Name : ");
        AccessoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        AccessoryLabel.setBounds(125, 160, 150, 30);
        contentPane.add(AccessoryLabel);
        AccessoryField = new JTextField();
        AccessoryField.setBounds(125, 185, 300, 30);
        contentPane.add(AccessoryField);

        // Count label Field
        CountLabel = new JLabel("Count : ");
        CountLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        CountLabel.setBounds(125, 220, 100, 30);
        contentPane.add(CountLabel);
        CountField = new JTextField();
        CountField.setBounds(125, 245, 300, 30);
        contentPane.add(CountField);

        // Date Label Field
        DateLabel = new JLabel("Date : ");
        DateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        DateLabel.setBounds(125, 285, 100, 30);
        contentPane.add(DateLabel);
        DateField = new JTextField();
        DateField.setBounds(125, 310, 300, 30);
        contentPane.add(DateField);

        // Save Cancel Button
        btnSave = new JButton("SAVE");
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSave.setBounds(125, 400, 100, 50);
        contentPane.add(btnSave);
        btnCancel = new JButton("CANCEL");
        btnCancel.setFont(new Font("Tohama", Font.PLAIN, 16));
        btnCancel.setBounds(325, 400, 100, 50);
        contentPane.add(btnCancel);

        // set windows
        setContentPane(contentPane);
        pack();
        setBounds(450, 190, 550, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

    }

    public void actionPerformed(ActionEvent event) {

    }

    // remove main
    // public static void main(String[] args) {
    //     EventQueue.invokeLater(new Runnable() {
    //         public void run() {
    //             try {
    //                 accessoryAdd frame = new accessoryAdd();
    //                 frame.setVisible(true);
    //             } catch (Exception e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //     });
    // }
}