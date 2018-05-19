import java.io.Serializable;

public class SDS implements Serializable {

    private int position;
    private String category;
    private int counter;


    public SDS(int position, String category, int counter){

        this.position = position;
        this.category = category;
        this.counter = counter;


    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
}
