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
import com.jfoenix.effects.JFXDepthManager;
import domain.BoxController;
import domain.SceneName;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Matthias
 */
public class BoxStartScreenController extends StackPane {

    private BoxController dc;
    @FXML
    private HBox mainPane;
    @FXML
    private HBox hBoxNavBar;
    @FXML
    private JFXDrawer testD;

    private JFXDialog dialogScreen;

    private JFXDialogLayout dialogContent;
    @FXML
    private JFXDrawer filterDrawer;
    @FXML
    private HBox paneLeft;
    @FXML
    private HBox paneRight;

    public BoxStartScreenController(BoxController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoxStartScreen.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }
        dialogScreen = new JFXDialog();
        
        //NEW
        
     //   dialogScreen.setOnDialogOpened(e -> UnsavedController.setDeciding(true));
        dialogScreen.setOnDialogClosed(e -> UnsavedController.setDeciding(false));
  
        
        dialogContent = new JFXDialogLayout();

        dialogScreen.setContent(dialogContent);
        dialogScreen.setDialogContainer(this);
        this.dc = dc;
        BoxOverViewController boxOverViewController = new BoxOverViewController(dc, dialogScreen);
        //    mainPane.getItems().add(new AnchorPane());
        JFXDepthManager.setDepth(hBoxNavBar, 45);
        paneLeft.getChildren().add(boxOverViewController);
        BoxMaxController boxMax = new BoxMaxController(dc, dialogScreen);
        JFXDepthManager.setDepth(boxMax, 2);
        paneRight.getChildren().add(boxMax);
        //   mainPane.setDividerPosition(0,50);
        //  SplitPane.setResizableWithParent(boxOverViewController, Boolean.TRUE);
        //BoxAccessActionsController boxAccessActionsController = new BoxAccessActionsController(dc);
        //  dc.addObserverBox(boxAccessActionsController);

        //   SplitPane.setResizableWithParent(boxAccessActionsController, Boolean.TRUE);
        //   mainPane.getItems().add(boxAccessActionsController);
        //    BoxExerciseListController boxExerciseListController = new BoxExerciseListController(dc);
        //    dc.addObserverBox(boxExerciseListController);
        //   mainPane.getItems().add(boxExerciseListController);
        //  mainPane.setDividerPositions(100, 100,100);
        String image = ExerciseController.class.getResource("background.jpg").toExternalForm();
        mainPane.setStyle("-fx-background-image: url('" + image + "'); "
                + "-fx-background-position: center center; "
                + "-fx-background-repeat: stretch;");

        dc.addObserverBox(boxMax);

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

        //  boxAccessActionsController.setDialog(dialogScreen);
        //boxMax.setDialog(dialogScreen);
        this.requestFocus();
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
