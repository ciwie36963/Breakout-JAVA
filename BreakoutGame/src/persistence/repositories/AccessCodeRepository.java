/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.repositories;

import domain.AccessCode;

/**
 *
 * @author Matthias
 */
public class AccessCodeRepository extends GenericRepository<AccessCode>{

    public AccessCodeRepository()
    {
        super(AccessCode.class);
    }
    
    public AccessCodeRepository(Class<AccessCode> type)
    {
        super(type);
    }
    
}
