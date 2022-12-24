package com.laundryapp.Model;

public class ModelRiwayat {
    private int idriwayat;
    private String Namariwayat;
    private String Tanggalriwayat;
    private String Jenisriwayat;
    private String Estimasiriwayat;
    private String JumlahBarangriwayat;
    private int TotalItemriwayat;
    private int Jumlahriwayat;


    public ModelRiwayat(int idriwayat,String Namariwayat, String Tanggalriwayat) {
        this.idriwayat = idriwayat;
        this.Namariwayat = Namariwayat;
        this.Tanggalriwayat = Tanggalriwayat;

    }

    public ModelRiwayat() {

    }
    public int getIdriwayat() {
        return idriwayat;
    }

    public String getNamariwayat() {
        return Namariwayat;
    }

    public String getTanggalriwayat() {
        return Tanggalriwayat;
    }
    public void setIdriwayat(int idriwayat) {
        this.idriwayat = idriwayat;
    }

    public void setNamariwayat(String namariwayat) {
        Namariwayat = namariwayat;
    }

    public void setTanggalriwayat(String tanggalriwayat) {
        Tanggalriwayat = tanggalriwayat;
    }
    public String getJenisriwayat() {
        return Jenisriwayat;
    }

    public void setJenisriwayat(String jenisriwayat) {
        Jenisriwayat = jenisriwayat;
    }

    public String getEstimasiriwayat() {
        return Estimasiriwayat;
    }

    public void setEstimasiriwayat(String estimasiriwayat) {
        Estimasiriwayat = estimasiriwayat;
    }

    public String getJumlahBarangriwayat() {
        return JumlahBarangriwayat;
    }

    public void setJumlahBarangriwayat(String jumlahBarangriwayat) {
        JumlahBarangriwayat = jumlahBarangriwayat;
    }

    public int getTotalItemriwayat() {
        return TotalItemriwayat;
    }

    public void setTotalItemriwayat(int totalItemriwayat) {
        TotalItemriwayat = totalItemriwayat;
    }

    public int getJumlahriwayat() {
        return Jumlahriwayat;
    }

    public void setJumlahriwayat(int jumlahriwayat) {
        Jumlahriwayat = jumlahriwayat;
    }



}
