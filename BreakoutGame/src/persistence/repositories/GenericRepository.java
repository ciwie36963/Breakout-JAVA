/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.repositories;

import domain.Category;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import persistence.JPAUtil;
import persistence.repositories.IGenericRepository;

/**
 *
 * @author Matthias
 */
public abstract class GenericRepository<T> implements IGenericRepository<T>{


    // <editor-fold defaultstate="collapsed" desc="Properties">
    
    private final Class<T> type;
    private static final EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
    private static final EntityManager em = emf.createEntityManager();
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    
    public GenericRepository(Class<T> type)
    {
        this.type = type;
    }
    
    // </editor-fold>
  
    // <editor-fold defaultstate="collapsed" desc="Methods to start, close and comit the transaction ">
    public static void closePersistency()
    {
        em.close();
        emf.close();
    }

    public static void startTransaction()
    {
        em.getTransaction().begin();
    }

    /**
     * Save any changes done to the objects in the context to the database
     */
    public static void commitTransaction()
    {
        em.getTransaction().commit();
    }

    public static void rollbackTransaction()
    {
        em.getTransaction().rollback();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Basic CRUD Operations">
    
    /**
     * Make a new record in the database based on the given object
     * @param object 
     */
    public void persistObject(T object)
    {   startTransaction();
        em.persist(object);
        commitTransaction();
    //    em.detach(object);
    }
    
     /**
     * Returns a list of all the objects from the database
     * @return A list of type T
     */
    public List<T> getAll() {
        System.out.println(type.getName());
        return em.createQuery("SELECT entity FROM " + type.getName() + " entity", type).getResultList();
    }
    
     /**
     * Returns an entity of type T
     * @param id
     * @return The asked entity or null if the entity wasn't found.
     */
    public T getById(int id)
    {
        T entity = em.find(type, id);
        return entity;
    }
    
     /**
     * Updates the object in the database with the given object. Future edits to the object should be done to the object returned from this method. Changing the parameter object will not edit the object in the database
     * @param object
     * @return 
     */
    public T update(T object) {
        startTransaction();
        T reObject = em.merge(object);
        commitTransaction();
     //    em.detach(object);
        return reObject;
    }
    
    /**
     * Search the database for the requested object
     * @param id
     * @return true of object was found, false if the object was not found.
     */
    public boolean exists(int id) {
        T entity = em.find(type, id);
        return entity != null;
    }
    
    /**
     * Deletes the object from the database after a merge
     * @param object 
     */
    public void delete(T object) {
        startTransaction();
        em.remove(em.merge(object));
        commitTransaction();
    }
    // </editor-fold>

    protected EntityManager getEntityManager() {
        return em;
    }

   
}
