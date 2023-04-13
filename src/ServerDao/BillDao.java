/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerDao;

import entities.ChiTietHoaDon;
import entities.HoaDon;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class BillDao {

    public static String getId() throws ClassNotFoundException, SQLException {
        int id = 0 ;
        String query = "select max(hd_maso)hd_maso from HoaDon";
        ResultSet rs = DbOperations.getData(query);
        if (rs.next()) {
            id = rs.getInt("hd_maso");           
        }

        return String.valueOf(id);
    }

    public static void save(HoaDon bill , int billid) throws ClassNotFoundException {
        try {
            String Query = "update hoadon set ltt_maso ='" + bill.getLtt_maso() +"' , kh_maso = '" + bill.getKh_maso() + "',nv_maso = '" + bill.getNv_maso() +"' , hd_ghichu = '" + bill.getHd_ghichu() + "',hd_tongtien ='" + bill.getHd_tongtien() +"' ,hd_ngaylap =getdate(),hd_tongtienchietkhau='"+bill.getHd_tongtienchietkhau()+"',hd_tinhtrangthanhtoan='"+bill.isHd_tinhtrangthanhtoan()+"' where hd_maso = '"+billid +"' ";
            DbOperations.setordeletdata(Query, "Hóa đơn được tạo thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    
    public static void saveTemp(){
        try {
            String Query = "insert into hoadon (ltt_maso,kh_maso,nv_maso,hd_tinhtrangthanhtoan) values (1,1,1,'false')";
            DbOperations.setordeletdata(Query,"");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void DeleteBillById(int id){
        try {
            String query ="delete from hoadon where hd_maso = '"+id+"'";
            DbOperations.setordeletdata(query, "");
        } catch (Exception e) {
        }
    }
    
    public static HoaDon getHoaDonById(String id){
        HoaDon hoaDon = new HoaDon();
        
        try {
            String query = "select * from hoadon where hd_maso ='"+id+"'";
            ResultSet rs =DbOperations.getData(query);
            while(rs.next()){
                hoaDon.setHd_maso(rs.getInt("hd_maso"));
                hoaDon.setKh_maso(rs.getInt("kh_maso"));
                hoaDon.setNv_maso(rs.getInt("nv_maso"));
                hoaDon.setLtt_maso(rs.getInt("ltt_maso"));
                hoaDon.setHd_ghichu(rs.getString("hd_ghichu"));
                hoaDon.setHd_tongtien(rs.getString("hd_tongtien"));
                hoaDon.setHd_ngaylap(String.valueOf(rs.getTimestamp("hd_ngaylap")));
                hoaDon.setHd_tongtienchietkhau(rs.getString("hd_tongtienchietkhau"));
                hoaDon.setHd_tinhtrangthanhtoan(rs.getBoolean("hd_tinhtrangthanhtoan"));
            }
        } catch (Exception e) {
        }
        return hoaDon;
        
    }
}
