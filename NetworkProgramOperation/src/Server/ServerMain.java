package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;

public class ServerMain {
    private static int findFirstPort(){
        int port = 1;
        while (port <= 65535){
            try {
                ServerSocket tempServer = new ServerSocket(port);
                tempServer.close();
            } catch (IOException e) {
                port++;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(5000);
            server.setSoTimeout(30000);
            System.out.println("Server partito, in attesa");
            while (true) {
                new Thread(new AppServer(server.accept())).start();
            }
        }catch (SocketTimeoutException e){
            System.err.println("NESSUN CLIENT CONNESSO IN 30 SEC");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
