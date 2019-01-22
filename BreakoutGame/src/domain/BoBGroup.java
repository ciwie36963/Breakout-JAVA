/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.IManageable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author geers
 */

@Entity
public class BoBGroup implements IManageable, Serializable {

    
    private SimpleStringProperty name = new SimpleStringProperty();
    private List<Student> students;
    
   
    private Path path;
    private int id;

    public BoBGroup()
    {
        students = new ArrayList<>();
    }

    public BoBGroup(String name)
    {
        this(name, new ArrayList<>());
    }

    public BoBGroup(String name, List<Student> students)
    {
        setName(name);
        this.students = students;
        
    }

    
     @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    public Path getPath()
    {
        return path;
    }
    
    
    
    public void setPath(Path path) {
        this.path = path;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
       
    public int getId()
    {
        return id;
    }

    @Override
    public void setId(int id)
    {
       this.id = id;
    }

    @Column(name = "name")
    public String getName()
    {
        return name.get();
    }

    public void setName(String name)
    {
        this.name.set(name);
    }

    @ManyToMany
    public List<Student> getStudents()
    {
        return students;
    }

    public void setStudents(List<Student> students)
    {
        this.students = new ArrayList<>(students);
    }

    
    public void testToString() {
   //     System.out.println(path.getToStringTest());
    }
    
    public void addStudent(Student student) {
        students.add(student);
    }
    public void removeStudent(Student student) {
        System.out.println(students.contains(student));
        students.remove(student);
    }
    
    public StringProperty nameProperty() {
        return name;
    }
}
