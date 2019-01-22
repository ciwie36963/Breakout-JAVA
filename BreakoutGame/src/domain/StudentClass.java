/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.IManageable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.JoinFetch;

/**
 *
 * @author geers
 */
@Entity

public class StudentClass implements IManageable, Serializable{

    
    @Id
    private String studentClassName;
    
 
    @OneToMany(mappedBy="studentClass", fetch = FetchType.EAGER)
    private Set<Student> students;

    public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    protected StudentClass()
    {
        students = new HashSet<>();
    }
    
    public StudentClass(String studentClassName) {
        this(studentClassName, new HashSet<>());
    }

    public StudentClass(String studentClassName, Set<Student> students)
    {
        setStudentClassName(studentClassName);
        this.students = students;
        
    }

    
    public String getStudentClassName() {
        return studentClassName;
    }

    public void setStudentClassName(String studentClassName) {
        if(studentClassName==null||studentClassName.trim().equals(""))
            throw new IllegalArgumentException();
        else
            this.studentClassName=studentClassName;
    }
    public void addStudent(Student student) {
        students.add(student);
    }
    public void removeStudent(Student student) {
        Iterator it = students.iterator();
        List<Student> studentsList = new ArrayList<>(students);
        studentsList.remove(student);
        students.clear();
        students.addAll(studentsList);
  
    }
    public void setStudents(List<Student> students) {
        this.students.clear();
        this.students.addAll(students);
    }
    @Override
    public int getId()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString()
    {
        return studentClassName;
    }

    
}
