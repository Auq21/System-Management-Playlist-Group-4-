// TUGAS KELOMPOK 1 - GROUP 4
// 1. AKHMAD FARUQ - NIM 2902820176
// 2. ALVITA KRIDAPRAKOSA - NIM 2602296406
// 3. CUCU CINTIA WIDIANI - NIM 2902806455
// 4. HASRIN CITRA UTAMI - NIM 2902816645
// 5. WIDIYA PRAMESTIKA - NIM 2902816304



// Class Lagu digunakan untuk merepresentasikan sebuah lagu
class Lagu {

    // Atribut dibuat private sebagai penerapan enkapsulasi
    private String judul;
    private String artis;
    private double durasi;

    // Constructor digunakan untuk memberikan nilai awal pada objek Lagu
    public Lagu(String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }

    // Getter untuk mengambil nilai judul
    public String getJudul() {
        return judul;
    }

    // Setter untuk mengubah nilai judul
    public void setJudul(String judul) {
        this.judul = judul;
    }

    // Getter untuk mengambil nilai artis
    public String getArtis() {
        return artis;
    }

    // Setter untuk mengubah nilai artis
    public void setArtis(String artis) {
        this.artis = artis;
    }

    // Getter untuk mengambil nilai durasi
    public double getDurasi() {
        return durasi;
    }

    // Setter untuk mengubah nilai durasi
    public void setDurasi(double durasi) {
        this.durasi = durasi;
    }

    // Method untuk menampilkan informasi lengkap sebuah lagu
    public void tampilkanInfo() {
        System.out.println("Judul  : " + judul);
        System.out.println("Artis   : " + artis);
        System.out.println("Durasi : " + durasi + " menit");
    }
}

// ================= CLASS USER (PARENT) =================
// Class User adalah parent class yang menyimpan atribut & perilaku
// yang dimiliki oleh seluruh jenis pengguna sistem (Admin & Member).
// Konsep INHERITANCE diterapkan di sini: Admin dan Member akan
class User {

    // protected agar dapat diakses langsung oleh class turunan (Admin, Member)
    protected String nama;
    protected String role;

    public User(String nama, String role) {
        this.nama = nama;
        this.role = role;
    }

    public String getNama() {
        return nama;
    }

    public String getRole() {
        return role;
    }

    // Method dasar tampilkanAkses().
    // Method inilah yang akan di-override secara berbeda oleh Admin
    // dan Member untuk mendemonstrasikan konsep POLYMORPHISM.
    public void tampilkanAkses() {
        System.out.println(nama + " memiliki akses standar sebagai pengguna sistem.");
    }
}


// ================= CLASS ADMIN (CHILD DARI USER) =================
// Admin mewarisi (extends) class User -> konsep INHERITANCE.
// Admin memiliki kemampuan tambahan: menambahkan lagu baru ke playlist.
class Admin extends User {

    public Admin(String nama) {
        // super() memanggil constructor parent class (User) untuk
        // menginisialisasi atribut nama & role yang diwariskan.
        super(nama, "Admin");
    }

    // POLYMORPHISM: method tampilkanAkses() di-override dengan
    // perilaku yang berbeda dari parent class dan dari class Member.
    @Override
    public void tampilkanAkses() {
        System.out.println(nama + " (Admin) -> dapat menambahkan lagu baru ke playlist "
                + "dan melihat seluruh daftar lagu yang tersimpan.");
    }

    // Method khusus Admin: menambahkan lagu baru ke dalam array playlist.
    // Logika: mencari slot kosong (null) pertama pada array, lalu
    // menyisipkan objek Lagu baru pada slot tersebut.
    public void tambahLagu(Lagu[] playlist, Lagu laguBaru) {
        for (int i = 0; i < playlist.length; i++) {
            if (playlist[i] == null) {
                playlist[i] = laguBaru;
                System.out.println("[Admin " + nama + "] Berhasil menambahkan lagu: \"" + laguBaru.getJudul() + "\"");
                return;
            }
        }
        System.out.println("[Admin " + nama + "] Playlist penuh, lagu baru tidak dapat ditambahkan.");
    }

    // Method khusus Admin: melihat seluruh daftar lagu pada playlist.
    public void lihatDaftarLagu(Lagu[] playlist) {
        System.out.println("--- Daftar Lagu (dilihat oleh Admin " + nama + ") ---");
        for (Lagu l : playlist) {
            if (l != null) {
                l.tampilkanInfo();
                System.out.println("-----------------------------");
            }
        }
    }
}


// ================= CLASS MEMBER (CHILD DARI USER) =================
// Member mewarisi (extends) class User -> konsep INHERITANCE.
// Member dapat menelusuri lagu, mencari berdasarkan judul, dan
// menghitung rata-rata durasi lagu dalam playlist.
class Member extends User {

    public Member(String nama) {
        super(nama, "Member");
    }

    // POLYMORPHISM: implementasi tampilkanAkses() yang berbeda
    // dibandingkan class Admin, meskipun nama method-nya sama persis.
    @Override
    public void tampilkanAkses() {
        System.out.println(nama + " (Member) -> dapat menelusuri daftar lagu, mencari lagu "
                + "berdasarkan judul, dan menghitung rata-rata durasi playlist.");
    }

    // Method khusus Member: menampilkan seluruh daftar lagu (read-only).
    public void lihatDaftarLagu(Lagu[] playlist) {
        System.out.println("--- Daftar Lagu (dilihat oleh Member " + nama + ") ---");
        for (Lagu l : playlist) {
            if (l != null) {
                l.tampilkanInfo();
                System.out.println("-----------------------------");
            }
        }
    }

    // Method khusus Member: mencari lagu berdasarkan judul (case-insensitive).
    // Logika: melakukan iterasi linear pada array dan mencocokkan judul.
    public void cariLaguByJudul(Lagu[] playlist, String judul) {
        boolean ditemukan = false;
        for (Lagu l : playlist) {
            if (l != null && l.getJudul().equalsIgnoreCase(judul)) {
                System.out.println("Lagu ditemukan:");
                l.tampilkanInfo();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Lagu dengan judul \"" + judul + "\" tidak ditemukan pada playlist.");
        }
    }

    // Method khusus Member: menghitung rata-rata durasi seluruh lagu
    // yang tersimpan (bukan rata-rata dari panjang array, agar slot
    // kosong/null tidak ikut mempengaruhi perhitungan).
    public double hitungRataRataDurasi(Lagu[] playlist) {
        double totalDurasi = 0;
        int jumlahLagu = 0;
        for (Lagu l : playlist) {
            if (l != null) {
                totalDurasi += l.getDurasi();
                jumlahLagu++;
            }
        }
        return jumlahLagu == 0 ? 0 : totalDurasi / jumlahLagu;
    }
}


// Class utama program
public class PlaylistOOP {

    public static void main(String[] args) {

        // Membuat objek Lagu menggunakan constructor
        Lagu lagu1 = new Lagu("Satu-Satu", "Idgitaf", 3.25);

        // Menampilkan informasi lagu
        lagu1.tampilkanInfo();
    }
}
