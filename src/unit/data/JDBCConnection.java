/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class JDBCConnection {

    public static Connection getJDBCConnection() {
        final String url = "jdbc:mysql://localhost:3306/qlcuahangthuoc";
        final String user = "root";
        final String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
        } catch (SQLException ex) {
        }
        return null;
    }

    public static void main(String[] args) {
        Connection connection = getJDBCConnection();

        if (connection != null) {
            System.out.println("Data connection successful");
        } else {
            System.out.println("Data connection failed");
        }
    }

}
