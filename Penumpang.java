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
