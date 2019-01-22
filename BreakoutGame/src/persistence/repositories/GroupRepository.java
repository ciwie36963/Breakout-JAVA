/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.repositories;

import domain.BoBGroup;
import javafx.scene.Group;

/**
 *
 * @author geers
 */
public class GroupRepository extends GenericRepository<BoBGroup> {
    
    public GroupRepository()
    {
        this(BoBGroup.class);
    }
            
    
    private GroupRepository(Class<BoBGroup> type) {
        super(type);
    }

    @Override
    public void delete(BoBGroup object)
    {
        super.delete(object); //To change body of generated methods, choose Tools | Templates.
    }
    
}
