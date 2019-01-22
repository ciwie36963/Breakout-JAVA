/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.IManageable;
import java.io.Serializable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Matthias
 */
@Entity
public class AccessCode implements Serializable, IManageable {

   
    
    
    
    private int id;
    
    private SimpleIntegerProperty code = new SimpleIntegerProperty();
    

     public AccessCode()
    {
    }
     
   
    public AccessCode(int code)
    {
        setCode(code);
    }
    
    public void copy(AccessCode accessCode) {
        setCode(accessCode.getCode());
    }
    
     @Column(name="code")
    public int getCode()
    {
        return code.get();
    }

    public void setCode(int code)
    {
        this.code.set(code);
    }

     
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
    public IntegerProperty codeProperty() {
        return code;
    }
    
}
