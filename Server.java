import java.io.*;

public class Server {

    public Server() {
        QueryInit init = new QueryInit();
    }


    public Reply messageReply(String message) {

        SDS[] sdsArray = new SDS[3];

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        Reply reply = null;

        File file = new File("umfragedatei.txt");
        //String filename = "umfragedatei.txt";


        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);


            while (true) {
                for (int i = 0; i < sdsArray.length; i++) {


                    //TODO: "Metadata" am anfang der SDS klasse, welche die Stelle in der Datei angibt somit wird nie das ende des files provoziert!
                    //TODO: andere möglichkeit mit vielen "If's" die drei möglichkeiten abfiltern und so ablesen if(ois.readobject().equals "ja"
                    System.out.println(fis.getChannel().position());
                    sdsArray[i] = (SDS) ois.readObject();
                    if (sdsArray[i].getPosition() == 2) {

                        break;

                    }
                    //TODO:Test ausgabe entfernen wenn fertig
                    System.out.println("Ausgelesene Daten: " + sdsArray[i].getCategory() + " " + sdsArray[i].getCounter());
                }


                if (message.equals("info")) {
                    reply = new Reply(true, sdsArray);
                    ois.close();
                    fis.close();
                    break;
                } else if (message.equals("ja") || message.equals("nein") || message.equals("enthalten")) {
                    //TODO: binäre semaphore einführen um simultaner zugriff zu vermeiden -> static membervariable?
                    //Semaphore sem = new Semaphore(1,true);
                    commitAnswer(message, sdsArray);
                    ois.close();
                    fis.close();
                    reply = new Reply(true, sdsArray);
                    break;
                } else {
                    System.out.println("ungültige eingabe...");
                    reply = new Reply();
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Datei nicht gefunden...");
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            //TODO entfernen wenn funktioniert
            System.out.println("nix mehr zu lesen boy!");
            e.printStackTrace();
        }


        return reply;
    }


    private void commitAnswer(String message, SDS[] sdsArray) {
        String filename = "umfragedatei.txt";

        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (SDS s : sdsArray) {
                System.out.println("For each schleife gibt aus: " + s.getCategory());
                if ((s.getCategory()).equals(message)) {
                    System.out.println("For each schleife hat ausgewählt: " + s.getCategory());
                    s.setCounter(s.getCounter() + 1);
                    System.out.println("Counter wurde geändert: " + s.getCounter());


                }
                oos.writeObject(s);

            }
            oos.flush();
            oos.close();
            fos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

