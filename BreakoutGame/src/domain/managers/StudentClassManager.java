/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.managers;

import domain.Session;
import domain.Student;
import domain.StudentClass;
import java.util.List;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import persistence.PersistenceController;

/**
 *
 * @author Matthias
 */
public class StudentClassManager extends Manager<StudentClass>{
    
    private ObservableList<Student> students;
    private FilteredList<Student> studentsFiltered;
    
    protected StudentClassManager()
    {
         this(new PersistenceController());
    }
    public StudentClassManager(PersistenceController persistence)
    {
        super(StudentClass.class, persistence);
        setItems(FXCollections.observableArrayList(persistence.getAllOfType(StudentClass.class)));
        students = FXCollections.observableArrayList();
        studentsFiltered = new FilteredList(students);
       
    }
    public StudentClass findByName(String name) {
        return getPersistenceController().getStudentClass(name);
    }
    @Override
    public void setSelected(StudentClass item)
    {
        super.setSelected(item); //To change body of generated methods, choose Tools | Templates.
        students.setAll(item.getStudents());
    }
    
    public FilteredList getStudents() {
        return studentsFiltered;
    }
    public void applyGrouplessFilter(List<Student> students) {
        studentsFiltered.setPredicate(e -> !students.contains(e));
    }
    @Override
    public void save(StudentClass object)
    {
        getPersistenceController().setPersistMode(getManagerMode());
        super.save(object); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
