/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

/**
 *
 * @author Matthias
 */
@Entity(name = "SessionPath")
public class Path implements Serializable {
   
    private List<Assignment> assignments;
  
    
    private int id;

    public Path()
    {
    }

    public Path(List<Assignment> assignments)
    {
        if(assignments != null)
        this.assignments = new ArrayList<>(assignments);
    }

   
    /*public String getToStringTest() {
        StringBuilder path = new StringBuilder();
        assignments.forEach(e -> {
            path.append(e.getReferenceNr()).append(" ").append(e.getExercise().getAssignment()).append(" ").append(e.getGroupOperation().getDescription()).append(" ").append(e.getAnwser()).append(" ").append(actions.get(counter++).getName()).append(" ").append(e.getAccessCode() != 0 ? e.getAccessCode() : "Geen code nodig , laatste actie").append("\n");
            
        });
        return path.toString();
    }*/

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="PathId")
    public List<Assignment> getAssignments()
    {
        return this.assignments;
    }

    public void setAssignments(List<Assignment> assignments)
    {
     
        Collections.sort(assignments);
        this.assignments = new ArrayList<>(assignments);
    }
    
}
