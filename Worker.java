import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Worker extends Thread {

  Socket socket = null;

  public Worker(Socket socket){
    this.socket = socket;
      }


  @Override
  public void run() {


    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
    } catch (IOException e) {
      e.printStackTrace();
    }




  }






}
