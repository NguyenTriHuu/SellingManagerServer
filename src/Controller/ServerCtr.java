/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import ServerDao.BillDao;
import ServerDao.DbOperations;
import ServerDao.ProductDao;
import ServerDao.UserDao;
import entities.HoaDon;
import entities.NhanVien;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.annotation.processing.Messager;

/**
 *
 * @author user
 */
public class ServerCtr extends Thread {

    private Socket socket;

    public ServerCtr(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String mes = (String) ois.readObject();
            System.out.println(mes);
            if (mes.equals("test")) {
                PrintWriter pr = new PrintWriter(socket.getOutputStream());
                pr.println("ok");
                pr.flush();
            } else if (mes.equals("SaveBill")) {
                try {
                    HoaDon bill = (HoaDon) ois.readObject();
                    BillDao.save(bill);
                    PrintWriter pr = new PrintWriter(socket.getOutputStream());
                    pr.println("Lưu Thành công");
                    pr.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    PrintWriter pr = new PrintWriter(socket.getOutputStream());
                    pr.println("Lưu Thất bại");
                    pr.flush();
                }
            } else if (mes.equals("SearchNameProduct")) {
                String name = (String) ois.readObject();
                ArrayList<String> list = ProductDao.SearchNameProduct(name);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(list);
                oos.flush();
            } else if (mes.equals("Login")) {
                try {
                    InputStreamReader in = new InputStreamReader(socket.getInputStream());
                    BufferedReader bf = new BufferedReader(in);
                    String username = bf.readLine();
                    String password = bf.readLine();
                    NhanVien auth = UserDao.Login(username, password);
                    if (auth == null) {
                        PrintWriter pr = new PrintWriter(socket.getOutputStream());
                        pr.println("Sai tài khoản hoặc mật khẩu");
                        pr.flush();
                    } else {
                        PrintWriter pr = new PrintWriter(socket.getOutputStream());
                        pr.println("Đăng nhập thành công");
                        pr.flush();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.err.println("Request Processing Error: " + e);
        }
        System.out.println("Complete..");
    }
}
