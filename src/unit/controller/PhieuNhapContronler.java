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
public class PhieuNhapContronler {

    public static Connection conn = JDBCConnection.getJDBCConnection();

    public ResultSet GetData() throws SQLException {
        ResultSet rs = null;
        Statement st = null;
        st = PhieuNhapContronler.conn.createStatement();
        String sql = "SELECT a.sophieunhap, b.tenduocpham, a.ngaynhap, a.solo, a.soluong, a.donvitinh, a.ngaysanxuat, "
                + "a.hansudung, c.tennhacc, a.gianhap FROM `phieunhaphang` as a, medicine as b, nhacungcap as c "
                + "WHERE a.maduocpham = b.maduocpham and a.manhacc = c.manhacc ORDER BY a.sophieunhap ASC";
        rs = st.executeQuery(sql);
        return rs;
    }

    public ResultSet GetSoLuong(String sophieunhap) throws SQLException {
        ResultSet rs = null;
        Statement st = DuocPhamContronler.conn.createStatement();
        String sql = "SELECT soluong from phieunhaphang WHERE sophieunhap ='"+ sophieunhap+"'";
        rs = st.executeQuery(sql);
        return rs;
    }
    
    public void Insert(String maduocpham, String ngaynhap, int solo, int soluong,
            String donvitinh, String ngaysanxuat, String hansudung, String manhacc, double gianhap) throws SQLException {
        Statement sta = DuocPhamContronler.conn.createStatement();
        String sql1 = "Insert into phieunhaphang (maduocpham, ngaynhap, solo,"
                + "soluong, donvitinh, ngaysanxuat, hansudung, manhacc, gianhap) values('" + maduocpham + "'  ,'"
                + ngaynhap + "','" + solo + "'  ,'" + soluong + "','" + donvitinh + "'  ,'" + ngaysanxuat + "' ,'" + hansudung
                + "' ,'" + manhacc + "','" + gianhap + "')";

        if (sta.executeUpdate(sql1) > 0) {
            System.out.println("Th??m d?????c ph???m th??nh c??ng");
            JOptionPane.showMessageDialog(null, "Th??m th??nh c??ng");
        }
    }
    
    public void Update(String sophieunhap,String ngaynhap, int solo, int soluong, String ngaysanxuat, String hansudung, String manhacc) throws SQLException {
        Statement sta = DuocPhamContronler.conn.createStatement();
        String sql2 = "UPDATE `phieunhaphang` SET `ngaynhap` = '"+ngaynhap+"' WHERE `phieunhaphang`.`sophieunhap` = '"+sophieunhap+"'";
        String sql3 = "UPDATE `phieunhaphang` SET `solo` = '"+solo+"' WHERE `phieunhaphang`.`sophieunhap` = '"+sophieunhap+"'";
        String sql4 = "UPDATE `phieunhaphang` SET `soluong` = '"+soluong+"' WHERE `phieunhaphang`.`sophieunhap` = '"+sophieunhap+"'";
        String sql6 = "UPDATE `phieunhaphang` SET `ngaysanxuat` = '"+ngaysanxuat+"' WHERE `phieunhaphang`.`sophieunhap` = '"+sophieunhap+"'";
        String sql7 = "UPDATE `phieunhaphang` SET `hansudung` = '"+hansudung+"' WHERE `phieunhaphang`.`sophieunhap` = '"+sophieunhap+"'";
        String sql9 = "UPDATE `phieunhaphang` SET `manhacc` = '"+manhacc+"' WHERE `phieunhaphang`.`sophieunhap` = '"+sophieunhap+"'";
        if( sta.executeUpdate(sql2)!=0 && sta.executeUpdate(sql3)!=0 && sta.executeUpdate(sql4)!=0 && 
           sta.executeUpdate(sql6)!=0 && sta.executeUpdate(sql7)!=0 && sta.executeUpdate(sql9)!=0 )
            JOptionPane.showMessageDialog(null,"C???p nh???t th??nh c??ng");
    }
    
    public void Delete(String sophieunhap) throws SQLException {
        Statement sta = DuocPhamContronler.conn.createStatement();
        String sql = "delete from `phieunhaphang` where sophieunhap = '" + sophieunhap + "'";
        if (sta.executeUpdate(sql) > 0) {
            System.out.println("X??a d?????c ph???m th??nh c??ng");
            JOptionPane.showMessageDialog(null, "X??a th??nh c??ng");
        }
    }

    public void Close() throws SQLException, IOException {
        if (PhieuNhapContronler.conn != null) {
            PhieuNhapContronler.conn.close();
        }
    }

    public static void main(String[] args) throws SQLException {
        PhieuNhapContronler a = new PhieuNhapContronler();
        ResultSet rs = null;
        rs = a.GetData();

        while (rs.next()) {
            System.out.print("S??? phi???u nh???p: " + rs.getString("sophieunhap") + "\t");
            System.out.print("T??n d?????c ph???m: " + rs.getString("tenduocpham") + "\t");
            System.out.print("Ng??y nh???p: " + rs.getString("ngaynhap") + "\t");
            System.out.print("S??? l??: " + rs.getString("solo") + "\t");
            System.out.print("S??? l?????ng: " + rs.getString("soluong") + "\t");
            System.out.print("????n v??? t??nh: " + rs.getString("donvitinh") + "\t");
            System.out.print("Ng??y s???n xu???t: " + rs.getString("ngaysanxuat") + "\t");
            System.out.print("H???n s??? d???ng: " + rs.getString("hansudung") + "\t");
            System.out.print("Nh?? cung c???p: " + rs.getString("tennhacc") + "\t");
            System.out.print("Gi?? nh???p: " + rs.getString("gianhap") + "\t");
            System.out.println("");
        }
    }
}
