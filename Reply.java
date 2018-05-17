import java.io.Serializable;

public class Reply <SDS> implements Serializable {


    private String category = null;
    private int counter = 0;



    //Class die ein Reply objekt an den Client zurück gibt
    //muss den Status der aktuellen Umfrage kennen


    public Reply(SDS[] sds) {


        this.category = category;
        this.counter = counter;

    }




    public Reply() {}


}
