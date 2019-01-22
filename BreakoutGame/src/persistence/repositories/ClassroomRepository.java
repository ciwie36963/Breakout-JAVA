/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.repositories;

import domain.Classroom;

/**
 *
 * @author geers
 */
public class ClassroomRepository extends GenericRepository<Classroom>{
    
    public ClassroomRepository()
    {
        this(Classroom.class);
    }
    
    private ClassroomRepository(Class<Classroom> type) {
        super(type);
    }
    
}
