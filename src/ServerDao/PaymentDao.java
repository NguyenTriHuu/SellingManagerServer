/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerDao;

import entities.LoaiThanhToan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class PaymentDao {
    public static int getIdByNamePayment(String name ) throws SQLException{
        String query ="select ltt_maso from loaithanhtoan where ltt_ten='"+name+"'";
        ResultSet rs = DbOperations.getData(query);
        int res=0;
        while(rs.next()){
            res=rs.getInt("ltt_maso");
        }
        return res;
    }
    
    public static ArrayList<LoaiThanhToan> getAllRecord () throws SQLException{
        ArrayList<LoaiThanhToan> list= new ArrayList<>();
        String query ="select * from loaithanhtoan";
        ResultSet rs =DbOperations.getData(query);
        while(rs.next()){
            LoaiThanhToan ltt = new LoaiThanhToan();
            ltt.setLtt_maso(rs.getInt("ltt_maso"));
            ltt.setLtt_ten(rs.getString("ltt_ten"));
            list.add(ltt);
        }
        return list;
    }
}