/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.repositories;


import domain.BoBAction;
import domain.Exercise;
import javafx.beans.value.ChangeListener;
import org.eclipse.persistence.annotations.ChangeTracking;

/**
 *
 * @author geers
 */
public class ActionRepository extends GenericRepository<BoBAction> {
    
    public ActionRepository()
    {
        this(BoBAction.class);
    }
    
    private ActionRepository(Class<BoBAction> type) {
        super(type);
    }
    
}
