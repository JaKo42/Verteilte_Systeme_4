import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientCommunicator {

    private final static int PORT = 5000;

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ClientCommunicator(String server) {

        try {
            socket = new Socket(server, PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Reply communicate(String message) {
        Reply reply = null;

        try {
            out.writeObject(message);
            reply = (Reply) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return reply;

    }


    public void stop(){

        try {
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}