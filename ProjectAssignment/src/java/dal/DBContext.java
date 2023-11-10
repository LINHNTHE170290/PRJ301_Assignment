/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sonnt
 */
public class DBContext {
    public Connection connection;

    public DBContext(String url, String username, String pass) {
        try {
            //goi driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //conect
            connection = DriverManager.getConnection(url, username, pass);
            System.out.println("Connected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DBContext() {
        this("jdbc:sqlserver://localhost:1433;databaseName=GrandingManagementSystem",
                "sa", "12345678");
    }
    public ResultSet getData(String sql){
        ResultSet rs=null;
         Statement state;
        try {
            state = connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
             rs = state.executeQuery(sql);
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return rs;
    }

    public static void main(String[] args) {
        new DBContext();
    }
}







