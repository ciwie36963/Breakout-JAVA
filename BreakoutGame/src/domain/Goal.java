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
 * @author Matthias
 */
@Entity
public class Goal implements Serializable, IManageable, Comparable<Object> {

    private int id;

    private SimpleStringProperty code = new SimpleStringProperty();
    public Goal()
    {
    }

    public Goal(String code)
    {
        setCode(code);
    }

    public Goal(Goal goal) {
        copy(goal);
    }
    public void copy(Goal goal) {
        setCode(goal.getCode());
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

    @Column(name = "code")
    public String getCode()
    {
        return this.code.get();
    }

    public void setCode(String code)
    {
        if(code == null || code.trim().isEmpty()) 
            throw new IllegalArgumentException("Code moet ingevuld zijn");
        this.code.set(code);
    }
    
    public StringProperty code() {
        return code;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.code);
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
        final Goal other = (Goal) obj;
        if (!Objects.equals(this.getCode(), other.getCode()))
        {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object o)
    {
        Goal that = (Goal) o;
        return this.getCode().compareTo(that.getCode());
    }

    
    
    
}
