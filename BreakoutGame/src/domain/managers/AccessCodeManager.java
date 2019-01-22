/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.managers;

import domain.AccessCode;
import domain.Box;
import domain.GroupOperation;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceController;

/**
 *
 * @author Matthias
 */
public class AccessCodeManager extends Manager<AccessCode>{

    protected AccessCodeManager()
    {
        super(AccessCode.class, new PersistenceController());
   
    }
    
    
    
    public AccessCodeManager(PersistenceController persistenceController)
    {
        super(AccessCode.class, persistenceController);
        setItems(FXCollections.observableArrayList(persistenceController.getAllOfType(AccessCode.class)));
    
        
    }
  
     public void changeFilter(List<AccessCode> accessCodes) {
         getFilteredItems().setPredicate(e -> !accessCodes.contains(e));
         System.out.println("");
     }

    @Override
    public void save(AccessCode object)
    {
        ((AccessCode) getSelected() ).copy(object);
        super.save(object);
    }
    
}
