package Form;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Class.accessory;
import Class.database;
import Class.lend_accessory;
import Class.staff;

import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.util.*;
import java.util.Date;

public class BorrowPopup extends JFrame implements ActionListener {

    database db = new database();
    private JPanel contentPane;
    JButton logout, btnSave, btnCancel;
    JLabel AddLabel, UserLabel, AccessoryLabel, CountLabel, DateLabel;
    JTextField UserField, AccessoryField, CountField, DateField;
    JTable listTable;
    String accessoryData[];
    JComboBox<String> accessoryCombo;
    accessory acc[];
    String dateStr = "";
    staff staffData;

    public BorrowPopup(staff staff) {
        staffData = staff;
        getData();
        setTitle("Add Lend");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        contentPane.setLayout(null);

        // Button Add Data
        AddLabel = new JLabel("Borrow");
        AddLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        AddLabel.setBounds(150, 50, 100, 30);
        contentPane.add(AddLabel);

        // Name Label Field
        UserLabel = new JLabel("Username : ");
        UserLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        UserLabel.setBounds(125, 100, 100, 30);
        contentPane.add(UserLabel);
        UserField = new JTextField();
        UserField.setBounds(125, 125, 300, 30);
        contentPane.add(UserField);

        // Accessory Label Field
        AccessoryLabel = new JLabel("Accessory Name : ");
        AccessoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        AccessoryLabel.setBounds(125, 160, 150, 30);
        contentPane.add(AccessoryLabel);

        accessoryCombo = new JComboBox<String>(accessoryData);
        accessoryCombo.setMaximumRowCount(5);
        accessoryCombo.setBounds(125, 185, 300, 30);
        contentPane.add(accessoryCombo);

        // Count label Field
        CountLabel = new JLabel("Count : ");
        CountLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        CountLabel.setBounds(125, 220, 100, 30);
        contentPane.add(CountLabel);
        CountField = new JTextField();
        CountField.setBounds(125, 245, 300, 30);
        contentPane.add(CountField);

        // Date Label Field
        DateLabel = new JLabel("Date : ");
        DateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        DateLabel.setBounds(125, 285, 100, 30);
        contentPane.add(DateLabel);

        // datepicker
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setFont(new Font("Tahoma", Font.PLAIN, 14));
        datePicker.setBounds(125, 310, 300, 30);
        contentPane.add(datePicker);

        // Save Cancel Button
        btnSave = new JButton("SAVE");
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSave.setBounds(125, 400, 100, 50);
        btnSave.addActionListener(this);
        contentPane.add(btnSave);

        btnCancel = new JButton("CANCEL");
        btnCancel.setFont(new Font("Tohama", Font.PLAIN, 16));
        btnCancel.setBounds(325, 400, 100, 50);
        btnCancel.addActionListener(this);
        contentPane.add(btnCancel);

        // set windows

        setContentPane(contentPane);
        pack();
        setBounds(450, 190, 550, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

    }

    public class DateLabelFormatter extends AbstractFormatter {

        private String datePattern = "dd-MM-yyyy";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                Date date = cal.getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = dateFormat.format(date);
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }

    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnSave) {
            if (UserField.getText().isEmpty() || CountField.getText().isEmpty() || dateStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill All Field");
            } else {
                int index = accessoryCombo.getSelectedIndex();

                int a_id = acc[index].getAccessoryId();
                int count = Integer.parseInt(CountField.getText());

                String date = dateStr;
                lend_accessory lend = new lend_accessory();
                lend.setUserName(UserField.getText());
                lend.setAccessoryId(a_id);
                lend.setLendNumber(count);
                lend.setBorrow_date(date);
                lend.setLendSId(staffData.getSId());
                lend.addLendAccessory();
                JOptionPane.showMessageDialog(null, "Lend Success");
                setVisible(false); // you can't see me!
                dispose(); // Destroy the JFrame object
            }
        } else if (event.getSource() == btnCancel) {
            setVisible(false); // you can't see me!
            dispose(); // Destroy the JFrame object
        }
    }

    public void getData() {
        Connection conn = db.getConnection();
        String sql = "Select * from accessory";
        int row_count = 0;
        String sql_count = "select count(*) AS recordCount from accessory";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql_count);
            
            if (rs.next()) {
                row_count = rs.getInt("recordCount");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            acc = new accessory[row_count];
            accessoryData = new String[row_count];
            int i = 0;
            while (rs.next()) {
                acc[i] = new accessory(Integer.parseInt(rs.getString("a_id")), rs.getString("a_name"),
                Integer.parseInt(rs.getString("a_count")));
                System.out.println(rs.getString("a_name"));
                accessoryData[i] = rs.getString("a_name");
                
                i++;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // remove main
    // public static void main(String[] args) {
    // EventQueue.invokeLater(new Runnable() {
    // public void run() {
    // try {
    // accessoryAdd frame = new accessoryAdd();
    // frame.setVisible(true);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }
    // });
    // }
}