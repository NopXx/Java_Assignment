package Class;

import java.sql.*;

public class accessory {
    private int accessoryId;
    private String accessoryName;
    private int accessoryCount;
    private database db = new database();

    public accessory() {
    }

    public accessory(int Id, String Name, int count) {
        accessoryId = Id;
        accessoryName = Name;
        accessoryCount = count;
    }

    public void setAccessoryId(int Id) {
        accessoryId = Id;
    }

    public void setAccessoryName(String Name) {
        accessoryName = Name;
    }

    public void setAccessoryCount(int Count) {
        accessoryCount = Count;
    }

    public int getAccessoryId() {
        return accessoryId;
    }

    public String getAccessoryName() {
        return accessoryName;
    }

    public int getAccessoryCount() {
        return accessoryCount;
    }

    public void addAccessory() {
        Connection conn = db.getConnection();
        String sql = "INSERT INTO accessory (a_name, a_count) VALUES (?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, getAccessoryName());
            ps.setInt(2, getAccessoryCount());
            // query
            ps.executeUpdate();
            // close connection
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editAccessory() {
        Connection conn = db.getConnection();
        String sql = "UPDATE accessory SET a_name =?, a_count =? WHERE a_id =?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, getAccessoryName());
            ps.setInt(2, getAccessoryCount());
            ps.setInt(3, getAccessoryId());
            // query
            ps.executeUpdate();
            // close connection
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccessory(int id) {
        Connection conn = db.getConnection();
        String sql = "DELETE FROM accessory WHERE a_id =?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            // query
            ps.executeUpdate();
            // close connection
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "Accessory Id = " + accessoryId + "; Name = " + accessoryName + "; Count = " + accessoryCount;
    }
}
