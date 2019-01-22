/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.managers;

import domain.Category;
import domain.Goal;
import domain.BoBGroup;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import persistence.PersistenceController;

/**
 *
 * @author Matthias
 */
public class GoalManager extends Manager<Goal>{
    
   
    protected GoalManager()
    {
          super(Goal.class, new PersistenceController());
    }
    
    public GoalManager(PersistenceController persistence)
    {
        super(Goal.class, persistence);
        setItems(FXCollections.observableArrayList(persistence.getAllOfType(Goal.class)));
    }

    @Override
    public void save(Goal object)
    {
         getPersistenceController().setPersistMode(getManagerMode());
       ((Goal) getSelected() ).copy(object);
        super.save(object); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void changeFilter(List<Goal> goals) {
         getFilteredItems().setPredicate(e -> !goals.contains(e));
    }
}
