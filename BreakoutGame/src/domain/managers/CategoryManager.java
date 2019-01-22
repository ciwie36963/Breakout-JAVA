/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.managers;

import domain.BoBAction;
import domain.Category;
import domain.Exercise;
import javafx.collections.FXCollections;
import persistence.PersistenceController;

/**
 *
 * @author Matthias
 */
public class CategoryManager extends Manager<Category>{
    
 protected CategoryManager()
    {
         super(Category.class, new PersistenceController());
    }
    
    public CategoryManager(PersistenceController persitence)
    {
           super(Category.class, persitence);
        setItems(FXCollections.observableArrayList(persitence.getAllOfType(Category.class)));
    }
    
      @Override
    public void save(Category object)
    {
        getPersistenceController().setPersistMode(getManagerMode());
       ((Category) getSelected() ).copy(object);
        super.save(object); //To change body of generated methods, choose Tools | Templates.
    }
    
}
