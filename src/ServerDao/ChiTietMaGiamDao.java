/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerDao;

import entities.ChiTietMaGiam;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author user
 */
public class ChiTietMaGiamDao {
    public static void save(ArrayList<ChiTietMaGiam> ctmg){
        Iterator<ChiTietMaGiam> itr =ctmg.iterator();
        String queryString="";
        while(itr.hasNext()){
            ChiTietMaGiam temp =itr.next();
            queryString+="insert into chitietmagiam(gghh_maso,hh_maso,hh_date) values ('"+temp.getGghh_maso()+"','"+temp.getHh_maso()+"','"+temp.getHh_date()+"')";
        }
        DbOperations.setordeletdata(queryString,"");
    }
    
    public static String Check(ChiTietMaGiam ctmg){
        String id ="";
        
        try {
            String query ="select * from chitietmagiam where gghh_maso ='"+ctmg.getGghh_maso()+"'and hh_maso ='"+ctmg.getHh_maso()+"'and hh_date='"+ctmg.getHh_date()+"'";
            ResultSet rs= DbOperations.getData(query);
            while(rs.next()){
                id=String.valueOf(rs.getInt("gghh_maso"));
            }
        } catch (Exception e) {
        }
        return id;
    }
}
