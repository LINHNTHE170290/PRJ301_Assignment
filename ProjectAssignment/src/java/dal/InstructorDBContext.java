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
import model.Instructors;

/**
 *
 * @author admin
 */
public class InstructorDBContext extends DBContext{
    public ArrayList<Instructors> getAll() {
        ArrayList<Instructors> instructors = new ArrayList<>();
        try {
            if (connection != null) {  // Kiểm tra xem kết nối cơ sở dữ liệu đã được thiết lập hay chưa
                String sql = "SELECT*FROM Instructors;";
                PreparedStatement st = connection.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Instructors i = new Instructors();
                    i.setIid(rs.getInt("iid"));
                    i.setIname(rs.getString("iname"));
                    i.setIcode(rs.getString("icode"));
                    i.setBirthday(rs.getDate("birthday"));
                    i.setGender(rs.getBoolean("gender"));
                    i.setIemail(rs.getString("iemail"));
                    i.setIphone(rs.getString("iphone"));
                    instructors.add(i);
                }
                rs.close();
            } else {
                System.out.println("Database connection is not established.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return instructors;
    }
    public ArrayList<Instructors> searchByInsCode(String code) {
        ArrayList<Instructors> instructors = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Instructors";
            
            if(code.length() > 0)
                sql+= " WHERE icode = ?;";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            
             if(code.length() > 0)
                 stm.setString(1, code);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Instructors i = new Instructors();
                i.setIid(rs.getInt("iid"));
                i.setIname(rs.getString("iname"));
                i.setIcode(rs.getString("icode"));
                i.setBirthday(rs.getDate("birthday"));
                i.setGender(rs.getBoolean("gender"));
                i.setIemail(rs.getString("iemail"));
                i.setIphone(rs.getString("iphone"));
                instructors.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instructors;

    }
    
     public void insert(Instructors model) {
        try {
            connection.setAutoCommit(false);
            String sql_insert = "INSERT INTO Instructors (iid, icode, iname, birthday, gender, iemail, iphone)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm_insert = connection.prepareStatement(sql_insert);
            stm_insert.setString(1, model.getIcode());
            stm_insert.setString(2, model.getIname());
            stm_insert.setDate(3, model.getBirthday());
            stm_insert.setBoolean(4, model.isGender());
            stm_insert.setString(5, model.getIemail());
            stm_insert.setString(6, model.getIphone());
            stm_insert.executeUpdate();

            int affectedRows = stm_insert.executeUpdate();            
             if (affectedRows == 1) {
                ResultSet generatedKeys = stm_insert.getGeneratedKeys();
                if (generatedKeys.next()) {
                    model.setIid(generatedKeys.getInt(1));
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
                Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
     
    public void update(Instructors model){
        try {
            String sql_insert = "UPDATE Instructors\n"
                    + "   SET iid = ?\n"
                    + "      ,iname = ?\n"
                    + "      ,birthday = ?\n"
                    + "      ,gender = ?\n"
                    + "      ,iemail = ?\n"
                    + "      ,iphone = ?\n"
                    + " WHERE icode = ?";
            PreparedStatement stm_insert = connection.prepareStatement(sql_insert);
            stm_insert.setInt(1, model.getIid());
            stm_insert.setString(2, model.getIname());
            stm_insert.setDate(3, model.getBirthday());
            stm_insert.setBoolean(4, model.isGender());
            stm_insert.setString(5, model.getIemail());
            stm_insert.setString(7, model.getIphone());
            stm_insert.setString(8, model.getIcode());
            stm_insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void remove(Instructors model) {
        try {
            String sql_insert = "DELETE Instructors\n"
                    + " WHERE iid = ?";
            PreparedStatement stm_insert = connection.prepareStatement(sql_insert);
            stm_insert.setInt(1, model.getIid());
            stm_insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Instructors> searchByICode(String icode) {
        ArrayList<Instructors> instructorses = new ArrayList<>();

        try {
            String sql = "SELECT*FROM Instructors";
            
            if(icode.length() > 0)
                sql+= " WHERE icode = ?;";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            
             if(icode.length() > 0)
                 stm.setString(1, icode);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Instructors i = new Instructors();
                i.setIid(rs.getInt("iid"));
                i.setIname(rs.getString("iname"));
                i.setIcode(rs.getString("icode"));
                i.setBirthday(rs.getDate("birthday"));
                i.setGender(rs.getBoolean("gender"));
                i.setIemail(rs.getString("iemail"));
                i.setIphone(rs.getString("iphone"));
                instructorses.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(InstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instructorses;

    }
    
     public static void main(String[] args) {
        InstructorDBContext dbConnect = new InstructorDBContext();
        ArrayList<Instructors> inslist = dbConnect.getAll();

        // Print the list of students
        for (Instructors student : inslist) {
            System.out.println("Instructor ID: " + student.getIid());
            System.out.println("Instructor Name: " + student.getIname());
            System.out.println("Instructor Code: " + student.getIcode());
            System.out.println("Instructor Birthday: " + student.getBirthday());
            System.out.println("Instructor Gender: " + student.isGender());
            System.out.println("Student Email: " + student.getIemail());
            System.out.println("Student Phone: " + student.getIphone());
        }
    }
}
