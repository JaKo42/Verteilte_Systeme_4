
public class ClientInfo {
    private ClientCommunicator communicator;

    private void outputReply(Reply reply){
        if (reply.isStatus()){
            System.out.println("Aktueller Stand der Umfrage:");
            System.out.println(reply.getCategory() + ": " + reply.getCounter());
        }else
            System.out.println("Eingabe ungültig...");

    }

    public ClientInfo(String[] argumente){

        communicator = new ClientCommunicator("localhost");
        Reply reply = communicator.communicate("info");
        outputReply(reply);
    }

    public static void main(String[] args) {

      /*  if (args.length != 2 ) {
            System.out.println("Syntax: java Client <server> <message>");
            System.exit(1);
        }
      */  ClientInfo client = new ClientInfo(args);
    }













/*
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        Socket socket = null;

        try {

            socket = new Socket("localhost", 5000);
            OutputStream out = socket.getOutputStream();
            PrintStream ps = new PrintStream(out, true);
            System.out.println("Geben Sie eine von diesen Antworten ein:");
            System.out.print("ja, ");
            System.out.print("nein, ");
            System.out.print("enthalten ");

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
                    System.out.println("Kann Socket nicht schliessen...");
                    e.printStackTrace();
                }

        }
    }*/
}