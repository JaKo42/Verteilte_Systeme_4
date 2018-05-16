import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;

public class Server {

  private static int zustimmung = 0;
  private static int ablehnung = 0;
  private static int enthaltung = 0;
  private final ServerSocket server;
  private static Stack<BufferedReader> messagePool;

  public Server(int port) throws IOException {
    server = new ServerSocket(port);
  }

  private void connect() {


    while (true) {
      Socket socket = null;

      try {
        socket = server.accept();
        //TODO: Thread erstellen der sich mit dem Client beschäftigt.
        Worker worker = new Worker(socket);
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


  private void getMessage(Socket socket) throws IOException {

    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    messagePool.add(in);

    messageProcessing(messagePool.pop());


    /*PrintStream out = new PrintStream(socket.getOutputStream());
    String s;

    while (in.ready()) {
      s = in.readLine();
      out.print(s);
    }*/
  }

  //TODO: Informationen über Umfrage zurückgeben
  private void returnInfo() {



  }


  //Test: Rückgabe wert auf boolean gesetzt um das ergebniss zu überprüfen.... Exception vielleicht besser?
  private boolean commitAnswer(int value) {
    if (value == 1) {
      zustimmung++;
      return true;
    } else if (value == 2) {
      ablehnung++;
      return true;
    } else if (value == 3) {
      enthaltung++;
      return true;
    } else {
      System.out.println("ungültige eingabe...");
      return false;
    }

  }


  private void messageProcessing(BufferedReader in, Socket socket) {

         if (in.equals("ja"))
      commitAnswer(1);
    else if (in.equals("nein")) {
      commitAnswer(2);
    } else if (in.equals("enthalten")) {
      commitAnswer(3);
         } else if (in.equals("info")) {
          returnInfo();
         } else {
         }
      System.out.println("ungültige eingabe");


  }


  public static void main(String[] args) throws IOException {

    Server server = new Server(5000);
    server.connect();

  }

}
