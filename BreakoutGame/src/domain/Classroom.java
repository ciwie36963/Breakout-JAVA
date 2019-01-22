/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.IManageable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author geers
 */
public class Classroom implements IManageable {

  

    private List<Student> students;
    private String name;
    public Classroom(String name)
    {
       this((name), new ArrayList<>());
    }

    public Classroom(String name, List<Student> students)
    {
        this.name = name;
        this.students = students;
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
    
}
