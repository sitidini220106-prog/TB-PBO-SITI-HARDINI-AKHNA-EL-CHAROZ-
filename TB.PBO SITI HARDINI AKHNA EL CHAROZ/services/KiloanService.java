package services;
public class KiloanService implements LaundryService {

    private final double pricePerKg = 7000;

    @Override
    public double calculatePrice(double qty) {
        return qty * pricePerKg;
    }

    @Override
    public String getServiceName() {
        return "Laundry Kiloan";
    }
}
