import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class accessoryForm extends JFrame implements ActionListener, MouseListener {

    database db = new database();
    private JPanel contentPane;
    JButton logout, btnAddData;
    JTable listTable;

    public accessoryForm() {

        String Data[][] = { { "1", "JPANG", "15", "Edit", "Delete" },
                { "2", "Homesweet", "20", "Edit", "Delete" },
                { "3", "Lay", "10", "Edit", "Delete" },
                { "4", "CocaCola", "20", "Edit", "Delete" } };

        String Header[] = { "No", "Name", "Count", "Edit", "Delete" };

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
        btnAddData = new JButton("Add Data");
        btnAddData.addActionListener(this);
        btnAddData.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnAddData.setBounds(50, 75, 100, 30);
        contentPane.add(btnAddData);

        DefaultTableModel model = new DefaultTableModel(Data, Header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        listTable = new JTable(model);
        listTable.getColumn("Edit").setCellRenderer(new ButtonRenderer());
        listTable.getColumn("Delete").setCellRenderer(new ButtonRenderer());
        listTable.setIntercellSpacing(new Dimension(10,10));
        listTable.setRowHeight(40);
        listTable.addMouseListener(this);

        JScrollPane scrollPane = new JScrollPane(listTable);
        scrollPane.setBounds(175, 75, 775, 300);
        contentPane.add(scrollPane);
    }

    public void actionPerformed(ActionEvent event) {
        // Handle button actions here
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
                    accessoryForm frame = new accessoryForm();
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
                if (column == 3) {
                    // Edit action
                    // Implement your edit logic here
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
