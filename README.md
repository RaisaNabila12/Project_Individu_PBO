# Project_Individu_PBO


**NAMA  : Raisa Nabila**

**NPM   : 2408107010037**


# Program Bus Trans Koetaradja

**Deskripsi Project**
Program ini adalah simulasi sederhana untuk mengatur penumpang Bus Trans Koetaradja. 
Jadi ceritanya, kita diminta pemerintah buat bikin sistem yang bisa mengatur siapa bisa duduk di mana, 
siapa yang harus berdiri, siapa yang boleh duduk di kursi prioritas, sampai menghitung pendapatan bus.


**Fitur Utama**

**1. Manajemen Penumpang**
- Menambahkan penumpang ke dalam bus.
- Menghapus penumpang dari bus.
- Menampilkan seluruh penumpang dalam bus berdasarkan:
  - Kursi biasa (maks. 16)
  - Kursi prioritas (maks. 4)
  - Penumpang berdiri (maks. 20)


**2. Aturan Kursi**
- Kursi Prioritas hanya untuk:
  - Lansia (umur > 60)
  - Anak-anak (umur < 10)
  - Ibu hamil
- Penumpang prioritas boleh duduk di kursi biasa
- Penumpang biasa tidak boleh duduk di kursi prioritas.
- Jika kursi penuh, penumpang akan berdiri.
- Total kapasitas bus: 40 penumpang.


**3. Sistem Pembayaran**
- Setiap penumpang memiliki kartu saldo.
- Ongkos bus: Rp2.000 (nilai static final).
- Penumpang hanya bisa naik jika saldonya mencukupi.
- Jika berhasil naik, saldonya dikurangi ongkos.
- Semua pembayaran dicatat sebagai total pendapatan bus.


**4. Simulasi Interaktif**
  Program menyediakan menu:
  - Naikkan Penumpang
  - Turunkan Penumpang
  - Lihat Penumpang dan Total Pendapatan


**Struktur Kelas**


Class **Penumpang**
Mewakili satu penumpang dengan atribut:
- Id
- Umur
- Hamil (boolean)
- Saldo

Method Penting:
- tambahSaldo()
- kurangiSaldo()
- Getter untuk semua atribut
  

Class **Bus**

Mewakili satu objek Bus yang berisi:
- Array/ArrayList penumpang biasa
- Array/ArrayList penumpang prioritas
- Array/ArrayList penumpang berdiri
- ONGKOS_BUS (2000)
- totalPendapatan

Method Penting:
- naikkanPenumpang()
- turunkanPenumpang()
- getJumlahPenumpang*()
- toString()


Class **TestBus**

Berfungsi sebagai main program untuk menjalankan simulasi, termasuk:
- Input data penumpang
- Menampilkan menu
- Menghubungkan interaksi user dengan class Bus dan Penumpang


**Konsep OOP yang Digunakan**

- Class & Object  -> Penumpang, Bus, dan objek-objeknya
- Encapsulation   -> Atribut dibuat private, akses via getter/setter
- Abstraction     ->	Method seperti naikkanPenumpang() menyembunyikan detail proses
- Polymorphism / Overriding (opsional)	-> Bisa diterapkan pada toString()
- Collection (Array / ArrayList)	-> Menyimpan daftar penumpang
- Exception Handling	-> Validasi input & saldo
- Static Final Field	-> ONGKOS_BUS pada class Bus


