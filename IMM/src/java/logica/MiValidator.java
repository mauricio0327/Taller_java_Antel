/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Salvador
 */
@FacesValidator(value="miValidator")
public class MiValidator implements  Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        String fecha = (String) value;
        if (fecha.matches("[0-3][0-9]/[0-1][0-9]/[0-2][0-9][0-9][0-9]")) {
            System.out.println("Formato fecha invalida");
            throw new ValidatorException(new FacesMessage("Formato fecha invalido"));
                
            }
        
    }
    
}