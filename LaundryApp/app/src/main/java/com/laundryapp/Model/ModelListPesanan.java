package com.laundryapp.Model;

public class ModelListPesanan {

    private int id;
    private String Nama;
    private String Tanggal;
    private String Jenis;
    private String Estimasi;
    private String JumlahBarang;
    private int TotalItem;
    private int Jumlah;

    public ModelListPesanan(int id,String Nama, String Tanggal,String Estimasi, String Jenis, int Jumlah, String JumlahBarang, int TotalItem) {
        this.id = id;
        this.Nama = Nama;
        this.Tanggal = Tanggal;
        this.Jumlah = Jumlah;
        this.Jenis = Jenis;
        this.Estimasi = Estimasi;
        this.JumlahBarang = JumlahBarang;
        this.TotalItem = TotalItem;
    }

    public ModelListPesanan() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }

    public String getEstimasi() {
        return Estimasi;
    }

    public void setEstimasi(String estimasi) {
        Estimasi = estimasi;
    }

    public int getJumlah() {
        return Jumlah;
    }

    public void setJumlah(int jumlah) {
        Jumlah = jumlah;
    }

    public String getJenis() {
        return Jenis;
    }

    public void setJenis(String jenis) {
        Jenis = jenis;
    }

    public String getJumlahBarang() {
        return JumlahBarang;
    }

    public void setJumlahBarang(String jumlahBarang) {
        JumlahBarang = jumlahBarang;
    }

    public int getTotalItem() {
        return TotalItem;
    }

    public void setTotalItem(int totalItem) {
        TotalItem = totalItem;
    }
}