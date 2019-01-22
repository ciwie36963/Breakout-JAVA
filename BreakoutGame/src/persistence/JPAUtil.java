/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Matthias
 */
public class JPAUtil {
    /**
     * TODO Change PU name to correct one
     */
    private final static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("BreakoutGamePU");
     public static EntityManagerFactory getEntityManagerFactory() {
         return EMF;
     }
     private JPAUtil() {
         
     }
     
    // <editor-fold defaultstate="collapsed" desc="Properties">

    
    // </editor-fold>
    
}
