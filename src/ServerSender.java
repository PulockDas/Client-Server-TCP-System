import java.io.PrintWriter;
import java.util.Scanner;

public class ServerSender implements Runnable {
    String msg;
    Scanner sc = new Scanner(System.in);

    @Override
    public void run() {
        while (true) {
            msg = sc.nextLine();
            for(PrintWriter out: server.allOut){
                out.println(msg);
                out.flush();
            }
        }
    }
}