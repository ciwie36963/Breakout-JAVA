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
import domain.BoBAction;
import domain.BoxController;
import domain.GroupOperation;
import domain.PersistMode;
import domain.managers.IManageable;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
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
public class BoBActionDetailController extends AnchorPane implements Observer{

    /**
     * Initializes the controller class.
     */
    private BoxController dc;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXTextField txtActionName;
   

    private JFXDialog dialog;
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblError;
    public BoBActionDetailController(BoxController dc, JFXDialog dia)
    {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("BoBActionDetail.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }
        this.dc = dc;
        dc.addObserverAction(this);
       
        this.dialog = dia;
           BoBAction bobaction = dc.getSelectedItem(BoBAction.class);
        if(bobaction != null)
            setTextFields(bobaction);
    }

    private void addNewAction(ActionEvent event)
    {
        dc.setManagerMode(BoBAction.class, PersistMode.NEW);
        dc.setSelectedItem(new BoBAction());
        txtActionName.requestFocus();
    }

    @FXML
    private void saveAction(ActionEvent event)
    {
        try
        {
            dc.saveAction(txtActionName.getText());
            dialog.close();
        } catch (Exception e)
        {
            setErrorDialog(e);
        }
    }

    @Override
    public void update(Observable o, Object arg)
    {
        
       
        BoBAction action = (BoBAction) arg;
        setTextFields(action);
    }

    private void removeAction(ActionEvent event)
    {
        try {
            dc.removeAction(); 
        } catch(IllegalArgumentException ex) {
            setErrorDialog(ex);
        }
      
    }
         private void setErrorDialog(Exception ex) {
             lblError.setVisible(true);
             lblError.setText(ex.getMessage());
    }

    private void setTextFields(BoBAction action) {
         if (action.getName() == null || action.getName().trim().isEmpty())
        {
            lblTitle.setText("Nieuwe actie");
                btnSave.setDisable(false);
                   txtActionName.setDisable(false);
        } else {
            lblTitle.setText("Actiedetails van: " + action.getName());
        if(!action.getName().equals("Zoek een kist")) {
          
            btnSave.setDisable(false);
   
       txtActionName.setDisable(false);
        } else {
                  btnSave.setDisable(true);
                   txtActionName.setDisable(true);
                   lblTitle.setText(lblTitle.getText() + " (niet wijzigbaar)");
        }
        txtActionName.setText(action.getName());              
        }
    }
    
    
    
}
