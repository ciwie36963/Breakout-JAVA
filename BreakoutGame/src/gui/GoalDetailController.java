/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import domain.ExerciseDomainController;
import domain.Goal;
import domain.PersistMode;
import domain.managers.IManageable;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Matthias
 */
public class GoalDetailController extends AnchorPane implements Observer{

    @FXML
    private JFXTextField txtGoalName;
    @FXML
    private JFXButton btnSave;

    private ExerciseDomainController dc;
    
    private JFXDialog dialog;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Label lblError;
    @FXML
    private Label lblTitle;

    public GoalDetailController(ExerciseDomainController dc, JFXDialog dia)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GoalDetail.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }

        this.dc = dc;
        this.dialog = dia;
        Goal goal = dc.getSelectedItem(Goal.class);
        if(goal != null)
            setTextFields(goal);
    }
    
    
    
    private void addNewGoal(ActionEvent event)
    {
        dc.setManagerMode(Goal.class, PersistMode.NEW);
        dc.setSelectedItem(new Goal());
    }

    @FXML
    private void saveGoal(ActionEvent event)
    {
        try
        {
            dc.saveGoal(txtGoalName.getText());
            dc.setManagerMode(Goal.class, PersistMode.UPDATE);
            dialog.close();
        } catch (Exception e)
        {
            setErrorDialog(e);
        }
    }

    private void deleteGoal(ActionEvent event)
    {
        try {
            dc.deleteGoal();
        } catch(Exception e) {
            setErrorDialog(e);
        }
    }

    @Override
    public void update(Observable o, Object arg)
    {
        Goal goal = (Goal) arg;
        setTextFields(goal);
    }
    
          private void setErrorDialog(Exception ex) {
           lblError.setVisible(true);
        lblError.setText(ex.getMessage());
    }
          private void setTextFields(Goal goal) {
                 txtGoalName.setText(goal.getCode());
        if(goal.getCode() == null || goal.getCode().trim().isEmpty()) {
              lblTitle.setText("Nieuwe doelstelling");
        } else
            lblTitle.setText("Doelstelling details van: " + goal.getCode());
          }
    
}
