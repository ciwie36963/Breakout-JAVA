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
import util.LangConfig;

/**
 *
 * @author Alexander
 */
@Entity
public class Category implements Serializable, IManageable {
    
//  
//    MATH("MATH"),
//    GEOGRAPHY("GEOGRAPHY"),
//    DUTCH("DUTCH");
    private int id;

    private SimpleStringProperty name = new SimpleStringProperty();
    
    public Category() {
        
    }
    public Category(String name)    
    {
        setName(name);
    }
    
    public Category(Category category) {
        copy(category);
    }
    public void copy(Category category) {
        setName(category.getName());
    }

        @Column(name = "name", unique = true)
    public String getName()
    {
        return name.get();
    }
    
    
    public void setName(String name) {
        if(name != null && !name.trim().isEmpty()) {
            this.name.set(name);
        } else {
            throw new IllegalArgumentException();
        }
        
    }
    public String getDescription() {
        return name.get();
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.name);
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
        final Category other = (Category) obj;
        if (!Objects.equals(this.name.get(), other.name.get()))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return name.get();
    }
    
    public StringProperty nameProperty() {
        return name;
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
    
    
    
}
