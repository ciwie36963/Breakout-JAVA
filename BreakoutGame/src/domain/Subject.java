/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Observer;

/**
 *
 * @author Matthias
 */
public interface Subject {
    void addObserver(Observer obs);
    void removeObserver(Observer obs);
}
