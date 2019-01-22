/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.BoxController;
import domain.ExerciseDomainController;
import domain.ListStudentController;
import domain.SceneName;
import domain.SessionController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import persistence.PersistenceController;
import persistence.Seeder;
import util.LangConfig;
import static javafx.application.Application.launch;

/**
 *
 * @author geers
 */
public class StartupMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //      Seeder.seedDatabaseWithStartData();
        LangConfig.setLang();

        Scene scene = new Scene(new MainScreenController(new ExerciseDomainController(new PersistenceController())));
        scene.getStylesheets().add(StartupMain.class.getResource("assets/css/jfoenix-components.css").toExternalForm());
        SceneController4 sc4 = new SceneController4(new ExerciseDomainController(new PersistenceController()), new BoxController(new PersistenceController()), new ListStudentController(), new SessionController(new PersistenceController()), stage, scene, new PersistenceController());
        Image icon = new Image("/gui/assets/img/icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("BOB Manager");
        //  sc4.createScene(SceneName.BOXSCREEN);
        // sc4.switchScene(SceneName.BOXSCREEN);

        //         stage.setMaximized(true);
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
