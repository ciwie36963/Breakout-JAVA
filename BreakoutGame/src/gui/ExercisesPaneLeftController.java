/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.effects.JFXDepthManager;
import domain.Box;
import domain.Exercise;
import domain.ExerciseDomainController;
import domain.PersistMode;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class ExercisesPaneLeftController extends AnchorPane {

    @FXML
    private JFXListView<Exercise> tblExercises;
    @FXML
    private JFXButton btnCreateExercise;
    @FXML
    private JFXButton btnCopyExercise;
    @FXML
    private JFXButton btnDeleteExercise;

    //NONFXML Attributes
    private ExerciseDomainController dc;
    private ObservableList<Exercise> listExercices;
    @FXML
    private JFXColorPicker colorPicker;

    private JFXDialog dialog;
    @FXML
    private Label lblOnderwerp;
    @FXML
    private JFXNodesList nodeList;

    private static final String FX_TEXT_FILL_WHITE = "-fx-text-fill:WHITE";
    private static final String ANIMATED_OPTION_BUTTON = "animated-option-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON = "animated-option-sub-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON2 = "animated-option-sub-button2";

    public ExercisesPaneLeftController(ExerciseDomainController dc, JFXDialog dialog) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ExercisesPaneLeft.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf("Error starting scene");
        }

        this.dc = dc;
        this.dialog = dialog;
//        dc.addObserver(this);
        listExercices = dc.getFilteredItems(Exercise.class);
        tblExercises.setItems(listExercices);
        tblExercises.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                setSelectedItemToDc();
            }
        });

        btnDeleteExercise.disableProperty().bind(Bindings.size(tblExercises.getItems()).isEqualTo(0));
        btnCopyExercise.disableProperty().bind(Bindings.size(tblExercises.getItems()).isEqualTo(0));

        JFXButton ssbutton1 = new JFXButton();

        JFXButton ssbutton2 = new JFXButton("A");
        Label lblA = new Label("A");
        lblA.setStyle("-fx-font-weight: bold;-fx-text-fill:WHITE");
        //lblA.setPadding(new Insets(0, 0, 0, 5));
        lblA.setAlignment(Pos.CENTER);
        ssbutton2.setGraphic(lblA);

        ssbutton2.setButtonType(JFXButton.ButtonType.RAISED);
        ssbutton2.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON2);

        nodeList.setSpacing(10);
        btnCreateExercise.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON2);
        btnCopyExercise.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON2);
        btnDeleteExercise.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON2);

        nodeList.addAnimatedNode(ssbutton2);
        nodeList.addAnimatedNode(btnCreateExercise);
        nodeList.addAnimatedNode(btnCopyExercise);
        nodeList.addAnimatedNode(btnDeleteExercise);

        nodeList.setRotate(180);

    }

    @FXML
    private void btnCreateExerciseOnClick(MouseEvent event) {
        dc.setManagerMode(Exercise.class, PersistMode.NEW);
        dc.setSelectedItem(new Exercise());
        nodeList.animateList();
    }

    @FXML
    private void btnCopyExerciseOnClick(MouseEvent event) {
        dc.setManagerMode(Exercise.class, PersistMode.NEW);
        Exercise exercise = new Exercise(tblExercises.getSelectionModel().getSelectedItem());
        //  box.copy();
        exercise.setName(exercise.getName() + "_" + "COPY");
        dc.setSelectedItem(exercise);
        nodeList.animateList();
    }

    @FXML
    private void btnDeleteExerciseOnClick(MouseEvent event) {

        try {
            dc.deleteExercise();
            //   dc.setSelectedItem(new Exercise());
            nodeList.animateList();
        } catch (Exception ex) {
            setErrorDialog(ex);
        }

    }

    private void setSelectedItemToDc() {
        dc.setManagerMode(Exercise.class, PersistMode.UPDATE);
        dc.setSelectedItem(tblExercises.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void colorPickerOnAction(ActionEvent event) {
        Color selectedColor = colorPicker.getValue();
        tblExercises.setBackground(new Background(new BackgroundFill(Paint.valueOf(selectedColor.toString()), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void setErrorDialog(Exception ex) {
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setBody(new Label(ex.getMessage()));
        JFXButton okButton = new JFXButton("OK");
        okButton.setOnMouseClicked(e -> dialog.close());
        okButton.setStyle("-fx-background-color: #112959;");
        layout.setActions(okButton);
        dialog.setContent(layout);
        dialog.show();
    }
}
