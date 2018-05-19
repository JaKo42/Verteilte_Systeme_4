import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class QueryInit {

    public QueryInit() {

        init();
    }


    private void init() {


        SDS ja = new SDS("ja", 0),
                nein = new SDS("nein", 0),
                enthaltung = new SDS("enthaltung", 0);

        String filename = "umfragedatei.txt";

        FileOutputStream fos;

        {
            try {
                fos = new FileOutputStream(filename);
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                oos.writeObject(nein);
                oos.writeObject(ja);
                oos.writeObject(enthaltung);

                oos.flush();
                oos.close();
                fos.close();
                System.out.println("Umfragedatei vorbereitet!");


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
