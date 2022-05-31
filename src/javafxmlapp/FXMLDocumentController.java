
package javafxmlapp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author ahmou
 */

// This is the sign in page
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Button signIn;
    
    @FXML
    private Label warningText;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        // Gets username and password from text boxes
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        // If username or password are empty, warn the user and don't sign in
        if (username == null || username.isEmpty()){
            warningText.setText("Please enter a valid username");
        }
        else if (password == null || password.isEmpty()){
            warningText.setText("Please enter a valid password");
        }
        
        else{
            // Sign In by checking if user exists in Database/Customers/ or Database/Employees/
            File checkFile = new File("Database/Customers/" + username + ".txt");
            if (checkFile.exists()){
                // Sign In customer
                String[] customer = DBHandler.readCustomer(username, password);
                
                // Validate password and sign in to customer table page
                if (password.equals(customer[0])){
                    FXMLEmployeeTableController.tableMode = "Customer";
                    OpenWindow("FXMLEmployeeTable.fxml", "Customer Table Page", signIn);
                    warningText.setText("");
                }
                else{
                    warningText.setText("Incorrect Password");
                }
            }
            
            checkFile = new File("Database/Employees/" + username + ".txt");
            if (checkFile.exists()){
                // Sign In employee
                String[] employee = DBHandler.readEmployee(username, password);
                
                // Validate password and sign in to employee table page
                if (password.equals(employee[0])){
                    FXMLEmployeeTableController.Empusername = username;
                    OpenWindow("FXMLEmployeeTable.fxml", "Employee Table Page", signIn);
                    warningText.setText("");
                }
                else{
                    warningText.setText("Incorrect password");
                }
            }
        else{
            // No user was found
            warningText.setText("Please enter a valid password or username");
            }
        }
    } 
    
    // Opens the sign up page when the user clicks sign up
    @FXML
    private void SignupWindow(ActionEvent event) throws IOException {
        OpenWindow("FXMLSignUp.fxml", "Sign Up Page", signIn);
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
        // Creates the database directories using multithreading
        Thread thread1 = new Thread(new DBHandler("Database/Customers/"));
        Thread thread2 = new Thread(new DBHandler("Database/Employees/"));
        Thread thread3 = new Thread(new DBHandler("Database/Products/"));
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
