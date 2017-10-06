 
package logica;

/**
 *
 * @author e945952
 */
public class Fabrica {
  private static Fabrica instance = null;

  private Fabrica() {
  };

  public static Fabrica getInstance() {
    /*
     * if (instance!=null) return instance; else{ instance= new Fabrica();
     * return instance; }
     */
    if (instance == null)
      instance = new Fabrica();
    return instance;
  }

  public IAdminIMM getControladorIMM() {
    return ControladorIMM.getInstancia();
  }
    /*
     * hay que el getinstance de controlador espectaculo a operaci�n de clase
     */
  }

