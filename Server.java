import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static int zustimmung = 0;
    private static int ablehnung = 0;
    private static int enthaltung = 0;
    private final ServerSocket server;

    public Server(int port) throws IOException {
        server = new ServerSocket(port);
    }

    private void connect() {





        while (true) {
            Socket socket = null;

            try {
                socket = server.accept();
                respond(socket);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null)
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }


    private void returnInfo(Socket socket){}

    private void commitAnswer(int value){
        if (value == 1) {
            zustimmung++;
        } else if (value==2) {
            ablehnung++;
        } else if (value == 3) {
            enthaltung++;
        } else
            System.out.println("ungültige eingabe...");


    }



    private void getMessage(Socket socket) throws IOException{

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        if (in.equals("zustimmen"))
            commitAnswer(1);
        else if (in.equals("ablehnen")) {
            commitAnswer(2);
        } else if (in.equals("enthalten")) {
            commitAnswer(3);
        }else
            System.out.println("ungültige eingabe");




        PrintStream out = new PrintStream(socket.getOutputStream());
        String s;

        while (in.ready()) {
            s = in.readLine();
            out.print(s);
        }
    }


    public static void main(String[] args) throws IOException {

        Server server = new Server(5000);
        server.connect();

    }

}
