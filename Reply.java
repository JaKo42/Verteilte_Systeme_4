import java.io.Serializable;

public class Reply implements Serializable {

  SDS[] sds = new SDS[3];


  //Class die ein Reply objekt an den ClientInfo zurück gibt
  //muss den Status der aktuellen Umfrage kennen


  public Reply( SDS[] sds) {

    System.arraycopy(sds, 0, this.sds, 0, sds.length);

  }


  public Reply() {
  }


}
