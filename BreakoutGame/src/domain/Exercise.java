/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.IManageable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.eclipse.persistence.annotations.ChangeTracking;

/**
 *
 * @author geers
 */
@Entity
@Table(name = "Exercises")
public class Exercise implements IManageable, Serializable {
    
    private int id;
    //   @Column(unique = true)
    private SimpleStringProperty name = new SimpleStringProperty();
    private String answer;
    private String feedback;
    private SimpleStringProperty assignment = new SimpleStringProperty();
    private SimpleObjectProperty<Category> category = new SimpleObjectProperty<>();
    private SimpleIntegerProperty timeInMinutes = new SimpleIntegerProperty(0);
    private List<GroupOperation> groupOperations;
    private Set<Goal> goals;
    /*   @Transient
    private ObservableList<GroupOperation> groupOperationsTemp;*/
    //  GroupOperation groupOperation;
    public Exercise(String name, String answer, String feedback, String assignment, Category category)
    {
        this(name, answer, feedback, assignment, category, new ArrayList<>());
    }
    
    public Exercise(String name, String answer, String feedback, String assignment, Category category, List<GroupOperation> operations)
    {
        setName(name);
        setAnswer(answer);
        setFeedback(feedback);
        setAssignment(assignment);
        setCategory(category);
        setTimeInMinutes(0);
        groupOperations = new ArrayList<>(operations);
        //     groupOperationsTemp = FXCollections.observableArrayList(groupOperations);
    }
    
    //NEW GOALS TODO
    public Exercise(String name, String answer, String feedback, String assignment, Category category, List<GroupOperation> operations, Collection<Goal> goals, int timeInMinutes)
    {
        setName(name);
        setAnswer(answer);
        setFeedback(feedback);
        setAssignment(assignment);
        setCategory(category);
        setTimeInMinutes(timeInMinutes);
        groupOperations = new ArrayList<>(operations);
        this.goals = new HashSet<>(goals);
    }

    //Creating / copying
    public void copy(Exercise exercise)
    {
        setName(exercise.getName());
        setAnswer(exercise.getAnswer());
        setFeedback(exercise.getFeedback());
        setAssignment(exercise.getAssignment());
        setCategory(exercise.getCategory());
        setTimeInMinutes(exercise.getTimeInMinutes());
        groupOperations = new ArrayList<>(exercise.getGroupOperations());
        goals = new HashSet<>(exercise.getGoals());
        // groupOperationsTemp = FXCollections.observableArrayList(groupOperations);
    }
    
    public Exercise()
    {
        category.set(new Category("STANDARD"));
        groupOperations = new ArrayList<>();
        goals = new HashSet<>();
    }
    
    public Exercise(Exercise ex)
    {
        copy(ex);
    }
    
    public boolean hasFeedback()
    {
        return !(feedback == null) && !(feedback.isEmpty());
    }

    @Column(name = "name", unique = true)
    public String getName()
    {
        return name.get();
    }
    
    @Column(name = "answer")
    public String getAnswer()
    {
        return answer;
    }

    @Column(name = "feedback")
    public String getFeedback()
    {
        return feedback;
    }

    @Column(name = "assignment")
    public String getAssignment()
    {
        return assignment.get();
    }

    @ManyToOne
    @JoinColumn(name = "category")
    public Category getCategory()
    {
        return category.get();
    }

    @ManyToMany
    @JoinColumn(name = "groupoperations")
    public List<GroupOperation> getGroupOperations()
    {
        return groupOperations;
    }
    
    public void setGroupOperations(List<GroupOperation> groupOperations)
    {
        this.groupOperations = groupOperations;
        
    }

    public void setName(String name)
    {
        if (name == null || name.trim().equals(""))
        {
            throw new IllegalArgumentException();
        } else
        {
            this.name.set(name);
        }
        
    }
    
    public void setAnswer(String answer)
    {
        if (answer == null || answer.trim().equals(""))
        {
            throw new IllegalArgumentException();
        } else
        {
            this.answer = answer.trim();
        }
    }
    
    public void setFeedback(String feedback)
    {
        if (feedback != null && !feedback.trim().equals(""))
        {
            this.feedback = feedback.trim();
        } else
        {
           throw new IllegalArgumentException("Feedback moet ingevuld zijn");
        }
    }
    
    public void setAssignment(String assignment)
    {
        if (assignment == null || assignment.trim().equals(""))
        {
            throw new IllegalArgumentException();
        } else
        {
            this.assignment.set(assignment.trim());
        }
    }
    
    public void setCategory(Category category)
    {
        if (category == null)
        {
            throw new IllegalArgumentException();
        } else
        {
            this.category.set(category);
        }
    }
    
    @ManyToMany
    @JoinColumn(name = "exercisegoals")
    public Set<Goal> getGoals()
    {
        return goals;
    }

    public void setGoals(Set<Goal> goals)
    {
        this.goals = goals;
    }
    
    
    public String getCategoryDescription()
    {
        return category.get().getDescription();
    }

    @Column(name = "timeInMinutes")
    public int getTimeInMinutes()
    {
        return timeInMinutes.get();
    }

    public void setTimeInMinutes(int timeInMinutes)
    {
        if(timeInMinutes < 0) {
            throw new IllegalArgumentException("Tijd mag niet kleiner zijn dan null");
        }
        this.timeInMinutes.set(timeInMinutes);
    }

  
    
    
    @Override
    public String toString()
    {
        return String.format("%s", this.name.get());
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
        System.out.println(id);
        this.id = id;
    }
    
    public StringProperty assignmentProperty()
    {
        return assignment;
    }
    
    public ObjectProperty categoryProperty()
    {
        return category;
    }
    
    public IntegerProperty timeInMinutesProperty() {
        return timeInMinutes;
    }
    public StringProperty nameProperty() {
        return name;
    }
    
}
