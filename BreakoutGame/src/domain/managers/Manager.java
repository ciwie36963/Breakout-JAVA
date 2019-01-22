


package domain.managers;

import domain.Exercise;
import domain.PersistMode;
import domain.Subject;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceController;
import java.lang.Class;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import javafx.collections.transformation.FilteredList;

public abstract class Manager<T extends domain.managers.IManageable> implements Subject
{
    /**
     * @param selected
     */
    private T selected;
    
    private final Class<T> type;
    
    private Set<Observer> observers;
    
    /**
    * @param items
    */
    private ObservableList<T> items;
    private FilteredList<T> itemsFiltered;
    private PersistenceController persistenceController;
    
    private PersistMode mode;
    
    protected Manager(Class<T> type, PersistenceController persistenceController)
    {
      //  setItems(FXCollections.observableArrayList());
     
        this.persistenceController = persistenceController;
        this.type = type;
        observers = new HashSet<>();
    }
    /**
     * @return an unmodifiable copy of {@code items}
     */
    public ObservableList<T> getItems()
    {
        return items;
    }
    
    public FilteredList<T> getFilteredItems() {
        return itemsFiltered;
    }
    /**
     * @return the selected item
     */
    public <T> T getSelected()
    {
        return (T) selected;
    }
    public PersistenceController getPersistenceController() {
        return persistenceController;
    }
    public void setItems(ObservableList newItems)
    {
        items = newItems;
        itemsFiltered = new FilteredList<>(items, p -> true);
        
    }
    
    public void setSelected(T item)
    {
        selected = item;
        notifyObservers(selected);
    }
    public void save(T object) {
         getPersistenceController().persistObject(type, getSelected());
        if(getPersistenceController().getPersistMode() == PersistMode.NEW )
            getItems().add(getSelected());
    }
    public void delete() throws java.lang.reflect.InvocationTargetException {
              persistenceController.deleteObject(type, selected);
              items.remove(selected);
    }

    @Override
    public void addObserver(Observer obs)
    {
        observers.add(obs);
    }

    @Override
    public void removeObserver(Observer obs)
    {
        observers.remove(obs);  
    }
    
    public void notifyObservers(T object) {
        observers.forEach(e -> e.update(null, object));
    }
    
    public PersistMode getManagerMode() {
        return mode;
    }
    public void setManagerMode(PersistMode mode) {
        this.mode = mode;
    }
}
