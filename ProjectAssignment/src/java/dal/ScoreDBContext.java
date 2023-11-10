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
import model.Points;
import model.Tests;

/**
 *
 * @author admin
 */
public class ScoreDBContext extends DBContext{
    public ArrayList<Tests> getScoreOfTestByCcodeAndStucode(String ccode, String stucode) {
        ArrayList<Tests> grades = new ArrayList<>();
        try {
            if (connection != null) {  // Kiểm tra xem kết nối cơ sở dữ liệu đã được thiết lập hay chưa
                String sql = "SELECT t.tname, p.score, p.percentage\n"
                        + "FROM Tests t\n"
                        + "JOIN Points p ON t.tid = p.tid\n"
                        + "JOIN Students s ON p.stuid = s.stuid\n"
                        + "JOIN Courses c ON p.cid = c.cid\n"
                        + "WHERE c.ccode = ? AND s.stucode = ?;";
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, ccode);
                st.setString(2, stucode);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Tests t = new Tests();
                    Points p = new Points();
                    t.setTname(rs.getString("tname"));
                    p.setScore(rs.getInt("score"));
                    p.setPercentage(rs.getFloat("percentage"));
                    grades.add(t);
                }
                rs.close();
            } else {
                System.out.println("Database connection is not established.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grades;
    }
}
