/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.List;
import java.util.Observable;
import javafx.collections.ObservableList;

/**
 *
 * @author Matthias
 */
public interface ExerciseFilter {
    void addCategoryToFilter(Category cat);
    void removeCategoryToFilter(Category cat);
    void changeGoalFilter(List<String> goals);
    ObservableList<Category> getClasses();
    void applyFilters();
}
