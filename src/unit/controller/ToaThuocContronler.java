/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import unit.data.JDBCConnection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class ToaThuocContronler {

    public static Connection conn = JDBCConnection.getJDBCConnection();

    public ResultSet GetData() throws SQLException {
        ResultSet rs = null;
        Statement st = null;
        st = DuocPhamContronler.conn.createStatement();
        String sql = "SELECT *from toathuoc";
        rs = st.executeQuery(sql);
        return rs;
    }

    public void Close() throws SQLException, IOException {
        if (ToaThuocContronler.conn != null) {
            ToaThuocContronler.conn.close();
        }
    }

    public void Delete(String maphieu) throws SQLException {
        Statement sta = ToaThuocContronler.conn.createStatement();
        String sql = "delete from `toathuoc` where maphieu = '" + maphieu + "'";
        if (sta.executeUpdate(sql) > 0) {
            System.out.println("Xóa dược phẩm thành công");
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        }
    }

    public void Insert(String ngayke, String makhach, double giathanh, String ghichu) throws SQLException {
        Statement sta = ToaThuocContronler.conn.createStatement();
        String sql1 = "Insert into toathuoc (ngayke, makhach, giathanh, ghichu) values('" + ngayke + "','" + makhach + "' ,'" + giathanh + "' ,'" + ghichu + "')";

        if (sta.executeUpdate(sql1) > 0) {
            System.out.println("Thêm dược phẩm thành công");
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        }
    }
    
    public static void main(String[] args) throws SQLException {
        ToaThuocContronler a = new ToaThuocContronler();
        ResultSet rs = null;
        rs = a.GetData();

        while (rs.next()) {
            System.out.print("Số phiếu toa thuốc: " + rs.getString("maphieu") + "\t");
            System.out.print("Ngày kê đơn: " + rs.getString("ngayke") + "\t");
            System.out.print("Khách hàng: " + rs.getString("makhach") + "\t");
            System.out.print("Giá thành: " + rs.getString("giathanh") + "\t");
            System.out.print("Ghi chú: " + rs.getString("ghichu") + "\t");
            System.out.println("");
        }
    }
}
