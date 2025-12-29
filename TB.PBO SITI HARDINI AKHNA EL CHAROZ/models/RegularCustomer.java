package models;

// Class RegularCustomer mewarisi Customer
public class RegularCustomer extends Customer {

    // Constructor memanggil constructor superclass
    public RegularCustomer(String name) {
        super(name);
    }

    // Diskon pelanggan regular = 0%
    @Override
    public double getDiscount() {
        return 0.0;
    }

    // Tipe pelanggan Regular
    @Override
    public String getCustomerType() {
        return "Regular";
    }
}


