/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerDao;

import entities.NhanVien;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author htson
 */
public class UserDao {

    public static NhanVien Login(String username, String password) throws SQLException {
        NhanVien nv = null;
        try {
            ResultSet db = DbOperations.getData("SELECT * from [SellingManager].[dbo].[NHANVIEN] WHERE NV_TAIKHOAN='" + username
                    + "' AND NV_MATKHAU='" + password + "'");
            while (db.next()) {
                nv = new NhanVien();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return nv;
    }

    public static void saveUser(NhanVien nhanVien) {
        try {

            String query = "insert into nhanvien (q_maso,nv_taikhoan,nv_matkhau,nv_hoten,nv_sdt,nv_email,nv_diachi,nv_ngaysinh) values ('" + nhanVien.getQ_maso() + "','" + nhanVien.getNv_taikhoan() + "','" + nhanVien.getNv_matkhau() + "','" + nhanVien.getNv_hoten() + "','" + nhanVien.getNv_sdt() + "','" + nhanVien.getNv_email() + "','" + nhanVien.getNv_diachi() + "','" + nhanVien.getNv_ngaysinh() + "')";
            DbOperations.setordeletdata(query, "Nhân viên đã được lưu thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int getIdByEmailUser(String email ) throws SQLException{
        String query ="select nv_maso from nhanvien where nv_email='"+email+"'";
        ResultSet rs = DbOperations.getData(query);
        int res=0;
        while(rs.next()){
            res=rs.getInt("nv_maso");
        }
        return res;
    }
    
    public static NhanVien getStaffById(String id){
        NhanVien nhanVien = new NhanVien();
        
        try {
            String query = "select * from nhanvien where nv_maso = '"+id+"'";
            ResultSet rs =DbOperations.getData(query);
            while(rs.next()){
                nhanVien.setNv_maso(rs.getInt("nv_maso"));
                nhanVien.setNv_hoten(rs.getString("nv_hoten"));
                nhanVien.setNv_email(rs.getString("nv_email"));
                nhanVien.setNv_diachi(rs.getString("nv_diachi"));
                nhanVien.setNv_ngaysinh(String.valueOf(rs.getTimestamp("nv_ngaysinh")));
                nhanVien.setQ_maso(rs.getInt("q_maso"));
                nhanVien.setNv_sdt(rs.getString("nv_sdt"));
                nhanVien.setNv_taikhoan(rs.getString("nv_taikhoan"));
                nhanVien.setNv_matkhau(rs.getString("nv_matkhau"));               
            }
        } catch (Exception e) {
        }
        return nhanVien;
        
    }
}
