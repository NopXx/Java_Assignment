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

        getData(null);

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
        btnSearch.addActionListener(this);
        contentPane.add(btnSearch);

        refButton = new JButton("Refresh");
        refButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        refButton.setBounds(870, 10, 100, 30);
        refButton.addActionListener(this);
        contentPane.add(refButton);

        listTable.addMouseListener(this);

        JScrollPane scrollPane = new JScrollPane(listTable);
        scrollPane.setBounds(375, 125, 555, 300);
        contentPane.add(scrollPane);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.add(panel);
    }

    // Get Data DB to Table
    public void getData(String keyword) {

        Connection conn = db.getConnection();
        int accessoryCount = 1; // ตัวแปรที่เก็บจำนวนข้อมูล accessory
        String sql;
        System.out.println("keywords: " + keyword);
        if (keyword == null) {
            sql = "SELECT * FROM accessory";
        } else {
            sql = "SELECT * FROM accessory WHERE a_name LIKE '%" + keyword + "%'";
        }
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // สร้างตาราง
            ResultSetMetaData rsmd = rs.getMetaData(); // หัวตาราง
            // เก็บข้อมูลที่โยนให้ตาราง
            DefaultTableModel dtm = new DefaultTableModel();
            // เริ่มเรียงคอลัมน์
            String[] colName = new String[rsmd.getColumnCount() + 3];
            // + 3 = column add to column from table
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
                // + 3 = column add to column from table
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
            // เพิ่มข้อมูลในตาราง
            listTable.setModel(dtm);
            // setup table
            listTable.getColumn("Edit").setCellRenderer(new ButtonRenderer());
            listTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());
            listTable.setIntercellSpacing(new Dimension(10, 10));
            listTable.setRowHeight(40);
            // hide column a_id
            listTable.getColumn("a_id").setMaxWidth(0);
            listTable.getColumn("a_id").setMinWidth(0);
            listTable.getColumn("a_id").setPreferredWidth(0);

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
            getData(null);
        } else if (event.getSource() == btnSearch) {
            if (SearchField.getText().isEmpty()) {
                getData(null);
            } else {
                getData(SearchField.getText());
            }
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
                if (column == 4) {
                    // column edit
                    // open form
                    String a_id = String.valueOf(listTable.getValueAt(row, 1));
                    new accessoryEdit(Integer.parseInt(a_id)).setVisible(true);

                } else if (column == 5) {
                    // column delete
                    String accessoryName = String.valueOf(listTable.getValueAt(row, 2));
                    String a_id = String.valueOf(listTable.getValueAt(row, 1));
                    int a = JOptionPane.showConfirmDialog(null,
                            "Delete Accessory\n" + accessoryName + " ?");
                    // JOptionPane.setRootFrame(null);
                    if (a == JOptionPane.YES_OPTION) {
                        accessory acc = new accessory();
                        acc.deleteAccessory(Integer.parseInt(a_id));
                        JOptionPane.showMessageDialog(null, "Delete Accessory success");
                        getData(null);
                    }
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
