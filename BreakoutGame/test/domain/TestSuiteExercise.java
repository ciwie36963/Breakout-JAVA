/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Matthias
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
{
  domain.CategoryTest.class, domain.ExerciseTest.class
})
public class TestSuiteExercise {

    @Before
    public void setUp() throws Exception
    {
    }
    
}
