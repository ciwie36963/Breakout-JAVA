/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import domain.BoBGroup;
import domain.Box;
import domain.Category;
import domain.ExerciseDomainController;
import domain.Goal;
import domain.PersistMode;
import domain.Session;
import domain.SessionController;
import domain.Student;
import domain.StudentClass;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import util.PDFGenerator;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class SessionModifyPaneRightController extends AnchorPane implements Observer {

    private SessionController dc;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtClass;
    @FXML
    private JFXDatePicker DPDate;
    @FXML
    private JFXToggleButton TBFeedback;
    @FXML
    private JFXToggleButton TBTile;
    @FXML
    private TableView<Box> tblBOBS;
    @FXML
    private TableColumn<Box, String> clmBob;
//    @FXML
//    private JFXTextField txtNaamBox;
    @FXML
    private TableView<Goal> tblGoal;
    @FXML
    private TableColumn<Goal, String> clmGoal;
    @FXML
    private JFXToggleButton TBGroups;
    @FXML
    private JFXButton btnGenerateGroup;
    @FXML
    private JFXTextField txfGroupAmount;
    @FXML
    private TableView<BoBGroup> tblGroups;
    @FXML
    private TableColumn<BoBGroup, String> clmGroups;
    @FXML
    private TableView<Student> tblGrouplessStudents;
    @FXML
    private TableColumn<Student, String> clmGrouplessStudents;
    @FXML
    private JFXButton btnNewGroup;
    @FXML
    private HBox hboxGroupEdit;
    @FXML
    private JFXButton btnStudent;
    @FXML
    private JFXComboBox<StudentClass> cmbClass;
    @FXML
    private JFXButton btnOpslaan;
    @FXML
    private JFXButton btnDeleteStudent;
    @FXML
    private JFXButton btnGeneratePaths;

    private JFXDialog dialog;

    public SessionModifyPaneRightController(SessionController dc, JFXDialog dialog) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SessionModifyPaneRight.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }
        this.dc = dc;
        this.dialog = dialog;
        clmBob.setCellValueFactory(e -> e.getValue().nameProperty());
        clmGoal.setCellValueFactory(e -> e.getValue().code());
        tblBOBS.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)
                -> {
            if (newSelection != null) {
                dc.setSelectedItem(newSelection);
            }
        });
        cmbClass.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)
                -> {
            if (newSelection != null) {
                dc.setSelectedItem(newSelection);
            }
        });
        tblGroups.setItems(dc.getGroupTempList());
        clmGroups.setCellValueFactory(e -> e.getValue().nameProperty());
        tblGroups.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)
                -> {
            if (newSelection != null) {
                dc.setSelectedItem(newSelection);

            }
        });

        cmbClass.setItems(dc.getFilteredItems(StudentClass.class));
        StringConverter<StudentClass> converter = new StringConverter<StudentClass>() {
            @Override
            public String toString(StudentClass classRoom) {
                return classRoom.getStudentClassName();
            }

            @Override
            public StudentClass fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        tblGrouplessStudents.setItems(dc.getStudentsFromClass());
        cmbClass.setConverter(converter);
        GroupEditDetailController groupEditDetailController = new GroupEditDetailController(dc);
        dc.addObserver(BoBGroup.class, groupEditDetailController);
        hboxGroupEdit.getChildren().add(groupEditDetailController);
        clmGrouplessStudents.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getFirstName() + " " + e.getValue().getLastName()));
        txfGroupAmount.disableProperty().bind(TBTile.selectedProperty());
        TBGroups.disableProperty().bind(TBTile.selectedProperty());

        DPDate.setDayCellFactory(e -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                setDisable(empty || calculateDateBoolean(item));
            }

        });

        TBTile.setOnAction(e -> {
              
            if (TBTile.isSelected()) {
              
                TBFeedback.setSelected(true);
            } else
                  TBGroups.setSelected(false);
        });

        // btnGenerateGroup.disableProperty().bind(TBTile.selectedProperty().or((TBTile.selectedProperty().not().and(Bindings.isEmpty(txfGroupAmount.textProperty())))));
        btnGeneratePaths.disableProperty().bind(Bindings.size(tblGroups.getItems()).isEqualTo(0));
        
        txtClass.textProperty().bind(Bindings.when(cmbClass.getSelectionModel().selectedItemProperty().isNotNull()).then(cmbClass.getSelectionModel().selectedItemProperty().asString()).otherwise(new SimpleStringProperty()));
      //  DPDate.setDefaultColor(Color.WHITE);
    }

    @Override
    public void update(Observable o, Object obj) {
        if (obj instanceof Session) {
            Session session = (Session) obj;
            txtName.setText(session.getName());
            txtDescription.setText(session.getDescription());
        /*    if (session.getClassRoom() != null) {
                txtClass.setText(session.getClassRoom().getStudentClassName());
            } else {
                txtClass.setText("");
            }*/
            DPDate.setValue(session.getActivationDate());
            TBFeedback.setSelected(session.isFeedback());
            TBTile.setSelected(session.isTile());
            ObservableList bobs = dc.getFilteredItems(Box.class);
            tblBOBS.setItems(bobs);
            tblBOBS.getSelectionModel().select(session.getBox());

            cmbClass.getSelectionModel().select(session.getClassRoom());
        }

        if (obj instanceof Box) {
            ObservableList goals = dc.getGoals();
            tblGoal.setItems(goals);
//            txtNaamBox.setText(((Box) obj).getName());
        }
        if (obj instanceof StudentClass) {
            System.out.println("test");
            StudentClass studentClass = (StudentClass) obj;
            dc.setStudents(studentClass.getStudents());
        }

    }

    @FXML
    private void generateGroups() {
        int amount;
        try {
            if (TBTile.isSelected()) {
                amount = cmbClass.getSelectionModel().getSelectedItem().getStudents().size();
            } else {
                amount = Integer.valueOf(txfGroupAmount.getText());
            }
            dc.generateGroups(amount, TBGroups.isSelected(), cmbClass.getSelectionModel().getSelectedItem());

        } catch (NumberFormatException e) {
            showError(new IllegalArgumentException("Gelieve een getal bij het aantal groepen in te geven"));
        } catch (IllegalArgumentException e) {
            showError(e);
        } catch (Exception e) {
            showError(new IllegalArgumentException("Onbekende fout"));
        }

    }

    private void generatePaths() {

    }

    @FXML
    private void btnStudentOnAction(ActionEvent event) {
        try {
            dc.addStudentToTempGroup(tblGrouplessStudents.getSelectionModel().getSelectedItem());
            dc.applyGrouplessStudentFilter();
        } catch (Exception e) {
            showError(new IllegalArgumentException("Onbekende fout"));
        }
    }

    @FXML
    private void btnOpslaanOnAction(ActionEvent event) {

        try {
            dc.saveSession(txtDescription.getText(), txtName.getText(), DPDate.getValue(), TBTile.isSelected(), TBFeedback.isSelected(), cmbClass.getSelectionModel().getSelectedItem());
            dc.setManagerMode(Session.class, PersistMode.UPDATE);
            PDFGenerator gen = new PDFGenerator();
            gen.createSessionDocument(dc.getSelectedItem(Session.class));
        } catch (IllegalArgumentException | IOException ex) {
            showError(ex);
        }
    }

    @FXML
    private void btnDeleteStudentOnAction(ActionEvent event) {
        try {
            dc.removeStudentFromTempGroup((Student) dc.getSelectedItem(Student.class));
            dc.applyGrouplessStudentFilter();
        } catch (Exception e) {
            showError(new IllegalArgumentException("Onbekende fout"));
        }

    }

    @FXML
    private void btnGeneratePathsOnAction(ActionEvent event) {
        try {
            dc.generatePaths();
        } catch (Exception e) {
            showError(new IllegalArgumentException("Onbekende fout"));
        }
    }

    @FXML
    private void newGroup(ActionEvent event) {
        try {
            dc.addNewTempGroup("NIEUWE GROUP");
        } catch (Exception e) {
            showError(new IllegalArgumentException("Onbekende fout"));
        }

    }

    private boolean calculateDateBoolean(LocalDate date) {
        if (date.isBefore(LocalDate.now())) {
            return true;
        }
        if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return true;
        }
        //PERIODE 

        /* if(date.isBefore(LocalDate.of(LocalDate.now().getYear()-1, Month.SEPTEMBER, 1))) {
            return true;
        }*/
        return false;
    }

    private void showComponentDialog(Parent pane) {
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setAlignment(Pos.BOTTOM_RIGHT);
        layout.setPadding(Insets.EMPTY);
        layout.setBody(pane);
        JFXButton okButton = new JFXButton("Cancel");
        okButton.setStyle("-fx-background-color: #112959;");
        okButton.setOnMouseClicked(e -> dialog.close());
        layout.setActions(okButton);

        dialog.setContent(layout);
        dialog.show();
    }

    private void showError(Exception e) {
        VBox erros = new VBox();
        erros.getChildren().addAll(new Label(e.getMessage(), new JFXButton("Ok")));
        showComponentDialog(erros);
    }
}
