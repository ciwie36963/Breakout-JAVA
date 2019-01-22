/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.managers.SessionManager;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.Test;
import persistence.PersistenceController;

/**
 *
 * @author Matthias
 */
public class SessionTest {
    
        @Test
        public void correcteConstructor() {
            Session session = new Session(new Box(), new ArrayList<BoBGroup>(), "test", new StudentClass("TEST"), "TEST", LocalDate.now(), true, true);
        }
    
    
       @Test(expected=IllegalArgumentException.class)
       public void nullNameThrowsException() {
           new Session(new Box(), new ArrayList<BoBGroup>(), "TEST", new StudentClass("TEST"), null, LocalDate.now(), true, true);
       }
         @Test(expected=IllegalArgumentException.class)
       public void emptyNameThrowsException() {
           new Session(new Box(), new ArrayList<BoBGroup>(), "TEST", new StudentClass("TEST"), "    ", LocalDate.now(), true, true);
       }
        @Test(expected=IllegalArgumentException.class)
       public void nullDescriptionThrowsException() {
           new Session(new Box(), new ArrayList<BoBGroup>(), null, new StudentClass("TEST"), "TEST", LocalDate.now(), true, true);
       }
         @Test(expected=IllegalArgumentException.class)
       public void emptyDescriptionThrowsException() {
           new Session(new Box(), new ArrayList<BoBGroup>(), "   ", new StudentClass("TEST"), "TEST", LocalDate.now(), true, true);
       }
       
          @Test(expected=IllegalArgumentException.class)
       public void activatedSessionDeletionError() throws InvocationTargetException {
           Session session = new Session(new Box(), new ArrayList<BoBGroup>(), "test", new StudentClass("TEST"), "TEST", LocalDate.now(), true, true);
              SessionManager sessionManager = new SessionManager(new PersistenceController());
              sessionManager.setSelected(session);
              session.setSessionStatus(SessionStatus.ACTIVATED);
              sessionManager.delete();
       }
       
       @Test(expected=IllegalArgumentException.class)
       public void dateBeforeToday() {
            new Session(new Box(), new ArrayList<BoBGroup>(), "test", new StudentClass("TEST"), "TEST", LocalDate.now().minusDays(1), true, true);
       }
      @Test(expected=IllegalArgumentException.class)
       public void notDistanctLearningBoxInvalid() {
           Box box = new Box("Test", "Test", new HashSet<Exercise>(Arrays.asList(new Exercise[] {
               new Exercise()
           })), new ArrayList<>());
            new Session(box, new ArrayList<BoBGroup>(), "test", new StudentClass("TEST"), "TEST", LocalDate.now(), false, false);
       }
      
       @Test(expected=IllegalArgumentException.class)
       public void distanceLearningForcedFeedback() {
            new Session(new Box(), new ArrayList<BoBGroup>(), "test", new StudentClass("TEST"), "TEST", LocalDate.now().minusDays(1), true, false);
       }
       
       
}
