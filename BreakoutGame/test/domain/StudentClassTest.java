/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author geers
 */
public class StudentClassTest {

    public StudentClassTest() {
    }
    
    @Before
    public void before()
    {
    }
    
    @Test
    public void correctConstructor()
    {
        StudentClass studentClass= new StudentClass("KlasNaam");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void nullStudentClassNameThrowsException()
    {
       StudentClass studentClass=new StudentClass(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void emptyStudentClassNameThrowsException()
    {
       StudentClass studentClass=new StudentClass("");
    }
}
