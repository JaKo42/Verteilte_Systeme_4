import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Server {

    /*private static int zustimmung = 0;
    private static int ablehnung = 0;
    private static int enthaltung = 0;
*/
    public Server() {
        QueryInit init = new QueryInit();
    }


    public Reply infoReply() {

        SDS sds;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Reply reply = null;

        String filename = "umfragedatei.txt";


        try {
            fis = new FileInputStream(filename);
            ois = new ObjectInputStream(fis);

            while (true) {
                sds = (SDS) ois.readObject();
                //TODO:Test ausgabe entfernen wenn fertig
                System.out.println("Ausgelesene Daten: " + sds.getCategory() + " " + sds.getCounter());


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return reply;
    }

}

/*
    public Reply messageProcessing(String message) {

        if (message.equals("ja"))
            return commitAnswer(1);
        else if (message.equals("nein")) {
            return commitAnswer(2);
        } else if (message.equals("enthalten")) {
            return commitAnswer(3);
        } else if (message.equals("info")) {
            returnInfo();
        } else {
        }
        System.out.println("ungültige eingabe");


    }

    private Reply commitAnswer(int value) {
        if (value >= 1 && value <= 3) {
            if (value == 1) {
                zustimmung++;
            } else if (value == 2) {
                ablehnung++;
            } else if (value == 3) {
                enthaltung++;
            }

            Reply reply = new Reply(true, zustimmung, ablehnung, enthaltung);
        } else if (value == 0) {
            Reply reply = new Reply(zustimmung, ablehnung, enthaltung);
        } else {
            System.out.println("ungültige eingabe...");
            Reply reply = new Reply();
            return reply;
        }

    }

}
*/
