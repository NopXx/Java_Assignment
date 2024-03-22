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

public class staffForm extends JFrame implements ActionListener, MouseListener {

    database db = new database();
    private JPanel contentPane;
    JButton refButton, btnAddData, btnSearch, homeBtn;
    JTextField SearchField;
    JTable listTable = new JTable();
    staff staffData;

    public staffForm(staff staff) {
        staffData = staff;
        
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

        refButton = new JButton("Refresh");
        refButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        refButton.setBounds(870, 10, 100, 30);
        refButton.addActionListener(this);
        contentPane.add(refButton);

        // Button Add Data
        btnAddData = new JButton("Add Data");
        btnAddData.addActionListener(this);
        btnAddData.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnAddData.setBounds(50, 75, 100, 30);
        contentPane.add(btnAddData);
        // Search Bar Button
        SearchField = new JTextField();
        SearchField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        SearchField.setBounds(500, 75, 325, 30);
        contentPane.add(SearchField);
        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSearch.setBounds(850, 75, 100, 30);
        btnSearch.addActionListener(this);
        contentPane.add(btnSearch);


        listTable.addMouseListener(this);
        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(listTable);
        scrollPane.setBounds(50, 125, 900, 300);
        getContentPane().add(scrollPane);

        
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == homeBtn) {
            dispose();
            new mainFrom(staffData).setVisible(true);
        } else if (event.getSource() == btnAddData) {
            new staffAdd().setVisible(true);
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
            setFont(new Font("Tahoma", Font.PLAIN, 14));
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
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    public void getData(String keyword) {

        Connection conn = db.getConnection();
        int accessoryCount = 1; // ตัวแปรที่เก็บจำนวนข้อมูล accessory
        String sql;
        System.out.println("keywords: " + keyword);
        if (keyword == null) {
            sql = "SELECT * FROM staff";
        } else {
            sql = "SELECT * FROM staff WHERE s_fname LIKE '%" + keyword + "%' or s_lname LIKE '%" + keyword + "%' or s_user LIKE '%" + keyword + "%';";
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
            listTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
            listTable.getColumn("Edit").setCellRenderer(new ButtonRenderer());
            listTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());
            listTable.setIntercellSpacing(new Dimension(10, 10));
            listTable.setRowHeight(40);
            // hide column a_id
            listTable.getColumn("s_id").setMaxWidth(0);
            listTable.getColumn("s_id").setMinWidth(0);
            listTable.getColumn("s_id").setPreferredWidth(0);
            // hide column a_id
            listTable.getColumn("s_pw").setMaxWidth(0);
            listTable.getColumn("s_pw").setMinWidth(0);
            listTable.getColumn("s_pw").setPreferredWidth(0);
            // set width column no
            listTable.getColumn("No").setMaxWidth(40);

        } catch (SQLException ex) {
            // Logger.getLogger(lab.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex, "error", JOptionPane.ERROR_MESSAGE);
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
                // Handle edit and delete actions here
                if (column == 8) {
                    // Edit action
                    // Implement your edit logic here
                    String s_id = String.valueOf(listTable.getValueAt(row, 1));
                    new staffEdit(Integer.parseInt(s_id)).setVisible(true);
                    System.out.println("Edit button clicked for row: " + row);
                } else if (column == 9) {
                    // Delete action
                    // Implement your delete logic here
                    String s_id = String.valueOf(listTable.getValueAt(row, 1));
                    if (Integer.parseInt(s_id) == staffData.getSId()) {
                        JOptionPane.showMessageDialog(this, "You can't delete your data", "error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        staff st = new staff();
                        String username = String.valueOf(listTable.getValueAt(row, 2));
                        int a = JOptionPane.showConfirmDialog(null,
                                "Delete Staff\n" + username + " ?");
                        if (a == JOptionPane.YES_OPTION) {
                            st.DeleteStaff(Integer.parseInt(s_id));
                            JOptionPane.showMessageDialog(null, "Delete Staff success");
                            getData(null);
                        }
                        
                        System.out.println("Delete button clicked for row: " + row);
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