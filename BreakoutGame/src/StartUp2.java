
import domain.AccessCode;
import domain.BoBAction;
import domain.Box;
import domain.Category;
import domain.Classroom;
import domain.Exercise;
import domain.ExerciseDomainController;
import domain.PersistMode;
import domain.ExerciseDomainController;
import domain.BoBGroup;
import domain.GroupOperation;
import domain.OperationCategory;
import domain.Session;
import domain.Student;
import domain.StudentClass;
import domain.managers.GroupManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import persistence.JPAUtil;
import persistence.PersistenceController;
import util.PDFGenerator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Matthias
 */
public class StartUp2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        /*    ExerciseDomainController uc = new ExerciseDomainController();
          EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
         Category math=new Category("MATH");
        Category dutch=new Category("DUTCH");
        Exercise ex = new Exercise("nee", "nee", "nee", "nee", math);
        em.getTransaction().begin();
        em.persist(math);
        em.persist(dutch);
        em.getTransaction().commit();
        uc.setManagerMode(PersistMode.NEW);
        uc.setExercise(new Exercise());
        
        uc.saveExercise(ex);
        ex.setId(1);
        uc.setManagerMode(PersistMode.UPDATE);
        uc.setExercise(ex);
     
        Exercise exA = new Exercise("Teest", "test", "teest", "test", dutch);
        
        uc.saveExercise(exA);
        System.out.println(ex.toString());
        uc.deleteExercise();
        System.out.println(exA.toString());*/
        GroupOperation[] goArray =
        {
            new GroupOperation(OperationCategory.MULTIPLY, "2"), new GroupOperation(OperationCategory.MIN, "1"), new GroupOperation(OperationCategory.PLUS, "1"), new GroupOperation(OperationCategory.MULTIPLY, "2"), new GroupOperation(OperationCategory.MIN, "50"), new GroupOperation(OperationCategory.PLUS, "10"), new GroupOperation(OperationCategory.SWAPCHAR, "a&b")
        };

        Category math = new Category("MATH");
        Category dutch = new Category("DUTCH");
        Category geography = new Category("GEOGRAPHY");

        Exercise ex1 = new Exercise("RANDOM NAME", "10", "test", "Hoeveel is 5 + 5?", math, Arrays.asList(goArray));
        Exercise ex2 = new Exercise("ANOTHER RANDOM NAME", "2003", "Zoek via wikipedia naar het correcte antwoord.", "In welk jaar is het boek De Davinci Code uitgegeven", dutch, (Arrays.asList(goArray)).subList(0, 3));
        Exercise ex3 = new Exercise("RANDOM NAME", "1",  "test", "test1?", math, Arrays.asList(goArray));
        Exercise ex4 = new Exercise("RANDOM NAME", "2",  "test", "test2?", math, Arrays.asList(goArray));
        Exercise ex5 = new Exercise("RANDOM NAME", "3",  "test", "Hoeveel is 5 + 5?", math, Arrays.asList(goArray));
     
        Student student = new Student("Jelle", "Geers",new StudentClass("2c1"),"07");
        Set<Student> students = new HashSet<>(Arrays.asList(new Student[]
        {
//            new Student("Jelle", "Geers",""),
//            new Student("Jelle", "Geers"),
//            new Student("Jelle", "Geers"),
//            new Student("Jelle", "Geers"),
//            new Student("Jelle", "Geers"),
//            new Student("Jelle", "Geers"),
//            new Student("Jelle", "Geers"),
//            new Student("Jelle", "Geers"),
//            new Student("Jelle", "Geers"),
//            new Student("Jelle", "Geers"),
//            new Student("Jelle", "Geers"),
//            new Student("Jelle", "Geers"),
//            new Student("Jelle", "Geers"),
//            new Student("Jelle", "Geers"),
//            new Student("Jelle", "Geers"),
//            new Student("Jelle", "Geers"),
//            new Student("Jelle", "Geers"),
//                   new Student("Jelle", "Geers"),
//            new Student("Jelle", "Geers"),
//            new Student("Jelle", "Geers"),
//            new Student("Jelle", "Geers"),
//            new Student("Jelle", "Geers"),
//                  
//        
//            new Student("Jelle", "Geers")

        }));
        StudentClass studentClass = new StudentClass("2c1", students);
        Set<Exercise> exercises = new HashSet<>();
        exercises.add(ex2);
        exercises.add(ex1);
        exercises.add(ex3);
        exercises.add(ex4);
        exercises.add(ex5);

        AccessCode a1 = new AccessCode(52);
        AccessCode a2 = new AccessCode(40);
        AccessCode a3 = new AccessCode(32);
        AccessCode a4 = new AccessCode(556);
        AccessCode a5 = new AccessCode(1);
        AccessCode a6 = new AccessCode(23);
        AccessCode a7 = new AccessCode(32131);
        AccessCode a8 = new AccessCode(545);
        AccessCode a9 = new AccessCode(7758);
        AccessCode a11 = new AccessCode(512);
        AccessCode a21 = new AccessCode(401);
        AccessCode a31 = new AccessCode(312);
        AccessCode a41 = new AccessCode(5156);
        AccessCode a51 = new AccessCode(11);
        AccessCode a61 = new AccessCode(231);
        AccessCode a71 = new AccessCode(321311);
        AccessCode a81 = new AccessCode(5451);
        AccessCode a91 = new AccessCode(77581);

        List<AccessCode> accesscodes = new ArrayList<>();

        accesscodes.add(a1);
        accesscodes.add(a2);
        accesscodes.add(a3);
        accesscodes.add(a4);
        accesscodes.add(a5);
        accesscodes.add(a6);
        accesscodes.add(a7);
        accesscodes.add(a8);
        accesscodes.add(a9);
        accesscodes.add(a11);
        accesscodes.add(a21);
        accesscodes.add(a31);
        accesscodes.add(a41);
        accesscodes.add(a51);
        accesscodes.add(a61);
        accesscodes.add(a71);
        accesscodes.add(a81);
        accesscodes.add(a91);

        BoBAction action = new BoBAction("Doe iets");
        BoBAction action3 = new BoBAction("Sprint iets");
        BoBAction action4 = new BoBAction("Zoek iets");
        BoBAction action5 = new BoBAction("Nog iets");
        BoBAction action6 = new BoBAction("Sing iets");

        BoBAction action2 = new BoBAction("Zoek een kist");

        List<BoBAction> acs = new ArrayList<>();
        acs.add(action);
        
        acs.add(action4);
        acs.add(action5);
        acs.add(action6);
        acs.add(action2);

        List<Exercise> test = new ArrayList();
        test.add(ex1);
        test.add(ex2);
        Box box = new Box("Box voor 22/05", "BoxMDF1", exercises, acs);

        BoBGroup group1 = new BoBGroup("something");
        BoBGroup group2 = new BoBGroup("thing");
        List<BoBGroup> groups = new ArrayList<>();
        groups.add(group2);
        groups.add(group1);
       

     //   GroupManager groupManager = new GroupManager(new PersistenceController());
        List<BoBGroup> groups2 = GroupManager.generateRandomGroups(studentClass, 22);
         Session session = new Session(box, groups2, "something something", studentClass, "Test session", LocalDate.now(), true, true);
       // session.generatePaths();  
    }

}
