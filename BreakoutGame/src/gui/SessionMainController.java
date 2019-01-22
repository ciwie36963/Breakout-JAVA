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
import domain.Box;
import domain.BoxController;
import domain.ExerciseDomainController;
import domain.SceneName;
import domain.Session;
import domain.SessionController;
import domain.Student;
import domain.StudentClass;
import gui.ExerciseDetailsPaneMidController;
import gui.ExercisesGroupOperationsPaneRightController;
import gui.ExercisesPaneLeftController;
import gui.FiltersBoxController;
import gui.GroupScreenController;
import gui.NavigationMenuController;
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
public class SessionMainController extends StackPane {

    private ExercisesPaneLeftController screen1;
    private ExerciseDetailsPaneMidController screen2;
    private ExercisesGroupOperationsPaneRightController screen3;

    private SessionModifyPaneRightController test;
    private SessionModifyPaneLeftController test2;

    private SessionController dc;
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
    private HBox paneLeft;
    @FXML
    private HBox paneRight;

    public SessionMainController(SessionController dc) {
        //fxml loader + basic dc
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SessionMain.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }
        this.dc = dc;

        //dialogs
        dialogScreen = new JFXDialog();
        dialogContent = new JFXDialogLayout();
        dialogScreen.setContent(dialogContent);
        dialogScreen.setDialogContainer(this);

//        screen1 = new ExercisesPaneLeftController(dc, dialogScreen);
//        screen2 = new ExerciseDetailsPaneMidController(dc, dialogScreen);
//        screen3 = new ExercisesGroupOperationsPaneRightController(dc, dialogScreen);
        test = new SessionModifyPaneRightController(dc, dialogScreen);
        test2 = new SessionModifyPaneLeftController(dc, dialogScreen);

        dc.addObserver(Session.class, test);
        dc.addObserver(Box.class, test);
        dc.addObserver(StudentClass.class, test);

        paneLeft.getChildren().add(test2);
        paneRight.getChildren().add(test);

        //bg + extra
        String image = SessionMainController.class.getResource("background.jpg").toExternalForm();
        splitPane.setStyle("-fx-background-image: url('" + image + "'); "
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
//        filterDrawer.setSidePane(new FiltersBoxController(dc, filterActions));            ---werkt even niet
        this.getChildren().remove(testD);
        this.getChildren().remove(filterDrawer);
        //  testD.open();
        testD.setOnDrawerClosed(e -> {
            this.getChildren().remove(testD);
        });
        filterDrawer.setOnDrawerClosed(e -> {
            this.getChildren().remove(filterDrawer);
        });

//        screen3.setDialog(dialogScreen);
    }

    private void test(MouseEvent event) {
        this.getChildren().add(testD);
        testD.open();
    }

    @FXML
    private void applyFilters(MouseEvent event) {
//        this.getChildren().add(filterDrawer);
//        dc.applyFilters();
//        filterDrawer.open();
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
