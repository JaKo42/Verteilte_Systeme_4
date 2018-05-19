import java.io.*;
import java.util.concurrent.Semaphore;

public class Server {

    private static Semaphore sem = new Semaphore(1, true);

    public Server() {
        QueryInit init = new QueryInit();
    }


    public Reply messageReply(String message) {

        SDS[] sdsArray = new SDS[3];

        FileInputStream fis;
        ObjectInputStream ois;

        Reply reply = null;

        File file = new File("umfragedatei.txt");
        //String filename = "umfragedatei.txt";


        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);


            label:
            while (true) {
                for (int i = 0; i < sdsArray.length; i++) {


                    System.out.println(fis.getChannel().position());
                    sdsArray[i] = (SDS) ois.readObject();


                    //TODO:Test ausgabe entfernen wenn fertig
                    System.out.println("Ausgelesene Daten: " + sdsArray[i].getCategory() + " " + sdsArray[i].getCounter());
                }

                switch (message) {
                    case "info":
                        reply = new Reply(true, sdsArray);
                        ois.close();
                        fis.close();
                        break label;

                    case "ja":
                    case "nein":
                    case "enthalten":
                        //TODO: binäre semaphore einführen um simultaner zugriff zu vermeiden -> static membervariable?
                        sem.acquire();
                        commitAnswer(message, sdsArray);
                        ois.close();
                        fis.close();
                        reply = new Reply(true, sdsArray);
                        break label;

                    default:
                        System.out.println("ungültige eingabe...");
                        reply = new Reply();
                        break;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Datei nicht gefunden...");
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }


        return reply;
    }


    private void commitAnswer(String message, SDS[] sdsArray) {
        String filename = "umfragedatei.txt";

        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            switch (message) {

                case "nein": sdsArray[0].setCounter(sdsArray[0].getCounter()+1);
                break;

                case "ja": sdsArray[1].setCounter(sdsArray[1].getCounter()+1);
                    break;

                case "enthalten": sdsArray[2].setCounter(sdsArray[2].getCounter()+1);
                    break;
            }

            for (SDS s:sdsArray) {
                oos.writeObject(s);
                }
            oos.flush();
            oos.close();
            fos.close();
            sem.release();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

