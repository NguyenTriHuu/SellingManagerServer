/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerDao;

import entities.HoaDon;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class BillDao {
    public static String getId() throws ClassNotFoundException, SQLException {
        int id = 1;
            String query= "select max(hd_maso)hd_maso from HoaDon";
            ResultSet rs = DbOperations.getData(query);
            if (rs.next()) {
                id = rs.getInt("hd_maso");
                id++;
            }

        return String.valueOf(id);
    }
    
    public static void save(HoaDon bill) throws ClassNotFoundException {
            try {
                String Query = "insert into hoadon (ltt_maso,kh_maso,nv_maso,hd_ghichu,hd_tongtien,hd_ngaylap) values ('"+bill.getLtt_maso()+"','"+bill.getKh_maso()+"','"+bill.getNv_maso()+"','"+bill.getHd_ghichu()+"',"
                        + "'"+bill.getHd_tongtien()+"','"+bill.getHd_ngaylap()+"')";              
            DbOperations.setordeletdata(Query, "Hóa đơn được tạo thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }
}
