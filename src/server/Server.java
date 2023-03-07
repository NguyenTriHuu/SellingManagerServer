/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package server;

import Controller.ServerCtr;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static final int NUM_OF_THREAD = 4;
    public static final int SERVER_PORT = 8888;

    public static void main(String[] args) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_THREAD);
        ServerSocket serverSocket = null;

        try {
            System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server started: ....");
            System.out.println("Waiting for a client ...");
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("Client accepted: " + socket);

                    ServerCtr handler = new ServerCtr(socket);
                    executor.execute(handler);
                } catch (Exception e) {
                    System.err.println(" Connection Error: " + e);
                }
            }
        } catch (Exception e1) {
            System.err.println(" Connection Error: " + e1);
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }

}
