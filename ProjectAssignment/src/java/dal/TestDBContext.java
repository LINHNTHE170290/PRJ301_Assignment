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
import model.Courses;
import model.Tests;

/**
 *
 * @author admin
 */
public class TestDBContext extends DBContext{
    public ArrayList<Tests> getAll() {
        ArrayList<Tests> tests = new ArrayList<>();
        
        try {
            
            if (connection != null) {  // Kiểm tra xem kết nối cơ sở dữ liệu đã được thiết lập hay chưa
                String sql = "SELECT tid, tname, type,time, knowledge, condition FROM Tests";
                PreparedStatement stm = connection.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Tests test = new Tests();
                    test.setTid(rs.getInt("tid"));
                    test.setTname(rs.getString("tname"));
                    test.setType(rs.getString("type"));
                    test.setTime(rs.getString("time"));
                    test.setKnowledge(rs.getString("knowledge"));
                    test.setCondition(rs.getString("condition"));
                    tests.add(test);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tests;
    }
    public ArrayList<Tests> getTestByCcode(String courseCode) {
        ArrayList<Tests> tests = new ArrayList<>();
        String sql = "SELECT t.tid, t.tname, t.type, t.time, t.method, t.knowledge, t.condition " +
                     "FROM Tests t " +
                     "INNER JOIN Courses c ON t.cid = c.cid " +
                     "WHERE c.ccode = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, courseCode);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Tests test = new Tests();
                test.setTid(rs.getInt("tid"));
                test.setTname(rs.getString("tname"));
                test.setType(rs.getString("type"));
                test.setTime(rs.getString("time"));
                test.setKnowledge(rs.getString("knowledge"));
                test.setCondition(rs.getString("condition"));

                // Lấy thông tin khóa học và gán cho bài kiểm tra
                Courses course = new Courses();
                course.setCcode(courseCode);
                test.setCourse(course);

                tests.add(test);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tests;
    }
    public void insert(Tests model) {
        try {
            connection.setAutoCommit(false);
            String sql_insert = "INSERT INTO Tests (tid, tname, type, time, knowledge, condition)\n" 
            + "VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement stm_insert = connection.prepareStatement(sql_insert);
            stm_insert.setString(1, model.getTname());
            stm_insert.setString(2, model.getType());
            stm_insert.setString(3, model.getTime());
            stm_insert.setString(4, model.getKnowledge());
            stm_insert.setString(4, model.getCondition());
             int affectedRows = stm_insert.executeUpdate();            
             if (affectedRows == 1) {
                ResultSet generatedKeys = stm_insert.getGeneratedKeys();
                if (generatedKeys.next()) {
                    model.setTid(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating test failed, no ID obtained.");
                }
                generatedKeys.close();
            } else {
                throw new SQLException("Creating test failed, no rows affected.");
            }
            
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(TestDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(TestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TestDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TestDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void update(Tests model){
        try {
            String sql_insert = "UPDATE Tests\n"
                    + "   SET tid = ?\n"
                    + "      ,tname = ?\n"
                    + "      ,type = ?\n"
                    + "      ,time = ?\n"
                    + "      ,knowledge = ?\n"
                    + "      ,condition = ?\n"
                    + " WHERE tid = ?";
            PreparedStatement stm_insert = connection.prepareStatement(sql_insert);
            stm_insert.setInt(1, model.getTid());
            stm_insert.setString(2, model.getTname());
            stm_insert.setString(3, model.getType());
            stm_insert.setString(4, model.getTime());
            stm_insert.setString(5, model.getKnowledge());
            stm_insert.setString(6, model.getCondition());
            stm_insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remove(Tests model) {
        try {
            String sql_insert = "DELETE Tests\n"
                    + " WHERE tid = ?";
            PreparedStatement stm_insert = connection.prepareStatement(sql_insert);
            stm_insert.setInt(1, model.getTid());
            stm_insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        TestDBContext dbConnect = new TestDBContext();
        ArrayList<Tests> testlist = dbConnect.getAll();

        // Print the list of tests
        for (Tests test : testlist) {
            System.out.println("Test ID: " + test.getTid());
            System.out.println("Test Name: " + test.getTname());
            System.out.println("Test Type: " + test.getType());
            System.out.println("Test Time: " + test.getTime());
            System.out.println("Test Knowledge: " + test.getKnowledge());
            System.out.println("Test Condition: " + test.getCondition());
        }   
    }
}
