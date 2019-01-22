


package domain.managers;

import domain.AccessCode;
import domain.BoBAction;
import domain.Box;
import java.util.List;
import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class ActionManager extends Manager<BoBAction>
{

    protected ActionManager()
    {
         super(BoBAction.class, new PersistenceController());
    }
    
    public ActionManager(PersistenceController persitence)
    {
           super(BoBAction.class, persitence);
        setItems(FXCollections.observableArrayList(persitence.getAllOfType(BoBAction.class)));
    }
    
     public void changeFilter(List<BoBAction> actions) {
         getFilteredItems().setPredicate(e -> !actions.contains(e));
         System.out.println("");
     }

    @Override
    public void save(BoBAction object)
    {
        getPersistenceController().setPersistMode(getManagerMode());
       ((BoBAction) getSelected() ).copy(object);
        super.save(object); //To change body of generated methods, choose Tools | Templates.
    }
     
     

}
