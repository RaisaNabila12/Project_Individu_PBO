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

    
