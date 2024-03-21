package Form;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Class.accessory;
import Class.database;
import Class.staff;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.util.*;

public class accessoryForm extends JFrame implements ActionListener, MouseListener {

    database db = new database();
    private JPanel contentPane;
    JButton refButton, btnAddData, btnSearch, homeBtn;
    JTextField SearchField;
    JTable listTable = new JTable();
    staff staffData;
    accessory accessoryData[];

    public accessoryForm(staff StaffData) {
        staffData = StaffData;

        getData("select * from accessory");

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
        lblNewLabel.setBounds(730, 10, 150, 30);
        contentPane.add(lblNewLabel);

        homeBtn = new JButton("Home");
        homeBtn.addActionListener(this);
        homeBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        homeBtn.setBounds(20, 10, 100, 30);
        contentPane.add(homeBtn);

        JLabel TitleLabel = new JLabel("จัดการข้อมูลอุปกรณ์");
        TitleLabel.setForeground(Color.BLACK);
        TitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        TitleLabel.setBounds(140, 10, 150, 30);
        contentPane.add(TitleLabel);

        // Button Add Data
        btnAddData = new JButton("Add Data");
        btnAddData.addActionListener(this);
        btnAddData.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnAddData.setBounds(375, 75, 100, 30);
        contentPane.add(btnAddData);

        SearchField = new JTextField();
        SearchField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        SearchField.setBounds(500, 75, 300, 30);
        contentPane.add(SearchField);

        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSearch.setBounds(825, 75, 100, 30);
        contentPane.add(btnSearch);

        refButton = new JButton("Refresh");
        refButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        refButton.setBounds(870, 10, 100, 30);
        refButton.addActionListener(this);
        contentPane.add(refButton);

        // setup table
        listTable.getColumn("Edit").setCellRenderer(new ButtonRenderer());
        listTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        listTable.setIntercellSpacing(new Dimension(10, 10));
        listTable.setRowHeight(40);

        // hide column a_id
        listTable.getColumn("a_id").setMaxWidth(0);
        listTable.getColumn("a_id").setMinWidth(0);
        listTable.getColumn("a_id").setPreferredWidth(0);
        listTable.addMouseListener(this);

        JScrollPane scrollPane = new JScrollPane(listTable);
        scrollPane.setBounds(375, 125, 555, 300);
        contentPane.add(scrollPane);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.add(panel);
    }

    // Get Data DB to Table
    public void getData(String sql) {
        Connection conn = db.getConnection();
        int accessoryCount = 1; // ตัวแปรที่เก็บจำนวน pd_id
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // สร้างตาราง
            ResultSetMetaData rsmd = rs.getMetaData(); // หัวตาราง
            // เก็บข้อมูลที่โยนให้ตาราง
            DefaultTableModel dtm = new DefaultTableModel();
            // เริ่มเรียงคอลัมน์
            String[] colName = new String[rsmd.getColumnCount() + 3];
            // วนรอบเพื่อสร้าง column และ add column loop for จะได้หัวตาราง
            int index = 0;
            for (; index < rsmd.getColumnCount() + 1; index++) {
                // ส่งค่า index เข้ามาด้วย
                if (index == 0) {
                    colName[index] = "No";
                } else {
                    colName[index] = rsmd.getColumnName(index);
                }
            }
            colName[index] = "Edit";
            colName[index + 1] = "Delete";
            for (int i = 0; i < colName.length; i++) {
                System.out.println(colName[i]);
            }
            dtm.setColumnIdentifiers(colName);
            // เริ่มเรียงข้อมูล
            while (rs.next()) {
                Object[] rowData = new Object[rsmd.getColumnCount() + 3];
                int i = 0;
                for (; i < rsmd.getColumnCount() + 1; i++) {
                    // index จะเริ่มต้นที่ 1
                    if (i == 0) {
                        rowData[i] = accessoryCount;
                    } else {
                        rowData[i] = rs.getObject(i);
                    }
                }
                rowData[index] = "Edit";
                rowData[index + 1] = "Delete";
                dtm.addRow(rowData);
                // นับจำนวนสินค้าคงคลัง
                accessoryCount++;
            }
            // แสดงข้อมูลในตาราง
            listTable.setModel(dtm);
            // แสดงจำนวนข้อมูลรายการที่พบ

        } catch (SQLException ex) {
            // Logger.getLogger(lab.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex, "error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == homeBtn) {
            dispose();
            new mainFrom(staffData).setVisible(true);
        } else if (event.getSource() == btnAddData) {
            new accessoryAdd().setVisible(true);
        } else if (event.getSource() == refButton) {
            dispose();
            new accessoryForm(staffData).setVisible(true);
        }
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setFont(new Font("Tahoma", Font.PLAIN, 16));
            setSize(20, 10);
            Border padding = BorderFactory.createEmptyBorder(0, 10, 0, 10);
            setBorder(BorderFactory.createCompoundBorder(getBorder(), padding));
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());

                System.out.println("row: " + row + " value: " + table.getValueAt(row, 1));
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int column = listTable.getColumnModel().getColumnIndexAtX(e.getX());
        int row = e.getY() / listTable.getRowHeight();

        if (row < listTable.getRowCount() && row >= 0 && column < listTable.getColumnCount() && column >= 0) {
            Object value = listTable.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
            } else {
                if (column == 3) {
                    // open form
                    new accessoryAdd().setVisible(true);
                    System.out.println("Edit button clicked for row: " + row);
                } else if (column == 4) {
                    // Delete action
                    // Implement your delete logic here
                    System.out.println("Delete button clicked for row: " + row);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
