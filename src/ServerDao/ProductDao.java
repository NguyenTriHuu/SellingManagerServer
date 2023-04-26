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
            String query = "insert into hanghoa (hh_maso,hh_tenhang,hh_giaban,hh_hinhanh,hh_date,lhh_maso,th_maso,hh_barcode,hh_trongluong,dvt_maso) values ('"+hanghoa.getHh_maso()+"','" + hanghoa.getHh_tenhang() + "','" + hanghoa.getHh_giaban() + "','" + hanghoa.getHh_hinhanh() + "','" + hanghoa.getHh_date() + "','" + hanghoa.getLhh_maso() + "','" + hanghoa.getTh_maso() + "','"+hanghoa.getHh_barcode()+"','"+hanghoa.getHh_trongluong()+"','"+hanghoa.getDvt_maso()+"')";
            DbOperations.setordeletdata(query, "Hàng hóa đã được lưu");
        } catch (Exception e) {
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
        } catch (SQLException e) {
        }
        return arrayList;
    }
    
    public static ArrayList<String> SearchNameProductAndId(String id){
        ArrayList<String> list =new ArrayList<>();
        try {
            String query ="select hh_maso,hh_tenhang from hanghoa where hh_maso like '%"+id+"%'";
            ResultSet rs = DbOperations.getData(query);
            while(rs.next()){
                String temp =rs.getString("hh_maso")+" - "+rs.getString("hh_tenhang");
                list.add(temp);
            }
        } catch (SQLException e) {
        }
        return list;
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
               product.setHh_date(String.valueOf(rs.getTimestamp("hh_date")));
               product.setHh_barcode(rs.getString("hh_barcode"));
               product.setLhh_maso(rs.getInt("lhh_maso"));
               product.setTh_maso(rs.getInt("th_maso"));
               product.setDvt_maso(rs.getInt("dvt_maso"));
               product.setHh_trongluong(rs.getFloat("hh_trongluong"));
               arrayList.add(product);
            }
        } catch (SQLException e) {
        }
        return arrayList;
    }
    
    public static HangHoa SearchProductById(String id){
        HangHoa product = new HangHoa();
        try {
            String query ="select * from hanghoa where hh_maso='"+id+"'";
            ResultSet rs = DbOperations.getData(query);
            while(rs.next()){
               product.setHh_maso(rs.getString("hh_maso"));
               product.setHh_tenhang(rs.getString("hh_tenhang"));
               product.setHh_hinhanh(rs.getString("hh_hinhanh"));
               product.setHh_giaban(rs.getString("hh_giaban"));
               product.setHh_date(String.valueOf(rs.getTimestamp("hh_date")));
               product.setHh_barcode(rs.getString("maCode"));
               product.setLhh_maso(rs.getInt("lhh_maso"));
               product.setTh_maso(rs.getInt("th_maso"));
               product.setDvt_maso(rs.getInt("dvt_maso"));
               product.setHh_trongluong(rs.getFloat("hh_trongluong"));
            }
        } catch (SQLException e) {
        }
       return product;
    }
    
    public static ArrayList<HangHoa> SearchProductByCategory(String Idcategory) throws SQLException {
        ArrayList<HangHoa> arrayList = new ArrayList<>();
        try {
            String query = "select * from hanghoa where lhh_maso = '" + Idcategory + "'";
            ResultSet rs = DbOperations.getData(query);
            while (rs.next()) {
               HangHoa product = new HangHoa();
               product.setHh_maso(rs.getString("hh_maso"));
               product.setHh_tenhang(rs.getString("hh_tenhang"));
               product.setHh_hinhanh(rs.getString("hh_hinhanh"));
               product.setHh_giaban(rs.getString("hh_giaban"));
               product.setHh_date(String.valueOf(rs.getTimestamp("hh_date")));
               product.setHh_barcode(rs.getString("hh_barcode"));
               product.setLhh_maso(rs.getInt("lhh_maso"));
               product.setTh_maso(rs.getInt("th_maso"));
               product.setDvt_maso(rs.getInt("dvt_maso"));
               product.setHh_trongluong(rs.getFloat("hh_trongluong"));
               arrayList.add(product);
            }
        } catch (SQLException e) {
        }
        return arrayList;
    }
}
