/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerDao;

import entities.LoaiHangHoa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class CategoryDao {
    public static ArrayList<LoaiHangHoa> getAllRecordCategory(){
        ArrayList<LoaiHangHoa> list =new ArrayList<>();
        try {
            String queryString="select * from loaihanghoa";
            ResultSet rs =DbOperations.getData(queryString);
            while(rs.next()){
                LoaiHangHoa category = new LoaiHangHoa();
                category.setLhh_maso(rs.getInt("lhh_maso"));
                category.setLhh_ten(rs.getString("lhh_ten"));
                list.add(category);
            }
        } catch (SQLException e) {
        }       
        return list;
    }
    
    public static String getId(String name){
        String idString="";
        try {
            String queryString="select lhh_maso from loaihanghoa where lhh_ten like '%"+name+"%'";
            ResultSet rs = DbOperations.getData(queryString);
            while(rs.next()){
                idString=String.valueOf(rs.getInt("lhh_maso"));
            }
        } catch (Exception e) {
        }
        return idString;
    }
}
