package server;
import modelo.*;
import java.io.*;
import java.net.*;

public class MainServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor esperando conexiones...");
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
