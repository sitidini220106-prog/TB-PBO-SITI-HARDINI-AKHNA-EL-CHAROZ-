package models;

public class MemberCustomer extends Customer {

    public MemberCustomer(String name) {
        super(name);
    }

    @Override
    public double getDiscount() {
        return 0.1; // 10% diskon
    }

    @Override
    public String getCustomerType() {
        return "Member";
    }
}
