/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import unit.data.JDBCConnection;

/**
 *
 * @author admin
 */
public class NhanVienContronler {
    public static Connection conn = JDBCConnection.getJDBCConnection();

    public ResultSet GetData() throws SQLException {
        ResultSet rs = null;
        Statement st = null;
        st = NhanVienContronler.conn.createStatement();
        String sql = "SELECT *FROM employee";
        rs = st.executeQuery(sql);
        return rs;
    }

    public void Insert(String maNV, String username, String name, String date, String gender, String phone, String email, String address, String password) throws SQLException {
        Statement sta = NhanVienContronler.conn.createStatement();
        String sql1 = "Insert into employee (maNV, username, name, dateofbirth, gender, phone, email, address, password) values('" + maNV + "','" + username + "','"
                + name + "','" + date + "'  ,'" + gender + "','" + phone + "'  ,'" + email + "','" + address + "','" + password + "')";

        if (sta.executeUpdate(sql1) > 0) {
            System.out.println("Thêm khách hàng thành công");
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        }
    }

    public void Update(String maNV, String username, String name, String date, String gender, String phone, String email, String address, String password) throws SQLException {
        Statement sta = NhanVienContronler.conn.createStatement();
        String sql1 = "UPDATE `employee` SET `username` = '" + username + "' WHERE `employee`.`maNV` = '" + maNV + "'";
        String sql2 = "UPDATE `employee` SET `name` = '" + name + "' WHERE `employee`.`maNV` = '" + maNV + "'";
        String sql3 = "UPDATE `employee` SET `dateofbirth` = '" + date + "' WHERE `employee`.`maNV` = '" + maNV + "'";
        String sql4 = "UPDATE `employee` SET `gender` = '" + gender + "' WHERE `employee`.`maNV` = '" + maNV + "'";
        String sql5 = "UPDATE `employee` SET `phone` = '" + phone + "' WHERE `employee`.`maNV` = '" + maNV + "'";
        String sql6 = "UPDATE `employee` SET `email` = '" + email + "' WHERE `employee`.`maNV` = '" + maNV + "'";
        String sql7 = "UPDATE `employee` SET `address` = '" + address + "' WHERE `employee`.`maNV` = '" + maNV + "'";
        String sql8 = "UPDATE `employee` SET `password` = '" + password + "' WHERE `employee`.`maNV` = '" + maNV + "'";
        if(sta.executeUpdate(sql1)!=0 && sta.executeUpdate(sql2)!=0 && sta.executeUpdate(sql3)!=0 && sta.executeUpdate(sql4)!=0 &&
                sta.executeUpdate(sql5)!=0 && sta.executeUpdate(sql6)!=0 && sta.executeUpdate(sql7)!=0 && sta.executeUpdate(sql8)!=0)
            JOptionPane.showMessageDialog(null,"Cập nhật thành công");
    }

    public void Delete(String maNV) throws SQLException {
        Statement sta = NhanVienContronler.conn.createStatement();
        String sql = "delete from `employee` where maNV = '" + maNV + "'";
        if (sta.executeUpdate(sql) > 0) {
            System.out.println("Xóa khách hàng thành công");
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        }
    }

    public ResultSet Timkiem(String tk) throws SQLException
    {
        Statement sta = NhanVienContronler.conn.createStatement();
        String sqltimkiem = "SELECT * FROM employee WHERE maNV LIKE '%"+tk+"%'";
        ResultSet rs = null;
        rs = sta.executeQuery(sqltimkiem);
        return rs;
    }
    
    public ResultSet TimkiemTen(String tk) throws SQLException
    {
        Statement sta = NhanVienContronler.conn.createStatement();
        String sqltimkiem = "SELECT * FROM employee WHERE name LIKE '%"+tk+"%'";
        ResultSet rs = null;
        rs = sta.executeQuery(sqltimkiem);
        return rs;
    }
    
    public void Close() throws SQLException, IOException {
        if (KhachHangContronler.conn != null) {
            KhachHangContronler.conn.close();
        }
    }

    public static void main(String[] args) throws SQLException {
        NhanVienContronler ncc = new NhanVienContronler();
        ResultSet rs = null;
        rs = ncc.GetData();

        while (rs.next()) {
            System.out.print("Mã nhân viên: " + rs.getString("maNV") + "\t");
            System.out.print("UserName: " + rs.getString("username") + "\t");
            System.out.print("Tên nhân viên: " + rs.getString("name") + "\t");
            System.out.print("Date of birth: " + rs.getString("dateofbirth") + "\t");
            System.out.print("Gender: " + rs.getString("gender") + "\t");
            System.out.print("Phone: " + rs.getString("phone") + "\t");
            System.out.print("Email: " + rs.getString("email") + "\t");
            System.out.print("Address: " + rs.getString("address") + "\t");
            System.out.print("Password: " + rs.getString("password") + "\t");
            System.out.println("");
        }
    }
}
