/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerDao;

import entities.NhanVien;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author htson
 */
public class UserDao {

    public static NhanVien Login(String username, String password) throws SQLException {
        NhanVien nv = null;
        try {
            ResultSet db = DbOperations.getData("SELECT * from [SellingManager].[dbo].[NHANVIEN] WHERE NV_TAIKHOAN='" + username
                    + "' AND NV_MATKHAU='" + password + "'");
            while (db.next()) {
                nv = new NhanVien();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return nv;
    }
}
