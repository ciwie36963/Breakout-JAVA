/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import domain.managers.CategoryManager;
import domain.managers.GroupOperationManager;
import domain.managers.ExerciseManager;
import domain.managers.GoalManager;
import domain.managers.IManageable;
import domain.managers.Manager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import persistence.PersistenceController;

/**
 *
 * @author geers
 */
public class ExerciseDomainController implements ExerciseFilter{

    private PersistenceController persistenceController;
    private GroupOperationManager groupOperationManager;
    private ExerciseManager exerciseManager;
    private GoalManager goalManager;
    private CategoryManager categoryManager;

    private Map<String, Manager> managers;
    private Map<String, ManagerFilter> filters;

    private Exercise exercise;

    public ExerciseDomainController(PersistenceController pc)
    {
        persistenceController = pc;
        groupOperationManager = new GroupOperationManager(persistenceController);
        exerciseManager = new ExerciseManager(persistenceController);
        goalManager = new GoalManager(persistenceController);
        categoryManager = new CategoryManager(persistenceController);
        
        managers = new HashMap<>();

        managers.put(Exercise.class.getSimpleName(), exerciseManager);
        managers.put(GroupOperation.class.getSimpleName(), groupOperationManager);
        managers.put(Goal.class.getSimpleName(), goalManager);
        managers.put(Category.class.getSimpleName(), categoryManager);
        
        filters = new HashMap<>();

        filters.put(GroupOperation.class.getSimpleName(), () -> groupOperationManager.changeFilter(exerciseManager.getGroupOperationsTemp()));
        filters.put(Goal.class.getSimpleName(), () -> goalManager.changeFilter(exerciseManager.getGoalsTemp()));
    }

    public FilteredList getFilteredItems(Class<? extends IManageable> className)
    {
        return managers.get(className.getSimpleName()).getFilteredItems();
    }

    public void setSelectedItem(IManageable item)
    {
        managers.get(item.getClass().getSimpleName()).setSelected(item);
    }
    
    public <T extends IManageable> T getSelectedItem(Class<T> className)
    {
        return (T) managers.get(className.getSimpleName()).getSelected();
    }
    
    //  public void 
    public ObservableList<GroupOperation> getGroupOperationsTemp()
    {
        return exerciseManager.getGroupOperationsTemp();
    }

    public ObservableList<Goal> getGoalsTemp()
    {
        return exerciseManager.getGoalsTemp();
    }

 

    public void changeFilterGroupOperations(List<GroupOperation> groupOperations)
    {
        groupOperationManager.changeFilter(groupOperations);
    }

    public void changeFilterGoal(List<Goal> goals)
    {
        goalManager.changeFilter(goals);
    }

    public void saveExercise(Exercise exercise)
    {
        exerciseManager.save(exercise);
    }

    public void saveExercise(String name, String answer, String feedback, String assignment, int categoryId, int time)
    {   
        try {
        exerciseManager.save(new Exercise(name, answer, feedback, assignment, exerciseManager.getCategory(categoryId), exerciseManager.getGroupOperationsTemp(), exerciseManager.getGoalsTemp(), time));
        } catch(IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        } catch(Exception ex) {
            throw new IllegalArgumentException("Deze bestaat al");
        } 
    }

    public void saveGroupOperation(OperationCategory cat, List<String> valueString)
    {
        try {
            groupOperationManager.save(new GroupOperation(cat, valueString.stream().collect(Collectors.joining("&"))));
        } catch(IllegalArgumentException ex) {
            throw new IllegalArgumentException("Een of meerdere velden zijn leeg");
        } catch(Exception ex) {
            throw new IllegalArgumentException("Deze bestaat al");
        } 
      
    }
    public void saveCategory(String name)
    {
        try
        {
            categoryManager.save(new Category(name));
        } catch (IllegalArgumentException ex)
        {
            throw new IllegalArgumentException("Een of meerdere velden zijn leeg");
        } catch (Exception ex)
        {
            throw new IllegalArgumentException("Deze bestaat al");
        }

    }

    public void saveGoal(String name)
    {

        try
        {
            goalManager.save(new Goal(name));
        } catch (IllegalArgumentException ex)
        {
            throw new IllegalArgumentException("Een of meerdere velden zijn leeg");
        } catch (Exception ex)
        {
            throw new IllegalArgumentException("Deze bestaat al");
        }
    }

    public void deleteExercise()
    {
        try {
               exerciseManager.delete();
        } catch (IllegalArgumentException ex){
            throw new IllegalArgumentException(ex.getMessage());
        }catch(Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            throw new IllegalArgumentException("Er is een fout opgetreden: deze fout komt meestal voor omdat de oefening zich binnen een box bevind");
        }
     
    }

    public void deleteGroupOperation()
    {
        try
        {
            groupOperationManager.delete();
        } catch (Exception ex)
        {
            throw new IllegalArgumentException("Er is een fout opgetreden: deze fout komt meestal voor omdat de groepsbewerking zich binnen een oefening bevind");
        }
    }

    public void deleteGoal() {
        try
        {
            goalManager.delete();
        } catch (Exception ex)
        {
            throw new IllegalArgumentException("Er is een fout opgetreden: deze fout komt meestal voor omdat de doelstelling zich binnen een oefening bevind");
        }
    }
    public void deleteCategory() {
        try
        {
            categoryManager.delete();
        } catch (Exception ex)
        {
           throw new IllegalArgumentException("Er is een fout opgetreden: deze fout komt meestal voor omdat het vak zich binnen een oefening bevind");
        }
    }
    public void deleteExercise(Exercise exercise)
    {
        //  exerciseManager.delete(exercise);
    }
    

    public void setManagerMode(Class<? extends IManageable> className, PersistMode persistMode) {
          System.out.println(className.getSimpleName());
       managers.get(className.getSimpleName()).setManagerMode(persistMode);
    }

    public void setManagerModeGroupOp(PersistMode persistMode)
    {
        groupOperationManager.setManagerMode(persistMode);
    }

    public void addObserver(Class<? extends IManageable> className, Observer obs) {
          managers.get(className.getSimpleName()).addObserver(obs);
    }
    public void addObserverExercise(Observer obs)
    {
        exerciseManager.addObserver(obs);
    }

    public void addObserverGroupOperation(Observer obs)
    {
        groupOperationManager.addObserver(obs);
    }

    public void addObserverGoal(Observer goal)
    {
        goalManager.addObserver(goal);
    }

    public void removeObserverExercise(Observer obs)
    {
        exerciseManager.removeObserver(obs);
    }

    public void removeObserverGroupOperation(Observer obs)
    {
        groupOperationManager.removeObserver(obs);
    }

    public <T extends IManageable> void addToTempList(List<T> obj)
    {
        if (obj.size() > 0)
        {
            exerciseManager.addObjectToTemp(obj);
            ManagerFilter filter = filters.get(obj.get(0).getClass().getSimpleName());
            if (filter != null)
            {
                filter.applyFilter();
            }
        }
    }

    public <T extends IManageable> void removeFromTempList(List<T> obj)
    {
        //Need to retrieve an object because of type erasure
        if (obj.size() > 0)
        {
            System.out.println(obj.size());
            String test = obj.get(0).getClass().getSimpleName();
            exerciseManager.removeObjectFromTemp(obj);
            ManagerFilter filter = filters.get(test);
            if (filter != null)
            {
                filter.applyFilter();
            }
        }
    }
    public void applyFilters() {
        exerciseManager.changeFilter(new ArrayList<>());
    }
    
        public void addCategoryToFilter(Category cat) {
        exerciseManager.addCategoryToFilter(cat);
          exerciseManager.changeFilter(new ArrayList<>());
        
    
    }
        public void removeCategoryToFilter(Category cat) {
        exerciseManager.removeCategoryToFilter(cat);
          exerciseManager.changeFilter(new ArrayList<>());
    }
         public void changeGoalFilter(List<String> goals) {
        exerciseManager.changeGoalFilter(goals);
          exerciseManager.changeFilter(new ArrayList<>());
    }

    @Override
    public ObservableList<Category> getClasses()
    {
        return categoryManager.getFilteredItems();
    }

}
