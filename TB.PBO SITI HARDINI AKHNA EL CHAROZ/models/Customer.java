package models;

public abstract class Customer {

    protected String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double getDiscount();
    public abstract String getCustomerType();
}
