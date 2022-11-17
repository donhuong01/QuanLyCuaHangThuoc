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
public class KhachHangContronler {

    public static Connection conn = JDBCConnection.getJDBCConnection();

    public ResultSet GetData() throws SQLException {
        ResultSet rs = null;
        Statement st = null;
        st = KhachHangContronler.conn.createStatement();
        String sql = "SELECT *FROM khachhang";
        rs = st.executeQuery(sql);
        return rs;
    }

    public void Insert(String makhach, String tenkhach, String email, String phone, String address) throws SQLException {
        Statement sta = KhachHangContronler.conn.createStatement();
        String sql1 = "Insert into khachhang (makhach, tenkhach, email, phone, address) values('" + makhach + "','" + tenkhach + "'  ,'"
                + email + "','" + phone + "'  ,'" + address + "')";

        if (sta.executeUpdate(sql1) > 0) {
            System.out.println("Thêm khách hàng thành công");
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        }
    }

    public void Update(String makhach, String tenkhach, String email, String phone, String address) throws SQLException {
        Statement sta = KhachHangContronler.conn.createStatement();
        String sql1 = "UPDATE `khachhang` SET `tenkhach` = '" + tenkhach + "' WHERE `khachhang`.`makhach` = '" + makhach + "'";
        String sql2 = "UPDATE `khachhang` SET `email` = '" + email + "' WHERE `khachhang`.`makhach` = '" + makhach + "'";
        String sql3 = "UPDATE `khachhang` SET `phone` = '" + phone + "' WHERE `khachhang`.`makhach` = '" + makhach + "'";
        String sql4 = "UPDATE `khachhang` SET `address` = '" + address + "' WHERE `khachhang`.`makhach` = '" + makhach + "'";
        if(sta.executeUpdate(sql1)!=0 && sta.executeUpdate(sql2)!=0 && sta.executeUpdate(sql3)!=0 && sta.executeUpdate(sql4)!=0)
            JOptionPane.showMessageDialog(null,"Cập nhật thành công");
    }

    public void Delete(String makhach) throws SQLException {
        Statement sta = KhachHangContronler.conn.createStatement();
        String sql = "delete from `khachhang` where makhach = '" + makhach + "'";
        if (sta.executeUpdate(sql) > 0) {
            System.out.println("Xóa khách hàng thành công");
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        }
    }

    public void Close() throws SQLException, IOException {
        if (KhachHangContronler.conn != null) {
            KhachHangContronler.conn.close();
        }
    }

    public ResultSet Timkiem(String tkm) throws SQLException {
        Statement sta = KhachHangContronler.conn.createStatement();
        String sqltimkiem = "SELECT * FROM khachhang WHERE tenkhach LIKE '%"+tkm +"%'";
        ResultSet rs = null;
        rs = sta.executeQuery(sqltimkiem);
        return rs;
    }
    
    public ResultSet TimkiemDC(String tkm) throws SQLException {
        Statement sta = KhachHangContronler.conn.createStatement();
        String sqltimkiem = "SELECT * FROM khachhang WHERE address LIKE '%"+tkm +"%'";
        ResultSet rs = null;
        rs = sta.executeQuery(sqltimkiem);
        return rs;
    }

    public static void main(String[] args) throws SQLException {
        KhachHangContronler a = new KhachHangContronler();
        ResultSet rs = null;
        rs = a.GetData();

        while (rs.next()) {
            System.out.print("Mã khách: " + rs.getString("makhach") + "\t");
            System.out.print("Tên khách: " + rs.getString("tenkhach") + "\t");
            System.out.print("Email: " + rs.getString("email") + "\t");
            System.out.print("Phone: " + rs.getString("phone") + "\t");
            System.out.print("Address: " + rs.getString("address") + "\t");
            System.out.println("");

        }

    }
}
