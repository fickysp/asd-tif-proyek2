package Proyek2;

public class MataKuliah {
    private String kode;
    private String nama;
    private String sks;
    private String semester;
    private SingleLinkedList prasyarat;

    public MataKuliah(){}
    public MataKuliah(String k, String n, String s, String sm){
        this.setKode(k);
        this.setNama(n);
        this.setSks(s);
        this.setSemester(sm);
    }
    public MataKuliah(String k, String n, String s, String sm, SingleLinkedList pr){
        this.setKode(k);
        this.setNama(n);
        this.setSks(s);
        this.setSemester(sm);
        this.setPrasyarat(pr);
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSks() {
        return sks;
    }

    public void setSks(String sks) {
        this.sks = sks;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public SingleLinkedList getPrasyarat() {
        return prasyarat;
    }

    public void setPrasyarat(SingleLinkedList prasyarat) {
        this.prasyarat = prasyarat;
    }
}
