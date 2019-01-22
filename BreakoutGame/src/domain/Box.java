/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.IManageable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderColumn;
import javax.persistence.Transient;

/**
 *
 * @author geers
 */
@NamedQueries({
    @NamedQuery(name = "BoxExists", query = "SELECT b FROM Box b WHERE b.name = :name")
    ,
 @NamedQuery(name = "BoxInSession", query = "SELECT DISTINCT s.box.id FROM Session s")
    ,
@NamedQuery(name = "ExInBox", query = "SELECT DISTINCT b.id FROM Session s JOIN S.box b join b.exercises ex WHERE :id  IN (SELECT ex2.id FROM b.exercises ex2) AND s.sessionStatus = domain.SessionStatus.ACTIVATED")})
@Entity
public class Box implements Serializable, IManageable {

    private int id;
    private SimpleStringProperty description = new SimpleStringProperty();
    private SimpleStringProperty name = new SimpleStringProperty();

    private Set<Exercise> exercises;

    // private List<AccessCode> accessCodes;
    private List<BoBAction> actions;

    public Box() {
        //Treasurechest
        actions = new ArrayList<>();
        //     BoBAction boBAction = new BoBAction("Zoek een kist");
        //   boBAction.setId(1);
        //    actions.add(boBAction);
        exercises = new HashSet<>();
        //     accessCodes = new ArrayList<>();
    }

    public Box(String description, String name, Set<Exercise> exercises, List<BoBAction> actions) {
        setDescription(description);
        setName(name);
        setExercises(new HashSet<>(exercises));
        // setAccessCodes(new ArrayList<>(accessCodes));
        setActions(new ArrayList<>(actions));
    }

    public void copy(Box box) {
        setDescription(box.getDescription());
        //Needed to update table..
        setName("RESET");
        setName(box.getName());
        setExercises(new HashSet<>(box.getExercises()));
        // setAccessCodes(new ArrayList<>(box.getAccessCodes()));
        setActions(new ArrayList<>(box.getActions()));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "description")
    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Omschrijving is leeg");
        }
        this.description.set(description);
    }

    @Column(name = "name", unique = true)
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Naam is leeg");
        }
        this.name.set(name);
    }

    @ManyToMany
    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return String.format("%s", getName());
    }


    /*   @ManyToMany
    public List<AccessCode> getAccessCodes()
    {
        return accessCodes;
    }*/

 /*  public void setAccessCodes(List<AccessCode> accessCodes)
    {
        this.accessCodes = accessCodes;
    }*/
    @ManyToMany
    @OrderColumn
    public List<BoBAction> getActions() {
        return actions;
    }

    public void setActions(List<BoBAction> actions) {
        this.actions = new ArrayList<>(actions);
    }

    public void addAction(BoBAction action) {
        if (actions.isEmpty()) {
            actions.add(0, action);
        } else {
            actions.add(actions.size() - 2, action);
        }

    }

    public void removeAction(BoBAction action) {
        //Check if equal to treasure (cant remove treasure = exception)
        actions.remove(action);
    }

    /* public void addAccessCode(AccessCode accessCode) {
        accessCodes.add(accessCode);
    }
    public void removeAccessCode(AccessCode accessCode) {
        accessCodes.remove(accessCode);
    }*/
    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public boolean isValidBox() {
        return getActions().size() == getExercises().size();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Box other = (Box) obj;
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        return true;
    }

}
