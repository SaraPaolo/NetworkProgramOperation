package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(System.currentTimeMillis());
        try {
            Socket clientSocket = new Socket("localhost", 5000);
            System.out.println("Server contattato");
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream()); //invio da client
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //ricezione da server
            String inputString;

            for (int i = 0; i < 10; i++) {
                System.out.println("INSERIRE L'OPERAZIONE DA RISOLVERE");
                inputString = sc.nextLine();
                writer.println(inputString);
                writer.flush();
                System.out.println(reader.readLine());
            }
            clientSocket.close();
        } catch (Exception e) {
            System.out.println("eccezione " + e.getMessage());
        }
    }
}
