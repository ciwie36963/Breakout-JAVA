/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.groupOperationBehaviours.AnwserBehaviour;
import domain.groupOperationBehaviours.AnwserBehaviourFactory;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;


/**
 *
 * @author Matthias
 */
@Entity
public class Assignment implements Serializable, Comparable<Assignment>{
    
    private int referenceNr;
    private Exercise exercise;
    private GroupOperation groupOperation;
    private int accessCode;
    
    @Transient
    private AnwserBehaviour anwserBehaviour;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Assignment()
    {
    }
    public Assignment(int referenceNr, Exercise exercise, GroupOperation groupOperation, int accessCode)
    {
        this.referenceNr = referenceNr;
        this.groupOperation = groupOperation;
        this.exercise = exercise;
        this.accessCode = accessCode;
     
        anwserBehaviour = AnwserBehaviourFactory.createAnwserBehaviour(groupOperation.getCategory());
    }
    
    public String getAnwser() {
        if(anwserBehaviour == null) {
            anwserBehaviour = AnwserBehaviourFactory.createAnwserBehaviour(groupOperation.getCategory());
        }
        return anwserBehaviour.getAnwser(exercise.getAnswer(), groupOperation.getValueString());
    }

    public int getReferenceNr()
    {
        return referenceNr;
    }

    public void setReferenceNr(int referenceNr)
    {
        this.referenceNr = referenceNr;
    }

    public Exercise getExercise()
    {
        return exercise;
    }

    public void setExercise(Exercise exercise)
    {
        this.exercise = exercise;
    }

    public GroupOperation getGroupOperation()
    {
        return groupOperation;
    }

    public void setGroupOperation(GroupOperation groupOperation)
    {
        this.groupOperation = groupOperation;
    }

    public int getAccessCode()
    {
        return accessCode;
    }

    public void setAccessCode(int accessCode)
    {
        this.accessCode = accessCode;
    }

    public AnwserBehaviour getAnwserBehaviour()
    {
        return anwserBehaviour;
    }

    public void setAnwserBehaviour(AnwserBehaviour anwserBehaviour)
    {
        this.anwserBehaviour = anwserBehaviour;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public int compareTo(Assignment o)
    {
      return Integer.compare(this.getReferenceNr(), o.getReferenceNr());
    }
    
    
    
}
