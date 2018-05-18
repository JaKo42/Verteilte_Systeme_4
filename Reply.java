import java.io.Serializable;

public class Reply implements Serializable {

  private boolean status = false;
  SDS[] sds = new SDS[3];


  //Class die ein Reply objekt an den ClientInfo zurück gibt
  //muss den Status der aktuellen Umfrage kennen


  public Reply(Boolean status, SDS[] sds) {
    this.status = status;
    System.arraycopy(sds, 0, this.sds, 0, sds.length);

  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }





  public Reply() {
  }


}
