package main;

import java.util.Scanner;
import repositories.OrderRepository;
import models.Customer;
import models.RegularCustomer;
import models.MemberCustomer;
import models.Order;
import services.LaundryService;
import services.KiloanService;

public class main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        OrderRepository repo = new OrderRepository();

        while (true) {

            System.out.println("\n=== MOMICHI LAUNDRY ===");
            System.out.println("1. Tambah Order");
            System.out.println("2. Lihat Semua Order");
            System.out.println("3. Update Order (Tambah Kilo)");
            System.out.println("4. Hapus Order");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            int pilih = input.nextInt();
            input.nextLine(); 
            // ===================== TAMBAH ORDER =====================
            if (pilih == 1) {

                System.out.println("Tipe Pelanggan:");
                System.out.println("1. Regular");
                System.out.println("2. Member");
                System.out.print("Pilih: ");
                int tipe = input.nextInt();
                input.nextLine();

                System.out.print("Nama pelanggan: ");
                String nama = input.nextLine();

                System.out.print("Jumlah laundry (kg): ");
                double qty = input.nextDouble();

                // INHERITANCE + POLYMORPHISM
                Customer customer;
                if (tipe == 2) {
                    customer = new MemberCustomer(nama);
                } else {
                    customer = new RegularCustomer(nama);
                }

                // INTERFACE
                LaundryService service = new KiloanService();

                // OBJECT + CONSTRUCTOR
                Order order = new Order(customer, service, qty);

                // JDBC CREATE
                repo.insert(order);
            }

            // ===================== LIHAT DATA =====================
            else if (pilih == 2) {
                repo.getAll();
            }

            // ===================== UPDATE ORDER =====================
            else if (pilih == 3) {

                System.out.print("Masukkan ID order: ");
                int id = input.nextInt();

                System.out.print("Masukkan tambahan kilo: ");
                double tambah = input.nextDouble();

                // JDBC UPDATE + PERHITUNGAN
                repo.updateQuantity(id, tambah);
            }

            // ===================== DELETE ORDER =====================
            else if (pilih == 4) {

                System.out.print("Masukkan ID order yang akan dihapus: ");
                int id = input.nextInt();

                // JDBC DELETE
                repo.delete(id);
            }

            // ===================== KELUAR =====================
            else if (pilih == 0) {
                System.out.println("Terima kasih, program selesai.");
                break;
            }

            // ===================== VALIDASI =====================
            else {
                System.out.println("Menu tidak valid.");
            }
        }

        input.close();
    }
}
