/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerDao;

import entities.GiamGia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class DiscountDao {
    
    public static ArrayList<GiamGia> getAllRecord(){
       ArrayList<GiamGia> list =new ArrayList<>();
        try {
                String queryString="select * from giamgiahanghoa where gglhh_ngayketthuc >getdate()";
            ResultSet rs= DbOperations.getData(queryString);
            while(rs.next()){
                GiamGia gg =new GiamGia();
                gg.setGghh_maso(rs.getInt("gghh_maso"));
                gg.setGghh_ngaybd(String.valueOf(rs.getDate("gglhh_ngaybatdau")));
                gg.setGghh_ngaykt(String.valueOf(rs.getDate("gglhh_ngayketthuc")));
                gg.setGghh_phantram(rs.getInt("gghh_phantram"));
                list.add(gg);              
            }
        } catch (SQLException e) {
        }
       return list;
    }
    
    public static void updateDis (GiamGia discount){
        try {
            String queryString="update giamgiahanghoa set gglhh_ngaybatdau='"+discount.getGghh_ngaybd()+"' , gglhh_ngayketthuc='"+discount.getGghh_ngaykt()+"',gghh_phantram='"+discount.getGghh_phantram()+"' where gghh_maso='"+discount.getGghh_maso()+"'";
            DbOperations.setordeletdata(queryString,"");
        } catch (Exception e) {
        }
    }
    
    public static void AddDiscount(GiamGia discount){
        try {
            String query ="insert into giamgiahanghoa(gglhh_ngaybatdau,gglhh_ngayketthuc,gghh_phantram) values('"+discount.getGghh_ngaybd()+"','"+discount.getGghh_ngaykt()+"','"+discount.getGghh_phantram()+"')";
            DbOperations.setordeletdata(query,"");
        } catch (Exception e) {
        }
    }
    
    public static String getIdDiscount(){
        int id = 0;
        try {
            String query="select max(gghh_maso)gghh_maso from giamgiahanghoa";
            ResultSet rs= DbOperations.getData(query);
            while(rs.next()){
                id=rs.getInt("gghh_maso");
            }
        } catch (Exception e) {
        }
        return String.valueOf(id+1);
    }
    
    
    public static void Delete(String id){
        try {
            String queryString="delete from chitietmagiam where gghh_maso='"+id+"' delete from giamgiahanghoa where gghh_maso='"+id+"'" ;
            DbOperations.setordeletdata(queryString,"");
        } catch (Exception e) {
        }
    }
}
