/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.BoxController;
import gui.BoxStartScreenController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import persistence.PersistenceController;
import persistence.Seeder;
import util.LangConfig;

/**
 *
 * @author Matthias
 */
public class boxscreentest extends Application {
    
    @Override
    public void start(Stage primaryStage)
    {
              Seeder.seedDatabaseWithStartData();
        LangConfig.setLang();

        PersistenceController pc = new PersistenceController();
        BoxController dc = new BoxController(pc);
        BoxStartScreenController root = new BoxStartScreenController(dc);
        
        Scene scene = new Scene(root, 300, 250);
          scene.getStylesheets().add(boxscreentest.class.getResource("gui/assets/css/jfoenix-components.css").toExternalForm());
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
