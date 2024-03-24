package Form;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Class.database;
import Class.return_accessory;
import Class.staff;

import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.util.*;
import java.util.Date;
import java.util.jar.Attributes.Name;

public class ReturnPopup extends JFrame implements ActionListener {

    database db = new database();
    private JPanel contentPane;
    JButton logout, btnSave, btnCancel;
    JLabel AddLabel, UserLabel, AccessoryLabel, CountLabel, CountDateLabel, BrDateLabel, RtDateLabel, StatusLabel,
            FineLabel;
    JTextField UserField, AccessoryField, CountField, CountDateField, BrDateField, RtDateField, StatusField, FineField;
    JTable listTable;
    String dateStr;
    int list_id;
    private staff staffData;

    public ReturnPopup(int id, staff staff) {
        list_id = id;
        staffData = staff;
        setTitle("Return Accessory");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        contentPane.setLayout(null);

        // Button Add Data
        AddLabel = new JLabel("Return Accessory");
        AddLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        AddLabel.setBounds(125, 50, 150, 30);
        contentPane.add(AddLabel);

        // Name Label Field
        UserLabel = new JLabel("Username : ");
        UserLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        UserLabel.setBounds(125, 100, 100, 30);
        contentPane.add(UserLabel);
        UserField = new JTextField();
        UserField.setEditable(false);
        UserField.setBounds(125, 125, 300, 30);
        contentPane.add(UserField);

        // Accessory Label Field
        AccessoryLabel = new JLabel("Accessory Name : ");
        AccessoryLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        AccessoryLabel.setBounds(125, 160, 150, 30);
        contentPane.add(AccessoryLabel);
        AccessoryField = new JTextField();
        AccessoryField.setEditable(false);
        AccessoryField.setBounds(125, 185, 300, 30);
        contentPane.add(AccessoryField);

        // Count label Field
        CountLabel = new JLabel("Count: ");
        CountLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        CountLabel.setBounds(125, 220, 150, 30);
        contentPane.add(CountLabel);

        CountField = new JTextField();
        CountField.setEditable(false);
        CountField.setBounds(125, 245, 300, 30);
        contentPane.add(CountField);

        // Date Label Field
        BrDateLabel = new JLabel("Borrow Date : ");
        BrDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        BrDateLabel.setBounds(125, 285, 150, 30);
        contentPane.add(BrDateLabel);
        BrDateField = new JTextField();
        BrDateField.setEditable(false);
        BrDateField.setBounds(125, 310, 300, 30);
        contentPane.add(BrDateField);

        RtDateLabel = new JLabel("Return Date : ");
        RtDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        RtDateLabel.setBounds(125, 345, 150, 30);
        contentPane.add(RtDateLabel);
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setFont(new Font("Tahoma", Font.PLAIN, 14));
        datePicker.setBounds(125, 370, 300, 30);
        contentPane.add(datePicker);

        CountDateLabel = new JLabel("Return Count : ");
        CountDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        CountDateLabel.setBounds(125, 400, 150, 30);
        contentPane.add(CountDateLabel);
        CountDateField = new JTextField();
        CountDateField.setBounds(125, 430, 300, 30);
        contentPane.add(CountDateField);

        // Accessory Fine Label Field
        StatusLabel = new JLabel("Accessory Status : ");
        StatusLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        StatusLabel.setBounds(125, 460, 150, 30);
        contentPane.add(StatusLabel);
        StatusField = new JTextField();
        StatusField.setBounds(125, 490, 300, 30);
        contentPane.add(StatusField);

        FineLabel = new JLabel("Fine : ");
        FineLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        FineLabel.setBounds(125, 520, 150, 30);
        contentPane.add(FineLabel);
        FineField = new JTextField();
        FineField.setBounds(125, 550, 300, 30);
        contentPane.add(FineField);

        // Save Cancel Button
        btnSave = new JButton("SAVE");
        btnSave.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSave.setBounds(125, 600, 100, 50);
        btnSave.addActionListener(this);
        contentPane.add(btnSave);

        btnCancel = new JButton("CANCEL");
        btnCancel.setFont(new Font("Tohama", Font.PLAIN, 16));
        btnCancel.setBounds(325, 600, 100, 50);
        btnCancel.addActionListener(this);
        contentPane.add(btnCancel);

        // set windows
        setContentPane(contentPane);
        getData(list_id);
        pack();
        setBounds(450, 190, 550, 850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == btnSave) {
            if (CountDateField.getText().isEmpty() || StatusField.getText().isEmpty() || FineField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields");
            } else {
                return_accessory re_acc = new return_accessory();
                re_acc.setListId(list_id);
                re_acc.setReturn_number(Integer.parseInt(CountDateField.getText()));
                re_acc.setReturn_date(dateStr);
                re_acc.setAccessoryStatus(StatusField.getText());
                re_acc.setFine(Double.parseDouble(FineField.getText()));
                re_acc.setReturn_sId(staffData.getSId());
                re_acc.UpdateReturn();
                JOptionPane.showMessageDialog(null, "Update Return Accessory Success");
                setVisible(false);
                dispose();
            }
        } else if (event.getSource() == btnCancel) {
            setVisible(false); // you can't see me!
            dispose(); // Destroy the JFrame object
        }
    }

    public void getData(int id) {
        Connection conn = db.getConnection();
        String sql = "SELECT `list`.`list_id`, `list`.`username`, `list`.`a_id`, `accessory`.`a_name`, `list`.`lend_number`, `list`.`borrow_date`, `list`.`return_date`\r\n"
                + //
                "FROM `list` \r\n" + //
                "\tLEFT JOIN `accessory` ON `list`.`a_id` = `accessory`.`a_id` WHERE `list`.`list_id` = " + id;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                UserField.setText(rs.getString("username"));
                AccessoryField.setText(rs.getString("a_name"));
                CountField.setText(rs.getString("lend_number"));
                BrDateField.setText(rs.getString("borrow_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    // remove main
    // public static void main(String[] args) {
    //     EventQueue.invokeLater(new Runnable() {
    //         public void run() {
    //             try {
    //                 ReturnPopup frame = new ReturnPopup();
    //                 frame.setVisible(true);
    //             } catch (Exception e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //     });
    // }
}