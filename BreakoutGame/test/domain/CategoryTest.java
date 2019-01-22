/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import org.junit.Test;

/**
 *
 * @author geers
 */
public class CategoryTest {
    @Test(expected=IllegalArgumentException.class)
    public void legeCategoryNameThrowsException()
    {
        new Category("");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void nullCategoryNameThrowsException()
    {
        new Category("");
    }
    
    
    
}
