/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerDao;

import entities.TableViewAllDiscount;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class TableViewAllDiscountDao {
    public static ArrayList<TableViewAllDiscount> getAllRecords(String query){
        ArrayList<TableViewAllDiscount> list=new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData(query);
            while(rs.next()){
               TableViewAllDiscount temp = new TableViewAllDiscount();
               temp.setGghh_maso(Integer.parseInt(rs.getString("gghh_maso")));
               temp.setGghh_phantram(rs.getInt("gghh_phantram"));
               temp.setHh_maso(rs.getString("hh_maso"));
               temp.setHh_ten(rs.getString("hh_tenhang"));
               temp.setLhh_ten(rs.getString("lhh_ten"));
               temp.setHh_date(String.valueOf(rs.getDate("hh_date")));
               temp.setNgay_bd(String.valueOf(rs.getDate("gglhh_ngaybatdau")));
               temp.setNgay_kt(String.valueOf(rs.getDate("gglhh_ngayketthuc")));
              list.add(temp);
            }
        } catch (NumberFormatException | SQLException e) {
        }
        return list;
    }    
}
