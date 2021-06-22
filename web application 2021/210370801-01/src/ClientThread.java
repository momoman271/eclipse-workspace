import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    Socket socket;
    int counter;

    public ClientThread(Socket cl, int c) {
        this.socket = cl;
        this.counter = c;
    }

    //接続処理スレッド
    public void run() {
        try {
            BufferedReader in = 
              new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String line = in.readLine();
            System.out.println("** " + counter);
            System.out.println("> "+line);
            if( line.startsWith("GET")) {
                writeHtml(out);
                while(in.ready()) {
                    System.out.println("> "+in.readLine());
                }
            } else {
                out.println("HTTP/1.1 400 Bad Request");
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeHtml(PrintWriter out) {
        out.println("HTTP/1.1 200 OK");
        out.println("");
        out.println("Multi Web Server: ");
        out.println("GET method");
    }
}