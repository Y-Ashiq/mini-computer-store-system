/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Youssef
 */
// Despite being called Employee Table, this is also the page for customers
public class FXMLEmployeeTableController implements Initializable {
    
    // Initializes observablelist (used for table)
    ObservableList <Product> list = FXCollections.observableArrayList();
    
    // Depending on tableMode will show or hide employee-specific functions
    public static String tableMode = "Employee";
    public static String Empusername = "";
    @FXML
    private TableView<Product> protable;
    @FXML
    private TableColumn<Product, String> pname;
    @FXML
    private TableColumn<Product, Integer> pqty;
    @FXML
    private TableColumn<Product, Double> pprice;
    @FXML
    private TextField pName;
    @FXML
    private TextField pPrice;
    @FXML
    private TextField pQTY;
    @FXML
    private Button Add_Product;
    @FXML
    private Button Remove_Product;
    @FXML
    private Button Download_Files;
    
    @FXML
    private GridPane gridPane;
    @FXML
    private Label EmpName;
    @FXML
    private Label EmpJob;
    @FXML
    private Label EmpSalary;
    @FXML
    private GridPane EMPGrid;
    
    
    // Adds a product
    @FXML
    private void handleButtonAction(ActionEvent event){
       // Gets text from text fields
       String name = pName.getText();
       double price = Double.parseDouble(pPrice.getText());
       int QTY = Integer.parseInt(pQTY.getText());
       
       // Creates a new product file and adds it to the table
       Product pro = new Product(name,price,QTY);
       protable.getItems().add(pro);
       DBHandler.addProduct(name, price, QTY);
    }
    
    @FXML
    private void RemoveButton(ActionEvent event){
        // Removes selected product in table
        ObservableList <Product> p1,p2;
        p1 = protable.getItems();
        p2 = protable.getSelectionModel().getSelectedItems();
        String name = p2.get(0).getName();
        p2.forEach(p1 :: remove);
        
        // Removes selected product's file
        DBHandler.deleteProduct(name);
    }
    
    @FXML
    private void DownloadFiles(ActionEvent event){
        try{
            Thread thread1 = new Thread(new Server());
            Thread thread2 = new Thread(new Client());
            thread1.start();
            thread2.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    private void productinfo(){
        // Initializes table
        pname.setCellValueFactory(new PropertyValueFactory<>("name"));
        pqty.setCellValueFactory(new PropertyValueFactory<>("QTY"));
        pprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        protable.setItems(list);
        
        // Adds all products from Database/Products/ folder into table
        Product[] prodArr = DBHandler.readProductFolder();
        list.addAll(prodArr);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productinfo();
        // Checks if the user is a customer. If yes, removes employee-specific buttons and textareas
        // (All of which are in the gridpane)
        if (tableMode.equals("Customer")){
            gridPane.setVisible(false);
            EMPGrid.setVisible(false);
        }
        
        else{
            Employee e = DBHandler.readEmployee(Empusername);
            EmpName.setText(String.valueOf(e.getUsername()));
            EmpSalary.setText(String.valueOf(e.getSalary()));
            EmpJob.setText(String.valueOf(e.getJob()));
        }
        
    }
}
