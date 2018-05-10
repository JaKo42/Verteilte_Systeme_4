import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientSend {

    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        Socket socket = null;

        try {

            socket = new Socket("localhost", 3141);
            OutputStream out = socket.getOutputStream();
            PrintStream ps = new PrintStream(out, true);
            System.out.println("Geben Sie eine von diesen Antworten ein:");
            System.out.print("ja, ");
            System.out.print("nein, ");
            System.out.print("sonstiges ");

            ps.println(scanner.nextLine());





        } catch (UnknownHostException e) {
            System.out.println("Unknown Host...");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOProbleme...");
            e.printStackTrace();
        } finally {
            if (socket != null)
                try {
                    socket.close();
                    System.out.println("Socket geschlossen...");
                } catch (IOException e) {
                    System.out.println("Socket nicht zu schliessen...");
                    e.printStackTrace();
                }

        }
    }
}