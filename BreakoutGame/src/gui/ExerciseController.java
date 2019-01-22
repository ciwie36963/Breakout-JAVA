/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;
import domain.BoxController;
import domain.ExerciseDomainController;
import domain.SceneName;
import gui.ExerciseDetailsPaneMidController;
import gui.ExercisesGroupOperationsPaneRightController;
import gui.ExercisesPaneLeftController;
import gui.FiltersBoxController;
import gui.GroupScreenController;
import gui.NavigationMenuController;
import gui.SceneController4;
import gui.SessionModifyPaneLeftController;
import gui.SessionModifyPaneRightController;
import gui.StartScreenController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import persistence.PersistenceController;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class ExerciseController extends StackPane {

    private StartScreenController startScreen;
    private GroupScreenController groupScreen;
    private ExercisesPaneLeftController screen1;
    private ExerciseDetailsPaneMidController screen2;
    private ExercisesGroupOperationsPaneRightController screen3;

    private SessionModifyPaneRightController test;
    private SessionModifyPaneLeftController test2;

    private ExerciseDomainController dc;
    @FXML
    private HBox splitPane;
    @FXML
    private HBox hBoxNavBar;
    @FXML
    private JFXDrawer filterDrawer;
    @FXML
    private JFXDrawer testD;

    private JFXDialog dialogScreen;

    private JFXDialogLayout dialogContent;
    @FXML
    private VBox vbox;
    @FXML
    private HBox hboxDetail;
    @FXML
    private HBox hboxGroupDetails;

    public ExerciseController(ExerciseDomainController dc) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Exercise.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }

        dialogScreen = new JFXDialog();
        dialogContent = new JFXDialogLayout();

        dialogScreen.setContent(dialogContent);
        dialogScreen.setDialogContainer(this);

        this.dc = dc;
        startScreen = new StartScreenController(dc);
        groupScreen = new GroupScreenController(dc);
        screen1 = new ExercisesPaneLeftController(dc, dialogScreen);
        screen2 = new ExerciseDetailsPaneMidController(dc, dialogScreen);
        screen3 = new ExercisesGroupOperationsPaneRightController(dc, dialogScreen);

        //test = new SessionModifyPaneRightController(dc);
        //test2 = new SessionModifyPaneLeftController(dc);
//        testScreen = new TestScreenController(dc);
//        exerciseDetailScreen = new ExerciseDetailScreenController(dc);
//        this.add(startScreen, 0, 0);
//        this.add(testScreen, 1, 0);
        dc.addObserverExercise(screen2);
        dc.addObserverExercise(screen3);

        //de linker hbox
        splitPane.getChildren().add(screen1);
        
        //de rechtervbox met 2hboxes
        hboxDetail.getChildren().add(screen2);
        hboxGroupDetails.getChildren().add(screen3);
        
        

        //foto
        String image = ExerciseController.class.getResource("background.jpg").toExternalForm();
        vbox.setStyle("-fx-background-image: url('" + image + "'); "
                + "-fx-background-position: center center; "
                + "-fx-background-repeat: stretch;");

        testD.setSidePane(new NavigationMenuController());
        List<Node> filterActions = new ArrayList<>();
        JFXButton closeFilter = new JFXButton("Sluit filters");
        closeFilter.getStyleClass().add("closeButton");
        closeFilter.setOnAction(e -> {
            filterDrawer.close();
        });
        filterActions.add(closeFilter);
        filterDrawer.setSidePane(new FiltersBoxController(dc, filterActions));
        this.getChildren().remove(testD);
        this.getChildren().remove(filterDrawer);
        //  testD.open();
        testD.setOnDrawerClosed(e -> {
            this.getChildren().remove(testD);
        });
        filterDrawer.setOnDrawerClosed(e -> {
            this.getChildren().remove(filterDrawer);
        });

        screen3.setDialog(dialogScreen);
        testD.setDefaultDrawerSize(350);
        filterDrawer.setDefaultDrawerSize(350);
    }

    private void test(MouseEvent event) {
        this.getChildren().add(testD);
        testD.open();
    }

    @FXML
    private void applyFilters(MouseEvent event) {
        this.getChildren().add(filterDrawer);
        dc.applyFilters();
        filterDrawer.open();
    }

    @FXML
    private void goToBox(MouseEvent event) {
        SceneController4.createScene(SceneName.BOXSCREEN);
        SceneController4.switchScene(SceneName.BOXSCREEN);
    }

    @FXML
    private void goToEx(MouseEvent event) {
        SceneController4.createScene(SceneName.EXERCISEMAINSCREEN2);
        SceneController4.switchScene(SceneName.EXERCISEMAINSCREEN);
    }

    @FXML
    private void goToSessions(MouseEvent event) {
        SceneController4.createScene(SceneName.SESSIONMAIN);
        SceneController4.switchScene(SceneName.SESSIONMAIN);
    }

    @FXML
    private void goToStudent(MouseEvent event) {
        SceneController4.createScene(SceneName.STUDENTSSCREEN);
        SceneController4.switchScene(SceneName.STUDENTSSCREEN);
    }

}
