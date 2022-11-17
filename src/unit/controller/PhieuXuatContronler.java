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
public class PhieuXuatContronler {

    public static Connection conn = JDBCConnection.getJDBCConnection();

    public ResultSet GetData() throws SQLException {
        ResultSet rs = null;
        Statement st = null;
        st = PhieuNhapContronler.conn.createStatement();
        String sql = "SELECT a.maphieuxuat, b.tenduocpham, a.ngayxuat, c.tenkhach, a.soluong, a.donvitinh,"
                + " b.giaban, b.ghichu FROM `phieuxuathang` as a, medicine as b, khachhang as c "
                + "WHERE a.maduocpham = b.maduocpham and a.makhach = c.makhach";
        rs = st.executeQuery(sql);
        return rs;
    }
    
    public void Insert(String maduocpham, String ngayxuat, String makhach, int soluong,
            String donvitinh, double giaban, String ghichu) throws SQLException {
        Statement sta = DuocPhamContronler.conn.createStatement();
        String sql1 = "Insert into phieuxuathang (maduocpham, ngayxuat, makhach,"
                + "soluong, donvitinh, giaban, ghichu) values('" + maduocpham + "'  ,'"
                + ngayxuat + "','" + makhach + "'  ,'" + soluong + "','" + donvitinh + "'  ,'" + giaban + "' ,'" + ghichu + "')";

        if (sta.executeUpdate(sql1) > 0) {
            System.out.println("Thêm dược phẩm thành công");
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        }
    }
    
    public void Update(String maphieuxuat,String ngayxuat, String makhach, int soluong, String ghichu) throws SQLException {
        Statement sta = DuocPhamContronler.conn.createStatement();
        String sql1 = "UPDATE `phieuxuathang` SET `ngayxuat` = '"+ngayxuat+"' WHERE `phieuxuathang`.`maphieuxuat` = '"+maphieuxuat+"'";
        String sql2 = "UPDATE `phieuxuathang` SET `makhach` = '"+makhach+"' WHERE `phieuxuathang`.`maphieuxuat` = '"+maphieuxuat+"'";
        String sql3 = "UPDATE `phieuxuathang` SET `soluong` = '"+soluong+"' WHERE `phieuxuathang`.`maphieuxuat` = '"+maphieuxuat+"'";
        String sql5 = "UPDATE `phieuxuathang` SET `ghichu` = '"+ghichu+"' WHERE `phieuxuathang`.`maphieuxuat` = '"+maphieuxuat+"'";
        if( sta.executeUpdate(sql1)!=0 && sta.executeUpdate(sql2)!=0 && sta.executeUpdate(sql3)!=0 && sta.executeUpdate(sql5)!=0)
            JOptionPane.showMessageDialog(null,"Cập nhật thành công");
    }
    
    public void Delete(String maphieuxuat) throws SQLException {
        Statement sta = DuocPhamContronler.conn.createStatement();
        String sql = "delete from `phieuxuathang` where maphieuxuat = '" + maphieuxuat + "'";
        if (sta.executeUpdate(sql) > 0) {
            System.out.println("Xóa dược phẩm thành công");
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        }
    }
    
    public void Close() throws SQLException, IOException {
        if (PhieuXuatContronler.conn != null) {
            PhieuXuatContronler.conn.close();
        }
    }
    
    public static void main(String[] args) throws SQLException {
        PhieuXuatContronler a = new PhieuXuatContronler();
        ResultSet rs = null;
        rs = a.GetData();

        while (rs.next()) {
            System.out.print("Số phiếu xuất: " + rs.getString("maphieuxuat") + "\t");
            System.out.print("Tên dược phẩm: " + rs.getString("tenduocpham") + "\t");
            System.out.print("Ngày xuất: " + rs.getString("ngayxuat") + "\t");
            System.out.print("Khách hàng: " + rs.getString("tenkhach") + "\t");
            System.out.print("Số lượng: " + rs.getString("soluong") + "\t");
            System.out.print("Đơn vị tính: " + rs.getString("donvitinh") + "\t");
            System.out.print("Giá bán: " + rs.getString("giaban") + "\t");
            System.out.print("Ghi chú: " + rs.getString("ghichu") + "\t");
            System.out.println("");
        }
    }
}
