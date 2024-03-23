package Form;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Class.database;
import Class.staff;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class reportAdminForm extends JFrame implements ActionListener {
    database db = new database();
    private JPanel contentPane;
    JButton logoutButton, btnAllData, homeBtn, btnReturnData;
    JTextField SearchField;
    JTable listTable = new JTable();
    staff staffData;

    public reportAdminForm(staff staff) {
        staffData = staff;
        setTitle("Accessory Management System");
        setBounds(450, 190, 1014, 597);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel(staffData.getRole() + " " +
                staffData.getSFname());
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(730, 10, 150, 30);
        contentPane.add(lblNewLabel);

        logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        logoutButton.setBounds(870, 10, 100, 30);
        logoutButton.addActionListener(this);
        contentPane.add(logoutButton);

        JLabel label = new JLabel("รายงาน");
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label.setBounds(440, 10, 150, 30);
        contentPane.add(label);

        getData(null, 0);
        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(listTable);
        scrollPane.setBounds(50, 125, 900, 300);
        getContentPane().add(scrollPane);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == logoutButton) {
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

    public void getData(String keyword, int btn) {

        Connection conn = db.getConnection();
        int accessoryCount = 1; // ตัวแปรที่เก็บจำนวนข้อมูล accessory
        String sql;
        System.out.println("keywords: " + keyword);
        if (keyword == null) {
            sql = "SELECT `list`.`username`, `accessory`.`a_name`, `list`.`accessory_status`, `list`.`fine`\r\n"
                    + //
                    "FROM `list` \r\n" + //
                    "\tLEFT JOIN `accessory` ON `list`.`a_id` = `accessory`.`a_id` WHERE `list`.`return_date` IS NOT NULL";
        } else {
            sql = "SELECT `list`.`username`, `accessory`.`a_name`, `list`.`accessory_status`, `list`.`fine`\r\n"
                    + //
                    "FROM `list` \r\n" + //
                    "\tLEFT JOIN `accessory` ON `list`.`a_id` = `accessory`.`a_id` WHERE `list`.`return_date` IS NOT NULL";
        }

        switch (btn) {
            case 0:
                sql += "";
                break;
            case 1:
                sql += "WHERE `list`.`return_date` IS NOT NULL";
        }
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // สร้างตาราง
            ResultSetMetaData rsmd = rs.getMetaData(); // หัวตาราง
            // เก็บข้อมูลที่โยนให้ตาราง
            DefaultTableModel dtm = new DefaultTableModel();
            // เริ่มเรียงคอลัมน์
            String[] colName = new String[rsmd.getColumnCount() + 1];
            // + 1 = column add to column from table
            // วนรอบเพื่อสร้าง column และ add column loop for จะได้หัวตาราง
            colName[0] = "No";
            colName[1] = "UserName";
            colName[2] = "Accessory Name";
            colName[3] = "Accessory Status";
            colName[4] = "Fine";

            dtm.setColumnIdentifiers(colName);
            // เริ่มเรียงข้อมูล
            while (rs.next()) {
                Object[] rowData = new Object[rsmd.getColumnCount() + 1];
                // + 1 = column add to column from table
                int i = 0;
                for (; i < rsmd.getColumnCount() + 1; i++) {
                    // index จะเริ่มต้นที่ 1
                    if (i == 0) {
                        rowData[i] = accessoryCount;
                    } else {
                        rowData[i] = rs.getObject(i);
                    }
                }
                dtm.addRow(rowData);
                // นับจำนวนสินค้าคงคลัง
                accessoryCount++;
            }
            // เพิ่มข้อมูลในตาราง
            listTable.setModel(dtm);
            // setup table
            listTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
            listTable.setIntercellSpacing(new Dimension(10, 10));
            listTable.setRowHeight(40);

            // set width column no
            listTable.getColumn("No").setMaxWidth(40);

        } catch (SQLException ex) {
            // Logger.getLogger(lab.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex, "error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
