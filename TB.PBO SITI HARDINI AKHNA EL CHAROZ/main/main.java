package main; // Package utama untuk class Main

// Import Scanner untuk input dari user melalui console
import java.util.Scanner;

// Import repository untuk operasi CRUD database
import repositories.OrderRepository;

// Import class model
import models.Customer;
import models.RegularCustomer;
import models.MemberCustomer;
import models.Order;

// Import interface dan implementasi service laundry
import services.LaundryService;
import services.KiloanService;

// Class utama program
public class main {

    // Method main sebagai titik awal program dijalankan
    public static void main(String[] args) {

        // Membuat objek Scanner untuk membaca input user
        Scanner input = new Scanner(System.in);

        // Membuat objek OrderRepository untuk akses database
        OrderRepository repo = new OrderRepository();

        // Perulangan agar menu terus muncul sampai user keluar
        while (true) {

            // Menampilkan menu utama aplikasi
            System.out.println("\n=== MOMICHI LAUNDRY ===");
            System.out.println("1. Tambah Order");
            System.out.println("2. Lihat Semua Order");
            System.out.println("3. Update Order (Tambah Kilo)");
            System.out.println("4. Hapus Order");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            // Menerima pilihan menu dari user
            int pilih = input.nextInt();
            input.nextLine(); // Membersihkan buffer input

            // ===================== TAMBAH ORDER =====================
            if (pilih == 1) {

                // Menampilkan pilihan tipe pelanggan
                System.out.println("Tipe Pelanggan:");
                System.out.println("1. Regular");
                System.out.println("2. Member");
                System.out.print("Pilih: ");

                // Input tipe pelanggan
                int tipe = input.nextInt();
                input.nextLine();

                // Input nama pelanggan
                System.out.print("Nama pelanggan: ");
                String nama = input.nextLine();

                // Input jumlah laundry dalam kilogram
                System.out.print("Jumlah laundry (kg): ");
                double qty = input.nextDouble();

                // INHERITANCE + POLYMORPHISM
                // Objek Customer dapat berupa RegularCustomer atau MemberCustomer
                Customer customer;
                if (tipe == 2) {
                    customer = new MemberCustomer(nama); // Member mendapat diskon
                } else {
                    customer = new RegularCustomer(nama); // Regular tanpa diskon
                }

                // INTERFACE
                // Menggunakan interface LaundryService
                LaundryService service = new KiloanService();

                // OBJECT + CONSTRUCTOR
                // Membuat objek Order dengan constructor
                Order order = new Order(customer, service, qty);

                // JDBC CREATE
                // Menyimpan data order ke database
                repo.insert(order);
            }

            // ===================== LIHAT DATA =====================
            else if (pilih == 2) {

                // JDBC READ
                // Menampilkan semua data order dari database
                repo.getAll();
            }

            // ===================== UPDATE ORDER =====================
            else if (pilih == 3) {

                // Input ID order yang ingin diupdate
                System.out.print("Masukkan ID order: ");
                int id = input.nextInt();

                // Input tambahan kilo laundry
                System.out.print("Masukkan tambahan kilo: ");
                double tambah = input.nextDouble();

                // JDBC UPDATE + PERHITUNGAN
                // Menambah kilo dan menghitung ulang total pembayaran
                repo.updateQuantity(id, tambah);
            }

            // ===================== DELETE ORDER =====================
            else if (pilih == 4) {

                // Input ID order yang akan dihapus
                System.out.print("Masukkan ID order yang akan dihapus: ");
                int id = input.nextInt();

                // JDBC DELETE
                // Menghapus data order dari database
                repo.delete(id);
            }

            // ===================== KELUAR =====================
            else if (pilih == 0) {

                // Mengakhiri program
                System.out.println("Terima kasih, program selesai.");
                break;
            }

            // ===================== VALIDASI =====================
            else {

                // Pesan jika user memilih menu yang tidak tersedia
                System.out.println("Menu tidak valid.");
            }
        }

        // Menutup Scanner setelah program selesai
        input.close();
    }
}

