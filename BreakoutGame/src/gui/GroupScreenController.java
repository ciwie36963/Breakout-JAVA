/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Exercise;
import domain.GroupOperation;
import domain.OperationCategory;
import domain.ExerciseDomainController;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class GroupScreenController extends GridPane implements Observer{

    @FXML
    private TableView<GroupOperation> tblViewGroupOperations;
    @FXML
    private TableColumn<GroupOperation, String> clmDescription;
    @FXML
    private TableView<GroupOperation> tblViewSelectedGroupOperations;

    //Non fxml attributes
    private ExerciseDomainController dc;
    @FXML
    private TableColumn<GroupOperation, String> tblSelectedDesc;

    public GroupScreenController(ExerciseDomainController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GroupScreen.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }

//        dc.addObserver(this);
        this.dc = dc;
           clmDescription.setCellValueFactory(e -> e.getValue().descriptionProperty());
        tblViewGroupOperations.setItems(dc.getFilteredItems(GroupOperation.class));
        System.out.println(dc.getFilteredItems(GroupOperation.class));

    }

    @Override
    public void update(Observable o, Object arg)
    {
         Exercise exercise = (Exercise) arg;
         tblViewSelectedGroupOperations.setItems(dc.getGroupOperationsTemp()); //deze gebruiken
    //     exercise.addGroupOperationTemp(new GroupOperation(OperationCategory.MULTIPLY, "5"));
       //clmCat.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getCategory().getDescription()));
         tblSelectedDesc.setCellValueFactory(e -> e.getValue().descriptionProperty()); //deze gebruiken
         dc.changeFilterGroupOperations(exercise.getGroupOperations());
    //     tblViewGroupOperations.setItems(dc.getGroupOperations());
    }

}
