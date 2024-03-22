package Form;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Class.database;
import Class.staff;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class staffEdit extends JFrame implements ActionListener {
    database db = new database();
    private JPanel contentPane;
    JButton logout, btnSave, btnCancel;
    JLabel AddLabel, UserLabel, PassLabel, NameLabel, LnameLabel, TelLabel, RoleLabel;
    JTextField UserField, PassField, NameField, LnameField, TelField, RoleField;
    JTable listTable;
    JComboBox<String> RoleCombo;
    String[] roleData;

    public staffEdit(int staff_id) {
        setTitle("Edit Staff");

        roleData = new String[2];
        roleData[0] = "เจ้าหน้าที่";
        roleData[1] = "ผู้บริหาร";
        setTitle("Add Staff");
        setBounds(450, 190, 1014, 597);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Button Add Data
        AddLabel = new JLabel("Edit Staff");
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
        TelLabel = new JLabel("Tel : ");
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
        RoleCombo = new JComboBox<String>(roleData);
        RoleCombo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        RoleCombo.setBounds(600, 300, 250, 30);
        contentPane.add(RoleCombo);

        // Save Cancel Button
        btnSave = new JButton("SAVE");
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSave.setBounds(350, 400, 100, 50);
        btnSave.addActionListener(this);
        contentPane.add(btnSave);
        btnCancel = new JButton("CANCEL");
        btnCancel.setFont(new Font("Tohama", Font.PLAIN, 16));
        btnCancel.setBounds(550, 400, 100, 50);
        btnCancel.addActionListener(this);
        contentPane.add(btnCancel);

        getData(staff_id);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnSave) {
            if (UserField.getText().isEmpty() || PassField.getText().isEmpty() || NameField.getText().isEmpty() || LnameField.getText().isEmpty() || TelField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill All Field");
            } else {
                staff st = new staff();
                st.setSUser(UserField.getText());
                st.setSPw(PassField.getText());
                st.setSFname(NameField.getText());
                st.setSLname(LnameField.getText());
                st.setSTel(TelField.getText());
                st.setRole(RoleCombo.getSelectedItem().toString());
                st.UpdateStaff();
                JOptionPane.showMessageDialog(null, "Add Success");
                this.dispose();
            }
        } else if (event.getSource() == btnCancel) {
            setVisible(false); // you can't see me!
            dispose(); // Destroy the JFrame object
        }
    }

    public void getData(int id) {
        Connection conn = db.getConnection();
        String sql = "SELECT * FROM staff WHERE s_id = " + id;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                UserField.setText(rs.getString("s_user"));
                PassField.setText(rs.getString("s_pw"));
                NameField.setText(rs.getString("s_fname"));
                LnameField.setText(rs.getString("s_lname"));
                TelField.setText(rs.getString("s_tel"));
                RoleCombo.setSelectedItem(rs.getString("role"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
