package Class;

import java.sql.*;

public class lend_accessory {
    private int list_id;
    private String username;
    private int accessoryId;
    private String accessoryName;
    private int lend_number;
    private String lend_date;
    private String borrow_date;
    private int lend_sId;

    private database db = new database();

    public lend_accessory() {
    }

    public void setListId(int list_id) {
        this.list_id = list_id;
    }

    public void setUserName(String name) {
        username = name;
    }

    public void setAccessoryName(String name) {
        accessoryName = name;
    }

    public void setAccessoryId(int id) {
        accessoryId = id;
    }

    public void setLendNumber(int number) {
        lend_number = number;
    }

    public void setLendDate(String date) {
        lend_date = date;
    }

    public void setBorrow_date(String borrow_date) {
        this.borrow_date = borrow_date;
    }

    public void setLendSId(int sId) {
        lend_sId = sId;
    }

    public int getListId() {
        return list_id;
    }

    public String getUserName() {
        return username;
    }

    public String getAccessoryName() {
        return accessoryName;
    }

    public int getAccessoryId() {
        return accessoryId;
    }

    public int getLendNumber() {
        return lend_number;
    }

    public String getLendDate() {
        return lend_date;
    }

    public int getLendSId() {
        return lend_sId;
    }

    public String getBorrowDate() {
        return borrow_date;
    }

    public void addLendAccessory() {
        Connection conn = db.getConnection();
        String sql = "INSERT INTO `list` (`username`, `a_id`, `lend_number`, `lend_date`, `borrow_date`, `lend_s_id`) VALUES (?,?,?, now() ,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, getUserName());
            ps.setInt(2, getAccessoryId());
            ps.setInt(3, getLendNumber());
            ps.setString(4, getBorrowDate());
            ps.setInt(5, getLendSId());
            // query
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateLendAccessory() {
        Connection conn = db.getConnection();
        String sql = "UPDATE `list` SET `username` = ?, `a_id` = ?, `lend_number` =?, `borrow_date` =?, `lend_s_id` = ? WHERE `list_id` = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, getUserName());
            ps.setInt(2, getAccessoryId());
            ps.setInt(3, getLendNumber());
            ps.setString(4, getBorrowDate());
            ps.setInt(5, getLendSId());
            ps.setInt(6, getListId());
            // query
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLendAccessory(int id) {
        Connection conn = db.getConnection();
        String sql = "DELETE FROM `list` WHERE `list_id` =?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            // query
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "Username: " + username + ", " + accessoryName + ", " + accessoryId + ", " + lend_number + ", "
                + lend_date + ", " + borrow_date + ", " + lend_sId;
    }
}
