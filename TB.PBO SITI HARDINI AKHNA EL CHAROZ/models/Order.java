package models;

import services.LaundryService;
import java.text.SimpleDateFormat;
import java.util.Date;

// Class Order untuk menyimpan data transaksi
public class Order {

    // Atribut order
    private Customer customer;
    private LaundryService service;
    private double quantity;
    private double total;
    private String orderDate;

    // Constructor Order
    public Order(Customer customer, LaundryService service, double quantity) {
        this.customer = customer;
        this.service = service;
        this.quantity = quantity;

        // Manipulasi DATE (mengambil tanggal sekarang)
        this.orderDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        // Hitung total saat objek dibuat
        calculateTotal();
    }

    // Method menghitung total pembayaran
    private void calculateTotal() {
        double subtotal = service.calculatePrice(quantity);
        total = subtotal - (subtotal * customer.getDiscount());
    }

    // Getter
    public Customer getCustomer() {
        return customer;
    }

    public LaundryService getService() {
        return service;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }

    public String getOrderDate() {
        return orderDate;
    }
}

