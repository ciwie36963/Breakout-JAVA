/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDrawer;
import domain.SceneName;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Matthias
 */
public class NavigationMenuController extends AnchorPane {

    @FXML
    private AnchorPane AnchorPane;

    public NavigationMenuController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NavigationMenu.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }

    }

    @FXML
    private void goToEx(ActionEvent event) {
        SceneController4.createScene(SceneName.EXERCISEMAINSCREEN2);
        SceneController4.switchScene(SceneName.EXERCISEMAINSCREEN);
    }

    @FXML
    private void goToBox(ActionEvent event) {
        SceneController4.createScene(SceneName.BOXSCREEN);
        SceneController4.switchScene(SceneName.BOXSCREEN);
    }

    @FXML
    private void goToSessions(ActionEvent event) {
        SceneController4.createScene(SceneName.SESSIONMAIN);
        SceneController4.switchScene(SceneName.SESSIONMAIN);

    }

}
