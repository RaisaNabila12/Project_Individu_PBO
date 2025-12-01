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

        
