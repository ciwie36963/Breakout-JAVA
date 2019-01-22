/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.BoxController;
import domain.SceneName;
import domain.ExerciseDomainController;
import domain.ListStudentController;
import domain.SessionController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import persistence.Seeder;
import util.LangConfig;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import persistence.PersistenceController;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;

/**
 *
 * @author Alexander
 */
public class Startup extends Application {

    @Override
    public void start(Stage stage) throws Exception {

      Seeder.seedDatabaseWithStartData();
           LangConfig.setLang();
        
        PersistenceController pc = new PersistenceController();
        Scene scene = new Scene(new MainScreenController(new ExerciseDomainController(pc)),1300,800);
        scene.getStylesheets().add(StartupMain.class.getResource("assets/css/jfoenix-components.css").toExternalForm());
     
        SceneController4 sc4=new SceneController4(new ExerciseDomainController(new PersistenceController()), new BoxController(pc),new ListStudentController(),new SessionController(new PersistenceController()), stage, scene, pc);
        Image icon = new Image("/gui/assets/img/icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("BOB Manager");
      //  sc4.createScene(SceneName.BOXSCREEN);
       // sc4.switchScene(SceneName.BOXSCREEN);
    
 //         stage.setMaximized(true);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
