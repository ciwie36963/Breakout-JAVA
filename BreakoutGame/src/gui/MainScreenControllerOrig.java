/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class MainScreenControllerOrig implements Initializable {

    @FXML
    private AnchorPane acpScherm;
    @FXML
    private StackPane stpVoorAlert;
    @FXML
    private HBox hboxContent;
    @FXML
    private VBox vboxNavigatie;
    @FXML
    private VBox vboxGreeting;
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblDatum;
    @FXML
    private Label lblInstruction;
    @FXML
    private JFXButton btnSession;
    @FXML
    private JFXButton btnBox;
    @FXML
    private JFXButton btnExercise;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSessionOnAction(ActionEvent event) {
    }

    @FXML
    private void btnBoxStartScreen(ActionEvent event) {
    }

    @FXML
    private void btnExerciseStart(ActionEvent event) {
    }

    
}
