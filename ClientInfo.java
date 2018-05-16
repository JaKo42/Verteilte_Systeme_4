import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientInfo {




  public static void main(String[] args) {

    Socket socket = null;

    try {

      socket = new Socket("localhost", 5000);
      OutputStream out = socket.getOutputStream();
      PrintStream ps = new PrintStream(out, true);


      ps.println("info");





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
          System.out.println("Kann Socket nicht schliessen...");
          e.printStackTrace();
        }

    }
  }

}
