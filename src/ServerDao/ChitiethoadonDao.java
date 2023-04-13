/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerDao;

import entities.ChiTietHoaDon;
import entities.ResultCTHD;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class ChitiethoadonDao {
    public static void Save(ChiTietHoaDon cthd ){
        try {
            String query="insert into chitiethoadon (hd_maso,hh_maso,hh_date,cthd_soluong) values ('"+cthd.getHd_maso()+"','"+cthd.getHh_maso()+"','"+cthd.getHh_date()+"','"+cthd.getSoluong()+"')";
            DbOperations.setordeletdata(query, "");
            
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    
    public static ArrayList<ResultCTHD> GetResultCTHDbyIdHD(String id){
        ArrayList <ResultCTHD> list = new ArrayList<>();      
        try {
            String queryString ="select hanghoa.HH_TENHANG,hanghoa.HH_GIABAN,CHITIETHOADON.CTHD_SOLUONG from CHITIETHOADON inner join hoadon  on CHITIETHOADON.HD_MASO=hoadon.HD_MASO inner join HANGHOA on CHITIETHOADON.HH_MASO =HANGHOA.HH_MASO where hoadon.HD_MASO ='"+id+"'";
            ResultSet rs =DbOperations.getData(queryString);
            while(rs.next()){
                ResultCTHD rss = new ResultCTHD();
                rss.setHh_tenhang(rs.getString("hh_tenhang"));
                rss.setHh_giaban(rs.getString("hh_giaban"));
                rss.setSoluong(rs.getInt("cthd_soluong"));
                list.add(rss);
            }
        } catch (Exception e) {
        }
        return  list;
        
    }
}
