package com.example.gnlm;

public class GunlukNesne {
    private int id;
    private String tarih;
    private String baslik;
    private String metin;

    public GunlukNesne(int id, String tarih, String baslik, String metin) {
        this.id = id;
        this.tarih = tarih;
        this.baslik = baslik;
        this.metin = metin;
    }
    public GunlukNesne() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getMetin() {
        return metin;
    }

    public void setMetin(String metin) {
        this.metin = metin;
    }

    @Override
    public String toString() {
        return "TARİH: "+tarih+"  BAŞLIK: "+baslik+"   "+metin;
    }
}
