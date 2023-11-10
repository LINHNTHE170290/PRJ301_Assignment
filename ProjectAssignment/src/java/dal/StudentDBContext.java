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
import model.Students;

/**
 *
 * @author admin
 */
public class StudentDBContext extends DBContext{
    public ArrayList<Students> getAll() {
        ArrayList<Students> students = new ArrayList<>();
        try {
            if (connection != null) {  // Kiểm tra xem kết nối cơ sở dữ liệu đã được thiết lập hay chưa
                String sql = "SELECT*FROM Students;";
                PreparedStatement st = connection.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Students s = new Students();
                    s.setStuid(rs.getInt("stuid"));
                    s.setStucode(rs.getString("stucode"));
                    s.setStuname(rs.getString("stuname"));
                    s.setBirthday(rs.getDate("birthday"));
                    s.setGender(rs.getBoolean("gender"));
                    s.setStuemail(rs.getString("stuemail"));
                    s.setStuphone(rs.getString("stuphone"));
                    students.add(s);
                }
                rs.close();
            } else {
                System.out.println("Database connection is not established.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }
    
    
    public void insert(Students model) {
        try {
            connection.setAutoCommit(false);
            String sql_insert = "INSERT INTO Students (stuid, stucode, stuname, birthday, gender, stuemail, stuphone)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm_insert = connection.prepareStatement(sql_insert);
            stm_insert.setString(1, model.getStucode());
            stm_insert.setString(2, model.getStuname());
            stm_insert.setDate(3, model.getBirthday());
            stm_insert.setBoolean(4, model.isGender());
            stm_insert.setString(5, model.getStuemail());
            stm_insert.setString(6, model.getStuphone());
             int affectedRows = stm_insert.executeUpdate();            
             if (affectedRows == 1) {
                ResultSet generatedKeys = stm_insert.getGeneratedKeys();
                if (generatedKeys.next()) {
                    model.setStuid(generatedKeys.getInt(1));
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
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void update(Students model){
        try {
            String sql_insert = "UPDATE Students\n"
                    + "   SET stuid = ?\n"
                    + "      ,stuname = ?\n"
                    + "      ,birthday = ?\n"
                    + "      ,gender = ?\n"
                    + "      ,stuemail = ?\n"
                    + "      ,stuphone = ?\n"
                    + " WHERE stucode = ?";
            PreparedStatement stm_insert = connection.prepareStatement(sql_insert);
            stm_insert.setInt(1, model.getStuid());
            stm_insert.setString(2, model.getStuname());
            stm_insert.setDate(3, model.getBirthday());
            stm_insert.setBoolean(4, model.isGender());
            stm_insert.setString(5, model.getStuemail());
            stm_insert.setString(7, model.getStuphone());
            stm_insert.setString(8, model.getStucode());
            stm_insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remove(Students model) {
        try {
            String sql_insert = "DELETE Students\n"
                    + " WHERE stuid = ?";
            PreparedStatement stm_insert = connection.prepareStatement(sql_insert);
            stm_insert.setInt(1, model.getStuid());
            stm_insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Students> searchByStuCode(String stucode) {
        ArrayList<Students> students = new ArrayList<>();

        try {
            String sql = "SELECT*FROM Students";
            
            if(stucode.length() > 0)
                sql+= " WHERE stucode = ?;";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            
             if(stucode.length() > 0)
                 stm.setString(1, stucode);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Students s = new Students();
                s.setStuid(rs.getInt("stuid"));
                s.setStuname(rs.getString("stuname"));
                s.setStucode(rs.getString("stucode"));
                s.setBirthday(rs.getDate("birthday"));
                s.setGender(rs.getBoolean("gender"));
                s.setStuemail(rs.getString("stuemail"));
                s.setStuphone(rs.getString("stuphone"));
                students.add(s);
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
        return students;

    }
    
    public static void main(String[] args) {
        StudentDBContext dbConnect = new StudentDBContext();
        ArrayList<Students> stulist = dbConnect.getAll();

        // Print the list of students
        for (Students student : stulist) {
            System.out.println("Student ID: " + student.getStuid());
            System.out.println("Student Code: " + student.getStucode());
            System.out.println("Student Name: " + student.getStuname());
            System.out.println("Student Birthday: " + student.getBirthday());
            System.out.println("Student Gender: " + student.isGender());
            System.out.println("Student Email: " + student.getStuemail());
            System.out.println("Student Phone: " + student.getStuphone());
        }
    }
    
}
