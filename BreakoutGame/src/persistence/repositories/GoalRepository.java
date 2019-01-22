/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.repositories;

import domain.Goal;

/**
 *
 * @author Matthias
 */
public class GoalRepository extends GenericRepository<Goal>{
    
    public GoalRepository(Class<Goal> type)
    {
        super(type);
    }
       public GoalRepository()
    {
        super(Goal.class);
    }
    
}
