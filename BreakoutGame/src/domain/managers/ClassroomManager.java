


package domain.managers;

import domain.Box;
import domain.Classroom;
import javafx.collections.FXCollections;
import persistence.PersistenceController;


public class ClassroomManager extends Manager<Classroom>
{

    protected ClassroomManager()
    {
          super(Classroom.class, new PersistenceController());
    }
    
    public ClassroomManager(PersistenceController persitence)
    {
           super(Classroom.class, persitence);
        setItems(FXCollections.observableArrayList(persitence.getAllOfType(Classroom.class)));
    }
    
}
