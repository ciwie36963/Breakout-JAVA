/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.components;

import com.jfoenix.controls.JFXCheckBox;

/**
 *
 * @author Matthias
 */
public class CheckboxTest<T> extends JFXCheckBox{

    public T item;
   
    public CheckboxTest(String text, T item)
    {
        super(text);
        this.item = item;
    }
    public T getItem() {
        return item;
    }
    public void setItem(T item) {
        this.item = item;
    }
    
}
