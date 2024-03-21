package Form;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Class.database;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.util.*;

public class BorrowForm extends JFrame implements ActionListener, MouseListener {

    database db = new database();
    private JPanel contentPane;
    JButton logout, btnBorrow, btnReturn, btnSearch;
    JTextField SearchField;
    JTable listTable;

    public BorrowForm() {
        // staff staffData = StaffData;
        // System.out.println(staffData.toString());
        // if (staffData.getSUser() == "" || staffData.getSUser() == null) {
        // dispose();
        // loginFrom login = new loginFrom();
        // login.setVisible(true);
        // // JOptionPane.showMessageDialog(null, "Please Login");
        // }
        String Data[][] = { { "1", "Waka", "FootBall", "3", "13/02/2567", "15/02/2567", "Edit", "Delete" },
                { "2", "Sukjai", "BasketBall", "1", "13/02/2567", "14/02/2567", "Edit", "Delete" },
                { "3", "Lama", "BasketBall", "1", "15/02/2567", "16/02/2567", "Edit", "Delete" },
                { "4", "Hello", "Table Tennis", "2", "18/02/2567", "18/02/2567", "Edit", "Delete" }, };

        String Header[] = { "No", "Username", "Accessory Name", "Count", "Borrow Date", "Return Date", "Edit",
                "Delete" };

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
        btnBorrow = new JButton("Borrow");
        btnBorrow.addActionListener(this);
        btnBorrow.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnBorrow.setBounds(50, 75, 100, 30);
        contentPane.add(btnBorrow);
        btnReturn = new JButton("Return");
        btnReturn.addActionListener(this);
        btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnReturn.setBounds(175, 75, 100, 30);
        contentPane.add(btnReturn);
        // Search Bar Button
        SearchField = new JTextField();
        SearchField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        SearchField.setBounds(500, 75, 325, 30);
        contentPane.add(SearchField);
        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSearch.setBounds(850, 75, 100, 30);
        contentPane.add(btnSearch);

        DefaultTableModel model = new DefaultTableModel(Data, Header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        listTable = new JTable(model);
        listTable.getColumn("Edit").setCellRenderer(new ButtonRenderer());
        listTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        listTable.setIntercellSpacing(new Dimension(10, 10));
        listTable.getColumn("No").setMaxWidth(25);
        listTable.setRowHeight(40);
        listTable.addMouseListener(this);
        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(listTable);
        scrollPane.setBounds(50, 125, 900, 300);
        getContentPane().add(scrollPane);

    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnBorrow) {
            new BorrowPopup().setVisible(true);
        } else if (event.getSource() == btnReturn) {
            new ReturnPopup().setVisible(true);
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

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BorrowForm frame = new BorrowForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
                if (column == 6) {
                    // Edit action
                    // Implement your edit logic here
                    new BorrowPopup().setVisible(true);
                    System.out.println("Edit button clicked for row: " + row);
                } else if (column == 7) {
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