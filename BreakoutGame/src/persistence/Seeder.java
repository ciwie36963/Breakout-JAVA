/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;


import domain.AccessCode;
import domain.BoBAction;
import domain.BoBGroup;
import domain.Box;
import domain.Category;
import domain.Exercise;
import domain.Goal;
import domain.GroupOperation;
import domain.OperationCategory;
import domain.Session;
import domain.SessionStatus;
import domain.Student;
import domain.StudentClass;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistence.repositories.BoxRepository;

/**
 *
 * @author Matthias
 */
public class Seeder {

    public static void seedDatabaseWithStartData()
    {
        
        GroupOperation[] goArray =
        {
            new GroupOperation(OperationCategory.MULTIPLY, "1"), new GroupOperation(OperationCategory.MIN, "1"), new GroupOperation(OperationCategory.PLUS, "1"), new GroupOperation(OperationCategory.MULTIPLY, "2"), new GroupOperation(OperationCategory.MIN, "50"), new GroupOperation(OperationCategory.PLUS, "10"), new GroupOperation(OperationCategory.SWAPCHAR, "a&b")
        };
          GroupOperation[] goArray2 =
        {
            new GroupOperation(OperationCategory.SWAPCHAR, "a&b")
        };
        Category math = new Category("Wiskunde");
        Category dutch = new Category("Nederlands");
        Category geography = new Category("Aardrijkskunde");
        
        Set<Goal> goals = new HashSet<>(Arrays.asList(new Goal[]
        {
            new Goal("C50"),
            new Goal("C1"),
            new Goal("E45"),
            new Goal("E74"),
            new Goal("D65"),
            new Goal("B56")
        
        }));
        
        Exercise ex1 = new Exercise("5+5", "10", "D:\\Documents\\test.pdf", "D:\\Documents\\test.pdf", math, Arrays.asList(goArray));
        Exercise ex2 = new Exercise("Vraag Nederlands Boek", "2003", "D:\\Documents\\test.pdf", "D:\\Documents\\test.pdf", dutch, (Arrays.asList(goArray)).subList(0, 3), goals, 60);
        Exercise ex3 = new Exercise("Winnaar Eurovisiesongfestival 1974", "abba", "D:\\Documents\\test.pdf", "D:\\Documents\\test.pdf", dutch, (Arrays.asList(goArray)).subList(6, 7), goals, 60);
      
        StudentClass studentClass = new StudentClass("2c2");
        List<List<GroupOperation>> listG = new ArrayList<>();
        List<GroupOperation> groupOp1 = Arrays.asList(new GroupOperation[] {
                new GroupOperation(OperationCategory.MULTIPLY, "2"),
                new GroupOperation(OperationCategory.MIN, "5")
            });
        List<GroupOperation> groupOp2 =  Arrays.asList(new GroupOperation[] {
                new GroupOperation(OperationCategory.MULTIPLY, "3"),
                new GroupOperation(OperationCategory.MIN, "1")
            });
        List<GroupOperation> groupOp3 = Arrays.asList(new GroupOperation[] {
                new GroupOperation(OperationCategory.MULTIPLY, "10"),
                new GroupOperation(OperationCategory.MIN, "3")
            });
       // CollectionsExtension.addAll(listG, groupOp1, groupOp2, groupOp3);
       listG.add(groupOp1);
       listG.add(groupOp2);
       listG.add(groupOp3);
        //Student student=new Student("Jelle","Geers",studentClass,"07");
        Set<Exercise> exercises = new HashSet<>(Arrays.asList(new Exercise[]{
            new Exercise("3 + 5", "8", "D:\\Documents\\feedback.pdf", "D:\\Documents\\vraag.pdf", math, groupOp1),
             new Exercise("1 + 1", "2", "D:\\Documents\\feedback.pdf", "D:\\Documents\\vraag.pdf", math, groupOp2),
              new Exercise("10 + 5", "15", "D:\\Documents\\feedback.pdf", "D:\\Documents\\vraag.pdf", math, groupOp3)
            
        } ));
        //studentClass.addStudent(student);
        exercises.add(ex2);
        exercises.add(ex1);
        AccessCode a1 = new AccessCode(52);
        AccessCode a2 = new AccessCode(40);
        AccessCode a3 = new AccessCode(32);
        AccessCode a4 = new AccessCode(556);
        AccessCode a5 = new AccessCode(1);
        AccessCode a6 = new AccessCode(23);
        AccessCode a7 = new AccessCode(32131);
        AccessCode a8 = new AccessCode(545);
        AccessCode a9 = new AccessCode(7758);
//          List<AccessCode> accesscodes = new ArrayList<>();
//          
//           accesscodes.add(a1);
//           accesscodes.add(a2);
//           accesscodes.add(a3);
//           accesscodes.add(a4);
//           accesscodes.add(a5);
//            accesscodes.add(a6);
//             accesscodes.add(a7);
//              accesscodes.add(a8);
//               accesscodes.add(a9);
        
        BoBAction action = new BoBAction("Doe iets");
        BoBAction action2 = new BoBAction("Zoek een kist");
        
        List<BoBAction> acs = new ArrayList<>();
        acs.add(action2);
        acs.add(action);
        List<BoBAction> acbrotherhood = new ArrayList();
        acbrotherhood.add(action2);
        List<Exercise> test = new ArrayList();
        test.add(ex1);
        test.add(ex2);
        BoBGroup group1 = new BoBGroup("something");
        BoBGroup group2 = new BoBGroup("thing");
        Box box = new Box("Box In Sessie", "BoxMDF1", new HashSet<>(test), acs);
        Box box2 = new Box("Box niet in sessie", "BoxMDF2", new HashSet<>(Arrays.asList(new Exercise[] {ex2})), acs);
        Box box3 = new Box("Box uitleg", "BoxMDF3", new HashSet<>(Arrays.asList(new Exercise[] {ex3})), acbrotherhood);
        
        List<Student> students = Arrays.asList(new Student[]
        {
            new Student("Jan", "Smets", studentClass, "1"),
            new Student("Pieter", "Pod", studentClass, "2"),
            new Student("Jip", "Janneke", studentClass, "3"),
            new Student("Ano", "Niem", studentClass, "4"),
            new Student("John", "Hert", studentClass, "5"),
            new Student("Persoon", "Smets", studentClass, "6"),
            new Student("Persoon", "Smets", studentClass, "6"),
            new Student("Persoon", "Smets", studentClass, "7"),
            new Student("Persoon", "Smets", studentClass, "8"),
            new Student("Persoon", "Smets", studentClass, "9"),
            new Student("Persoon", "Smets", studentClass, "10"),
        
        });
        studentClass.setStudents(students);
        Session session = new Session(box, null, "GEACTIVEERDE SESSIE", studentClass, "GEACTIVEERDE SESSIE", LocalDate.now(), true, true);
        session.setSessionStatus(SessionStatus.ACTIVATED);
        
        Session session2 = new Session(box2, null, "NIET ACTIEVE SESSIE", studentClass, "NIET ACTIEVE SESSIE", LocalDate.now(), true, true);
        
        Session session3 = new Session(box3, null, "SESSIE GEEN AFSTANDSLEREN", studentClass, "SESSIE GEEN AFSTANDSLEREN", LocalDate.now(), false, false);
        session3.setSessionStatus(SessionStatus.ACTIVATED);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BreakoutGamePU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();

        //Seeding groupoperations (no cascading persist)
        Arrays.stream(goArray).forEach(e -> em.persist(e));
        listG.stream().forEach(e -> ((List<GroupOperation>) e).forEach(e2 -> em.persist(e2)));
        //Seeding Categories
        em.persist(math);
        em.persist(dutch);
        em.persist(geography);

        //Seeding goals
        goals.forEach(e -> em.persist(e));

        //Seeding exercises
        em.persist(ex1);
        em.persist(ex2);
        em.persist(ex3);
        exercises.forEach(e -> em.persist(e));

        //Seeding actions
        acs.forEach(e -> em.persist(e));

        //Seeding accesscodes
        //accesscodes.forEach(e -> em.persist(e));
        //Seeding box
        em.persist(box);
        em.persist(box2);
        em.persist(box3);
        
        //Seeding Student & class
        em.persist(studentClass);
        students.forEach(e -> em.persist(e));
        //em.persist(student);
        
        em.persist(session);
        em.persist(session2);
        em.persist(session3);
        
        em.getTransaction().commit();
        em.close();

        //    emf.close();
        BoxRepository boxRepository = new BoxRepository();
        System.out.println(boxRepository.contains("BoxMDF1"));
    }
}
