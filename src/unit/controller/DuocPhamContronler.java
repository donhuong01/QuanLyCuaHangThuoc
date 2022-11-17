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
public class DuocPhamContronler {

    public static Connection conn = JDBCConnection.getJDBCConnection();

    public ResultSet GetData() throws SQLException {
        ResultSet rs = null;
        Statement st = null;
        st = DuocPhamContronler.conn.createStatement();
        String sql = "SELECT *from medicine";
        rs = st.executeQuery(sql);
        return rs;
    }
    
    public ResultSet GetSoLuong(String maduocpham) throws SQLException {
        ResultSet rs = null;
        Statement st = DuocPhamContronler.conn.createStatement();
        String sql = "SELECT soluong from medicine WHERE maduocpham ='"+maduocpham+"'";
        rs = st.executeQuery(sql);
        return rs;
    }
    
    public ResultSet GetMa(String maduocpham) throws SQLException {
        ResultSet rs = null;
        Statement st = DuocPhamContronler.conn.createStatement();
        String sql = "SELECT maduocpham from medicine WHERE maduocpham ='"+maduocpham+"'";
        rs = st.executeQuery(sql);
        return rs;
    }
    
    public ResultSet GetDuTruToiThieu(String maduocpham) throws SQLException {
        ResultSet rs = null;
        Statement st = DuocPhamContronler.conn.createStatement();
        String sql = "SELECT dutrutoida from medicine WHERE maduocpham ='"+maduocpham+"'";
        rs = st.executeQuery(sql);
        return rs;
    }
    
    public void SetSoLuong(String maduocpham, int soluong) throws SQLException, IOException {
        Statement sta = DuocPhamContronler.conn.createStatement();
        String sql = "update `medicine` SET `soluong` = '"+soluong+"' WHERE `medicine`.`maduocpham`='"+maduocpham+"'";
        sta.executeUpdate(sql);
    }
   
    public void Insert(String maduocpham, String tenduocpham, String loaiduocpham, int dutrutoida, int soluong,
            String donvitinh, double gianhap, double giaban, String ghichu) throws SQLException {
        Statement sta = DuocPhamContronler.conn.createStatement();
        String sql1 = "Insert into medicine (maduocpham, tenduocpham, loaiduocpham, dutrutoida,"
                + "soluong, donvitinh, gianhap, giaban, ghichu) values('" + maduocpham + "','" + tenduocpham + "'  ,'" 
                + loaiduocpham + "','"+ dutrutoida + "'  ,'" + soluong + "','" + donvitinh + "'  ,'" + gianhap + "' ,'" + giaban
                + "' ,'" + ghichu + "')";

        if (sta.executeUpdate(sql1) > 0) {
            System.out.println("Thêm dược phẩm thành công");
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        }
    }

    public void Update(String maduocpham, String tenduocpham, String loaiduocpham, int dutrutoida, int soluong,
            String donvitinh, double gianhap, double giaban, String ghichu) throws SQLException {
        Statement sta = DuocPhamContronler.conn.createStatement();
        String sql1 = "UPDATE `medicine` SET `tenduocpham` = '"+tenduocpham+"' WHERE `medicine`.`maduocpham` = '"+maduocpham+"'";
        String sql2 = "UPDATE `medicine` SET `loaiduocpham` = '"+loaiduocpham+"' WHERE `medicine`.`maduocpham` = '"+maduocpham+"'";
        String sql3 = "UPDATE `medicine` SET `dutrutoida` = '"+dutrutoida+"' WHERE `medicine`.`maduocpham` = '"+maduocpham+"'";
        String sql4 = "UPDATE `medicine` SET `soluong` = '"+soluong+"' WHERE `medicine`.`maduocpham` = '"+maduocpham+"'";
        String sql5 = "UPDATE `medicine` SET `donvitinh` = '"+donvitinh+"' WHERE `medicine`.`maduocpham` = '"+maduocpham+"'";
        String sql6 = "UPDATE `medicine` SET `gianhap` = '"+gianhap+"' WHERE `medicine`.`maduocpham` = '"+maduocpham+"'";
        String sql7 = "UPDATE `medicine` SET `giaban` = '"+giaban+"' WHERE `medicine`.`maduocpham` = '"+maduocpham+"'";
        String sql9 = "UPDATE `medicine` SET `ghichu` = '"+ghichu+"' WHERE `medicine`.`maduocpham` = '"+maduocpham+"'";
        if(sta.executeUpdate(sql1)!=0 && sta.executeUpdate(sql2)!=0 && sta.executeUpdate(sql3)!=0 && sta.executeUpdate(sql4)!=0 && 
                sta.executeUpdate(sql5)!=0 && sta.executeUpdate(sql6)!=0 && sta.executeUpdate(sql7)!=0 && sta.executeUpdate(sql9)!=0)
            JOptionPane.showMessageDialog(null,"Cập nhật thành công");
    }

    public void Delete(String maduocpham) throws SQLException {
        Statement sta = DuocPhamContronler.conn.createStatement();
        String sql = "delete from `medicine` where maduocpham = '" + maduocpham + "'";
        if (sta.executeUpdate(sql) > 0) {
            System.out.println("Xóa dược phẩm thành công");
            JOptionPane.showMessageDialog(null, "Xóa thành công");
        }
    }

    public void Close() throws SQLException, IOException {
        if (DuocPhamContronler.conn != null) {
            DuocPhamContronler.conn.close();
        }
    }

    public ResultSet TimkiemMa(String tkm) throws SQLException {
        Statement sta = DuocPhamContronler.conn.createStatement();
        String sqltimkiem = "SELECT * FROM medicine WHERE maduocpham LIKE '%"+tkm+"%'";
        ResultSet rs = null;
        rs = sta.executeQuery(sqltimkiem);
        return rs;
    }
    
    public ResultSet Timkiem(String tk) throws SQLException
    {
        Statement sta = DuocPhamContronler.conn.createStatement();
        String sqltimkiem = "SELECT * FROM medicine WHERE tenduocpham LIKE '%"+tk+"%'";
        ResultSet rs = null;
        rs = sta.executeQuery(sqltimkiem);
        return rs;
    }
    
    public static void main(String[] args) throws SQLException {
        DuocPhamContronler a = new DuocPhamContronler();
        ResultSet rs = null;
        rs = a.GetData();

        while (rs.next()) {
            System.out.print("Mã dược phẩm: " + rs.getString("maduocpham") + "\t");
            System.out.print("Tên dược phẩm: " + rs.getString("tenduocpham") + "\t");
            System.out.print("Loại dược phẩm: " + rs.getString("loaiduocpham") + "\t");
            System.out.print("Số lượng dự trữ tối đa: " + rs.getString("dutrutoida") + "\t");
            System.out.print("Số lượng: " + rs.getString("soluong") + "\t");
            System.out.print("Đơn vị tính: " + rs.getString("donvitinh") + "\t");
            System.out.print("Giá nhập: " + rs.getString("gianhap") + "\t");
            System.out.print("Giá bán: " + rs.getString("giaban") + "\t");
            System.out.print("Ghi chú: " + rs.getString("ghichu") + "\t");
            System.out.println("");
        }
    }
}
