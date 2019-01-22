/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import domain.BoxController;
import domain.Category;
import domain.ExerciseFilter;
import domain.Goal;
import gui.components.CheckboxTest;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Matthias
 */
public class FiltersBoxController extends AnchorPane {

    @FXML
    private VBox vboxClasses;

    private ExerciseFilter dc;
    private JFXListView<Category> lstClasses;
    @FXML
    private VBox vboxGoals;
    @FXML
    private JFXTextArea txtGoalFilter;
    @FXML
    private HBox hboxActions;

    public FiltersBoxController(BoxController dc)
    {
        this(dc, new ArrayList<>());
    }
    
    public FiltersBoxController(ExerciseFilter dc, List<Node> actions)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FiltersBox.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try
        {
            loader.load();
        } catch (IOException ex)
        {
            System.out.printf(ex.getMessage());
        }
        this.dc = dc;
//        lstClasses.setItems(dc.getClasses());
        /*lstClasses.setCellFactory(CheckBoxListCell.forListView((Category item)
                -> 
                {
                    BooleanProperty observable = new SimpleBooleanProperty();
                    observable.addListener((obs, wasSelected, isNowSelected) -> 
                            {
                                System.out.println();
                                System.out.println("Check box for " + item + " changed from " + wasSelected + " to " + isNowSelected);
                                if(isNowSelected)
                                   dc.addCategoryToFilter(item);
                                else
                                    dc.removeCategoryToFilter(item);
                    });
                    return observable;
        }));*/
     
        dc.getClasses().forEach(e -> {
            JFXCheckBox c = new JFXCheckBox(((Category)e).getDescription());
            c.setOnAction(e2 -> {
                if(c.isSelected())
                  dc.addCategoryToFilter((Category) e);
                else
                  dc.removeCategoryToFilter((Category) e);
            });
            vboxClasses.getChildren().add(c);
        });
        
        dc.getClasses().addListener((ListChangeListener.Change<? extends Category> ch) ->
        {
            while (ch.next())
            {
            ch.getAddedSubList().stream().forEach(e -> {
            JFXCheckBox c = new JFXCheckBox(((Category)e).getDescription());
            c.setOnAction(e2 -> {
                if(c.isSelected())
                  dc.addCategoryToFilter((Category) e);
                else
                  dc.removeCategoryToFilter((Category) e);
            });
            vboxClasses.getChildren().add(c);
            });
            List<Node> toBeRemoved = new ArrayList<>();
            ch.getRemoved().stream().forEach(e -> {
               vboxClasses.getChildren().stream().forEach(e2 -> {
                  if(e2 instanceof CheckBox) {
                
                  if(e.getName().equals(((CheckBox) e2).getText())) {
                      toBeRemoved.add(e2);
                  }
                  }
               });
            });
            vboxClasses.getChildren().removeAll(toBeRemoved);
            }
        });
        
        txtGoalFilter.setPromptText("Type hier de doelstellingcodes in: ");
        txtGoalFilter.setLabelFloat(false);
        PseudoClass empty = PseudoClass.getPseudoClass("empty");
txtGoalFilter.pseudoClassStateChanged(empty, true);
txtGoalFilter.textProperty().addListener((obs, oldText, newText) -> {
    txtGoalFilter.pseudoClassStateChanged(empty, newText.isEmpty());
});
        
       /*  dc.getGoals().forEach(e -> {
            JFXCheckBox c = new JFXCheckBox(((Goal)e).getCode());
            c.setOnAction(e2 -> {
                if(c.isSelected())
                    System.out.println("in");
                else
                    System.out.println("out");
            });
            vboxGoals.getChildren().add(c);
        });*/
        /*lstClasses.setCellFactory(e -> {
           CheckBoxListCell test = new CheckBoxListCell();
           
          //  System.out.println(test.itemProperty().getBean().getCheckBox());
            test.getItem();
           test.setSkin(new JFXCheckBox().getSkin());
           test.setSelectedStateCallback(e2 -> {
                BooleanProperty observable = new SimpleBooleanProperty();
                    observable.addListener((obs, wasSelected, isNowSelected) -> 
                            {
                                System.out.println("Check box for " + e + " changed from " + wasSelected + " to " + isNowSelected);
                               
                    });
                    return observable;
           });
           return test;
       });*/
        hboxActions.getChildren().addAll(actions);
    }

    @FXML
    private void changeGoalFilter(KeyEvent event)
    {
        System.out.println(System.getProperty("line.separator"));
        String filterText = txtGoalFilter.getText().toLowerCase().replaceAll(" ", "").replaceAll(System.getProperty("line.separator"), ";").replaceAll("\n", ";").replaceAll("\r", ";");
        System.out.println(filterText);
      
        if(!filterText.equals("")) 
            dc.changeGoalFilter(Arrays.asList(filterText.split(";")));
        else
            dc.changeGoalFilter(new ArrayList<>());
    }

}
