package models;

// Class MemberCustomer mewarisi Customer
public class MemberCustomer extends Customer {

    // Constructor
    public MemberCustomer(String name) {
        super(name);
    }

    // Diskon member 10%
    @Override
    public double getDiscount() {
        return 0.1;
    }

    // Tipe pelanggan Member
    @Override
    public String getCustomerType() {
        return "Member";
    }
}
