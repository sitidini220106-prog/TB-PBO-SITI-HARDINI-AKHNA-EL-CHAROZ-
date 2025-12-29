package models;

import services.LaundryService;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {

    private Customer customer;
    private LaundryService service;
    private double quantity;
    private double total;
    private String orderDate;

    public Order(Customer customer, LaundryService service, double quantity) {
        this.customer = customer;
        this.service = service;
        this.quantity = quantity;
        this.orderDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        calculateTotal();
    }

    private void calculateTotal() {
        double subtotal = service.calculatePrice(quantity);
        total = subtotal - (subtotal * customer.getDiscount());
    }

    // ====== GETTER (INI PENTING) ======
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
