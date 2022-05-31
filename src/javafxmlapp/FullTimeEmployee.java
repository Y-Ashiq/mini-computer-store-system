package javafxmlapp;

public class FullTimeEmployee extends Employee  {
    
    public FullTimeEmployee(String username, String password, String name, String email, String job, double salary){
        super(salary, job, username, password, name, email);
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
    
    // getSalary and other useful functions are in the Employee class
}
