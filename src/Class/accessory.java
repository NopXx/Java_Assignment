package Class;
import java.sql.*;

public class accessory {
    private int accessoryId;
    private String accessoryName;
    private int accessoryCount;
    private database db = new database();

    public accessory() {}

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
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "Accessory Id = " + accessoryId + "; Name = " + accessoryName + "; Count = " + accessoryCount;
    }
}
