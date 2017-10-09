/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;


import logica.ITerminales;
import logica.IAdminAgencia;
/**
 *
 * @author e945952
 */
public class Fabrica {
  private static Fabrica instance = null;

  private Fabrica() {
  };

  public static Fabrica getInstance() {

    if (instance == null)
      instance = new Fabrica();
    return instance;
  }

  public IAdminAgencia getiControladorAgencia() {
    return ControladorAgencia.getInstancia();
  }

  public ITerminales getiControladorTerminales() {
    return ControladorTerminales.getInstancia();

  }
}