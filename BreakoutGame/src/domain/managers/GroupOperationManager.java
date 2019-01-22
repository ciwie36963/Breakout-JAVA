


package domain.managers;

import domain.GroupOperation;
import domain.OperationCategory;
import domain.managers.Manager;
import java.util.List;
import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class GroupOperationManager extends Manager<GroupOperation>
{

    protected GroupOperationManager()
    {
         super(GroupOperation.class, new PersistenceController());
      //  setItems(FXCollections.observableList(persistence.getAllOfType(Exercise.class)));
    }
    

    public GroupOperationManager(PersistenceController persistence)
    {
         super(GroupOperation.class, persistence);
        setItems(FXCollections.observableList(persistence.getAllOfType(GroupOperation.class)));
    }
    
    @Override
    public void save(GroupOperation object)
    {
          getPersistenceController().setPersistMode(getManagerMode());
        ((GroupOperation) getSelected()).copy(object);
        super.save(object);
    }
    private boolean returnvalue;
     public void changeFilter(List<GroupOperation> categories) {
         getFilteredItems().setPredicate(e -> !categories.contains(e));
         System.out.println("");
     }
    
}
