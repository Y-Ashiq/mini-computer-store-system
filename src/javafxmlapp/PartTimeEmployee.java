package javafxmlapp;

public class PartTimeEmployee extends Employee{

    public PartTimeEmployee(String username, String password, String name, String email, String job, double salary) {
        super(salary, job, username, password, name, email);
    }
   
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    public double getSalary(){
        return salary / 1.2;
    }
    
    // other useful functions are in the Employee class
}
