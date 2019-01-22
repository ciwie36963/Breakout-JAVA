/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.repositories;

import domain.Box;
import domain.Session;
import javax.persistence.TypedQuery;

/**
 *
 * @author geers
 */
public class SessionRepository extends GenericRepository<Session>{
    
    public SessionRepository()
    {
        this(Session.class);
    }
    
    private SessionRepository(Class<Session> type) {
        super(type);
    }
    
    public boolean boxInSession(Box box) {
        TypedQuery<Integer> query =  getEntityManager().createNamedQuery("BoxInSession", int.class);
        if(query.getResultList().isEmpty())
            return false;
        return query.getResultList().contains(box.getId());
       
    }
    public void removeSession(Session session) {
        getEntityManager().getTransaction().begin();
            TypedQuery<Session> query =  getEntityManager().createNamedQuery("Remove", Session.class);
        query.setParameter("id", session.getId());
        query.executeUpdate();
        getEntityManager().getTransaction().commit();
    }
}
