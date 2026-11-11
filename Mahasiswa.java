package Proyek2;

public class Mahasiswa {
    private String semesterBerikutnya;
    private SingleLinkedList mkLulus;

    public Mahasiswa(String semester, SingleLinkedList mkLulus) {
        this.semesterBerikutnya = semester;
        this.mkLulus = mkLulus;
    }

    public String getSemesterBerikutnya() { return semesterBerikutnya; }
    public SingleLinkedList getMkLulus() { return mkLulus; }
}
