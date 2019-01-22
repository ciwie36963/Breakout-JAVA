/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import domain.BoBGroup;
import domain.PersistMode;
import domain.SessionController;
import domain.Student;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class GroupEditDetailController extends AnchorPane implements Observer {

    @FXML
    private TableView<Student> tblStudents;
    @FXML
    private JFXTextField txfGroupName;
    @FXML
    private JFXButton btnAddGroup;

    private SessionController dc;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TableColumn<Student, String> clmName;

    public GroupEditDetailController(SessionController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GroupEditDetail.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }
        this.dc = dc;
        tblStudents.setItems(dc.getStudentsFromGroup());
        tblStudents.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)
                -> {
            if (newSelection != null) {
                dc.setSelectedItem(newSelection);
            }
        });
        clmName.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getFirstName() + " " + e.getValue().getLastName()));

    }

    @Override
    public void update(Observable o, Object arg) {
        BoBGroup boBGroup = (BoBGroup) arg;
        txfGroupName.setText(boBGroup.getName());

    }

    @FXML
    private void btnAddGroupOnAction(ActionEvent event) {
        try {
            dc.setGroupName(txfGroupName.getText());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    private void removeFromGroup(ActionEvent event)
    {
        try
        {
            dc.removeStudentFromTempGroup((Student) dc.getSelectedItem(Student.class));
            dc.applyGrouplessStudentFilter();
        } catch (Exception e)
        {
        }
    }

    /**
     * Initializes the controller class.
     */
}
