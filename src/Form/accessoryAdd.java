package Form;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Class.accessory;
import Class.database;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.util.*;
import java.util.jar.Attributes.Name;

public class accessoryAdd extends JFrame implements ActionListener {

    database db = new database();
    private JPanel contentPane;
    JButton logout, btnSave, btnCancel;
    JLabel AddLabel, NameLabel, CountLabel;
    JTextField NameField, CountField;
    JTable listTable;

    public accessoryAdd() {
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
        AddLabel = new JLabel("Add Data");
        AddLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        AddLabel.setBounds(150, 75, 100, 30);
        contentPane.add(AddLabel);

        // Name Label Field
        NameLabel = new JLabel("Name : ");
        NameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        NameLabel.setBounds(75, 150, 100, 30);
        contentPane.add(NameLabel);
        NameField = new JTextField();
        NameField.setBounds(150, 150, 300, 30);
        contentPane.add(NameField);
        // Count label Field
        CountLabel = new JLabel("Count : ");
        CountLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        CountLabel.setBounds(75, 250, 100, 30);
        contentPane.add(CountLabel);
        CountField = new JTextField();
        CountField.setBounds(150, 250, 300, 30);
        contentPane.add(CountField);

        // Save Cancel Button
        btnSave = new JButton("SAVE");
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSave.setBounds(125, 350, 100, 50);
        btnSave.addActionListener(this);
        contentPane.add(btnSave);
        btnCancel = new JButton("CANCEL");
        btnCancel.setFont(new Font("Tohama", Font.PLAIN, 16));
        btnCancel.setBounds(325, 350, 100, 50);
        btnCancel.addActionListener(this);
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
        if (event.getSource() == btnSave) {
            if (NameField.getText().isEmpty() || CountField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill All Field");
            } else {
                accessory acc = new accessory();
                acc.setAccessoryName(NameField.getText());
                acc.setAccessoryCount(Integer.parseInt(CountField.getText()));
                acc.addAccessory();
                JOptionPane.showMessageDialog(null, "Insert Accessory Success");
                setVisible(false); // you can't see me!
                dispose(); // Destroy the JFrame object
            }

        } else if (event.getSource() == btnCancel) {
            setVisible(false); // you can't see me!
            dispose(); // Destroy the JFrame object
        }
    }

    // remove main
    // public static void main(String[] args) {
    // EventQueue.invokeLater(new Runnable() {
    // public void run() {
    // try {
    // accessoryAdd frame = new accessoryAdd();
    // frame.setVisible(true);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
    // });
    // }
}