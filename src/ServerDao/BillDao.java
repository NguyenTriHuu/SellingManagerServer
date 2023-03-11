/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerDao;

import entities.HoaDon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

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
                String Query = "insert into hoadon (nv_maso,lhd_maso,tt_maso,hd_ghichu,hd_ngaylap,hd_tongtien) values ('"+bill.getNv_maso()+"','"+bill.getLhd_maso()+"','"+bill.getTt_maso()+"','"+bill.getHd_ghichu()+"',"
                        + "'"+bill.getHd_ngaylap()+"','"+bill.getHd_tongtien()+"')";              
            DbOperations.setordeletdata(Query, "Hóa đơn được tạo thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }
}
