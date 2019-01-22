/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.IManageable;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import util.LocalDateAttributeConverter;

/**
 *
 * @author geers
 */
@NamedQuery(name = "Remove", query = "DELETE FROM Session S WHERE s.id = :id")
@Entity
@Table(name = "BoBSession")
public class Session implements IManageable, Serializable {
    
    private int id;
       
    private Box box;
   
    private List<BoBGroup> groups;
    private SimpleStringProperty description = new SimpleStringProperty();

    private StudentClass classRoom;
    private SimpleStringProperty name = new SimpleStringProperty();
   

  
   private LocalDate activationDate;
    private boolean tile;
    private boolean feedback;

    private SessionStatus sessionStatus;
    
    
    
    public Session()
    {
        activationDate = LocalDate.now();
        groups = new ArrayList<>();
        setSessionStatus(SessionStatus.SCHEDULED);
    }

    public Session(Box box, List<BoBGroup> groups, String description, StudentClass classRoom, String name, LocalDate activationDate, boolean tile, boolean feedback)
    {
           setTile(tile);
        setFeedback(feedback);
      
        setBox(box);
        setGroups(groups);
        setDescription(description);
        setClassRoom(classRoom);
        setName(name);
       setActivationDate(activationDate);
      
        setSessionStatus(SessionStatus.SCHEDULED);
    
    }
    
    public Session(Session session) {
        copy(session);
    }
    
    public void copy(Session session) {
        setTile(session.isTile());
        setFeedback(session.isFeedback());
        setName(session.getName());
        setDescription(session.getDescription());
        setActivationDate(session.getActivationDate());
        
        //setSessionStatus(session.getSessionStatus());
        setClassRoom(session.getClassRoom());
        setBox(session.getBox());
        setGroups(session.getGroups());
    }
    
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

   
   @Convert(converter = LocalDateAttributeConverter.class)
    public LocalDate getActivationDate()
    {
        return activationDate;
    }

    public void setActivationDate(LocalDate activationDate)
    {
       /* if(!activationDate.isEqual(LocalDate.now()) && activationDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Datum ligt voor vandaag");
        }*/
        this.activationDate = activationDate;
    }

    public boolean isFeedback()
    {
        return feedback;
    }

    public void setFeedback(boolean feedback)
    {
        if(this.tile && !feedback)
            throw new IllegalArgumentException("Afstandsonderwijs kan alleen maar feedback krijgen");
        this.feedback = feedback;
    }

@ManyToOne
    public Box getBox()
    {
        return box;
    }

    public void setBox(Box box)
    {
        if(!tile && !box.isValidBox())
            throw new IllegalArgumentException("Als er niet met afstandsonderwijs wordt gewerkt moet het aantal acties in een box gelijk zijn aan het aantal oefeningen; \n\nAantal oefeningen: " + box.getExercises().size() + "\nAantal acties: " + box.getActions().size());
        this.box = box;
    }
    
    
@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
@JoinColumn(name="BoBSessionID")
    public List<BoBGroup> getGroups()
    {
        return groups;
    }

    public void setGroups(List<BoBGroup> groups)
    {  if(groups != null )
        this.groups = new ArrayList<>(groups);
    }

    public String getDescription()
    {
        return description.get();
    }

    public void setDescription(String description)
    {
          if(description == null || description.trim().isEmpty())
            throw new IllegalArgumentException("Omschrijving is leeg");
        this.description.set(description.trim());
    }

   
    public StudentClass getClassRoom()
    {
        return classRoom;
    }

    public void setClassRoom(StudentClass classRoom)
    {
        this.classRoom = classRoom;
    }

    @Column(name = "name", unique = true)
    public String getName()
    {
        return name.get();
    }

    public void setName(String name)
    {
        if(name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Naam is leeg");
        this.name.set(name.trim());
    }

    public boolean isTile()
    {
        return tile;
    }

    public void setTile(boolean tile)
    {
        this.tile = tile;
    }
    
    
    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }
    
    public void setSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
  /*  @Transient
   public void testToString() {
        groups.forEach(e -> e.testToString());
    }*/

    @Override
    public String toString() {
        return String.format("%s", getName());
    }
    
    
    
   
    public StringProperty nameProperty() {
        return name;
    }
    
    public StringProperty descriptionProperty() {
        return description;
    }

}
