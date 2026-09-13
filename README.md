# System Manajemen Playlist Musik - Kelompok 4

Tugas Kelompok 1 - Data Structures and Algorithm Analysis (Week 3)

## Identitas Kelompok
1. Akhmad Faruq - 2902820176
2. Alvita Kridaprakosa - 2602296406
3. Cucu Cintia Widiani - 2902806455
4. Hasrin Citra Utami - 2902816645
5. Widiya Pramestika - 2902816304

## Deskripsi Tugas
Sistem manajemen playlist musik sederhana menggunakan Java, menerapkan konsep OOP (enkapsulasi, inheritance, polymorphism) dan array untuk menyimpan kumpulan objek lagu. Sistem memiliki dua peran: **Admin** (menambah lagu, melihat daftar) dan **Member** (menelusuri, mencari lagu, menghitung rata-rata durasi).

## Soal 1 - Class Lagu (30%)
Class `Lagu` merepresentasikan setiap lagu dengan atribut `judul`, `artis`, `durasi` yang dibuat `private` (enkapsulasi), diakses lewat getter/setter. Constructor mengisi nilai awal, dan `tampilkanInfo()` mencetak informasi lengkap lagu.

## Soal 2 - Class User, Admin, Member (45%)
`User` adalah parent class (atribut `nama`, `role`). `Admin` dan `Member` adalah child class yang `extends User`, memanggil `super(nama, role)` di constructor-nya. Admin punya `tambahLagu()` dan `lihatDaftarLagu()`; Member punya `lihatDaftarLagu()`, `cariLaguByJudul()`, dan `hitungRataRataDurasi()`. Semua data lagu disimpan di array `Lagu[] playlist` (10 slot).

## Soal 3 - Penjelasan Inheritance dan Polymorphism (25%)

**Inheritance**: `Admin extends User` dan `Member extends User` membuat keduanya otomatis mewarisi atribut dan method dari `User` tanpa menulis ulang kode. Constructor memanggil `super(nama, role)` untuk inisialisasi atribut yang diwariskan. Di luar itu, tiap child class tetap punya method spesifik sesuai perannya.

**Polymorphism**: Method `tampilkanAkses()` di-override berbeda oleh `Admin` dan `Member` (ditandai `@Override`). Ini adalah *runtime polymorphism* — dibuktikan lewat `User[] users = { admin, member }` di `main()`, di mana method yang dijalankan tetap mengikuti tipe objek asli meski diakses lewat referensi tipe `User`.

## Cara Menjalankan
\`\`\`
javac PlaylistOOP.java
java PlaylistOOP
\`\`\`

## Tautan
- File utama: [PlaylistOOP.java](./PlaylistOOP.java)
