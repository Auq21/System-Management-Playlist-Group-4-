// TUGAS KELOMPOK 1 - GROUP 4
// 1. AKHMAD FARUQ - NIM 2902820176
// 2. ALVITA KRIDAPRAKOSA - NIM 2602296406
// 3. CUCU CINTIA WIDIANI - NIM 2902806455
// 4. HASRIN CITRA UTAMI - NIM 2902816645
// 5. WIDIYA PRAMESTIKA - 2902816304



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


// Class utama program
public class PlaylistOOP {

    public static void main(String[] args) {

        // Membuat objek Lagu menggunakan constructor
        Lagu lagu1 = new Lagu("Satu-Satu", "Idgitaf", 3.25);

        // Menampilkan informasi lagu
        lagu1.tampilkanInfo();
    }
}
