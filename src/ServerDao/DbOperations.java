/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 *
 * @author user
 */

public class DbOperations {
    private static String connectionUrl = "jdbc:sqlserver://DESKTOP-OMSFQEI\\SQLEXPRESS:1433;databaseName=SellingManager;user=sa;password=123;encrypt=true;trustServerCertificate=true";
    private static String USERNAME = "sa";
    private static String PASSWORD = "123";
    public static void setordeletdata(String Query, String msg) {    
        try {
            Connection con = (Connection) ConnectionProvider.getConnection(connectionUrl,USERNAME,PASSWORD);
            Statement st = con.createStatement();
            st.executeUpdate(Query);
            if (!msg.equals("")) {
                JOptionPane.showMessageDialog(null, msg);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static ResultSet getData(String query) {
        try {
            Connection con = (Connection) ConnectionProvider.getConnection(connectionUrl,USERNAME,PASSWORD);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
