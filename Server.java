import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    private final ServerSocket socket;

    public Server(int port)throws IOException {
    socket = new ServerSocket(port);
    }


    private static int zustimmung = 0;
    private static int ablehnung = 0;
    private static int enthaltung = 0;


    private void connect{}

}
