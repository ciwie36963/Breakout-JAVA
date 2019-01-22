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
import javafx.animation.FadeTransition;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import persistence.PersistenceController;

/**
 *
 * @author geers
 */
public class SceneController4 {
    private static ExerciseDomainController dc;
    private static Stage primaryStage;
    private static SceneFactory sf;
    private static Scene scene; 
    private static Parent parent;

    public SceneController4(ExerciseDomainController dc, BoxController dc2,ListStudentController dc3 ,SessionController dc4,Stage primaryStage, Scene scene, PersistenceController pc) {
        this.dc = dc;
        this.primaryStage = primaryStage;
        sf = new SceneFactory(dc, dc2,dc3,dc4, pc);
        this.scene = scene;
        primaryStage.setScene(scene);
    }
    
    
    public static void createScene(SceneName sn) {
     // primaryStage.setMaximized(true);
      parent = sf.createScene(sn);
    } 

    public static void switchScene(SceneName sn) {
  
//        primaryStage.setMaximized(true);
 
        scene.setRoot(parent);
      //  primaryStage.setScene(scene);
     //   primaryStage.setMaximized(true);
    }
    
    public static Stage getStage() {
        return primaryStage;
    }
}
