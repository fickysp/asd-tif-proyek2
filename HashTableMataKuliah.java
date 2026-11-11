package Proyek2;

class HashNode {
    String key; // Berisi kode MK (misal: "SO", "AL")
    MataKuliah value; // Objek utuh MataKuliah
    HashNode next; // Pointer untuk menangani collision (Chaining)

    public HashNode(String key, MataKuliah value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

public class HashTableMataKuliah {
    private HashNode[] table;
    private int capacity;
    private int size;

    public HashTableMataKuliah(int capacity) {
        this.capacity = capacity;
        this.table = new HashNode[capacity];
        this.size = 0;
    }

    // Fungsi Hash kustom: Mengonversi kode MK (String) menjadi indeks array
    private int getHash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            // Menjumlahkan nilai ASCII karakter lalu di-modulo dengan kapasitas
            hash = (hash + key.charAt(i)) % capacity;
        }
        return hash;
    }

    // Method untuk memasukkan data MataKuliah
    public void put(String key, MataKuliah value) {
        int index = getHash(key);
        HashNode head = table[index];

        // Cek apakah key sudah ada (mencegah duplikasi)
        HashNode current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value; // Update data jika key sama
                return;
            }
            current = current.next;
        }

        // Jika belum ada, sisipkan Node baru di posisi paling depan (head) pada indeks tersebut
        HashNode newNode = new HashNode(key, value);
        newNode.next = head;
        table[index] = newNode;
        size++;
    }

    // Method untuk mencari dan mengambil data MataKuliah dalam waktu O(1)
    public MataKuliah get(String key) {
        int index = getHash(key);
        HashNode current = table[index];

        // Telusuri linked list pada indeks tersebut jika terjadi collision
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null; // Mengembalikan null jika kode MK tidak ditemukan
    }

    // Method tambahan KHUSUS untuk Tahap 3 (Evaluasi Kelayakan)
    // Mengembalikan semua mata kuliah dalam bentuk array biasa agar mudah di-looping
    public MataKuliah[] getAll() {
        MataKuliah[] allMK = new MataKuliah[size];
        int idx = 0;

        for (int i = 0; i < capacity; i++) {
            HashNode current = table[i];
            while (current != null) {
                allMK[idx++] = current.value;
                current = current.next;
            }
        }
        return allMK;
    }
}