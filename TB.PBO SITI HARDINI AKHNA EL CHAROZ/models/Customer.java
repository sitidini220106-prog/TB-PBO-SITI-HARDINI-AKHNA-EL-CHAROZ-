package models; // Package untuk model / entitas

// Abstract class Customer sebagai superclass
public abstract class Customer {

    // Atribut nama pelanggan
    protected String name;

    // Constructor untuk mengisi nama pelanggan
    public Customer(String name) {
        this.name = name;
    }

    // Method untuk mengambil nama pelanggan
    public String getName() {
        return name;
    }

    // Abstract method untuk diskon (akan dioverride)
    public abstract double getDiscount();

    // Abstract method untuk tipe pelanggan
    public abstract String getCustomerType();
}
