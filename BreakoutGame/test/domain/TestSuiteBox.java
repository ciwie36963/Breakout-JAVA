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
 domain.BoBActionTest.class, domain.BoxTest.class
})
public class TestSuiteBox {

    @Before
    public void setUp() throws Exception
    {
    }
    
}
