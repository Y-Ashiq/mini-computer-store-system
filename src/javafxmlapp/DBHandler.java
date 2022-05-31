package javafxmlapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public  class DBHandler implements Runnable {
    
    // Creating an object of DBHandler is only used in multithreading the creation of directories
    String dirName;
    DBHandler(String dirName){
        this.dirName = dirName;
    }
    
    // Adds a directory, used in initializing the database
    public void addDirectory(){
        File file = new File(dirName);
        file.mkdirs();
    }
    
    // Used in multithreading, adds all three directories in the same time
    @Override
    public void run(){
        addDirectory();
    }
    
    // Adds an employee to Database/Employees/(username).txt with all variables separated by commas (CSV)
    public static void addemployee (String username, String password, String name, String email, String job, float salary){
        File file = new File("Database/Employees/" + username + ".txt");
        
        // Attempts to create a new file
        try{
            if (file.createNewFile()){
                // If successfully created the file, insert variables into text file separated by commas
                FileWriter writer = new FileWriter("Database/Employees/" + username + ".txt");
                writer.write(password + "," + name + "," + email + "," + job + "," + salary);
                writer.close();
                System.out.println("Created an employee");
            }
            else{
                // If failed to create a file, it's because the file already exists    
                System.out.println("Already there");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    
    // Adds a customer to Database/Customers/(username).txt with all variables separated by commas (CSV)
    public static void addcustomer(String username, String password, String name, String email){
        File file = new File("Database/Customers/" + username + ".txt");
        
        // Attempts to create a new file
        try{
            if (file.createNewFile()){
                // If successfully created the file, insert variables into text file separated by commas
                FileWriter writer = new FileWriter("Database/Customers/" + username + ".txt");
                writer.write(password + "," + name + "," + email);
                writer.close();
                System.out.println("Created a customer");
            }
            else{
                // If failed to create a file, it's because the file already exists
                System.out.println("Already there");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    
    // Adds a product to Database/Products/name.txt with all variables separated by commas (CSV)
    public static void addProduct(String name , double price , int QTY){
        File file = new File("Database/Products/" + name + ".txt");
        
        // Attempts to create a new file
        try{
            if (file.createNewFile()){
                // If successfully created the file, insert variables into text file separated by commas
                FileWriter writer = new FileWriter("Database/Products/" + name + ".txt");
                writer.write(price + "," + QTY);
                writer.close();
                System.out.println("Created a product");
            }
            else{
                // If failed to create a file, it's because the file already exists
                System.out.println("Already there");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    
    
    // Deletes a product, used in the employee table.
     public static void deleteProduct(String name){
        try{
            File file = new File("Database/Products/" + name + ".txt");
            //System.gc();
            //System.out.println(file.getAbsolutePath());
             
            // Attempts to delete the file
            //System.out.println(file.canRead() + "  " + file.canWrite());
            if (file.delete()){
                System.out.println("Removed a product (" + name + ")");
            }
            else{
                // If failed, it's because the file doesn't exist
                System.out.println("File doesn't exist");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    // Reads customer data and separates variables by comma, returning a string array
    public static String[] readCustomer(String username, String password){
        try{
            File file = new File("Database/Customers/" + username + ".txt");
            Scanner reader = new Scanner(file);
            
            String data = reader.nextLine();
            String[] output = data.split(",");
            reader.close();
            return output;
        }
        catch (FileNotFoundException e){
            return null;
        }
    }
    
    // Reads employee data and separated variables by comma, returning a string array
    public static String[] readEmployee(String username, String password){
        try{
            File file = new File("Database/Employees/" + username + ".txt");
            Scanner reader = new Scanner(file);
            String data = reader.nextLine();
            
            String[] output = data.split(",");
            reader.close();
            return output;
        }
        catch (FileNotFoundException e){
            return null;
        }
    }
    
    // Reads an employee by username only and returns an employee object (used for polymorphism)
    public static Employee readEmployee(String username){
        Employee emp;
        
        try{
            File file = new File("Database/Employees/" + username + ".txt");
            Scanner reader = new Scanner(file);
            String data = reader.nextLine();
            
            String[] output = data.split(",");
            if (output[3] == "Full Time Employee"){
                emp = new FullTimeEmployee(username, output[0], output[1], output[2], output[3], Double.parseDouble(output[4]));
            }
            else{
                emp = new FullTimeEmployee(username, output[0], output[1], output[2], output[3], Double.parseDouble(output[4]));
            }
            reader.close();
            return emp;
        }
        catch (FileNotFoundException e){
            return null;
        }
    }
    
    // Reads product data and separated variables by comma, returning a string array
    public static String[] readProduct(String name){
        try{
            File file = new File("Database/Products/" + name + ".txt");
            Scanner reader = new Scanner(file);
            String data = reader.nextLine();
            String[] output = data.split(",");
            output[1] = output[1].trim();
            reader.close();
            return output;
        }
        catch (FileNotFoundException e){
            return null;
        }
    }
    
    // Overload of readProduct to read directly from a file
    public static String[] readProduct(File f){
        try{
            Scanner reader = new Scanner(f);
            //System.out.println(f.getName());
            String data = reader.nextLine();
            String[] output = data.split(",");
            output[1] = output[1].trim();
            //System.out.println(output[0] + " " + output[1]);
            reader.close();
            return output;
        }
        catch (FileNotFoundException e){
            return null;
        }
    }
    
    // Reads every file in the Products folder and returns everything as a product array, used in tables
    public static Product[] readProductFolder(){
        File[] files = new File("Database/Products/").listFiles();
        Product[] products = new Product[files.length];
        int i = 0;
        // Loops over every file in the folder and creates a product with all the variables in it
        for (File f : files){
            System.out.println(f.getName());
            String[] productFiles = readProduct(f);
            // Product name, product price, product qty
            products[i] = new Product(f.getName().replace(".txt", ""), Double.parseDouble(productFiles[0]), Integer.parseInt(productFiles[1].trim()));
            i++;
        }
        return products;
    }
    
}
