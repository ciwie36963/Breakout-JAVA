/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.StudentClassManager;
import domain.managers.StudentManager;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import javafx.collections.transformation.FilteredList;
import persistence.PersistenceController;

/**
 *
 * @author geers
 */
public class ListStudentController{
    private StudentManager studentManager;
    private StudentClassManager studentClassManager;
    private PersistenceController persistenceController;
    ExcelStudentsImport excelStudentsImport;
  
    public ListStudentController() {
        this.persistenceController = new PersistenceController();
        this.studentManager = new StudentManager(persistenceController);
        this.studentClassManager = new StudentClassManager(persistenceController);
        this.excelStudentsImport=new ExcelStudentsImport();
    }

    public void setManagerMode(PersistMode persistMode) {
        persistenceController.setPersistMode(persistMode);
    }
    
     public List<Student> getListAllStudents()
    {
        return persistenceController.getAllOfType(Student.class);
    }
     
    public FilteredList getStudents() {
        return studentManager.getFilteredItems();
    }
     public void createStudent(Student student) {
         List<Student> studenten=this.getListAllStudents();
         studenten.stream().forEach(studentnaam -> System.out.printf("%s%n", studentnaam.getFirstName()));
         if( !persistenceController.StudentExists(student.getStudentClass(), student.getClassNumber())==true) {
             StudentClass studentClass = studentClassManager.findByName(student.getStudentClass().getStudentClassName());
             student.setStudentClass(studentClass);
             studentManager.addStudent(student);
             studentClassManager.setManagerMode(PersistMode.UPDATE);
              studentClass.addStudent(student);
             studentClassManager.setSelected(studentClass);       
             studentClassManager.save(studentClass);
         }
            
    }
     
    public void setStudent(Student student)
    {
        studentManager.setSelected(student);
    }
    

    public void ImportStudentsExcel(String bestandsNaam) {
        excelStudentsImport.AddStudentsExcel(bestandsNaam);
    }
    
//    public void getExcelStudentsObject()
//    {
//        List<String> listStudentsExcel=excelStudentsImport.AddStudentsExcel();
//        ListIterator<String> lijstIterator=listStudentsExcel.listIterator();
//        List<Student> listStudents= new ArrayList<>();
//        //klasNummer opvragen
//        String klas=lijstIterator.next();
//        
//        while(lijstIterator.hasNext())
//        {
//            Student student=new Student(lijstIterator.next(),lijstIterator.next(),klas,lijstIterator.next());
//            listStudents.add(student);
//        }
//        
//        //Lijst omzetten naar een ListIterator
//        ListIterator<Student> lijstIterator2;
//        lijstIterator2= listStudents.listIterator();
//        while(lijstIterator2.hasNext())
//        {
//            studentManager.save(lijstIterator2.next());
//        }
//    }

    public void removeStudent() {
        try
        {
            studentManager.delete();
        }catch(Exception ex)
        {
            throw new IllegalArgumentException("Student kan niet worden verwijderd");
        }
    }




}
