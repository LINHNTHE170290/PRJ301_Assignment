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
import model.Marks;
import model.Points;
import model.Tests;

/**
 *
 * @author admin
 */
public class AverageScoreDBContext extends DBContext{
    public ArrayList<Marks> getAverageByStuidAndCid() {
        ArrayList<Marks> marks = new ArrayList<>();
        try {
            if (connection != null) {  // Kiểm tra xem kết nối cơ sở dữ liệu đã được thiết lập hay chưa
                String sql = "SELECT total\n" 
                     + "FROM Marks\n"
                     + "WHERE stuid = (SELECT stuid FROM Students WHERE stucode = ?)\n" 
                     + "AND cid = (SELECT cid FROM Courses WHERE ccode = ?);";
                PreparedStatement st = connection.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Marks m = new Marks();
                    m.setTotal(rs.getInt("total"));
                    marks.add(m);
                }
                rs.close();
            } else {
                System.out.println("Database connection is not established.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AverageScoreDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return marks;
    }
}
