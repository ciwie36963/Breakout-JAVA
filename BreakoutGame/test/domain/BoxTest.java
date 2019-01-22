/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matthias
 */
public class BoxTest {
    
    public BoxTest()
    {
    }
    
    @Test
    public void correctConstructor() {
       Box box = new Box("Test", "test", new HashSet<Exercise>(), new ArrayList<BoBAction>());
        assertEquals("Test", box.getDescription());
        assertEquals("test", box.getName());
    }
    
    @Test
    public void lessActionsThanExercisesBoxInvalid() {
          Box box = new Box("Test", "Test", new HashSet<Exercise>(Arrays.asList(new Exercise[] {
               new Exercise()
           })), new ArrayList<>());
          assertFalse(box.isValidBox());
    }
    @Test(expected=IllegalArgumentException.class)
    public void nullNameThrowsException()
    {
        new Box("Test", null, new HashSet<Exercise>(), new ArrayList<BoBAction>());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void emptyNameThrowsException()
    {
       new Box("Test", "  ", new HashSet<Exercise>(), new ArrayList<BoBAction>());
    }
     @Test(expected=IllegalArgumentException.class)
    public void nullDescriptionThrowsException()
    {
        new Box(null, " test", new HashSet<Exercise>(), new ArrayList<BoBAction>());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void emptyDescriptionThrowsException()
    {
       new Box(" ", " dsdf ", new HashSet<Exercise>(), new ArrayList<BoBAction>());
    }
}
