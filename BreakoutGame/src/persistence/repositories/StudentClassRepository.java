/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.repositories;

import domain.StudentClass;

/**
 *
 * @author Matthias
 */
public class StudentClassRepository extends GenericRepository<StudentClass>{
    
      public StudentClassRepository(Class<StudentClass> type)
    {
        super(type);
    }
       public StudentClassRepository()
    {
        super(StudentClass.class);
    }
    
       public StudentClass getByName(String name) {
           return getEntityManager().find(StudentClass.class, name);
       }
}
