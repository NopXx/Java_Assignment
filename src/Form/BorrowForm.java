package Form;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Class.accessory;
import Class.database;
import Class.lend_accessory;
import Class.staff;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.util.*;

public class BorrowForm extends JFrame implements ActionListener, MouseListener {

    database db = new database();
    private JPanel contentPane;
    JButton refButton, btnReturn, btnBorrow, btnSearch, homeBtn;
    JTextField SearchField;
    JTable listTable = new JTable();
    staff staffData;
    int list_id, list_select_id;

    public BorrowForm(staff staff) {

        setTitle("Accessory Management System");
        setBounds(450, 190, 1014, 597);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        staffData = staff;

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        homeBtn = new JButton("Home");
        homeBtn.addActionListener(this);
        homeBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        homeBtn.setBounds(20, 10, 100, 30);
        contentPane.add(homeBtn);

        JLabel lblNewLabel = new JLabel(staffData.getRole() + " " + staffData.getSFname());
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(730, 10, 150, 30);
        contentPane.add(lblNewLabel);

        refButton = new JButton("Refresh");
        refButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        refButton.setBounds(870, 10, 100, 30);
        refButton.addActionListener(this);
        contentPane.add(refButton);

        // Button Add Data
        btnBorrow = new JButton("Borrow");
        btnBorrow.addActionListener(this);
        btnBorrow.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnBorrow.setBounds(50, 75, 100, 30);
        contentPane.add(btnBorrow);

        btnReturn = new JButton("Return");
        btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnReturn.setBounds(175, 75, 100, 30);
        btnReturn.setEnabled(false);
        btnReturn.addActionListener(this);
        contentPane.add(btnReturn);

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

        getData(null);

    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnBorrow) {
            new BorrowPopup(staffData).setVisible(true);
        } else if (event.getSource() == btnReturn) {
            new ReturnPopup(list_select_id, staffData).setVisible(true);
        } else if (event.getSource() == refButton) {
            getData(null);
        } else if (event.getSource() == btnSearch) {
            if (SearchField.getText().isEmpty()) {
                getData(null);
            } else {
                getData(SearchField.getText());
            }
        } else if (event.getSource() == homeBtn) {
            dispose();
            new mainFrom(staffData).setVisible(true);
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
                if (listTable.getValueAt(row, 7) == null) {
                    btnReturn.setEnabled(true);
                    System.out.println("");
                    list_select_id = Integer.parseInt(String.valueOf(listTable.getValueAt(row, 1)));
                } else {
                    btnReturn.setEnabled(false);
                }
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Get Data DB to Table
    public void getData(String keyword) {
        btnReturn.setEnabled(false);
        Connection conn = db.getConnection();
        int accessoryCount = 1; // ตัวแปรที่เก็บจำนวนข้อมูล accessory
        String sql;
        System.out.println("keywords: " + keyword);
        if (keyword == null) {
            sql = "SELECT `list`.`list_id`, `list`.`username`, `list`.`a_id`, `accessory`.`a_name`, `list`.`lend_number`, `list`.`borrow_date`, `list`.`return_date`\r\n"
                    + //
                    "FROM `list` \r\n" + //
                    "\tLEFT JOIN `accessory` ON `list`.`a_id` = `accessory`.`a_id`;";
        } else {
            sql = "SELECT `list`.`list_id`, `list`.`username`, `list`.`a_id`, `accessory`.`a_name`, `list`.`lend_number`, `list`.`borrow_date`, `list`.`return_date`\r\n"
                    + //
                    "FROM `list` \r\n" + //
                    "\tLEFT JOIN `accessory` ON `list`.`a_id` = `accessory`.`a_id` WHERE `list`.`username` LIKE '%"
                    + keyword + "%' or `accessory`.`a_name` LIKE '%" + keyword + "%';";
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
            colName[4] = "Accessory Name";
            colName[5] = "Count";
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
            // hide column list_id
            listTable.getColumn("list_id").setMaxWidth(0);
            listTable.getColumn("list_id").setMinWidth(0);
            listTable.getColumn("list_id").setPreferredWidth(0);
            // hide column a_id
            listTable.getColumn("a_id").setMaxWidth(0);
            listTable.getColumn("a_id").setMinWidth(0);
            listTable.getColumn("a_id").setPreferredWidth(0);

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
                    if (listTable.getValueAt(row, 7) == null) {
                    String list_id = String.valueOf(listTable.getValueAt(row, 1));
                    new BorrowEdit(Integer.parseInt(list_id), staffData).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "can't edit the selected");
                    }

                    System.out.println("Edit button clicked for row: " + row);
                } else if (column == 9) {
                    // Delete action
                    // Implement your delete logic here
                    System.out.println(listTable.getValueAt(row, 7) == null);
                    if (listTable.getValueAt(row, 7) == null) {
                        String list_id = String.valueOf(listTable.getValueAt(row, 1));
                        lend_accessory lend = new lend_accessory();
                        String accessoryName = String.valueOf(listTable.getValueAt(row, 4));
                        String username = String.valueOf(listTable.getValueAt(row, 2));
                        int a = JOptionPane.showConfirmDialog(null,
                                "Delete Lend\n" + username + " : " + accessoryName + " ?");
                        if (a == JOptionPane.YES_OPTION) {
                            lend.deleteLendAccessory(Integer.parseInt(list_id));
                            JOptionPane.showMessageDialog(null, "Delete Lend success");
                            getData(null);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "can't delete the selected");
                    }
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