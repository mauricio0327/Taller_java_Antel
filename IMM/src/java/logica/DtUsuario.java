/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author e945952
 */
public class DtUsuario {
    
    private String userName;
    private String pass;
    private String nombre;
    private String apellido;

    public DtUsuario(String userName, String pass, String nombre, String apellido) {
        this.userName = userName;
        this.pass = pass;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public DtUsuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
