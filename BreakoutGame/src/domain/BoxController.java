/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.AccessCodeManager;
import domain.managers.ActionManager;
import domain.managers.BoxManager;
import domain.managers.CategoryManager;
import domain.managers.ExerciseManager;
import domain.managers.IManageable;
import domain.managers.Manager;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.transformation.FilteredList;
import persistence.PersistenceController;

/**
 *
 * @author Matthias
 */
public class BoxController implements ExerciseFilter{
    
    private PersistenceController persistenceController;
    private BoxManager boxManager;
    private ExerciseManager exerciseManager;
    private ActionManager actionManager;
   private AccessCodeManager accessCodeManager;
   private CategoryManager categoryManager; 
   
    private Map<String, Manager> managers;
    private Map<String, ManagerFilter> filters;

    public BoxController(PersistenceController pc)
    {
        persistenceController = pc;
        boxManager= new BoxManager(persistenceController);
        exerciseManager = new ExerciseManager(persistenceController);
       actionManager = new ActionManager(persistenceController);
        accessCodeManager = new AccessCodeManager(persistenceController);
        categoryManager = new CategoryManager(persistenceController);
        
        filters = new HashMap<>();
        
        filters.put(Exercise.class.getSimpleName(), () -> exerciseManager.changeFilter(boxManager.getExerciseTemp()));
        filters.put(BoBAction.class.getSimpleName(), () ->  actionManager.changeFilter(boxManager.getActionsTemp()));
     //   filters.put(AccessCode.class.getSimpleName(), () -> accessCodeManager.changeFilter(boxManager.getAccessCodesTemp()));
        
        managers = new HashMap<>();
        
        managers.put(Box.class.getSimpleName(), boxManager);
        managers.put(Exercise.class.getSimpleName(), exerciseManager);
        managers.put(BoBAction.class.getSimpleName(), actionManager);
        managers.put(AccessCode.class.getSimpleName(), accessCodeManager);
        managers.put(Category.class.getSimpleName(), categoryManager);
    }
    
    
   /* public FilteredList getBoxes() {
        return boxManager.getFilteredItems();
    }
    
    public FilteredList getExercises() {
        return exerciseManager.getFilteredItems();
    }
    
    public FilteredList getActions() {
        return actionManager.getFilteredItems();
    }
    
    public FilteredList getAccessCodes() {
        return accessCodeManager.getFilteredItems();
    }*/
    
    public FilteredList getFilteredItems(Class<? extends IManageable> className) {
        return managers.get(className.getSimpleName()).getFilteredItems();
    }
    
   /* public void setSelectedBox(Box box) {
        boxManager.setSelected(box);
        applyFilters();
    }
    
    public void setSelectedAccessCode(AccessCode code) {
        accessCodeManager.setSelected(code);
    }*/
    
    public void addObserverBox(Observer obs) {
        boxManager.addObserver(obs);
    }
    
    public void addObserverAction(Observer obs) {
        actionManager.addObserver(obs);
    }
    
    public void setSelectedItem(IManageable obj) {
        managers.get(obj.getClass().getSimpleName()).setSelected(obj);
        if(obj.getClass().getSimpleName().equals(Box.class.getSimpleName()))
            applyFilters();
    }
    
      public <T extends IManageable> T getSelectedItem(Class<T> className)
    {
        return (T) managers.get(className.getSimpleName()).getSelected();
    }
    
 /*   public void addObserver(String className, Observer object) {
        managers.get(className).addObserver(object);
    }*/
  /*  public ObservableList<AccessCode> getTempListAccessCodes() {
        return boxManager.getAccessCodesTemp();
    }*/
     public ObservableList<Exercise> getTempListExercises() {
        return boxManager.getExerciseTemp();
    }
      public ObservableList<BoBAction> getTempListBoBActions() {
        return  boxManager.getActionsTemp();
    }
    
      public ObservableList<Goal> getGoals() {
          return exerciseManager.getGoals();
      }
    public ObservableList<Goal> getListGoal() {
        return boxManager.getGoals();
    }
      
    public void applyFilters() {
     //   ManagerFilter test = () -> exerciseManager.changeFilter(boxManager.getExerciseTemp());
       // exerciseManager.changeFilter(boxManager.getExerciseTemp());
    //    actionManager.changeFilter(boxManager.getActionsTemp());
      //  accessCodeManager.changeFilter(boxManager.getAccessCodesTemp());
        filters.entrySet().forEach(e -> e.getValue().applyFilter());
    }
    
    public void changeGoalFilter(List<String> goals) {
        exerciseManager.changeGoalFilter(goals);
        exerciseManager.changeFilter(boxManager.getExerciseTemp());
    }
    
    public void saveBox(String name, String description) {
        try
        {
              Box box = new Box(description, name, new HashSet<>(boxManager.getExerciseTemp()), boxManager.getActionsTemp());
              boxManager.save(box);
        } catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception ex) {
            throw new IllegalArgumentException("Box met deze naam bestaat al");
        }
            
      
      
    }
    
    public void saveAction(String name) {
        try
        {
            BoBAction action = new BoBAction(name);
            actionManager.save(action);
            
        } catch (IllegalArgumentException e) {
              throw new IllegalArgumentException("Je hebt het veld leeggelaten");
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Deze actie bestaat al");
        }
    }
      public void setManagerMode(Class<? extends IManageable> className, PersistMode persistMode) {
          System.out.println(className.getSimpleName());
       managers.get(className.getSimpleName()).setManagerMode(persistMode);
    }
    
      public <T extends  IManageable> void addToTempList(List<T> obj) {
        if (obj.size() > 0)
        {
            boxManager.addObjectToTemp(obj);
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
            String test = obj.get(0).getClass().getSimpleName();
            boxManager.removeObjectFromTemp(obj);
            ManagerFilter filter = filters.get(test);
            if (filter != null)
            {
                filter.applyFilter();
            }
        }
      }
    
    public void removeBox() {
        try
        {
            boxManager.delete();
        } catch (Exception ex)
        {
            throw new IllegalArgumentException("Je kan deze box niet verwijderen omdat deze zich nog in een sessie bevind");
        }
    }
    public void removeAction() {
        try
        {
            actionManager.delete();
        } catch (Exception ex)
        {
           throw new IllegalArgumentException("Je kan deze actie niet verwijderen omdat deze nog in een box zit");
        }
    }
    
    public void changeBoxFilter(String filter) {
        boxManager.changeBoxNameFilter(filter);
    }
    public void applyExerciseFilter(Category cat) {
       
    }
    public void addCategoryToFilter(Category cat) {
        exerciseManager.addCategoryToFilter(cat);
         exerciseManager.changeFilter(boxManager.getExerciseTemp());
    }
        public void removeCategoryToFilter(Category cat) {
        exerciseManager.removeCategoryToFilter(cat);
         exerciseManager.changeFilter(boxManager.getExerciseTemp());
    }

    @Override
    public ObservableList<Category> getClasses()
    {
        return categoryManager.getFilteredItems();
    }
 
    public BoBAction getStartAction() {
        return boxManager.getStartAction();
    }
}
