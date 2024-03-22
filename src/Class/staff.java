package Class;

import java.sql.*;

public class staff {
    private database db = new database();
    private int s_id;
    private String s_user;
    private String s_pw;
    private String s_fname;
    private String s_lname;
    private String s_tel;
    private String role;

    public staff() {
    }

    public void setSId(int id) {
        s_id = id;
    }
    public void setSUser(String user) {
        s_user = user;
    }
    public void setSPw(String pw) {
        s_pw = pw;
    }
    public void setSFname(String fname) {
        s_fname = fname;
    }
    public void setSLname(String lname) {
        s_lname = lname;
    }
    public void setSTel(String tel) {
        s_tel = tel;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public int getSId() {
        return s_id;
    }
    public String getSUser() {
        return s_user;
    }
    public String getSPw() {
        return s_pw;
    }
    public String getSFname() {
        return s_fname;
    }
    public String getSLname() {
        return s_lname;
    }
    public String getSTel() {
        return s_tel;
    }
    public String getRole() {
        return role;
    }

    public void AddStaff() {
        Connection conn = db.getConnection();
        String sql = "INSERT INTO staff (s_id, s_user, s_pw, s_fname, s_lname, s_tel, role) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s_user);
            ps.setString(2, s_pw);
            ps.setString(3, s_fname);
            ps.setString(4, s_lname);
            ps.setString(5, s_tel);
            ps.setString(6, role);
            // query
            ps.executeUpdate();
            // close connection
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateStaff() {
        Connection conn = db.getConnection();
        String sql = "UPDATE staff SET s_user =?, s_pw =?, s_fname =?, s_lname =?, s_tel =?, role =? WHERE s_id =?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s_user);
            ps.setString(2, s_pw);
            ps.setString(3, s_fname);
            ps.setString(4, s_lname);
            ps.setString(5, s_tel);
            ps.setString(6, role);
            ps.setInt(7, s_id);
            // query
            ps.executeUpdate();
            // close connection
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteStaff(int id) {
        Connection conn = db.getConnection();
        String sql = "DELETE FROM staff WHERE s_id =?";
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
        return "Staff Id = " + s_id + "; Username = " + s_user + "; Password = " + s_pw + "; First Name = " + s_fname + "; Last Name = " + s_lname + "; Phone Number = " + s_tel + "; Role = " + role;
    }
}
