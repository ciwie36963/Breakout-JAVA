/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.repositories;

import domain.Box;
import domain.Classroom;
import domain.Student;
import domain.StudentClass;
import javax.persistence.TypedQuery;

/**
 *
 * @author geers
 */
public class StudentRepository extends GenericRepository<Student> {
    
    public StudentRepository()
    {
        this(Student.class);
    }
    
    private StudentRepository(Class<Student> type) {
        super(type);
    }
    
    public boolean contains(StudentClass studentClass, String classnumber) {
        TypedQuery<Student> query =  getEntityManager().createNamedQuery("StudentExists", Student.class);
        query.setParameter("studentClass", studentClass.getStudentClassName());
        query.setParameter("classnumber", classnumber);
        return !query.getResultList().isEmpty();
    }
}
