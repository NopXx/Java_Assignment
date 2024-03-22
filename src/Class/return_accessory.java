package Class;

import java.sql.*;

public class return_accessory {
    private int list_id;
    private int return_number;
    private String return_date;
    private int return_sId;
    private double fine;
    private String accessoryStatus;
    private database db = new database();

    public return_accessory() {
    }

    public void setListId(int listId) {
        this.list_id = listId;
    }

    public int getListId() {
        return list_id;
    }
    public void setReturn_number(int return_number) {
        this.return_number = return_number;
    }

    public int getReturn_number() {
        return return_number;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public double getFine() {
        return fine;
    }

    public void setAccessoryStatus(String accessoryStatus) {
        this.accessoryStatus = accessoryStatus;
    }

    public String getAccessoryStatus() {
        return accessoryStatus;
    }

    public int getReturn_sId() {
        return return_sId;
    }

    public void setReturn_sId(int return_sId) {
        this.return_sId = return_sId;
    }

    public void UpdateReturn() {
        Connection conn = db.getConnection();
        String sql = "UPDATE list SET return_date = ?, return_number = ?, return_s_id = ?, accessory_status = ?, fine = ? WHERE list_id = ?;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, return_date);
            ps.setInt(2, return_number);
            ps.setInt(3, return_sId);
            ps.setString(4, accessoryStatus);
            ps.setDouble(5, fine);
            ps.setInt(6, list_id);
            // query
            ps.executeUpdate();
            // close connection
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "ReturnNumber : " + return_number + ", ReturnDate : " + return_date + ", RuturnSId : " + return_sId
                + ", Fine : " + fine + ", AccessoryStatus : " + accessoryStatus;
    }
}
