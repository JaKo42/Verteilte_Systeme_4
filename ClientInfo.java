
public class ClientInfo {

    private void outputReply(Reply reply){
        if (reply != null){
            System.out.println("Aktueller Stand der Umfrage:");
            for (SDS s: reply.sds) {
                System.out.println(s.getCategory() +": " + s.getCounter());
            }
        }else
        System.out.println("Eingabe ungültig...");

    }

    public ClientInfo(String[] argumente){

        ClientCommunicator communicator = new ClientCommunicator("localhost");
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

}