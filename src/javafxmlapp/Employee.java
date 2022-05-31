package javafxmlapp;

public class Employee extends Person implements EmployeeInterface {

    private String job;

    private String username;

    private String password;

    public Employee(double salary, String job, String username, String password, String name, String email) {
        // Calls Person constructor to initialize name and email
        super(name, email);
        this.salary = salary;
        this.job = job;
        this.username = username;
        this.password = password;
    }
    
    @Override
    public double getSalary(){
        return salary;
    }
    
    @Override
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }
    
    @Override
    public void setJob(String job) {
        this.job = job;
    }

    public String getUsername() {
        return username;
    }
    
    @Override
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

  }
