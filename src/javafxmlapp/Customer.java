package javafxmlapp;

public class Customer extends Person {
    private String username;
    private String password;

    public Customer(String username, String password, String name, String email) {
        // Sets the name and email (from Person constructor)
        super(name, email);
        // Sets username and password
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
