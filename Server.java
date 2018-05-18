import java.io.*;

public class Server {

  private SDS[] sdsArray = new SDS[3];
  private SDS sds;
  private SDS sds1;
  private SDS sds2;

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
        for (int i = 0; i < sdsArray.length ; i++) {

          sdsArray[i] = (SDS) ois.readObject();

          //TODO:Test ausgabe entfernen wenn fertig
          System.out.println("Ausgelesene Daten: " + sdsArray[i].getCategory() + " " + sdsArray[i].getCounter());
        }




        if (message.equals("info")) {
          reply = new Reply(true, sdsArray);
          ois.close();
          fis.close();
          break;
        } else if (message.equals("ja") || message.equals("nein") || message.equals("enthalten")) {
//                    Semaphore sem = new Semaphore(1,true);
          ois.close();
          fis.close();
          commitAnswer(message);
          reply = new Reply(true,sdsArray);
          break;
        } else {
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

      for (SDS s:sdsArray) {
        System.out.println("For each schleife gibt aus: "+ s.getCategory());
        if ((s.getCategory()).equals(message)) {
          System.out.println("For each schleife hat ausgewählt: " + s.getCategory());
        s.setCounter(s.getCounter() + 1);
          System.out.println("Counter wurde geändert: "+ s.getCounter());
        oos.writeObject(s);
        oos.flush();
        oos.close();
        fos.close();

        }
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }


  }

}

