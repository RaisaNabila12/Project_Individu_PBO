public class Penumpang {
    // Atribut (Encapsulation - private fields)
    private int id; 
    private String nama; // Tambahan untuk mempermudah identifikasi di simulasi
    private int umur; 
    private boolean hamil; 
    private int saldo;

    // Saldo awal penumpang adalah 10.000
    private static final int SALDO_AWAL = 10000;

    // Constructor 
    // + Penumpang(id: int, umur: int, hamil: boolean)
    public Penumpang(int id, String nama, int umur, boolean hamil) {
        this.id = id;
        this.nama = nama;
        this.umur = umur;
        this.hamil = hamil;
        this.saldo = SALDO_AWAL; // Saldo awal 10.000 
    }

    // Method Accessor (getters) 
    // + getID(): int 
    public int getID() {
        return id;
    }

    // Tambahan method getName() untuk mempermudah implementasi method turunkanPenumpang(nama) 
    public String getNama() {
        return nama;
    }

    // + getUmur(): int 
    public int getUmur() {
        return umur;
    }

    // + getHamil(): Boolean 
    public boolean isHamil() {
        return hamil;
    }

    // + getSaldo(): int 
    public int getSaldo() {
        return saldo;
    }

    // Method Mutator (setters/modifiers)
    // + tambahSaldo(saldobaru: int) 
    public void tambahSaldo(int saldoBaru) {
        this.saldo += saldoBaru;
    }

    // + kurangiSaldo(ongkos: int): void 
    public void kurangiSaldo(int ongkos) {
        if (this.saldo >= ongkos) {
            this.saldo -= ongkos;
        } else {
            // Seharusnya sudah dicek sebelum naik, tapi untuk keamanan
            throw new IllegalArgumentException("Saldo tidak cukup.");
        }
    }

    // Tambahan method untuk mengecek status prioritas
    // Digunakan untuk logika di Class Bus
    public boolean isPrioritas() {
        // Lansia: umur lebih dari 60 tahun 
        // Anak-anak: umur kurang dari 10 tahun 
        // Ibu hamil 
        return this.umur > 60 || this.umur < 10 || this.hamil;
    }
}
