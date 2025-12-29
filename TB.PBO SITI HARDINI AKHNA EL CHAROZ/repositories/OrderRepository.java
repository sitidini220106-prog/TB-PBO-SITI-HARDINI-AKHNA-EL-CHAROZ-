package repositories;

import java.sql.*;
import java.util.ArrayList;   // COLLECTION FRAMEWORK
import java.util.List;

import config.DBConnection;
import models.Order;
import models.Customer;
import models.RegularCustomer;
import services.KiloanService;
import services.LaundryService;

public class OrderRepository {

    // ================= CREATE =================
    public void insert(Order order) {

        String sql = "INSERT INTO orders (customer_name, service_name, quantity, total, order_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, order.getCustomer().getName());
            stmt.setString(2, order.getService().getServiceName());
            stmt.setDouble(3, order.getQuantity());
            stmt.setDouble(4, order.getTotal());
            stmt.setString(5, order.getOrderDate());

            stmt.executeUpdate();
            System.out.println("Data berhasil disimpan");

        } catch (Exception e) {
            System.out.println("Insert error: " + e.getMessage());
        }
    }

    // ================= READ (COLLECTION FRAMEWORK) =================
    public void getAll() {

        // COLLECTION FRAMEWORK
        List<Order> orders = new ArrayList<>();

        String sql = "SELECT * FROM orders";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                // Ambil data dari database
                String name = rs.getString("customer_name");
                double qty = rs.getDouble("quantity");
                String date = rs.getString("order_date");

                // Object pendukung
                Customer customer = new RegularCustomer(name);
                LaundryService service = new KiloanService();

                // Object Order
                Order order = new Order(customer, service, qty);
                order.setOrderDate(date);

                // SIMPAN KE COLLECTION
                orders.add(order);
            }

            // TAMPILKAN DATA DARI COLLECTION
            System.out.println("\n=== DATA ORDER ===");
            for (Order o : orders) {
                System.out.println(o.getCustomer().getName()
                        + " | " + o.getQuantity() + " kg"
                        + " | Rp " + o.getTotal()
                        + " | " + o.getOrderDate());
            }

        } catch (Exception e) {
            System.out.println("Read error: " + e.getMessage());
        }
    }

    // ================= UPDATE =================
    public void updateQuantity(int id, double tambahanKg) {

        String sqlSelect = "SELECT quantity FROM orders WHERE id=?";
        String sqlUpdate = "UPDATE orders SET quantity=?, total=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement psSelect = conn.prepareStatement(sqlSelect)) {

            psSelect.setInt(1, id);
            ResultSet rs = psSelect.executeQuery();

            if (rs.next()) {

                double lama = rs.getDouble("quantity");
                double baru = lama + tambahanKg;

                double hargaPerKg = 5000;
                double totalBaru = baru * hargaPerKg;

                PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
                psUpdate.setDouble(1, baru);
                psUpdate.setDouble(2, totalBaru);
                psUpdate.setInt(3, id);
                psUpdate.executeUpdate();

                System.out.println("Order berhasil diupdate");

            } else {
                System.out.println("ID tidak ditemukan");
            }

        } catch (Exception e) {
            System.out.println("Update error: " + e.getMessage());
        }
    }

    // ================= DELETE =================
    public void delete(int id) {

        String sql = "DELETE FROM orders WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Data berhasil dihapus");

        } catch (Exception e) {
            System.out.println("Delete error: " + e.getMessage());
        }
    }
}

