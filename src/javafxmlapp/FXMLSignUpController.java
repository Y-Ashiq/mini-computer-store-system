/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ahmou
 */
public class FXMLSignUpController implements Initializable {
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private ChoiceBox jobChoice;
    
    @FXML
    private TextField fullNameField;
    
    @FXML
    private TextField emailField;
    
    @FXML
    private Button signUp;
    
    @FXML
    private Label warningText;
    
    // Sign Up Button Action
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        // Gets variables from textfields and dropdown menu
        String username = usernameField.getText();
        String password = passwordField.getText();
        String fullName = fullNameField.getText();
        String email = emailField.getText();
        String job = jobChoice.getValue().toString();
        
        // Checks if they're empty and warns the user
        if (username == null || username.isEmpty()){
            warningText.setText("Please enter a valid username");
        }
        else if (password == null || password.isEmpty()){
            warningText.setText("Please enter a valid password");
        }
        else if (fullName == null || fullName.isEmpty()){
            warningText.setText("Please enter a valid full name");
        }
        else if (email == null || email.isEmpty()){
            warningText.setText("Please enter a valid Email");
        }
        else{
            // Sign Up
            
            // Checks if username already exists in Customers or Employees folder
            // And doesn't create a new user if a file is found
            File checkFile = new File("Database/Customers/" + username + ".txt");
            if (checkFile.exists()){
                warningText.setText("This user already exists");
                return;
            }
            checkFile = new File("Database/Employees/" + username + ".txt");
            if (checkFile.exists()){
                warningText.setText("This user already exists");
                return;
            }
            
            // Adds a new customer/employee
            warningText.setText(null);
            if (job.equals("Customer")){
                DBHandler.addcustomer(username, password, fullName, email);
            }
            else if (job.equals("Half Time Employee")){
                DBHandler.addemployee(username, password, fullName, email, job, 1000);
            }
            else if (job.equals("Full Time Employee")){
                DBHandler.addemployee(username, password, fullName, email, job, 1200);
            }
            
            // Opens sign in page
            OpenWindow("FXMLDocument.fxml", "Sign In Page", signUp);
        }
    }
    
    // Switches current page
    private void OpenWindow(String FXMLName, String windowName, Button button) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(FXMLName));
        Stage stage = new Stage() ;
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle(windowName);
        stage.show();
        Stage temp = (Stage) button.getScene().getWindow();
        temp.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // Initializes dropdown menu
        jobChoice.getItems().removeAll(jobChoice.getItems());
        jobChoice.getItems().addAll("Customer", new Separator(), "Half Time Employee", "Full Time Employee");
        jobChoice.getSelectionModel().select("Customer");
        
        // Creates the database directories using multithreading
        Thread thread1 = new Thread(new DBHandler("Database/Customers/"));
        Thread thread2 = new Thread(new DBHandler("Database/Employees/"));
        Thread thread3 = new Thread(new DBHandler("Database/Products/"));
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
