import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class server {
    static List<PrintWriter> allOut;

    public static void main(String[] args) {
        final ServerSocket serverSocket;
        Socket clientSocket;
        BufferedReader in;
        allOut = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(8080);
            clientSocket = serverSocket.accept();
            while (clientSocket != null) {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                allOut.add(out);

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                Thread sender = new Thread(new ServerSender());
                sender.start();

                Thread receiver = new Thread(new ServerReceiver(in, out, clientSocket, serverSocket));
                receiver.start();
                clientSocket = serverSocket.accept();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
