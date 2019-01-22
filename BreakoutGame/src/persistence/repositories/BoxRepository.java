/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.repositories;

import domain.Box;
import javax.persistence.TypedQuery;

/**
 *
 * @author geers
 */
public class BoxRepository extends GenericRepository<Box> {

    public BoxRepository()
    {
        this(Box.class);
    }
    
    private BoxRepository(Class<Box> type) {
        super(type);
    }
    
    public boolean contains(String name) {
        TypedQuery<Box> query =  getEntityManager().createNamedQuery("BoxExists", Box.class);
        query.setParameter("name", name);
        return !query.getResultList().isEmpty();
    }
}
