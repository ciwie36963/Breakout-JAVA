/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import domain.Classroom;
import domain.Exercise;
import domain.ListStudentController;
import domain.Student;
import domain.ExerciseDomainController;
import domain.PersistMode;
import domain.SceneName;
import domain.StudentClass;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author geers
 */
public class ListStudentsController extends StackPane {

    private JFXDialog dialog;
    private ListStudentController dc;
    String bestandsNaam;
    @FXML
    private TableView<Student> tblStudents;
    private ObservableList<Student> listStudents;
    @FXML
    private TableColumn<Student, String> clmLastName;
    @FXML
    private TableColumn<Student, String> clmFirstName;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;
    @FXML
    private JFXTextField txtFirstName;
    @FXML
    private JFXTextField txtLastName;
    @FXML
    private TableColumn<Student, String> clmClassroom;
    @FXML
    private TableColumn<Student, String> clmClassnumber;
    @FXML
    private JFXTextField txtClassroom;
    @FXML
    private JFXTextField txtClassnumber;
    @FXML
    private Button btnStudentsImport;
    @FXML
    private JFXTextField txtBestandsNaam;
    @FXML
    private JFXButton btnExcelBestand;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private HBox hBoxNavBar;

    public ListStudentsController(ListStudentController dc) {

        this.dialog = new JFXDialog();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListStudents.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf("Error starting scene");
        }
        String image = ExerciseController.class.getResource("background.jpg").toExternalForm();
        anchorPane.setStyle("-fx-background-image: url('" + image + "'); "
                + "-fx-background-position: center center; "
                + "-fx-background-repeat: stretch;");
        this.dc = dc;
        btnStudentsImport.disableProperty().bind(txtBestandsNaam.textProperty().isEmpty());
        listStudents = FXCollections.observableArrayList(dc.getListAllStudents());
        //listStudents = dc.getStudents();
        tblStudents.setItems(listStudents);
        binders();

        clmFirstName.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getFirstName()));
        clmLastName.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getLastName()));
        clmClassroom.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getStudentClass().getStudentClassName()));
        clmClassnumber.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getClassNumber()));
        btnRemove.setOnAction(this::btnRemoveStudent);
        tblStudents.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                setStudentToDC();
            }
        });
    }

    @FXML
    private void btnAddStudent(ActionEvent event) {
        Student student;
        try {
            dc.setManagerMode(PersistMode.NEW);
             try {
                int x = Integer.parseInt(txtClassnumber.getText());
            } catch (Exception e) {
                JFXDialogLayout layout = new JFXDialogLayout();
            layout.setBody(new Label("Het klasnummer moet integer zijn"));
            JFXButton okButton = new JFXButton("OK");
            okButton.setPadding(new Insets(5, 10, 5, 10));
            okButton.setStyle("-fx-background-color: #112959;");
            okButton.setOnMouseClicked(e2 -> dialog.close());
            layout.setActions(okButton);
            dialog.setDialogContainer(this);
            dialog.setContent(layout);
            dialog.show();
            }
            student = new Student(txtFirstName.getText(), txtLastName.getText(), new StudentClass(txtClassroom.getText()), txtClassnumber.getText());
            dc.createStudent(student);
            refreshTableView();
            txtFirstName.setText("");
            txtLastName.setText("");
            txtClassnumber.setText("");
            txtClassroom.setText("");
        } catch (Exception ex) {
            JFXDialogLayout layout = new JFXDialogLayout();
            layout.setBody(new Label("Gelieve eerst alle velden 'correct' in te vullen van de student"));
            JFXButton okButton = new JFXButton("OK");
            okButton.setPadding(new Insets(5, 10, 5, 10));
            okButton.setStyle("-fx-background-color: #112959;");
            okButton.setOnMouseClicked(e2 -> dialog.close());
            layout.setActions(okButton);
            dialog.setDialogContainer(this);
            dialog.setContent(layout);
            dialog.show();
        }
        binders();

//        dc.setStudent(student);
//        listStudents.add(student);
////        Student student= new Student(txtVoornaam.getText(),txtAchternaam.getText());
////        dc.setStudent(student);
////        listStudents.add(student);
////        dc.createUser(student);
//        System.out.printf("%s %s is added %n",student.getFirstName(),student.getLastName());
//        
    }

    @FXML
    private void btnRemoveStudent(ActionEvent event) {
        try {
            dc.removeStudent();
            tblStudents.getSelectionModel().getSelectedIndex();
            refreshTableView();
        } catch (IllegalArgumentException ex) {
            JFXDialogLayout layout = new JFXDialogLayout();
            layout.setBody(new Label("Gelieve eerste een student te selecteren welke je wilt verwijderen"));
            JFXButton okButton = new JFXButton("OK");
            okButton.setPadding(new Insets(5, 10, 5, 10));
            okButton.setStyle("-fx-background-color: #112959;");
            okButton.setOnMouseClicked(e2 -> dialog.close());
            layout.setActions(okButton);
            dialog.setDialogContainer(this);
            dialog.setContent(layout);
            dialog.show();

        }
        binders();

        /*      Student student=new Student(tblStudents.getSelectionModel().getSelectedItem().getFirstName(),
                    tblStudents.getSelectionModel().getSelectedItem().getLastName(),
                    tblStudents.getSelectionModel().getSelectedItem().getClassRoom(),
                    tblStudents.getSelectionModel().getSelectedItem().getClassNumber());*/
//        System.out.printf("%s %s is verwijderd",student.getFirstName(),student.getLastName());
//        dc.removeUser(student);
//        dc.setManagerMode(PersistMode.REMOVE);
//        Student student= tblStudents.getSelectionModel().getSelectedItem();
//        System.out.printf("eeeeee %s",student.getFirstName());
//        listStudents.remove(student);
//        dc.deleteStudent(student);
    }

    private void setStudentToDC() {
        dc.setManagerMode(PersistMode.UPDATE);
        dc.setStudent(tblStudents.getSelectionModel().getSelectedItem());
    }

    public void update(Observable o, Object obj) {
        Student student = (Student) obj;

    }

    private void btnSaveStudent(ActionEvent event) {
//         Student student=new Student(txtVoornaam.getText(),txtAchternaam.getText());
//         dc.saveStudent(txtVoornaam.getText(),txtAchternaam.getText());
//         listStudents.add(student);
    }

    @FXML
    private void btnStudentsImportExcel(ActionEvent event) {
        try {
            dc.ImportStudentsExcel(bestandsNaam);
            refreshTableView();
            btnStudentsImport.disableProperty();
            this.txtBestandsNaam.setText("");
//        btnStudentsImport.setDisable(true);
        } catch (Exception ex) {
            JFXDialogLayout layout = new JFXDialogLayout();
            layout.setBody(new Label("U wou student(en) toevoegen die al werden toegevoegd aan de lijst"));
            JFXButton okButton = new JFXButton("OK");
            okButton.setPadding(new Insets(5, 10, 5, 10));
            okButton.setStyle("-fx-background-color: #112959;");
            okButton.setOnMouseClicked(e2 -> dialog.close());
            layout.setActions(okButton);
            dialog.setDialogContainer(this);
            dialog.setContent(layout);
            dialog.show();
            this.txtBestandsNaam.setText("");
        }
        binders();

    }

    public void refreshTableView() {
        listStudents = FXCollections.observableArrayList(dc.getListAllStudents());
        tblStudents.setItems(listStudents);
    }

    private void binders() {
        btnAdd.disableProperty().bind(Bindings.size(tblStudents.getItems()).isEqualTo(0));
        btnRemove.disableProperty().bind(Bindings.size(tblStudents.getItems()).isEqualTo(0));
        txtFirstName.disableProperty().bind(Bindings.size(tblStudents.getItems()).isEqualTo(0));
        txtLastName.disableProperty().bind(Bindings.size(tblStudents.getItems()).isEqualTo(0));
        txtClassnumber.disableProperty().bind(Bindings.size(tblStudents.getItems()).isEqualTo(0));
        txtClassroom.disableProperty().bind(Bindings.size(tblStudents.getItems()).isEqualTo(0));
    }

    @FXML
    private void btnExcelFileOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel bestand", "*.xlsx")
        );
        btnStudentsImport.disableProperty().bind(txtBestandsNaam.textProperty().isEmpty());
        try {
            File selectedFile = fileChooser.showOpenDialog(SceneController4.getStage());
            bestandsNaam = selectedFile.getPath();
            txtBestandsNaam.setText(selectedFile.getPath());

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }

    @FXML
    private void applyFilters(MouseEvent event) {
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
