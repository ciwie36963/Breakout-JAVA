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
    domain.GroupTest.class, domain.BoBActionTest.class, domain.SessionTest.class, domain.StudentTest.class, domain.CategoryTest.class, domain.StudentClassTest.class, domain.ExerciseTest.class
})
public class TestSuite {

    @Before
    public void setUp() throws Exception
    {
    }
    
}
