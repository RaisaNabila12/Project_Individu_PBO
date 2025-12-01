import java.util.ArrayList; // Menggunakan ArrayList sebagai koleksi data 
import java.util.List;

public class Bus {
    // Atribut
    // Ongkos bus adalah 2000 untuk setiap penumpang dan nilainya static dan final 
    private static final int ONGKOS_BUS = 2000; // - ONGKOS BUS: int 

    private List<Penumpang> penumpangBiasa; // penumpang Biasa: Penumpang [16] (maksimal 16 kursi) 
    private List<Penumpang> penumpangPrioritas; // penumpang Prioritas: Penumpang[4] (maksimal 4 kursi) 
    private List<Penumpang> penumpangBerdiri; // - penumpang Berdiri: Penumpang [20] (maksimal 20 orang berdiri) 

    private int totalPendapatan; // - totalPendapatan: int

    // Kapasitas Bus
    private static final int KAPASITAS_KURSI_BIASA = 16;
    private static final int KAPASITAS_KURSI_PRIORITAS = 4;
    private static final int KAPASITAS_BERDIRI = 20;
    private static final int MAKSIMAL_KAPASITAS_BUS = 40; // Maksimal kapasitas bus adalah 40 orang 

    // Constructor 
    // + Bus()
    public Bus() {
        // Inisialisasi koleksi data (menggunakan ArrayList) 
        this.penumpangBiasa = new ArrayList<>();
        this.penumpangPrioritas = new ArrayList<>();
        this.penumpangBerdiri = new ArrayList<>();
        // Total pendapatan bus per hari bermula dari nilai 0 
        this.totalPendapatan = 0;
    }

    // Method Accessor (getters) 
    // + getPenumpang Biasa(): Penumpang[] 
    public List<Penumpang> getPenumpangBiasa() {
        return penumpangBiasa;
    }

    // + getPenumpang Prioritas(): Penumpang[] 
    public List<Penumpang> getPenumpangPrioritas() {
        return penumpangPrioritas;
    }

    // + getPenumpang Berdiri(): Penumpang[] 
    public List<Penumpang> getPenumpangBerdiri() {
        return penumpangBerdiri;
    }

    // + getJumlahPenumpang Biasa(): int 
    public int getJumlahPenumpangBiasa() {
        return penumpangBiasa.size();
    }

    // + getJumlah Penumpang Prioritas(): int 
    public int getJumlahPenumpangPrioritas() {
        return penumpangPrioritas.size();
    }

    // + getJumlah Penumpang Berdiri(): int 
    public int getJumlahPenumpangBerdiri() {
        return penumpangBerdiri.size();
    }

    // Menghitung total jumlah penumpang di bus
    public int getTotalPenumpang() {
        return getJumlahPenumpangBiasa() + getJumlahPenumpangPrioritas() + getJumlahPenumpangBerdiri();
    }

    // Mendapatkan total pendapatan bus
    public int getTotalPendapatan() {
        return totalPendapatan;
    }
    
    // Method Mutator (core logic)
    
    // + naikkan Penumpang (penumpang: Penumpang): Boolean 
    public boolean naikkanPenumpang(Penumpang penumpang) {
        // Penumpang tidak bisa lagi menaiki bus jika bus sudah penuh 
        if (getTotalPenumpang() >= MAKSIMAL_KAPASITAS_BUS) {
            System.out.println("Gagal: Bus sudah penuh (Kapasitas Maksimal " + MAKSIMAL_KAPASITAS_BUS + " orang).");
            return false;
        }

        // Saat penumpang naik juga perlu memastikan saldo kartu penumpang masih mencukupi 
        if (penumpang.getSaldo() < ONGKOS_BUS) {
            System.out.println("Gagal: Saldo tidak mencukupi untuk membayar ongkos bus (" + ONGKOS_BUS + "). Saldo: " + penumpang.getSaldo());
            return false;
        }

        // --- Logika Penempatan Penumpang ---

        boolean isPrioritas = penumpang.isPrioritas();

        if (isPrioritas) {
            // 1. Penumpang Prioritas coba duduk di Kursi Prioritas 
            if (getJumlahPenumpangPrioritas() < KAPASITAS_KURSI_PRIORITAS) {
                penumpangPrioritas.add(penumpang);
            }
            // 2. Jika Kursi Prioritas penuh, Penumpang Prioritas boleh duduk di Kursi Biasa 
            else if (getJumlahPenumpangBiasa() < KAPASITAS_KURSI_BIASA) {
                penumpangBiasa.add(penumpang);
            }
            // 3. Jika semua kursi penuh, Penumpang Prioritas harus Berdiri 
            else if (getJumlahPenumpangBerdiri() < KAPASITAS_BERDIRI) {
                penumpangBerdiri.add(penumpang);
            }
        } else {
            // 1. Penumpang Biasa akan duduk di kursi biasa jika masih tersedia 
            if (getJumlahPenumpangBiasa() < KAPASITAS_KURSI_BIASA) {
                penumpangBiasa.add(penumpang);
            }
            // 2. Penumpang biasa dilarang duduk di kursi prioritas 
            // 3. Jika kursi sudah penuh, maka penumpang harus berdiri
            else if (getJumlahPenumpangBerdiri() < KAPASITAS_BERDIRI) {
                penumpangBerdiri.add(penumpang);
            }
        }

        // Cek apakah penumpang berhasil ditempatkan
        if (penumpangBiasa.contains(penumpang) || penumpangPrioritas.contains(penumpang) || penumpangBerdiri.contains(penumpang)) {
            // Kurangi saldo penumpang
            penumpang.kurangiSaldo(ONGKOS_BUS);

            // Bila penumpang berhasil naik, maka total pendapatan bus akan ditambah 
            totalPendapatan += ONGKOS_BUS;

            System.out.println("Berhasil: " + penumpang.getNama() + " berhasil naik. Saldo tersisa: " + penumpang.getSaldo());
            return true;
        } else {
            // Ini seharusnya hanya terjadi jika bus sudah penuh, tapi sebagai fallback
            System.out.println("Gagal: Penumpang tidak dapat ditempatkan di bus.");
            return false;
        }
    }
    
    // + turunkan Penumpang (nama: String): Boolean 
    public boolean turunkanPenumpang(String nama) {
        boolean berhasilTurun = false;
        Penumpang penumpangDitemukan = null;

        // Cari di penumpang Biasa
        for (Penumpang p : penumpangBiasa) {
            if (p.getNama().equalsIgnoreCase(nama)) {
                penumpangDitemukan = p;
                break;
            }
        }
        if (penumpangDitemukan != null) {
            penumpangBiasa.remove(penumpangDitemukan);
            berhasilTurun = true;
        }
        
        // Jika belum ditemukan, cari di penumpang Prioritas
        if (!berhasilTurun) {
            for (Penumpang p : penumpangPrioritas) {
                if (p.getNama().equalsIgnoreCase(nama)) {
                    penumpangDitemukan = p;
                    break;
                }
            }
            if (penumpangDitemukan != null) {
                penumpangPrioritas.remove(penumpangDitemukan);
                berhasilTurun = true;
            }
        }
        
        // Jika belum ditemukan, cari di penumpang Berdiri
        if (!berhasilTurun) {
            for (Penumpang p : penumpangBerdiri) {
                if (p.getNama().equalsIgnoreCase(nama)) {
                    penumpangDitemukan = p;
                    break;
                }
            }
            if (penumpangDitemukan != null) {
                penumpangBerdiri.remove(penumpangDitemukan);
                berhasilTurun = true;
            }
        }

        if (berhasilTurun) {
            System.out.println("Berhasil: " + nama + " berhasil turun.");
            return true;
        } else {
            // Sebaliknya, kembalikan false 
            System.out.println("Gagal: Penumpang " + nama + " tidak ditemukan!");
            return false;
        }
    }

    // Method untuk mencetak informasi Bus 
    // + toString(): String
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        // Penumpang Biasa
        sb.append("Penumpang Biasa (Kursi: ").append(getJumlahPenumpangBiasa()).append("/").append(KAPASITAS_KURSI_BIASA).append("): ");
        if (penumpangBiasa.isEmpty()) {
            sb.append("<kosong>");
        } else {
            for (int i = 0; i < penumpangBiasa.size(); i++) {
                sb.append(penumpangBiasa.get(i).getNama());
                if (i < penumpangBiasa.size() - 1) sb.append(", ");
            }
        }
        sb.append("\n");

        // Penumpang Prioritas
        sb.append("Penumpang Prioritas (Kursi: ").append(getJumlahPenumpangPrioritas()).append("/").append(KAPASITAS_KURSI_PRIORITAS).append("): ");
        if (penumpangPrioritas.isEmpty()) {
            sb.append("<kosong>");
        } else {
            for (int i = 0; i < penumpangPrioritas.size(); i++) {
                sb.append(penumpangPrioritas.get(i).getNama());
                if (i < penumpangPrioritas.size() - 1) sb.append(", ");
            }
        }
        sb.append("\n");
        
        // Penumpang Berdiri
        sb.append("Penumpang Berdiri (Total: ").append(getJumlahPenumpangBerdiri()).append("/").append(KAPASITAS_BERDIRI).append("): ");
        if (penumpangBerdiri.isEmpty()) {
            sb.append("<kosong>");
        } else {
            for (int i = 0; i < penumpangBerdiri.size(); i++) {
                sb.append(penumpangBerdiri.get(i).getNama());
                if (i < penumpangBerdiri.size() - 1) sb.append(", ");
            }
        }
        sb.append("\n");

        // Jumlah semua penumpang
        sb.append("Jumlah Semua Penumpang: ").append(getTotalPenumpang()).append(" (Max: ").append(MAKSIMAL_KAPASITAS_BUS).append(")\n");
        // Total pendapatan bus
        sb.append("Total Pendapatan Bus: ").append(totalPendapatan);

        return sb.toString();
    }
}
