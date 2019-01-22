/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import domain.Category;
import domain.Exercise;
import domain.ExerciseDomainController;
import domain.PersistMode;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class ExerciseDetailsPaneMidController extends AnchorPane implements Observer {

    @FXML
    private JFXTextField txtAnw;
    @FXML
    private JFXComboBox<Category> cmbCategory;
    @FXML
    private JFXTextField txtEx;
    @FXML
    private JFXButton btnSave;

    private ExerciseDomainController dc;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField txfFeedback;
    @FXML
    private JFXButton btnFeedback;
    @FXML
    private JFXTextField txfOpdracht;
    @FXML
    private JFXButton btnAssignment;
    @FXML
    private JFXTextField txtTime;
    @FXML
    private JFXToggleButton btnToggleTime;
  
    private JFXDialog dialog;
    @FXML
    private Label lblOefeningActie;
    
       private static final String FX_TEXT_FILL_WHITE = "-fx-text-fill:WHITE";
    private static final String ANIMATED_OPTION_BUTTON = "animated-option-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON = "animated-option-sub-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON2 = "animated-option-sub-button2";
    public ExerciseDetailsPaneMidController(ExerciseDomainController dc, JFXDialog dialog) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ExerciseDetailsPaneMid.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }

//        dc.addObserver(this);
        this.dc = dc;
        this.dialog = dialog;
        cmbCategory.setItems(dc.getFilteredItems(Category.class));
        StringConverter<Category> converter = new StringConverter<Category>() {
            @Override
            public String toString(Category category) {
                return category.getDescription();
            }

            @Override
            public Category fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };
        cmbCategory.setConverter(converter);
        /*cmbCategory.getSelectionModel().selectedItemProperty().addListener((o, ol, nw) -> {
            System.out.println(cmbCategory.getValue().getDescription());
        });*/
        
       txtTime.disableProperty().bind(btnToggleTime.selectedProperty().not());
      txfFeedback.setDisable(true);
      txfOpdracht.setDisable(true);
      
    }


    @Override
    public void update(Observable o, Object obj) {
        Exercise exercise = (Exercise) obj;
        txtAnw.setText(exercise.getAnswer());
        txtEx.setText(exercise.getName());
        //    txtCat.setText(exercise.getCategoryDescription());
        cmbCategory.getSelectionModel().select(exercise.getCategory());
        txfOpdracht.setText(exercise.getAssignment());
        txfFeedback.setText(exercise.getFeedback());
        //     tblViewGroupOperations.setItems(exercise.getGroupOperationsObservableList()); //deze gebruiken
        //clmCat.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getCategory().getDescription()));
        //  clmValue.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getDescription()))); //deze gebruiken
        if(exercise.getTimeInMinutes() > 0) {
            btnToggleTime.setSelected(true);
            txtTime.setText(String.valueOf(exercise.getTimeInMinutes()));
        } else {
            btnToggleTime.setSelected(false);
            txtTime.clear();
        }
             

    }

    @FXML
    private void btnFeedbackOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("PDF Files", "*.pdf"));

        File selectedFile = fileChooser.showOpenDialog(SceneController4.getStage()); //moet nog aangepast worden
        if(selectedFile != null)
        txfFeedback.setText(selectedFile.getPath());
    }

    @FXML
    private void btnAssignmentOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("PDF Files", "*.pdf"));

        File selectedFile = fileChooser.showOpenDialog(SceneController4.getStage()); //moet nog aangepast worden
           if(selectedFile != null)
        txfOpdracht.setText(selectedFile.getPath());
    }

    @FXML
    private void btnSaveExOnAction(ActionEvent event)
    {
        try {
        dc.saveExercise(txtEx.getText(), txtAnw.getText(), txfFeedback.getText(), txfOpdracht.getText(), cmbCategory.getSelectionModel().getSelectedIndex(), Integer.valueOf(!txtTime.getText().equals("") ? txtTime.getText() : "0"));       
        dc.setManagerMode(Exercise.class, PersistMode.UPDATE);
        } catch(NumberFormatException e) {
            setErrorDialog(new NumberFormatException("Gelieve een getal in te vullen in het tijd veld"));
        } catch(IllegalArgumentException e) {
            setErrorDialog(e);
        }
        }

    @FXML
    private void changeTimeSelection(ActionEvent event)
    {
        if(!btnToggleTime.isSelected()) {
            txtTime.clear();
        }
    }

        private void setErrorDialog(Exception ex) {
         JFXDialogLayout layout = new JFXDialogLayout();
            layout.setBody(new Label(ex.getMessage()));
            JFXButton okButton = new JFXButton("OK");
              okButton.setStyle("-fx-background-color: #112959;");
            okButton.setOnMouseClicked(e -> dialog.close());
            layout.setActions(okButton);
            dialog.setContent(layout);
            dialog.show();
    }
}
