


package domain.managers;

import domain.Classroom;
import domain.BoBGroup;
import domain.Student;
import domain.StudentClass;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Set;
import java.util.stream.IntStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceController;


public class GroupManager extends Manager<BoBGroup>
{

  private ObservableList students;
    
    protected GroupManager()
    {
         this(new PersistenceController());
    }
    
    public GroupManager(PersistenceController persistence)
    {
          super(BoBGroup.class, persistence);
        setItems(FXCollections.observableArrayList(persistence.getAllOfType(BoBGroup.class)));
        students = FXCollections.observableArrayList();
           
    }
    
    public void removeStudent(Student student) {
        students.remove(student);
    }
    public ObservableList getStudents() {
        return students;
    }

    @Override
    public void setSelected(BoBGroup item)
    {
        super.setSelected(item);
        students.setAll(((BoBGroup)getSelected()).getStudents());
    }
    
    
    
    public static List<BoBGroup> generateRandomGroups(StudentClass studentClass, int amount) {
      
        //Group amount validation
        if(amount <= 0)
            throw new IllegalArgumentException("Het aantal te maken groepen mag niet 0 of negatief zijn");
        if(amount > studentClass.getStudents().size())
           throw new IllegalArgumentException("Het aantal te maken groepen mag niet groter zijn dan het aantal leerlingen");
       
        List<Student> students = new ArrayList<>(studentClass.getStudents()); //GET CLASSROOM STUDENTS TODO
        List<BoBGroup> groups = new ArrayList<>();
        int counter = 0;
        int groupSize = students.size() / amount;
        Collections.shuffle(students);
        while (students.size() >= groupSize)
        {            
            List<Student> studentsToBeAdded = new ArrayList<>();
            IntStream.range(0, groupSize).forEach(e -> {
                System.out.println(e);
                System.out.println("List " + students.size());
                studentsToBeAdded.add(students.remove(0));
            });
            groups.add(new BoBGroup("Groep " + ++counter, studentsToBeAdded));
        }
        //classroom % amount != 0
        if(Math.ceil((double) students.size() / 2) >= Math.ceil((double)groupSize / 2)) {
            groups.add(new BoBGroup("Groep " + ++counter, students));
        } else {
            int counterRemaining = 0;
            while (!students.isEmpty())
            {                
                groups.get(counterRemaining++).addStudent(students.remove(0));
            }
        }
            
            /*  if(students.size() > 0) 
            groups.add(new BoBGroup("Groep " + counter, students));*/
        
        return groups;
    }
    
    public static List<BoBGroup> generateEmptyGroups(int amount) {
        List<BoBGroup> groups = new ArrayList<>();
        IntStream.rangeClosed(1, amount).forEach(e -> {
            groups.add(new BoBGroup("Groep " + e));
        });
        return groups;
    }

    public void setStudents(Set<Student> students)
    {
        this.students.setAll(students);
    }
    
  
}
