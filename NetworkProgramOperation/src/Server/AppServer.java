package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.regex.PatternSyntaxException;

public class AppServer extends Thread {

    private Socket client;

    public AppServer(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            System.out.println("connessione ricevuta");
            PrintWriter writer = new PrintWriter(client.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String inputString, outputString;
            while (true) {
                inputString = reader.readLine();
                System.out.println("messaggio ricevuto: " + inputString);
                outputString = operazione(inputString);
                writer.println(outputString);
                writer.flush();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String operazione(String inputString) {
        String outputString = "";
        int op1 = 0, op2 = 0, result = 0;
        try {
            String[] chars = inputString.split(" ");
            try {
                op1 = Integer.parseInt(chars[0]);
                op2 = Integer.parseInt(chars[2]);
            } catch (NumberFormatException e) {
                return "ERRORE: NON SONO STATI INSERITI DEI NUMERI COME OPERATORI";
            }
            if ((chars[1].equals("+")) || (chars[1].equals("-")) || (chars[1].equals("*")) || (chars[1].equals("/"))) {
                switch (chars[1]) {
                    case "+":
                        result = op1 + op2;
                        break;
                    case "-":
                        result = op1 - op2;
                        break;
                    case "*":
                        result = op1 * op2;
                        break;
                    case "/":
                        result = op1 / op2;
                        break;
                }
                outputString = "RISULTATO: " + result;
            } else {
                return "ERRORE: NON è STATO INSERITO UN OPERATORE TRA QUELLI PROPOSTI";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return "ERRORE: SINTASSI MESSAGGIO ERRATA";
        }

        return outputString;
    }
}
