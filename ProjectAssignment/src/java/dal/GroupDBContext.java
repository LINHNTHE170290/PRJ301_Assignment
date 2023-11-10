/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Groups;
import model.Instructors;
import model.Students;

/**
 *
 * @author admin
 */
public class GroupDBContext extends DBContext{
    public ArrayList<Groups> getAll() {
        ArrayList<Groups> groups = new ArrayList<>();
        try {
            if (connection != null) {  // Kiểm tra xem kết nối cơ sở dữ liệu đã được thiết lập hay chưa
                String sql = "SELECT g.gname, s.stucode, s.stuname, s.birthday, s.gender, i.iname FROM Groups g\n" +
                    "INNER JOIN GroupStudent gs ON g.gid = gs.gid\n" +
                    "INNER JOIN Students  s ON gs.stuid = s.stuid\n" +
                    "INNER JOIN Instructors i ON g.iid = i.iid;";
                PreparedStatement st = connection.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Groups g = new Groups();
                    Students s = new Students();
                    Instructors i = new Instructors();
                    g.setGname(rs.getString("gname"));
                    s.setStucode(rs.getString("stucode"));
                    s.setStuname(rs.getString("stuname"));
                    s.setBirthday(rs.getDate("birthday"));
                    s.setGender(rs.getBoolean("gender"));
                    i.setIname(rs.getString("iname"));
                    groups.add(g);
                }
                rs.close();
            } else {
                System.out.println("Database connection is not established.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groups;
    }
    public ArrayList< Groups> searchByGroupId(String gid) {
        ArrayList<Groups> groups = new ArrayList<>();

        try {
            String sql = "SELECT g.gname, s.stucode, s.stuname, s.birthday, s.gender, i.iname FROM Groups g\n" +
                    "INNER JOIN GroupStudent gs ON g.gid = gs.gid\n" +
                    "INNER JOIN Students  s ON gs.stuid = s.stuid\n" +
                    "INNER JOIN Instructors i ON g.iid = i.iid;";
            
            if(gid.length() > 0)
                sql+= " WHERE gid = ?;";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            
             if(gid.length() > 0)
                 stm.setString(1, gid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Groups g = new Groups();
                Students s = new Students();
                Instructors i = new Instructors();
                g.setGname(rs.getString("gname"));
                s.setStucode(rs.getString("stucode"));
                s.setStuname(rs.getString("stuname"));
                s.setBirthday(rs.getDate("birthday"));
                s.setGender(rs.getBoolean("gender"));
                i.setIname(rs.getString("iname"));
                groups.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return groups;

    }
    
}
