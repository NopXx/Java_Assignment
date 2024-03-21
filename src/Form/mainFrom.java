package Form;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Class.database;
import Class.staff;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.util.*;

public class mainFrom extends JFrame implements ActionListener {
    database db = new database();
    private JPanel contentPane;
    JButton logout, accessoryBtn, staffBtn, listBtn, reportBtn;
    staff staffData;

    // staff dStaffData;
    public mainFrom() {
        dispose();
        loginFrom login = new loginFrom();
        login.setVisible(true);

    }

    public mainFrom(staff StaffData) {
        // check login
        staffData = StaffData;
        setTitle("Accessory Management System");
        setBounds(450, 190, 1014, 597);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel(staffData.getRole() + " " + staffData.getSFname());
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(700, 20, 273, 15);
        contentPane.add(lblNewLabel);

        logout = new JButton("Logout");
        logout.addActionListener(this);
        logout.setFont(new Font("Tahoma", Font.PLAIN, 16));
        logout.setBounds(890, 10, 100, 30);
        contentPane.add(logout);

        //
        accessoryBtn = new JButton("จัดการอุปกรณ์");
        accessoryBtn.addActionListener(this);
        accessoryBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
        accessoryBtn.setBounds(150, 100, 250, 120);
        contentPane.add(accessoryBtn);

        //
        listBtn = new JButton("จัดการข้อมูล ยืม-คืน");
        listBtn.addActionListener(this);
        listBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
        listBtn.setBounds(550, 100, 250, 120);
        contentPane.add(listBtn);

        //
        staffBtn = new JButton("จัดการข้อมูลเจ้าหน้าที่");
        staffBtn.addActionListener(this);
        staffBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
        staffBtn.setBounds(150, 280, 250, 120);
        contentPane.add(staffBtn);

        //
        reportBtn = new JButton("รายงาน");
        reportBtn.addActionListener(this);
        reportBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
        reportBtn.setBounds(550, 280, 250, 120);
        contentPane.add(reportBtn);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == accessoryBtn) {
            dispose();
            new accessoryForm(staffData).setVisible(true);
        } else if (e.getSource() == listBtn) {
            dispose();
            new BorrowForm().setVisible(true);
        } else if (e.getSource() == staffBtn) {
            dispose();
            new staffForm().setVisible(true);
        } else if (e.getSource() == logout) {
            System.out.println("click");
            int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
            // JOptionPane.setRootFrame(null);
            if (a == JOptionPane.YES_OPTION) {
                dispose();
                loginFrom login = new loginFrom();
                login.setVisible(true);
            }

        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new mainFrom();
                    // frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
