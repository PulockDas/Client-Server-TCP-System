import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerReceiver implements Runnable{
    BufferedReader in;
    PrintWriter out;
    String msg;
    Socket clientSocket;
    ServerSocket serverSocket;

    public ServerReceiver(BufferedReader in, PrintWriter out, Socket clientSocket, ServerSocket serverSocket){
        this.in = in;
        this.out = out;
        this.clientSocket = clientSocket;
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            msg = in.readLine();

            while (msg != null) {
                System.out.println("Client: " + msg);
                msg = in.readLine();
            }
            System.out.println("Client disconnected!");
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}