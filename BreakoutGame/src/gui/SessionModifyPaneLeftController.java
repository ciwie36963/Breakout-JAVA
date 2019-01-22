/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.effects.JFXDepthManager;
import domain.BoBGroup;
import domain.Exercise;
import domain.ExerciseDomainController;
import domain.GroupOperation;
import domain.PersistMode;
import domain.Session;
import domain.SessionController;
import domain.SessionStatus;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Alexander
 */
public class SessionModifyPaneLeftController extends AnchorPane {

//    @FXML
//    private TableView<Session> tblSessions;

    private SessionController dc;
    @FXML
    private AnchorPane pane;
//    @FXML
//    private TableColumn<Session, String> clmSessions;
    @FXML
    private JFXButton btnOpslaan;
    @FXML
    private JFXButton btnVerwijder;
    
    private JFXDialog dialog;
    @FXML
    private JFXListView<Session> tblSessions2;
    @FXML
    private JFXNodesList nodeList;
    private static final String FX_TEXT_FILL_WHITE = "-fx-text-fill:WHITE";
    private static final String ANIMATED_OPTION_BUTTON = "animated-option-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON = "animated-option-sub-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON2 = "animated-option-sub-button2";
    public SessionModifyPaneLeftController(SessionController dc, JFXDialog dialog) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SessionModifyPaneLeft.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }

        this.dc = dc;
        this.dialog = dialog;
        //clmSessions.setCellValueFactory(e -> e.getValue().nameProperty());
        tblSessions2.setItems(dc.getFilteredItems(Session.class));
      //  , (tblSessions2.getSelectionModel().getSelectedItem() != null && tblSessions2.getSelectionModel().getSelectedItem().getSessionStatus() != SessionStatus.ACTIVATED))
        btnVerwijder.disableProperty().bind(Bindings.size(tblSessions2.getItems()).isEqualTo(0));
      
        tblSessions2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                dc.setManagerMode(Session.class, PersistMode.UPDATE);
                setSelectedItemToDc();
            }
        });
        
            JFXButton ssbutton1 = new JFXButton();

        JFXButton ssbutton2 = new JFXButton("A");
        Label lblA = new Label("A");
        lblA.setStyle("-fx-font-weight: bold;-fx-text-fill:WHITE");
        lblA.setPadding(new Insets(0, 0, 0, 5));

        ssbutton2.setGraphic(lblA);

        ssbutton2.setButtonType(JFXButton.ButtonType.RAISED);
        ssbutton2.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON2);

        nodeList.setSpacing(10);
        btnOpslaan.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON2);
        btnVerwijder.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON2);
  

        nodeList.addAnimatedNode(ssbutton2);
        nodeList.addAnimatedNode(btnOpslaan);
        nodeList.addAnimatedNode(btnVerwijder);
       

        nodeList.setRotate(180);
    }

    private void setSelectedItemToDc() {
        dc.setManagerMode(Session.class, PersistMode.UPDATE);
        dc.setSelectedItem(tblSessions2.getSelectionModel().getSelectedItem());
        dc.setSelectedItem(new BoBGroup());
          dc.setSelectedItem(tblSessions2.getSelectionModel().getSelectedItem().getClassRoom());
         dc.applyGrouplessStudentFilter();
    }

    @FXML
    private void btnOpslaanOnAction(ActionEvent event)
    {
        dc.setSelectedItem(new Session());
        dc.setManagerMode(Session.class, PersistMode.NEW);
        dc.applyGrouplessStudentFilter();
    }

    @FXML
    private void btnVerwijderOnAction(ActionEvent event)
    {
        try
        {
            dc.removeSession();
        } catch (Exception e)
        {
            showError(e);
        }
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
