/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.IManageable;
import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author geers
 */
@Entity
public class BoBAction implements IManageable, Serializable {

    private SimpleStringProperty name = new SimpleStringProperty();
    public BoBAction()
    {
    }

    public BoBAction(String name)
    {
        setName(name);
    }
    
    
    public BoBAction(BoBAction action) {
        copy(action);
    }
    
    public void copy(BoBAction action) {
        setName(action.getName());
    }
    @Column(name="name", unique = true)
    public String getName()
    {
        return name.get();
    }

    public void setName(String name)
    {
        if(name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Mag niet leeg zijn");
        this.name.set(name);
    }
    
    
    
    
    
    private int id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public int getId()
    {
       return id;
    }

    @Override
    public void setId(int id)
    {
        this.id = id;
    }
    
    public StringProperty nameProperty() {
        return name;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.getName());
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final BoBAction other = (BoBAction) obj;
        if (!Objects.equals(this.getName(), other.getName()))
        {
            return false;
        }
        return true;
    }
    
}
