/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.BoxManager;
import domain.managers.GroupManager;
import domain.managers.IManageable;
import domain.managers.Manager;
import domain.managers.SessionManager;
import domain.managers.StudentClassManager;
import domain.managers.StudentManager;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import persistence.PersistenceController;

/**
 *
 * @author Matthias
 */
public class SessionController {

    private SessionManager sessionManager;
    private GroupManager groupManager;
    private BoxManager boxManager;
    private StudentManager studentManager;
    private StudentClassManager classManager;
    private Map<String, Manager> managers;

    public SessionController(PersistenceController persistenceController) {

        sessionManager = new SessionManager(persistenceController);
        groupManager = new GroupManager(persistenceController);
        boxManager = new BoxManager(persistenceController);
        studentManager = new StudentManager(persistenceController);
        classManager = new StudentClassManager(persistenceController);
        managers = new HashMap<>();

        managers.put(Session.class.getSimpleName(), sessionManager);
        managers.put(Box.class.getSimpleName(), boxManager);
        // STUDENT / STUDENTCLASS MANAGER
        managers.put(Student.class.getSimpleName(), studentManager);
        managers.put(BoBGroup.class.getSimpleName(), groupManager);
        managers.put(StudentClass.class.getSimpleName(), classManager);
    
    }

    public FilteredList getFilteredItems(Class<? extends IManageable> className) {
        return managers.get(className.getSimpleName()).getFilteredItems();
    }

    public void addObserver(Class<? extends IManageable> className, Observer obs) {
        managers.get(className.getSimpleName()).addObserver(obs);
    }

    public void saveSession(String description, String name, LocalDate activationDate, boolean tile, boolean feedback, StudentClass studentClass) {
        //TODO NEED OTHER MANAGERS => group, studentclass etc
        try
        {
            managers.get(Session.class.getSimpleName()).save(new Session(
                    (Box) managers.get(Box.class.getSimpleName()).getSelected(),
                    sessionManager.getTempGroups(),
                    description,
                    studentClass,
                    name,
                    activationDate,
                    tile,
                    feedback));
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("De sessie met deze naam bestaat al");
        }
    }

    public ObservableList getGroupTempList() {
        return sessionManager.getTempGroups();
    }
    public void addNewTempGroup(String name) {
        //TODO GET TEMP STUDENTS FROM MANAGER
        sessionManager.getTempGroups().add(new BoBGroup(name));
    }

    public void removeTempGroup(BoBGroup group) {
        sessionManager.getTempGroups().remove(group);

    }

    public void addStudentToTempGroup(Student student) {
        ((BoBGroup) groupManager.getSelected()).addStudent(student);
        groupManager.getStudents().add(student);
    }

    public void removeStudentFromTempGroup(Student student) {
        ((BoBGroup) groupManager.getSelected()).removeStudent(student);
        groupManager.removeStudent(student);
    }

    public ObservableList getStudentsFromClass() {
        return classManager.getStudents();
    }
    public ObservableList getStudentsFromGroup() {
        return groupManager.getStudents();
    }
    public void setStudents(Set<Student> students) {
        groupManager.setStudents(students);
    }
    public void generateGroups(int amount, boolean notEmpty, StudentClass studentClass) {
        try
        {
            List<BoBGroup> groups;
            if (!notEmpty)
            {
                groups = GroupManager.generateRandomGroups(studentClass, amount);
            } else
            {
                groups = GroupManager.generateEmptyGroups(amount);
            }
            sessionManager.clearTempGroups();
            sessionManager.addAllToTempGroup(groups);
            applyGrouplessStudentFilter();
        } catch (Exception e)
        {
            throw new IllegalArgumentException(e.getMessage());
        } 
    }

    public void generatePaths() {
        SessionManager.generatePaths(sessionManager.getTempGroups(), boxManager.getSelected());
    }

    public void setManagerMode(Class<? extends IManageable> className, PersistMode persistMode) {
        System.out.println(className.getSimpleName());
        managers.get(className.getSimpleName()).setManagerMode(persistMode);
    }
    
       public <T extends IManageable> T getSelectedItem(Class<T> className)
    {
        return (T) managers.get(className.getSimpleName()).getSelected();
    }
    public void setSelectedItem(IManageable item) {
        managers.get(item.getClass().getSimpleName()).setSelected(item);
    }
    
    public void setGroupName(String text) {
        ((BoBGroup)groupManager.getSelected()).setName(text);
    }

    public ObservableList<Goal> getGoals() {
        return boxManager.getGoals();
    }
    
    public int getAmountOfStudents() {
        return 10;
    }
    public void removeSession() {
        try
        {
            sessionManager.delete();
        } catch (InvocationTargetException ex)
        {
            Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void applyGrouplessStudentFilter() {
        classManager.applyGrouplessFilter(sessionManager.getTempGroups().stream().map(e -> e.getStudents()).flatMap(List::stream).collect(Collectors.toList()));
    }
}
