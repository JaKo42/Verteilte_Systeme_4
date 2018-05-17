import java.io.Serializable;

public class SDS implements Serializable {

    private String category;
    private int counter;


    public SDS(String category, int counter){
        this.category = category;
        this.counter = counter;

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
