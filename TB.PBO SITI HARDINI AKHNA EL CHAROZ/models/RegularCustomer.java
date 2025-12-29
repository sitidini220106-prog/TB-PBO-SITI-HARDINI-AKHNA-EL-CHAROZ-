package models;

public class RegularCustomer extends Customer {

    public RegularCustomer(String name) {
        super(name);
    }

    @Override
    public double getDiscount() {
        return 0.0;
    }

    @Override
    public String getCustomerType() {
        return "Regular";
    }
}
