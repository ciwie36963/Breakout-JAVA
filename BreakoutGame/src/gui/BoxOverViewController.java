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
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import domain.Box;
import domain.BoxController;
import domain.PersistMode;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Matthias
 */
public class BoxOverViewController extends AnchorPane implements Observer {

    @FXML
    private Font x1;

//    @FXML
//    private TableView<Box> tblBox;
//    @FXML
//    private TableColumn<Box, String> clmName;
//    @FXML
//    private TableColumn<Box, String> clmDescription;
    private BoxController dc;
    @FXML
    private JFXButton btnNew;
    @FXML
    private JFXButton btnCopy;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private JFXTextField txtBoxFilter;

    private JFXDialog dialog;
    @FXML
    private Label lblOnderwerp;
    @FXML
    private JFXListView<Box> tblBox2;
    @FXML
    private JFXNodesList nodeList;
    private static final String FX_TEXT_FILL_WHITE = "-fx-text-fill:WHITE";
    private static final String ANIMATED_OPTION_BUTTON = "animated-option-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON = "animated-option-sub-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON2 = "animated-option-sub-button2";

    public BoxOverViewController(BoxController dc, JFXDialog dialog) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoxOverView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }
        this.dc = dc;
        this.dialog = dialog;

        tblBox2.setItems(dc.getFilteredItems(Box.class));

//        clmDescription.setCellValueFactory(e -> e.getValue().descriptionProperty());
//        clmName.setCellValueFactory(e -> e.getValue().nameProperty());
        tblBox2.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)
                -> {
            if (newSelection != null) {
                btnRemove.disableProperty().bind(Bindings.size(tblBox2.getItems()).isEqualTo(0));
                btnCopy.disableProperty().bind(Bindings.size(tblBox2.getItems()).isEqualTo(0));
                setBoxToDc();
            }
        });
        btnRemove.setDisable(true);
        btnCopy.setDisable(true);

        JFXButton ssbutton1 = new JFXButton();

        JFXButton ssbutton2 = new JFXButton("A");
        Label lblA = new Label("A");
        lblA.setStyle("-fx-font-weight: bold;-fx-text-fill:WHITE");
        lblA.setPadding(new Insets(0, 0, 0, 5));

        ssbutton2.setGraphic(lblA);

        ssbutton2.setButtonType(JFXButton.ButtonType.RAISED);
        ssbutton2.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON2);

        nodeList.setSpacing(10);
        btnNew.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON2);
        btnCopy.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON2);
        btnRemove.getStyleClass().addAll(ANIMATED_OPTION_BUTTON, ANIMATED_OPTION_SUB_BUTTON2);

        nodeList.addAnimatedNode(ssbutton2);
        nodeList.addAnimatedNode(btnNew);
        nodeList.addAnimatedNode(btnCopy);
        nodeList.addAnimatedNode(btnRemove);

        nodeList.setRotate(180);
    }

    @Override
    public void update(Observable o, Object arg) {
        Box box = (Box) arg;

    }

    private void setBoxToDc() {
        dc.setSelectedItem(tblBox2.getSelectionModel().getSelectedItem());
        dc.setManagerMode(Box.class, PersistMode.UPDATE);
    }

    @FXML
    private void createNewBox(ActionEvent event) {
        dc.setManagerMode(Box.class, PersistMode.NEW);
        Box box = new Box();
        box.addAction(dc.getStartAction());
        dc.setSelectedItem(box);
    }

    @FXML
    private void copySelected(ActionEvent event) {
        try {
            dc.setManagerMode(Box.class, PersistMode.NEW);
            Box box = new Box();
            box.copy(tblBox2.getSelectionModel().getSelectedItem());
            box.setName(box.getName() + "_" + "COPY");
            dc.setSelectedItem(box);

        } catch (Exception e) {
            setErrorDialog(new IllegalArgumentException("Selecteer eerst een box"));
        }
    }

    @FXML
    private void removeSelected(ActionEvent event) {
        try {
            dc.removeBox();
            // dc.setSelectedItem(new Box());
            tblBox2.getSelectionModel().clearSelection();
            tblBox2.getSelectionModel().selectNext();
        } catch (Exception e) {
            setErrorDialog(e);

        }

    }

    @FXML
    private void changeBoxNameFilter(KeyEvent event) {
        dc.changeBoxFilter(txtBoxFilter.getText());
    }

    private void setErrorDialog(Exception ex) {
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setBody(new Label(ex.getMessage()));
        JFXButton okButton = new JFXButton("OK");
        okButton.setOnMouseClicked(e -> dialog.close());
        layout.setActions(okButton);
        dialog.setContent(layout);
        dialog.show();
    }

    private void setChangedialog() {
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setBody(new Label("Er zijn momenteel nog niet opgeslagen gegevens. Wat wil je doen?"));

        JFXButton okButton = new JFXButton("Doorgaan");
        okButton.getStyleClass().add("button-raised");
        JFXButton returnButton = new JFXButton("Terugkeren");
        returnButton.getStyleClass().add("button-raised");

        okButton.setOnMouseClicked(e -> {
            UnsavedController.setWhatToDo(true);
            dialog.close();
        });

        returnButton.setOnMouseClicked(e -> {
            dialog.close();
        });
        dialog.setOverlayClose(false);
        List<Button> buttons = new ArrayList();
        buttons.add(okButton);
        buttons.add(returnButton);
        layout.setActions(buttons);
        dialog.setContent(layout);
        dialog.show();
    }

}
