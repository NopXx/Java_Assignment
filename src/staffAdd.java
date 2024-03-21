import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.util.*;
import java.util.jar.Attributes.Name;

public class staffAdd extends JFrame implements ActionListener {

    database db = new database();
    private JPanel contentPane;
    JButton logout, btnSave, btnCancel;
    JLabel AddLabel, UserLabel, PassLabel, NameLabel, LnameLabel, TelLabel, RoleLabel;
    JTextField UserField, PassField, NameField, LnameField, TelField, RoleField;
    JTable listTable;

    public staffAdd() {
        // staff staffData = StaffData;
        // System.out.println(staffData.toString());
        // if (staffData.getSUser() == "" || staffData.getSUser() == null) {
        // dispose();
        // loginFrom login = new loginFrom();
        // login.setVisible(true);
        // // JOptionPane.showMessageDialog(null, "Please Login");
        // }

        setTitle("Accessory Management System");
        setBounds(450, 190, 1014, 597);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Button Add Data
        AddLabel = new JLabel("Add Staff Data");
        AddLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        AddLabel.setBounds(200, 75, 150, 30);
        contentPane.add(AddLabel);

        // User Label Field
        UserLabel = new JLabel("Username : ");
        UserLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        UserLabel.setBounds(125, 150, 250, 30);
        contentPane.add(UserLabel);
        UserField = new JTextField();
        UserField.setBounds(225, 150, 250, 30);
        contentPane.add(UserField);
        // Pass Label Field
        PassLabel = new JLabel("Password : ");
        PassLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        PassLabel.setBounds(500, 150, 250, 30);
        contentPane.add(PassLabel);
        PassField = new JTextField();
        PassField.setBounds(600, 150, 250, 30);
        contentPane.add(PassField);
        // Name Label Field 
        NameLabel = new JLabel("FirstName : ");
        NameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        NameLabel.setBounds(125, 225, 250, 30);
        contentPane.add(NameLabel);
        NameField = new JTextField();
        NameField.setBounds(225, 225, 250, 30);
        contentPane.add(NameField);
        // Lname label Field
        LnameLabel = new JLabel("Lastname : ");
        LnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        LnameLabel.setBounds(500, 225, 250, 30);
        contentPane.add(LnameLabel);
        LnameField = new JTextField();
        LnameField.setBounds(600, 225, 250, 30);
        contentPane.add(LnameField);
        // Tel Label Field
        TelLabel = new JLabel("Role : ");
        TelLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        TelLabel.setBounds(125, 300, 250, 30);
        contentPane.add(TelLabel);
        TelField = new JTextField();
        TelField.setBounds(225, 300, 250, 30);
        contentPane.add(TelField);
        // Role Label Field
        RoleLabel = new JLabel("Role : ");
        RoleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        RoleLabel.setBounds(500, 300, 250, 30);
        contentPane.add(RoleLabel);
        RoleField = new JTextField();
        RoleField.setBounds(600, 300, 250, 30);
        contentPane.add(RoleField);

        // Save Cancel Button
        btnSave = new JButton("SAVE");
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSave.setBounds(350, 400, 100, 50);
        contentPane.add(btnSave);
        btnCancel = new JButton("CANCEL");
        btnCancel.setFont(new Font("Tohama", Font.PLAIN, 16));
        btnCancel.setBounds(550, 400, 100, 50);
        contentPane.add(btnCancel);

    }

    public void actionPerformed(ActionEvent event) {

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    staffAdd frame = new staffAdd();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}