package repositories;

import java.sql.*;
import config.DBConnection;
import models.Order;

public class OrderRepository {

    // CREATE
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

    // READ
public void getAll() {
    String sql = "SELECT * FROM orders";

    try (Connection conn = DBConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        System.out.println("ID | Customer | Service | Qty | Total | Order Date");

        while (rs.next()) {
            System.out.println(
                rs.getInt("id") + " | " +
                rs.getString("customer_name") + " | " +
                rs.getString("service_name") + " | " +
                rs.getDouble("quantity") + " | " +
                rs.getDouble("total") + " | " +
                rs.getTimestamp("order_date")
            );
        }

    } catch (Exception e) {
        System.out.println("Read error: " + e.getMessage());
    }
}

// UPDATE: tambah jumlah laundry dan hitung ulang total
public void updateQuantity(int id, double tambahanQty) {

    String selectSql = "SELECT quantity, total FROM orders WHERE id=?";
    String updateSql = "UPDATE orders SET quantity=?, total=? WHERE id=?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {

        // Ambil data lama
        selectStmt.setInt(1, id);
        ResultSet rs = selectStmt.executeQuery();

        if (rs.next()) {

            double qtyLama = rs.getDouble("quantity");
            double totalLama = rs.getDouble("total");

            // Hitung harga per kg
            double hargaPerKg = totalLama / qtyLama;

            // Hitung data baru
            double qtyBaru = qtyLama + tambahanQty;
            double totalBaru = qtyBaru * hargaPerKg;

            // Update ke database
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setDouble(1, qtyBaru);
            updateStmt.setDouble(2, totalBaru);
            updateStmt.setInt(3, id);
            updateStmt.executeUpdate();

            System.out.println("Order berhasil diupdate.");

        } else {
            System.out.println("Order dengan ID tersebut tidak ditemukan.");
        }

    } catch (Exception e) {
        System.out.println("Update error: " + e.getMessage());
    }
}

    // DELETE
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
