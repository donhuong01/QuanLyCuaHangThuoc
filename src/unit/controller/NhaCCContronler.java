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
public class NhaCCContronler {
    public static Connection conn = JDBCConnection.getJDBCConnection();

    public ResultSet GetData() throws SQLException {
        ResultSet rs = null;
        Statement st = null;
        st = NhaCCContronler.conn.createStatement();
        String sql = "SELECT *FROM nhacungcap";
        rs = st.executeQuery(sql);
        return rs;
    }

    public void Insert(String manhacc, String tennhacc, String phone, String email, String address) throws SQLException {
        Statement sta = NhaCCContronler.conn.createStatement();
        String sql1 = "Insert into nhacungcap (manhacc, tennhacc, phone, email, address) values('" + manhacc + "','" + tennhacc + "'  ,'"
                + phone + "','" + email + "'  ,'" + address + "')";

        if (sta.executeUpdate(sql1) > 0) {
            System.out.println("Thêm khách hàng thành công");
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        }
    }

    public void Update(String manhacc, String tennhacc, String phone, String email, String address) throws SQLException {
        Statement sta = NhaCCContronler.conn.createStatement();
        String sql1 = "UPDATE `nhacungcap` SET `tennhacc` = '" + tennhacc + "' WHERE `nhacungcap`.`manhacc` = '" + manhacc + "'";
        String sql2 = "UPDATE `nhacungcap` SET `phone` = '" + phone + "' WHERE `nhacungcap`.`manhacc` = '" + manhacc + "'";
        String sql3 = "UPDATE `nhacungcap` SET `email` = '" + email + "' WHERE `nhacungcap`.`manhacc` = '" + manhacc + "'";
        String sql4 = "UPDATE `nhacungcap` SET `address` = '" + address + "' WHERE `nhacungcap`.`manhacc` = '" + manhacc + "'";
        if(sta.executeUpdate(sql1)!=0 && sta.executeUpdate(sql2)!=0 && sta.executeUpdate(sql3)!=0 && sta.executeUpdate(sql4)!=0)
            JOptionPane.showMessageDialog(null,"Cập nhật thành công");
    }

    public void Delete(String manhacc) throws SQLException {
        Statement sta = NhaCCContronler.conn.createStatement();
        String sql = "delete from `nhacungcap` where manhacc = '" + manhacc + "'";
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
        Statement sta = NhaCCContronler.conn.createStatement();
        String sqltimkiem = "SELECT * FROM nhacungcap WHERE manhacc LIKE N'%" + tkm + "%'";
        ResultSet rs = null;
        rs = sta.executeQuery(sqltimkiem);
        return rs;
    }
    
    public ResultSet TimkiemDC(String tkm) throws SQLException {
        Statement sta = NhaCCContronler.conn.createStatement();
        String sqltimkiem = "SELECT * FROM nhacungcap WHERE address LIKE N'%" + tkm + "%'";
        ResultSet rs = null;
        rs = sta.executeQuery(sqltimkiem);
        return rs;
    }

    public static void main(String[] args) throws SQLException {
        NhaCCContronler ncc = new NhaCCContronler();
        ResultSet rs = null;
        rs = ncc.GetData();

        while (rs.next()) {
            System.out.print("Mã nhà CC: " + rs.getString("manhacc") + "\t");
            System.out.print("Tên nhà CC: " + rs.getString("tennhacc") + "\t");
            System.out.print("Email: " + rs.getString("email") + "\t");
            System.out.print("Phone: " + rs.getString("phone") + "\t");
            System.out.print("Address: " + rs.getString("address") + "\t");
            System.out.println("");

        }
    }
}
