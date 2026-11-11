package Proyek2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaders {
    // Membaca matakuliah.csv → mengisi HashTable
    // Format baris: Kode;Nama;sks;semester;ganjil/genap
    public static void loadMataKuliah(String filePath, HashTableMataKuliah hashTable) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                // Lewati baris header
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                // Lewati baris kosong
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(";");
                // Skip baris tidak valid
                if (parts.length < 5) continue;

                String kode     = parts[0].trim();
                String nama     = parts[1].trim();
                String sks      = parts[2].trim();
                String semester = parts[3].trim();
                // "Ganjil", "Genap", atau "Keduanya"
                String ganjilGenap = parts[4].trim();

                // Buat objek MataKuliah (prasyarat diisi nanti oleh loadPrasyarat)
                MataKuliah mk = new MataKuliah(kode, nama, sks, semester);

                // Simpan info ganjil/genap ke dalam field semester sebagai gabungan
                // Format internal: "1;Ganjil", "2;Genap", dst. agar mudah dicek
                mk.setSemester(semester + ";" + ganjilGenap);

                hashTable.put(kode, mk);
            }

        } catch (IOException e) {
            System.out.println("Error membaca file matakuliah: " + e.getMessage());
        } finally {
            if (br != null) {
                try { br.close(); } catch (IOException e) { /* abaikan */ }
            }
        }
    }


    // Membaca prasyarat.csv → mengisi prasyarat tiap MK di HashTable
    // Format baris: kodeMK;kodePreasyarat
    // Satu MK bisa punya beberapa baris (satu prasyarat per baris)
    public static void loadPrasyarat(String filePath, HashTableMataKuliah hashTable) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                // Lewati baris header
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(";");
                if (parts.length < 2) continue;

                String kodeMK         = parts[0].trim();
                String kodePrasyarat  = parts[1].trim();

                // Cari objek MataKuliah berdasarkan kode
                MataKuliah mk = hashTable.get(kodeMK);
                if (mk == null) {
                    System.out.println("Peringatan: kode MK tidak ditemukan di tabel → " + kodeMK);
                    continue;
                }

                // Jika MK belum punya list prasyarat, buat baru
                if (mk.getPrasyarat() == null) {
                    SingleLinkedList prasyaratList = new SingleLinkedList();
                    prasyaratList.init();
                    mk.setPrasyarat(prasyaratList);
                }

                // Tambahkan kode prasyarat ke dalam linked list
                mk.getPrasyarat().add(kodePrasyarat);
            }

        } catch (IOException e) {
            System.out.println("Error membaca file prasyarat: " + e.getMessage());
        } finally {
            if (br != null) {
                try { br.close(); } catch (IOException e) {/* biarkan */}
            }
        }
    }

    // Membaca mhs_xx.txt → mengembalikan objek Mahasiswa
    // Format:
    //   semester_berikutnya=ganjil   (atau genap)
    //   lulus=RPL,PDAS,PKK,...
    public static Mahasiswa loadMahasiswa(String filePath) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;

            String semesterBerikutnya = "";
            SingleLinkedList mkLulus = new SingleLinkedList();
            mkLulus.init();

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("semester_berikutnya=")) {
                    // Ambil nilai setelah "="
                    // Kapitalisasi huruf pertama: "ganjil" → "Ganjil"
                    String val = line.split("=")[1].trim();
                    semesterBerikutnya = val.substring(0, 1).toUpperCase()
                            + val.substring(1).toLowerCase();

                } else if (line.startsWith("lulus=")) {
                    // Ambil semua kode MK yang dipisah koma
                    String kodeMKStr = line.split("=")[1].trim();
                    String[] kodeArr = kodeMKStr.split(",");
                    for (String kode : kodeArr) {
                        kode = kode.trim();
                        if (!kode.isEmpty()) {
                            mkLulus.add(kode);
                        }
                    }
                }
            }

            return new Mahasiswa(semesterBerikutnya, mkLulus);

        } catch (IOException e) {
            System.out.println("Error membaca file mahasiswa: " + e.getMessage());
            return null;
        } finally {
            if (br != null) {
                try { br.close(); } catch (IOException e) { /* biarkan */ }
            }
        }
    }
}