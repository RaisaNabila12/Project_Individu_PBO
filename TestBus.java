import java.util.Scanner; // Menggunakan Scanner untuk input pengguna
// Gunakan koleksi data seperti ArrayList/List di Class Bus. 
// Gunakan Exception Handling untuk input (walaupun tidak diwajibkan untuk semua, ini contoh) 

public class TestBus {
    
    private static Bus transKoetaradja = new Bus();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextPenumpangId = 1; // Untuk ID unik otomatis

    public static void main(String[] args) {
        
        // Loop utama program
        int pilihan = 0;
        do {
            tampilkanMenu();
            
            try {
                // Pilihan: [cite: 72]
                System.out.print("Pilihan: ");
                pilihan = Integer.parseInt(scanner.nextLine());
                
                switch (pilihan) {
                    case 1:
                        naikkanPenumpangDariInput(); // 1. Naikkan Penumpang 
                        break;
                    case 2:
                        turunkanPenumpangDariInput(); // 2. Turunkan Penumpang 
                        break;
                    case 3:
                        lihatPenumpang(); // 3. Lihat Penumpang 
                        break;
                    case 0:
                        System.out.println("Terima kasih telah menggunakan simulasi Bus Trans Koetaradja.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } catch (NumberFormatException e) {
                // Exception Handling [cite: 127]
                System.out.println("Input harus berupa angka.");
                pilihan = -1; // Ulangi loop
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
                pilihan = -1; // Ulangi loop
            }

            System.out.println("==="); // Pemisah
            
        } while (pilihan != 0);
        
        scanner.close();
    }

    private static void tampilkanMenu() {
        System.out.println("\n===== BUS TRANS KOETARADJA ====="); // Contoh simulasi 
        System.out.println("MENU:"); 
        System.out.println("1. Naikkan Penumpang"); 
        System.out.println("2. Turunkan Penumpang");
        System.out.println("3. Lihat Penumpang dan Total Pendapatan"); 
        System.out.println("0. Keluar");
    }

    private static void naikkanPenumpangDariInput() {
        try {
            System.out.print("Nama: "); // Nama 
            String nama = scanner.nextLine();
            
            System.out.print("Umur: "); // Umur 
            int umur = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Hamil (y/n): "); // Hamil (y/n) [cite: 84]
            String hamilStr = scanner.nextLine();
            boolean hamil = hamilStr.equalsIgnoreCase("y");
            
            // Membuat objek baru 
            Penumpang p = new Penumpang(nextPenumpangId++, nama, umur, hamil);
            
            // Memanggil method 
            if (transKoetaradja.naikkanPenumpang(p)) { 
                // Penumpang Berhasil ditambahkan! 
                // Pesan berhasil/gagal sudah ada di method naikkanPenumpang
            }
        } catch (NumberFormatException e) {
            System.out.println("Gagal: Input umur harus berupa angka.");
        }
    }
    
    private static void turunkanPenumpangDariInput() {
        // Nama 
        System.out.print("Nama yang akan turun: "); 
        String nama = scanner.nextLine();
        
        // Memanggil method 
        if (transKoetaradja.turunkanPenumpang(nama)) {
            // Penumpang Berhasil Turun! 
            // Pesan berhasil/gagal sudah ada di method turunkanPenumpang
        } else {
            // Penumpang Tidak ditemukan! 
            // Pesan gagal sudah ada di method turunkanPenumpang
        }
    }

    private static void lihatPenumpang() {
        System.out.println("\n--- Data Bus Saat Ini ---");
        // Memanggil method toString() dari objek Bus
        System.out.println(transKoetaradja.toString()); 
        System.out.println("-------------------------");
    }
}
