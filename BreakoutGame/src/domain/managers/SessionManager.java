


package domain.managers;

import domain.Assignment;
import domain.Box;
import domain.Exercise;
import domain.BoBGroup;
import domain.Category;
import domain.Goal;
import domain.GroupOperation;
import domain.Session;
import domain.Path;
import domain.PersistMode;
import domain.SessionStatus;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.stream.IntStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceController;


public class SessionManager extends Manager<Session>
{
    private ObservableList<BoBGroup> tempGroups;
    protected SessionManager()
    {
         this(new PersistenceController());
    }
    public SessionManager(PersistenceController persistence)
    {
          super(Session.class, persistence);
        setItems(FXCollections.observableArrayList(persistence.getAllOfType(Session.class)));
        tempGroups = FXCollections.observableArrayList();
    }

    @Override
    public void setSelected(Session item)
    {
        super.setSelected(item); //To change body of generated methods, choose Tools | Templates.
        tempGroups.setAll(item.getGroups());
    }
    
     public static void generatePaths(List<BoBGroup> groups, Box box) {
         groups.stream().forEach(e -> {
            List<Assignment> assignments = new ArrayList<>();
            List<Exercise> shuffledExercises = new ArrayList<>(box.getExercises());
            Collections.shuffle(shuffledExercises);
            IntStream.rangeClosed(1, shuffledExercises.size()).forEach(e2 -> {
                List<GroupOperation> shuffledGroupOperations = new ArrayList<>(shuffledExercises.get(e2-1).getGroupOperations());
                Collections.shuffle(shuffledGroupOperations);
                if(e2 < shuffledExercises.size())
                     assignments.add(new Assignment(e2, shuffledExercises.get(e2-1),
                             shuffledGroupOperations.get(0), 
                             new Random().nextInt(1000) + 1));
                else
                     assignments.add(new Assignment(e2, shuffledExercises.get(e2-1),shuffledGroupOperations.get(0), 0));  
            });
            Path path = new Path(assignments);
            e.setPath(path);
        });
     }

    @Override
    public void delete() throws InvocationTargetException
    {
        Session session = getSelected();
        if (session.getSessionStatus() == SessionStatus.ACTIVATED)
        {
            throw new IllegalArgumentException("Sessie is geactiveerd en kan daarom dus niet verwijderd worden");
        }
        super.delete(); //To change body of generated methods, choose Tools | Templates.
    }
     
         
      @Override
    public void save(Session object)
    {  
        getPersistenceController().setPersistMode(getManagerMode());
       ((Session) getSelected() ).copy(object);
        super.save(object); //To change body of generated methods, choose Tools | Templates.
        
    }
     public static void generatePaths(Session session) {
        List<BoBGroup> groups = session.getGroups();
        Box box = session.getBox();
        SessionManager.generatePaths(groups, box);
        
    }
     public ObservableList<BoBGroup> getTempGroups() {
         return tempGroups;
     }
     
     public void clearTempGroups() {
         tempGroups.clear();
     }
     public void addAllToTempGroup(List<BoBGroup> groups) {
         tempGroups.addAll(groups);
     }
    
}
