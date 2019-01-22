/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import persistence.JPAUtil;

/**
 *
 * @author Matthias
 */
public class ExerciseTest {
   public ExerciseTest()
    {
    }
    
    @Before
    public void before()
    {
    }

    @Test
    public void testSomeMethod()
    {
        // TODO review the generated test code and remove the default call to fail.
      //  Assert.fail("The test case is a prototype.");
    }
    
    // <editor-fold defaultstate="collapsed" desc="Normal case tests">
    @Test 
    public void correctConstructor() {
           Exercise ex = new Exercise("Test", "blabla", "5", "DOET IETS", new Category("DUTCH"));
           //Assert with all getters
    }
  
    @Test
    public void notEmptyFeedBackHasFeedback() {
        Exercise ex = new Exercise("Test","blabla", "dddd", "DOET IETS", new Category("DUTCH"));
        Assert.assertTrue(ex.hasFeedback());
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Tests that throw an exception">
    
    @Test(expected = IllegalArgumentException.class)
    public void nullAnwserThrowsException() {
        Exercise ex = new Exercise("Test",null, "", "doe iets", new Category("DUTCH"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void emptyAnwserThrowsException() {
        Exercise ex = new Exercise("Test","   ", "", "doe iets", new Category("DUTCH"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void emptyQuestionThrowsException() {
        Exercise ex = new Exercise("Test","hallo", "", "   ", new Category("DUTCH"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void nullQuestionThrowsException() {
        Exercise ex = new Exercise("Test","hallo", "", null, new Category("DUTCH"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullCategoryThrowsException() {
        Exercise ex = new Exercise("Test","hallo", "", "hdhshddh", null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void nullNameThrowsException()
    {
        new Exercise(null,"tekst","","",new Category("DUTCH"));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void emptyNameThrowsException()
    {
        new Exercise("","tekst","","",new Category("DUTCH"));
    }
    
       @Test(expected=IllegalArgumentException.class)
    public void emptyOrNullFeedbackNoFeedback() {
        Exercise ex = new Exercise("Test","blabla", "  ", "DOET IETS", new Category("DUTCH"));
      
        
    }
      @Test(expected=IllegalArgumentException.class)
    public void nullFeedbackNoFeedback() {
        Exercise ex = new Exercise("Test","blabla", null, "DOET IETS", new Category("DUTCH"));
      
        
    }
    // </editor-fold>
    
    
}
