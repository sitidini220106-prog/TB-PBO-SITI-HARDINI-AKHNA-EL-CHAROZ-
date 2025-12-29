package services;

// Implementasi interface LaundryService
public class KiloanService implements LaundryService {

    // Harga per kilogram
    private final double pricePerKg = 7000;

    // Perhitungan harga laundry
    @Override
    public double calculatePrice(double qty) {
        return qty * pricePerKg;
    }

    // Nama layanan
    @Override
    public String getServiceName() {
        return "Laundry Kiloan";
    }
}
