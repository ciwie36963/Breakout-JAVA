/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.IManageable;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Matthias
 */
@Entity
@Table(name="GroupOperation")
public class GroupOperation implements IManageable {
    private int id;
    private OperationCategory category;
    private String valueString;

    private SimpleStringProperty description = new SimpleStringProperty();
    
    //Constructors
    /**
     * Standard constructor
     * Used for JPA
     */
    public GroupOperation() {
        
    }

    public GroupOperation(GroupOperation groupOperation) {
        copy(groupOperation);
    }
   
    public GroupOperation(OperationCategory category, String value)
    {
        this.category = category;
        setValueString(value);
    }
    
    public void copy(GroupOperation groupOperation) {
        setCategory(groupOperation.getCategory());
        setValueString(groupOperation.getValueString());
    }
    //Getters and setters
    @Column(name="category")
    public OperationCategory getCategory()
    {
        return category;
    }

    public void setCategory(OperationCategory category)
    {
        this.category = category;
    //    description.set(getDescription());
    }
    
    @Column(name="valuestring")
    public String getValueString()
    {
        return valueString;
    }
    @Transient
    public String getDescription() {
        return String.format(category.getDescription(), valueString.split("&"));
    }
    public void setValueString(String valueString)
    {
        if(valueString == null || valueString.isEmpty())
            throw new IllegalArgumentException("Veld is leeg");
        this.valueString = valueString;
        description.set(getDescription());
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    
    public StringProperty descriptionProperty() {
        return description;
    }
    
    
    
     @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.category);
        hash = 23 * hash + Objects.hashCode(this.valueString);
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
        final GroupOperation other = (GroupOperation) obj;
        if (!Objects.equals(this.valueString, other.valueString))
        {
            return false;
        }
        if (this.category != other.category)
        {
            return false;
        }
        return true;
    }
    
    
}
