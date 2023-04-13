/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerDao;

import entities.KhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class CustommerDao {

    public static void saveCus(KhachHang khachHang) {
        try {
            String Query = "insert into khachhang (kh_ten,kh_sdt,kh_diem,lkh_maso) values ('" + khachHang.getKh_ten() + "','" + khachHang.getKh_sdt() + "','" + khachHang.getKh_diem() + "','"+khachHang.getLkh_maso()+"')";
            DbOperations.setordeletdata(Query, "Khách hàng được lưu thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void updateCustommer(KhachHang khachHang){
        try {
            String Query = "update khachhang set kh_diem='"+khachHang.getKh_diem()+"' where kh_sdt='"+khachHang.getKh_sdt()+"' ";
            DbOperations.setordeletdata(Query, "Khách hàng được lưu thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getIdByNumPhoneCus(String num) throws SQLException {
        String query = "select kh_maso from khachhang where kh_sdt='" + num + "'";
        ResultSet rs = DbOperations.getData(query);
        int res = 0;
        while (rs.next()) {
            res = rs.getInt("kh_maso");
        }
        return res;
    }

    public static int getScoresByNumPhoneCus(String num) throws SQLException {
        String query = "select kh_diem from khachhang where kh_sdt='" + num + "'";
        ResultSet rs = DbOperations.getData(query);
        int res = 0;
        while (rs.next()) {
            res = rs.getInt("kh_diem");
        }
        return res;
    }

    public static KhachHang getCustommerByNum(String num) throws SQLException {
        String query = "select * from khachhang where kh_sdt='" + num + "'";
        ResultSet rs = DbOperations.getData(query);
        KhachHang khachhang = new KhachHang();
        while(rs.next()){
        khachhang.setKh_maso(rs.getInt("kh_maso"));
        khachhang.setKh_ten(rs.getString("kh_ten"));
        khachhang.setKh_sdt(rs.getString("kh_sdt"));
        khachhang.setKh_diem(rs.getInt("kh_diem"));
        khachhang.setLkh_maso(rs.getInt("lkh_maso"));
        
        }
        
        return khachhang;      
    }
    
    
    public static KhachHang getCustomerById(String id) throws SQLException{
        String query = "select * from khachhang where kh_maso='" + id + "'";
        ResultSet rs = DbOperations.getData(query);
        KhachHang khachhang = new KhachHang();
        while(rs.next()){
        khachhang.setKh_maso(rs.getInt("kh_maso"));
        khachhang.setKh_ten(rs.getString("kh_ten"));
        khachhang.setKh_sdt(rs.getString("kh_sdt"));
        khachhang.setKh_diem(rs.getInt("kh_diem"));
        khachhang.setLkh_maso(rs.getInt("lkh_maso"));
        }
        
        return khachhang;      
        
    }
}
