/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerDao;

import entities.HangHoa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class ProductDao {

    public static void save(HangHoa hanghoa) throws ClassNotFoundException {
        try {
            String query = "insert into hanghoa (hh_maso,hh_tenhang,hh_giaban,hh_hinhanh,hh_date,lhh_maso,th_maso) values ('"+hanghoa.getHh_maso()+"','" + hanghoa.getHh_tenhang() + "','" + hanghoa.getHh_giaban() + "','" + hanghoa.getHh_hinhanh() + "','" + hanghoa.getHh_date() + "','" + hanghoa.getLhh_maso() + "','" + hanghoa.getTh_maso() + "',)";
            DbOperations.setordeletdata(query, "Hàng hóa đã được lưu");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<String> SearchNameProduct(String name) throws SQLException {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            String query = "select hh_tenhang from hanghoa where hh_tenhang like '%" + name + "%'";
            ResultSet rs = DbOperations.getData(query);
            while (rs.next()) {
                arrayList.add(rs.getString("hh_tenhang"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
    
    public static ArrayList<HangHoa> SearchProductByName(String name) throws SQLException {
        ArrayList<HangHoa> arrayList = new ArrayList<>();
        try {
            String query = "select * from hanghoa where hh_tenhang like '%" + name + "%'";
            ResultSet rs = DbOperations.getData(query);
            while (rs.next()) {
               HangHoa product = new HangHoa();
               product.setHh_maso(rs.getString("hh_maso"));
               product.setHh_tenhang(rs.getString("hh_tenhang"));
               product.setHh_hinhanh(rs.getString("hh_hinhanh"));
               product.setHh_giaban(rs.getString("hh_giaban"));
               arrayList.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
