/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import ServerDao.BillDao;
import entities.HoaDon;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
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
            if (mes.equals("test")) {
                PrintWriter pr = new PrintWriter(socket.getOutputStream());
                pr.println("ok");
                pr.flush();
            } else if (mes.equals("SaveBill")) {              
                try {                  
                  HoaDon  bill = (HoaDon) ois.readObject();
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
            }

        } catch (Exception e) {
            System.err.println("Request Processing Error: " + e);
        }
        System.out.println("Complete..");
    }
}
