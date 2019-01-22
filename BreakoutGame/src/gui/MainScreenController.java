/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import domain.ExerciseDomainController;
import domain.SceneName;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author geers
 */
public class MainScreenController extends AnchorPane implements Observer {

    private ExerciseDomainController dc;
    @FXML
    private JFXButton btnBox;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private JFXButton btnExercise;
    @FXML
    private Label lblTitle;
    @FXML
    private JFXButton btnSession;
    @FXML
    private StackPane stpVoorAlert;
    @FXML
    private HBox hboxContent;
    @FXML
    private VBox vboxNavigatie;
    @FXML
    private VBox vboxGreeting;
    @FXML
    private Label lblDatum;
    @FXML
    private Label lblInstruction;
    @FXML
    private JFXButton btnStudent;

    public MainScreenController(ExerciseDomainController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf("Error starting scene");
        }

        this.dc = dc;
        this.requestFocus();
        String image = MainScreenController.class.getResource("background.jpg").toExternalForm();
        this.setStyle("-fx-background-image: url('" + image + "'); "
                + "-fx-background-position: center center; "
                + "-fx-background-repeat: stretch;");

        lblDatum.setText("Het is nu " + getUur() + " uur.");
    }
    
    	private String getUur() {
		Calendar nu = Calendar.getInstance();
		String uur = nu.get(Calendar.HOUR_OF_DAY) + ":" + nu.get(Calendar.MINUTE);
		return uur;
	}

    @FXML
    private void btnBoxStartScreen(ActionEvent event) {
        SceneController4.createScene(SceneName.BOXSCREEN);
        SceneController4.switchScene(SceneName.BOXSCREEN);
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void btnExerciseStart(ActionEvent event) {
        SceneController4.createScene(SceneName.EXERCISEMAINSCREEN2);
        SceneController4.switchScene(SceneName.EXERCISEMAINSCREEN2);
    }

    private void btnStudentsExcell(ActionEvent event) {
        SceneController4.createScene(SceneName.STUDENTSSCREEN);
        SceneController4.switchScene(SceneName.STUDENTSSCREEN);
    }

    @FXML
    private void btnSessionOnAction(ActionEvent event) {
        SceneController4.createScene(SceneName.SESSIONMAIN);
        SceneController4.switchScene(SceneName.SESSIONMAIN);
    }

    @FXML
    private void btnStudentOnAction(ActionEvent event) {
        SceneController4.createScene(SceneName.STUDENTSSCREEN);
        SceneController4.switchScene(SceneName.STUDENTSSCREEN);
    }
}
