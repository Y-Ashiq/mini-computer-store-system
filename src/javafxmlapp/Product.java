package javafxmlapp;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product implements ItemPrice {
    // SimpleProperty variables are used in the employee/customer tables
    // The big difference is that you use .get() to get the raw variables
    private SimpleStringProperty pName;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty QTY;
    
    public Product(String name, double price, int QTY) {
        // Initializes SimpleProperty variables
        this.pName = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.QTY = new SimpleIntegerProperty(QTY);
    }

    public String getName() {
        return pName.get();
    }

    public void setName(String pName) {
        this.pName = new SimpleStringProperty(pName);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price = new SimpleDoubleProperty(price);
    }

    public int getQTY() {
        return QTY.get();
    }

    public void setQTY(int QTY) {
        this.QTY = new SimpleIntegerProperty(QTY);
    }
    
}
