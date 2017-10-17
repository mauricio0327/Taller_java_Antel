/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Salvador
 */
@ManagedBean
@RequestScoped
public class LoginBeans {

    /**
     * Creates a new instance of LoginBeans
     */
    public LoginBeans() {
    }
    
    private String user;
    private String pass;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public String logeo() throws NamingException, SQLException{
        String mensaje="";
        DtUsuario usuario = new DtUsuario();
        try {
            InitialContext initContext = new InitialContext();
            DataSource ds;
            ds = (DataSource) initContext.lookup("java:jboss/datasources/MySqlDS");
            
            Connection conn = ds.getConnection(); 
            PreparedStatement ps = conn.prepareStatement("select * from userimm"); 
            ResultSet rs = ps.executeQuery();
            String a2 = "";
            boolean userbool = false;
            while ((rs.next())&&(!userbool)) {
                if (rs.getString("user").equals(user)){
                    userbool=true;
                    a2=rs.getString("pass");
                }    
            }
            
                if (pass.equals(a2)){
                    mensaje = "Logeo existoso";
                    usuario.setApellido(rs.getString("apellido"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setPass(rs.getString("pass"));
                    usuario.setUserName(rs.getString("user"));
                } else {
                    if (userbool){
                        mensaje = "contrasena invalida";
                    } else {
                        mensaje = "usuario inexistente";
                    }
                }
        ps.close();
        conn.close();
        FacesContext.getCurrentInstance().addMessage("idMessaDge", new FacesMessage(mensaje));
        } catch (NamingException ex) {
            Logger.getLogger(ControladorIMM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorIMM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mensaje;
    }
    
}
