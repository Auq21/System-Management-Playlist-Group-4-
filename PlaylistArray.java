// ============================================================================
// IDENTITAS KELOMPOK
// 1. AKHMAD FARUQ        - NIM 2902820176
// 2. ALVITA KRIDAPRAKOSA - NIM 2602296406
// 3. CUCU CINTIA WIDIANI - NIM 2902806455
// 4. HASRIN CITRA UTAMI  - NIM 2902816645
// 5. WIDIYA PRAMESTIKA   - NIM 2902816304
// KELAS / GROUP: GROUP 4
// ============================================================================

import java.util.Scanner;

// Class Lagu digunakan untuk merepresentasikan objek lagu (Atribut: judul, artis, durasi)
class Lagu {
    private String judul;
    private String artis;
    private double durasi;

    public Lagu(String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }

    public String getJudul() {
        return judul;
    }

    public String getArtis() {
        return artis;
    }

    public double getDurasi() {
        return durasi;
    }

    // Method untuk menampilkan info lengkap lagu
    public void tampilkanInfo() {
        System.out.println("Judul  : " + judul + " | Artis: " + artis + " | Durasi: " + durasi + " menit");
    }
}

// Class Utama PlaylistArray yang mengelola operasi-operasi Array
public class PlaylistArray {

    // Array statis dengan kapasitas maksimum 10 lagu
    private static Lagu[] playlist = new Lagu[10];
    
    // Variabel penanda jumlah lagu yang tersimpan saat ini
    private static int jumlahLagu = 0;
    
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Data awal (Dummy Data)
        playlist[0] = new Lagu("Satu-Satu", "Idgitaf", 3.25);
        playlist[1] = new Lagu("Tak Segampang Itu", "Anggi Marito", 4.10);
        playlist[2] = new Lagu("Lathi", "Weird Genius", 3.05);
        jumlahLagu = 3;

        int pilihan = 0;

        // Loop menu interaktif berbasis konsol
        do {
            System.out.println("\n=== MENU PLAYLIST MUSIK ===");
            System.out.println("1. Tampilkan semua lagu");
            System.out.println("2. Tambah lagu baru");
            System.out.println("3. Hapus lagu berdasarkan judul");
            System.out.println("4. Cari lagu berdasarkan judul");
            System.out.println("5. Urutkan lagu berdasarkan durasi");
            System.out.println("6. Keluar");
            System.out.print("Pilihan Anda: ");

            if (scanner.hasNextInt()) {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } else {
                System.out.println("Input tidak valid! Harap masukkan angka.");
                scanner.nextLine(); // Clear invalid input
                continue;
            }

            switch (pilihan) {
                case 1:
                    tampilkanSemuaLagu();
                    break;
                case 2:
                    tambahLagu();
                    break;
                case 3:
                    hapusLagu();
                    break;
                case 4:
                    cariLagu();
                    break;
                case 5:
                    urutkanLaguBerdasarkanDurasi();
                    break;
                case 6:
                    System.out.println("Terima kasih telah menggunakan sistem playlist!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        } while (pilihan != 6);
    }

    // 1. TRAVERSAL 
    // Logika: Memeriksa apakah playlist berisi lagu, kemudian melakukan iterasi
    // dari indeks 0 hingga jumlahLagu - 1 untuk menampilkan seluruh data lagu.
    // Kompleksitas Waktu: O(n)
    public static void tampilkanSemuaLagu() {
        System.out.println("\n--- DAFTAR PLAYLIST LAGU ---");
        if (jumlahLagu == 0) {
            System.out.println("Playlist masih kosong.");
            return;
        }

        for (int i = 0; i < jumlahLagu; i++) {
            System.out.print((i + 1) + ". ");
            playlist[i].tampilkanInfo();
        }
    }

    // 2. INSERTION 
    // Logika: Mengecek apakah jumlahLagu sudah mencapai batas array statis (10).
    // Jika belum penuh, data diproses dan ditambahkan ke indeks paling akhir (jumlahLagu),
    // kemudian variabel jumlahLagu bertambah +1.
    // Kompleksitas Waktu: O(1)
    public static void tambahLagu() {
        System.out.println("\n--- TAMBAH LAGU BARU ---");

        // Cek apakah playlist sudah penuh
        if (jumlahLagu >= playlist.length) {
            System.out.println("Gagal menambahkan lagu. Playlist sudah penuh (Maksimal 10 lagu)!");
            return;
        }

        System.out.print("Masukkan Judul Lagu : ");
        String judul = scanner.nextLine().trim();
        System.out.print("Masukkan Nama Artis : ");
        String artis = scanner.nextLine().trim();

        // Validasi: judul dan artis tidak boleh kosong
        if (judul.isEmpty() || artis.isEmpty()) {
            System.out.println("Judul dan artis tidak boleh kosong. Penambahan dibatalkan.");
            return;
        }

        // Input durasi dibaca sebagai teks lalu diubah ke angka, supaya format
        // "4.10" maupun "4,10" sama-sama diterima (tidak tergantung locale)
        System.out.print("Masukkan Durasi (menit): ");
        double durasi;
        while (true) {
            try {
                durasi = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
                if (durasi > 0) {
                    break;
                }
            } catch (NumberFormatException e) {
                // input bukan angka, ulangi
            }
            System.out.print("Durasi tidak valid! Masukkan angka lebih dari 0: ");
        }

        // Simpan di posisi kosong pertama, lalu naikkan counter
        playlist[jumlahLagu] = new Lagu(judul, artis, durasi);
        jumlahLagu++;

        System.out.println("Berhasil! Lagu \"" + judul + "\" telah ditambahkan ke playlist.");
        tampilkanSemuaLagu();
    }

    // 3. DELETION 
    // Logika: Mencari indeks dari judul lagu yang ingin dihapus. Jika ditemukan,
    // elemen-elemen setelah indeks tersebut digeser ke kiri 1 langkah (i = i + 1)
    // agar data dalam array tetap rapat (tanpa celah null di tengah). Indeks terakhir di-null-kan.
    // Kompleksitas Waktu: O(n)
    public static void hapusLagu() {
        System.out.println("\n--- HAPUS LAGU BERDASARKAN JUDUL ---");
        if (jumlahLagu == 0) {
            System.out.println("Playlist kosong, tidak ada lagu yang bisa dihapus.");
            return;
        }

        System.out.print("Masukkan judul lagu yang ingin dihapus: ");
        String judulHapus = scanner.nextLine().trim();

        int indexDitemukan = -1;
        // Cari posisi lagu yang akan dihapus (linear search)
        for (int i = 0; i < jumlahLagu; i++) {
            if (playlist[i].getJudul().equalsIgnoreCase(judulHapus)) {
                indexDitemukan = i;
                break;
            }
        }

        if (indexDitemukan != -1) {
            // Pergeseran elemen ke kiri agar array tetap rapat
            for (int i = indexDitemukan; i < jumlahLagu - 1; i++) {
                playlist[i] = playlist[i + 1];
            }
            playlist[jumlahLagu - 1] = null; // Menghapus referensi elemen terakhir
            jumlahLagu--;

            System.out.println("Berhasil! Lagu \"" + judulHapus + "\" telah dihapus.");
            tampilkanSemuaLagu();
        } else {
            System.out.println("Lagu dengan judul \"" + judulHapus + "\" tidak ditemukan.");
        }
    }

    // 4. SEARCHING 
    // Logika: Menggunakan teknik Linear Search, yaitu menelusuri array satu per satu
    // dari elemen pertama hingga ketemu judul yang cocok (case-insensitive).
    // Kompleksitas Waktu: O(n)
    public static void cariLagu() {
        System.out.println("\n--- CARI LAGU BERDASARKAN JUDUL ---");
        if (jumlahLagu == 0) {
            System.out.println("Playlist masih kosong.");
            return;
        }

        System.out.print("Masukkan judul lagu yang dicari: ");
        String judulCari = scanner.nextLine();

        boolean ditemukan = false;
        for (int i = 0; i < jumlahLagu; i++) {
            if (playlist[i].getJudul().equalsIgnoreCase(judulCari)) {
                System.out.println("\n[Lagu Ditemukan pada Indeks ke-" + i + "]");
                playlist[i].tampilkanInfo();
                ditemukan = true;
                break;
            }
        }

        if (!ditemukan) {
            System.out.println("Lagu dengan judul \"" + judulCari + "\" tidak ditemukan dalam playlist.");
        }
    }

    // 5. SORTING 
    // Logika: Menggunakan Bubble Sort untuk mengurutkan lagu secara ascending (durasi terpendek ke terpanjang).
    // Kompleksitas Waktu: O(n^2)
    public static void urutkanLaguBerdasarkanDurasi() {
        System.out.println("\n--- MENGURUTKAN LAGU BERDASARKAN DURASI (ASCENDING) ---");
        if (jumlahLagu < 2) {
            System.out.println("Jumlah lagu kurang untuk melakukan pengurutan.");
            return;
        }

        for (int i = 0; i < jumlahLagu - 1; i++) {
            for (int j = 0; j < jumlahLagu - i - 1; j++) {
                if (playlist[j].getDurasi() > playlist[j + 1].getDurasi()) {
                    // Tukar posisi (Swap)
                    Lagu temp = playlist[j];
                    playlist[j] = playlist[j + 1];
                    playlist[j + 1] = temp;
                }
            }
        }

        System.out.println("Playlist berhasil diurutkan berdasarkan durasi terpendek!");
        tampilkanSemuaLagu();
    }
}
