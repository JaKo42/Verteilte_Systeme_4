import java.io.Serializable;

public class Reply implements Serializable {

    private boolean status =false;
    private String category = null;
    private int counter = 0;



    //Class die ein Reply objekt an den ClientInfo zurück gibt
    //muss den Status der aktuellen Umfrage kennen


    public Reply(Boolean status, String category, int counter) {
        this.status = status;

        this.category = category;
        this.counter = counter;

    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }




    public Reply() {}


}
