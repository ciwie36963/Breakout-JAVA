/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.BoxController;
import domain.ListStudentController;
import domain.SceneName;
import static domain.SceneName.*;
import domain.ExerciseDomainController;
import domain.SessionController;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import javafx.scene.Parent;
import javafx.scene.Scene;
import persistence.PersistenceController;

/**
 *
 * @author Alexander
 */
public class SceneFactory {
    private final Map<SceneName, Supplier<Parent>> factory = new HashMap();

    public SceneFactory(ExerciseDomainController dc, BoxController dc2,ListStudentController dc3, SessionController dc4, PersistenceController pc) {
        add(MAINSCREEN,()-> new MainScreenController(new ExerciseDomainController(pc)));
        add(STARTSCREEN, () -> new StartScreenController(new ExerciseDomainController(pc)));
        add(EXERCISEMAINSCREEN2, () -> new ExerciseController(new ExerciseDomainController(pc)));
        add(BOXSCREEN, () ->new BoxStartScreenController(new BoxController(pc)));
        add(STUDENTSSCREEN,()->new ListStudentsController(new ListStudentController()));
        add(SESSIONMAIN,()->new SessionMainController(new SessionController(pc)));
        
    }
    
    public SceneFactory(ListStudentController dc)
    {
     //   add(STUDENTSSCREEN, () -> new Scene(new ListStudentsController(dc)));
    }
    
    private void add(SceneName sn, Supplier<Parent> scene) {
        factory.put(sn, scene);
    }
    
    public Parent createScene(SceneName sn) {
        Supplier<Parent> scene = factory.get(sn);
        return scene != null ? scene.get() : null;
    }
    
    
}
