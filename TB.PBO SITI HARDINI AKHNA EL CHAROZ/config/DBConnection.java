package config; // Package untuk konfigurasi database

// Import library JDBC
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Class untuk mengatur koneksi database
public class DBConnection {

    // URL koneksi ke database MySQL
    private static final String URL =
            "jdbc:mysql://localhost:3306/momichi_laundry";

    // Username database
    private static final String USER = "root";

    // Password database (kosong jika default XAMPP)
    private static final String PASS = "";

    // Method static untuk mendapatkan koneksi database
    public static Connection getConnection() {

        try {
            // Membuat koneksi ke database menggunakan DriverManager
            Connection conn = DriverManager.getConnection(URL, USER, PASS);

            // Menampilkan pesan jika koneksi berhasil
            System.out.println("Koneksi database berhasil.");

            // Mengembalikan objek Connection
            return conn;

        } catch (SQLException e) {

            // Menangani error jika koneksi gagal
            System.out.println("Koneksi database gagal: " + e.getMessage());

            // Mengembalikan null jika terjadi error
            return null;
        }
    }
}

