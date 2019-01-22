/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author Matthias
 */
public class UnsavedController {
    
    private static SimpleBooleanProperty changes = new SimpleBooleanProperty();
    private static boolean whatToDo;
    private static boolean deciding = true;
    public static void setChanges(boolean c) {
        changes.set(c);
        whatToDo = false;
        deciding = false;
    }
    public static boolean isChanging() {
        return changes.get();
    }
    public static BooleanProperty changesProperty() {
        return changes;
    }
    
    public static boolean getWhatToDo() {
        return whatToDo;
    }
    
    public static void setWhatToDo(boolean w) {
        whatToDo =w;
    }
    
     public static boolean isDeciding() {
        return deciding;
    }
    
    public static void setDeciding(boolean d) {
        deciding =d;
    }
}
