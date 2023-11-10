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

/**
 *
 * @author admin
 */
public class CourseDBContext extends DBContext{
    public ArrayList<Courses> getAll() {
	ArrayList<Courses> courses = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Courses";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Courses co = new Courses();
                co.setCid(rs.getInt("cid"));
                co.setCcode(rs.getString("ccode"));
                co.setCname(rs.getString("cname"));
                courses.add(co);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courses;
    }
    
    
    
    public void insert(Courses model) {
        try {
            connection.setAutoCommit(false);
            String sql_insert = "INSERT INTO Courses (cid, ccode, cname)\n"
                + "VALUES (?, ?, ?)";
            PreparedStatement stm_insert = connection.prepareStatement(sql_insert);
            stm_insert.setString(1, model.getCcode());
            stm_insert.setString(2, model.getCname());
             int affectedRows = stm_insert.executeUpdate();            
             if (affectedRows == 1) {
                ResultSet generatedKeys = stm_insert.getGeneratedKeys();
                if (generatedKeys.next()) {
                    model.setCid(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating student failed, no ID obtained.");
                }
                generatedKeys.close();
            } else {
                throw new SQLException("Creating student failed, no rows affected.");
            }
            
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void update(Courses model){
        try {
            String sql_insert = "UPDATE Courses\n"
                    + "   SET cid = ?\n"
                    + "      ,ccode = ?\n"
                    + "      ,cname = ?\n"
                    + " WHERE ccode = ?";
            PreparedStatement stm_insert = connection.prepareStatement(sql_insert);
            stm_insert.setInt(1, model.getCid());
            stm_insert.setString(2, model.getCcode());
            stm_insert.setString(3, model.getCname());
            stm_insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remove(Courses model) {
        try {
            String sql_insert = "DELETE Courses\n"
                    + " WHERE cid = ?";
            PreparedStatement stm_insert = connection.prepareStatement(sql_insert);
            stm_insert.setInt(1, model.getCid());
            stm_insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Courses> searchByCourseCode(String ccode) {
        ArrayList<Courses> courses = new ArrayList<>();

        try {
            String sql = "SELECT*FROM  Courses";
            
            if(ccode.length() > 0)
                sql+= " WHERE ccode = ?;";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            
             if(ccode.length() > 0)
                 stm.setString(1, ccode);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Courses c = new Courses();
                c.setCid(rs.getInt("stuid"));
                c.setCcode(rs.getString("stucode"));
                c.setCname(rs.getString("stuname"));
                courses.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return courses;

    }
	    
	
}
