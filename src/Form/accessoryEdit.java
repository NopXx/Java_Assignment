package Form;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Class.accessory;
import Class.database;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class accessoryEdit extends JFrame implements ActionListener {
    database db = new database();
    private JPanel contentPane;
    JButton logout, btnSave, btnCancel;
    JLabel AddLabel, NameLabel, CountLabel;
    JTextField NameField, CountField;
    JTable listTable;
    private int a_id;

    public accessoryEdit(int id) {
        a_id = id;
        
        setTitle("Edit Accessory");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        contentPane.setLayout(null);

        // Button Add Data
        AddLabel = new JLabel("Edit Data");
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

        getData(a_id);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnSave) {
            if (NameField.getText().isEmpty() || CountField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill All Field");
            } else {
                accessory acc = new accessory();
                acc.setAccessoryId(a_id);
                acc.setAccessoryName(NameField.getText());
                acc.setAccessoryCount(Integer.parseInt(CountField.getText()));
                acc.editAccessory();
                JOptionPane.showMessageDialog(null, "Edit Accessory Success");
                setVisible(false); // you can't see me!
                dispose(); // Destroy the JFrame object
            }

        } else if (event.getSource() == btnCancel) {
            setVisible(false); // you can't see me!
            dispose(); // Destroy the JFrame object
        }
    }

    // Get Data DB to Table
    public void getData(int id) {
        System.out.println(id);
        Connection conn = db.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM accessory WHERE a_id = " + id;
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                NameField.setText(rs.getString("a_name"));
                CountField.setText(rs.getString("a_count"));
            }
            
            

        } catch (SQLException ex) {
            // Logger.getLogger(lab.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex, "error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
