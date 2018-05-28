import java.util.Scanner;

public class ClientPart {

    public ClientPart(String host, String message) {

        ClientCommunicator communicator = new ClientCommunicator(host);
        Reply reply = communicator.communicate(message);
        outputReply(reply);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String host;
        String message;
        System.out.println("Host Adresse eingeben:");
        host = scanner.nextLine();

        System.out.println("\nNachricht eingeben\nGültige eingaben sind:");
        System.out.println("'ja', 'nein', 'enthalten'");
        message = scanner.nextLine();

        ClientPart client = new ClientPart(host, message);

    }

    private void outputReply(Reply reply) {
        try {


            if (reply != null) {
                System.out.println("\nAktueller Stand der Umfrage:");
                for (SDS s : reply.sds) {
                    System.out.println(s.getCategory() + ": " + s.getCounter());
                }
                System.out.println("\nIhre Stimme wurde gezählt!");
            }
        } catch (NullPointerException e) {
            System.out.println("Eingabe ungültig...");
            e.printStackTrace();
        }
    }

}

