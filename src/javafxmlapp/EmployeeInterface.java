/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmlapp;

/**
 *
 * @author ahmou
 */

// An interface for employee with all relavant methods
public interface EmployeeInterface {
    public double getSalary();
    public void setSalary(double salary);
    public String getJob();
    public void setJob(String job);
    public String getUsername();
    public void setUsername(String username);
    public String getPassword();
    public void setPassword(String password);
}
