/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import ServerDao.BillDao;
import ServerDao.ChitiethoadonDao;
import ServerDao.CustommerDao;
import ServerDao.DbOperations;
import ServerDao.PaymentDao;
import ServerDao.ProductDao;
import ServerDao.UserDao;
import entities.ChiTietHoaDon;
import entities.HangHoa;
import entities.HoaDon;
import entities.KhachHang;
import entities.LoaiThanhToan;
import entities.NhanVien;
import entities.ResultCTHD;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            String mes = (String) ois.readObject();
            System.out.println(mes);
            switch (mes) {               
                case "SearchNameProduct":
                    String name = (String) ois.readObject();
                    ArrayList<String> list = ProductDao.SearchNameProduct(name);
                    oos.writeObject(list);
                    oos.flush();
                    break;

                case "Login":
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
                } catch (IOException | SQLException e) {
                }
                break;

                case "getProductByName":
                    String namepr = (String) ois.readObject();
                    ArrayList<HangHoa> list2 = ProductDao.SearchProductByName(namepr);
                    oos.writeObject(list2);
                    oos.flush();
                    break;

                case "getCustommer":
                    String num = (String) ois.readObject();
                    KhachHang khachHang = CustommerDao.getCustommerByNum(num);
                    oos.writeObject(khachHang);
                    oos.flush();
                    break;
                    
                 case "getCustommerById":
                    String idCus = (String) ois.readObject();
                    KhachHang khGet = CustommerDao.getCustomerById(idCus);
                    oos.writeObject(khGet);
                    oos.flush();
                    break;

                case "saveNewCustommer":
                    try {
                    KhachHang kh1 = (KhachHang) ois.readObject();
                    CustommerDao.saveCus(kh1);
                    PrintWriter prrr = new PrintWriter(socket.getOutputStream());
                    prrr.println("Lưu Thành công");
                    prrr.flush();
                } catch (IOException | ClassNotFoundException e) {
                    PrintWriter pri = new PrintWriter(socket.getOutputStream());
                    pri.println("Lưu Thất bại");
                    pri.flush();
                }
                break;

                case "updateCustommer":
                    try {
                    KhachHang kh2 = (KhachHang) ois.readObject();
                    CustommerDao.updateCustommer(kh2);
                    PrintWriter prcus = new PrintWriter(socket.getOutputStream());
                    prcus.println("Cập nhật thành công");
                    prcus.flush();
                } catch (IOException | ClassNotFoundException e) {
                    PrintWriter prcus2 = new PrintWriter(socket.getOutputStream());
                    prcus2.println("Cập nhật thất bại");
                    prcus2.flush();
                }
                break;

                case "getAllRecordPay":
                    try {
                    ArrayList<LoaiThanhToan> list3 = PaymentDao.getAllRecord();
                    oos.writeObject(list3);
                    oos.flush();
                } catch (IOException | SQLException e) {
                }
                break;
                case "GetPayByName":
                    String nameLtt = (String) ois.readObject();
                    String id2 = String.valueOf(PaymentDao.getIdByNamePayment(nameLtt));
                    oos.writeObject(id2);
                    oos.flush();
                    break;

                case "GetIdStaff":
                    String email = (String) ois.readObject();
                    String id = String.valueOf(UserDao.getIdByEmailUser(email));
                    oos.writeObject(id);
                    oos.flush();
                    break;
                    
                case "GetStaffById":
                    String idStaffString = (String) ois.readObject();
                    NhanVien StaffNhanVien =UserDao.getStaffById(idStaffString);
                    oos.writeObject(StaffNhanVien);
                    oos.flush();
                    break;

                case "SaveNewBill":
                    HoaDon bill = (HoaDon) ois.readObject();
                    int idBillNew =Integer.valueOf((String) ois.readObject());
                ArrayList<ChiTietHoaDon> cthd = (ArrayList<ChiTietHoaDon>) ois.readObject();
                Iterator<ChiTietHoaDon> itr = cthd.iterator();                
                try {
                    BillDao.save(bill,idBillNew);
                    while (itr.hasNext()) {
                        ChitiethoadonDao.Save(itr.next());
                    }
                    oos.writeObject("Save Succesfull");
                } catch (ClassNotFoundException e) {
                }
                    break;

                case "getIdBill":
                String idbill = BillDao.getId();
                oos.writeObject(idbill);
                oos.flush();
                    break;

                case "SaveTemp":
                    try {                   
                    BillDao.saveTemp();
                    String idbildao = BillDao.getId();
                    oos.writeObject(idbildao);
                    oos.flush();
                } catch (Exception e) {
                }
                    break;
                
                case "DeleteBill":
                    try {
                    int maso_bill =Integer.parseInt((String) ois.readObject());
                    BillDao.DeleteBillById(maso_bill);
                } catch (Exception e) {
                    oos.writeObject("Lỗi");
                    oos.flush();
                }
                    break;
                    
                case "getHoaDonById":
                    String hoaDonId = (String) ois.readObject();
                    HoaDon hoaDonGet = BillDao.getHoaDonById(hoaDonId);
                    oos.writeObject(hoaDonGet);
                    oos.flush();
                    break;
                    
                 case "getCTHD":
                    String idHoaDonGet = (String) ois.readObject();
                    ArrayList <ResultCTHD> listCTHD =new ArrayList<>();
                    listCTHD=ChitiethoadonDao.GetResultCTHDbyIdHD(idHoaDonGet);
                    oos.writeObject(listCTHD);
                    oos.flush();
                    break;

                default:
                    throw new AssertionError();
            }

            /*if (mes.equals("test")) {
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
                } catch (IOException | ClassNotFoundException e) {
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
                } catch (IOException | SQLException e) {
                }
            } else if (mes.equals("getProductByName")) {
                String name = (String) ois.readObject();
                ArrayList<HangHoa> list = ProductDao.SearchProductByName(name);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(list);
                oos.flush();
            } else if (mes.equals("getCustommer")) {
                String num = (String) ois.readObject();
                KhachHang khachHang = CustommerDao.getCustommerByNum(num);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(khachHang);
                oos.flush();
            } else if (mes.equals("saveNewCustommer")) {
                try {
                    KhachHang kh = (KhachHang) ois.readObject();
                    CustommerDao.saveCus(kh);
                    PrintWriter pr = new PrintWriter(socket.getOutputStream());
                    pr.println("Lưu Thành công");
                    pr.flush();
                } catch (IOException | ClassNotFoundException e) {
                    PrintWriter pr = new PrintWriter(socket.getOutputStream());
                    pr.println("Lưu Thất bại");
                    pr.flush();
                }
            } else if (mes.equals("updateCustommer")) {
                try {
                    KhachHang kh = (KhachHang) ois.readObject();
                    CustommerDao.updateCustommer(kh);
                    PrintWriter pr = new PrintWriter(socket.getOutputStream());
                    pr.println("Cập nhật thành công");
                    pr.flush();
                } catch (IOException | ClassNotFoundException e) {
                    PrintWriter pr = new PrintWriter(socket.getOutputStream());
                    pr.println("Cập nhật thất bại");
                    pr.flush();
                }
            } else if (mes.equals("getAllRecordPay")) {
                try {
                    ArrayList<LoaiThanhToan> list = PaymentDao.getAllRecord();
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(list);
                    oos.flush();
                } catch (IOException | SQLException e) {
                }
            } else if (mes.equals("GetPayByName")) {
                String nameLtt = (String) ois.readObject();
                String id = String.valueOf(PaymentDao.getIdByNamePayment(nameLtt));
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(id);
                oos.flush();
            } else if (mes.equals("GetIdStaff")) {
                String email = (String) ois.readObject();
                String id = String.valueOf(UserDao.getIdByEmailUser(email));
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(id);
                oos.flush();
            } else if (mes.equals("SaveNewBill")) {
                HoaDon bill = (HoaDon) ois.readObject();
                ArrayList<ChiTietHoaDon> cthd = (ArrayList<ChiTietHoaDon>) ois.readObject();
                Iterator<ChiTietHoaDon> itr = cthd.iterator();
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                try {
                    BillDao.save(bill);
                    while (itr.hasNext()) {
                        ChitiethoadonDao.Save(itr.next());
                    }
                    oos.writeObject("Save Succesfull");
                } catch (ClassNotFoundException e) {
                }
            } else if (mes.equals("getIdBill")) {
                String id = BillDao.getId();
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(id);
                oos.flush();
            } else if (mes.equals("SaveTemp")) {
                try {
                    BillDao.saveTemp();
                } catch (Exception e) {
                }

            }*/

        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.err.println("Request Processing Error: " + e);
        }
        System.out.println("Complete..");
    }
}
