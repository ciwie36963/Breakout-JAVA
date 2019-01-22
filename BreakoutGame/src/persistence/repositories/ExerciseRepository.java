/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.repositories;

import domain.Box;
import domain.Exercise;
import javax.persistence.TypedQuery;

/**
 *
 * @author Matthias
 */
public class ExerciseRepository extends GenericRepository<Exercise>{
    
    // <editor-fold defaultstate="collapsed" desc="Properties">

    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">

    private ExerciseRepository(Class<Exercise> type)
    {
        super(type);
    }
    
    
    
    // </editor-fold>

    public ExerciseRepository()
    {
        super(Exercise.class);
    }
     public boolean exInBox(Exercise exercise) {
        TypedQuery<Integer> query =  getEntityManager().createNamedQuery("ExInBox", Integer.class);
     query.setParameter("id", exercise.getId());
        if(query.getResultList().isEmpty())
            return false;
        return true;
       
    }
}
