package services;

// Interface LaundryService
public interface LaundryService {

    // Method untuk menghitung harga
    double calculatePrice(double qty);

    // Method untuk mengambil nama layanan
    String getServiceName();
}

