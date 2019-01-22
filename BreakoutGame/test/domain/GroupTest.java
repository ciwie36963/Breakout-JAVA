/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.GroupManager;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Matthias
 */
public class GroupTest {
    
    private StudentClass studentClass;
    
    @Before
    public void init() {
        studentClass  =new StudentClass("TEST CLASS");
    }
    
    @Test
    public void groupGenerationEqualsAmountRequested() {
         studentClass.addStudent(new Student("S1"));
        studentClass.addStudent(new Student("s2"));
          List<BoBGroup> groups = GroupManager.generateRandomGroups(studentClass, 2);   
          Assert.assertEquals(2, groups.size());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void amountGroupsHigherAmountOfPeopleInClass()
    {  
     studentClass.addStudent(new Student("1"));
        studentClass.addStudent(new Student("2"));
         studentClass.addStudent(new Student("3"));
         GroupManager.generateRandomGroups(studentClass, Integer.MAX_VALUE);    
    }
    
      @Test(expected=IllegalArgumentException.class)
    public void negativeGroupAmountException()
    {
       GroupManager.generateRandomGroups(studentClass, Integer.MIN_VALUE);   
    }
    
        @Test(expected=IllegalArgumentException.class)
    public void zeroGroupAmountException()
    {  
         GroupManager.generateRandomGroups(studentClass, 0);       
    }
}
