import java.io.*;

public class Server {

        private SDS sds;

    public Server() {
        QueryInit init = new QueryInit();
    }


    public Reply messageReply(String message) {


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

                if (message.equals("info")){
                    ois.close();
                    fis.close();
                    reply = new Reply(true, sds.getCategory(), sds.getCounter());
                    break;
                }else if (message.equals("ja")|| message.equals("nein") || message.equals("enthalten")){
//                    Semaphore sem = new Semaphore(1,true);
                    ois.close();
                    fis.close();

                    commitAnswer(message);
                    reply = new Reply(true, sds.getCategory(), sds.getCounter());
                }else {
                    System.out.println("ungültige eingabe...");
                    reply = new Reply();
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Datei nicht gefunden...");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return reply;
    }






    private void commitAnswer(String message) {
        String filename = "umfragedatei.txt";

        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            if ((sds.getCategory()).equals(message)) {
                sds.setCounter(sds.getCounter() + 1);
                oos.writeObject(sds);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

