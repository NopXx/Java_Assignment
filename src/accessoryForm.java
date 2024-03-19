import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.util.*;

public class accessoryForm extends JFrame implements ActionListener {

    database db = new database();
    private JPanel contentPane;
    JButton logout, btnAddData;
    JTable listTable;

    public accessoryForm() {
        // staff staffData = StaffData;
        // System.out.println(staffData.toString());
        // if (staffData.getSUser() == "" || staffData.getSUser() == null) {
        // dispose();
        // loginFrom login = new loginFrom();
        // login.setVisible(true);
        // // JOptionPane.showMessageDialog(null, "Please Login");
        // }
        String Data[][] = { { "1", "JPANG", "15", "Edit / Delete" },
                { "2", "Homesweet", "20", "Edit / Delete" },
                { "3", "Lay", "10", "Edit / Delete" },
                { "4", "CocaCola", "20", "Edit / Delete" }, };

        String Header[] = { "No", "Name", "Count", "Action" };

        setTitle("Accessory Management System");
        setBounds(450, 190, 1014, 597);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
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
        btnAddData = new JButton("Add Data");
        btnAddData.addActionListener(this);
        btnAddData.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnAddData.setBounds(50, 75, 100, 30);
        contentPane.add(btnAddData);

        // Table
        listTable = new JTable(Data, Header);
        listTable.setBounds(175, 75, 775, 300);
        contentPane.add(listTable);
        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(listTable);
        scrollPane.setBounds(175, 75, 775, 300);
        getContentPane().add(scrollPane);

    }

    public void actionPerformed(ActionEvent event) {

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    accessoryForm frame = new accessoryForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}