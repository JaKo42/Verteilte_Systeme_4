import java.io.*;
import java.util.concurrent.Semaphore;

public class Server {

    private static Semaphore sem = new Semaphore(1, true);
    private File file = new File("umfragedatei.txt");
    private SDS[] sdsArray;

    public Server() {
        QueryInit init = new QueryInit();
        sdsArray = new SDS[3];
    }


    public synchronized Reply messageReply(String message) {


        FileInputStream fis;
        ObjectInputStream ois;

        Reply reply = null;



        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);


            label:
            while (true) {
                for (int i = 0; i < sdsArray.length; i++) {


                    sdsArray[i] = (SDS) ois.readObject();


                }

                switch (message) {
                    case "info":
                        reply = new Reply(sdsArray);
                        ois.close();
                        fis.close();
                        break label;

                    case "ja":
                    case "nein":
                    case "enthalten":
                       // sem.acquire();
                        commitAnswer(message, sdsArray);
                        ois.close();
                        fis.close();
                        reply = new Reply(sdsArray);
                        //sem.release();
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
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        return reply;
    }


    private void commitAnswer(String message, SDS[] sdsArray) {

        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            switch (message) {

                case "nein":
                    sdsArray[0].setCounter(sdsArray[0].getCounter() + 1);
                    break;

                case "ja":
                    sdsArray[1].setCounter(sdsArray[1].getCounter() + 1);
                    break;

                case "enthalten":
                    sdsArray[2].setCounter(sdsArray[2].getCounter() + 1);
                    break;
            }

            for (SDS s : sdsArray) {
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

