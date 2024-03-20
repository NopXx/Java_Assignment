public class staffForm {
    
}
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.util.*;

public class staffForm extends JFrame implements ActionListener {

    database db = new database();
    private JPanel contentPane;
    JButton logout, btnAddData, btnSearch;
    JTextField SearchField;
    JTable listTable;

    public staffForm() {
        // staff staffData = StaffData;
        // System.out.println(staffData.toString());
        // if (staffData.getSUser() == "" || staffData.getSUser() == null) {
        // dispose();
        // loginFrom login = new loginFrom();
        // login.setVisible(true);
        // // JOptionPane.showMessageDialog(null, "Please Login");
        // }
        String Data[][] = { { "1", "Waka", "Waka", "Manager", "Edit / Delete" },
                { "2", "Sukjai", "Jungboei", "Producer", "Edit / Delete" },
                { "3", "Lama", "Yana", "Employee", "Edit / Delete" },
                { "4", "Hello", "World", "Employee", "Edit / Delete" }, };

        String Header[] = { "No", "Name", "LastName", "Count", "Action" };

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
        btnAddData.setBounds(250, 75, 100, 30);
        contentPane.add(btnAddData);
        // Search Bar Button
        SearchField = new JTextField();
        SearchField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        SearchField.setBounds(475, 75, 350, 30);
        contentPane.add(SearchField);
        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSearch.setBounds(850, 75, 100, 30);
        contentPane.add(btnSearch);

        // Table
        listTable = new JTable(Data, Header);
        contentPane.add(listTable);
        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(listTable);
        scrollPane.setBounds(250, 125, 700, 300);
        getContentPane().add(scrollPane);

    }

    public void actionPerformed(ActionEvent event) {

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    staffForm frame = new staffForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}