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
import domain.Category;
import domain.ExerciseDomainController;
import domain.Goal;
import domain.GroupOperation;
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
public class ClassDetailController extends AnchorPane implements Observer{

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private JFXTextField txtClassName;
    @FXML
    private JFXButton btnSave;

    private ExerciseDomainController dc;
    
    private JFXDialog dialog;
    @FXML
    private Label lblError;
    @FXML
    private Label lblTitle;

    public ClassDetailController(ExerciseDomainController dc, JFXDialog dia)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClassDetail.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.printf(ex.getMessage());
        }
        this.dc = dc;
        this.dialog = dia;
         Category category = dc.getSelectedItem(Category.class);
        if(category != null)
            update(null, category);
    }
    
    
    private void addNewClass(ActionEvent event)
    {
        try
        {
          dc.setManagerMode(Category.class, PersistMode.NEW);
          dc.setSelectedItem(new Category());
        } catch (Exception e)
        {
            setErrorDialog(e);
        }
    }

    @FXML
    private void saveClass(ActionEvent event)
    {
        try {
             dc.saveCategory(txtClassName.getText());
        dc.setManagerMode(Category.class, PersistMode.UPDATE);
        dialog.close();
        } catch (Exception e) {
            setErrorDialog(e);
        }
       
    }

    private void removeClass(ActionEvent event)
    { 
        try {
            dc.deleteCategory();
        } catch(Exception ex) {
            setErrorDialog(ex);
        }
    }

    @Override
    public void update(Observable o, Object arg)
    {
        Category cat = (Category) arg;
        setTextFields(cat);
        }
    
    private void setErrorDialog(Exception ex) {
            lblError.setVisible(true);
        lblError.setText(ex.getMessage());
    }
    
    private void setTextFields(Category cat) {
         txtClassName.setText(cat.getName());
         if(cat.getName()== null || cat.getName().trim().isEmpty()) {
              lblTitle.setText("Nieuw vak");
        } else
            lblTitle.setText("Vak details van: " + cat.getName());
    }
    
}
