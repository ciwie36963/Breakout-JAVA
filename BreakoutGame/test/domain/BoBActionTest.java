/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matthias
 */
public class BoBActionTest {
    
    public BoBActionTest()
    {
    }
    
    @Test
    public void correctTest() {
        String name = "DOE IETS";
        BoBAction boBAction = new BoBAction(name);
        assertEquals(name, boBAction.getName());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void nameEmpty()
    {
      new BoBAction("");
    }
}
