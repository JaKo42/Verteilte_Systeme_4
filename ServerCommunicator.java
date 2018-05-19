import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCommunicator extends Thread {


    private final static int PORT = 5000;

    private static Server server;

    private Socket incoming;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ServerCommunicator(Socket incoming) {
        this.incoming = incoming;

        try {
            out = new ObjectOutputStream(incoming.getOutputStream());
            in = new ObjectInputStream(incoming.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("ServerComunicator waiting for clients...");

            server = new Server();

            while (true) {

                Socket incoming = serverSocket.accept();
                ServerCommunicator communicator = new ServerCommunicator(incoming);
                communicator.start();
            }


        } catch (IOException e) {
            e.printStackTrace();

        }


    }

    @Override
    public void run() {
        try {
            String message = (String) in.readObject();
            Reply reply = server.messageReply(message);
            out.writeObject(reply);

            out.flush();
            incoming.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }


}
