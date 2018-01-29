package com.example.giftlist.giftlist.Data;


public class MyDataGifts {

    // Nie twórzcie tak nigdy zmiennych.
    //    private int id,cena;
    //    private String nazwa,kategoria,opis ,image_link;
    // Tylko normalnie po kolei:
    private int id_prezentu;

    private int cena;

    private String nazwa;

    private String kategoria;

    private String opis;

    private String image_link;

    public MyDataGifts(int id_prezentu, String nazwa, String kategoria, int cena, String opis, String image_link) {
        this.id_prezentu = id_prezentu;
        this.nazwa = nazwa;
        this.kategoria = kategoria;
        this.cena = cena;
        this.opis = opis;
        this.image_link = image_link;

    }

    public int getId_prezentu() {
        return id_prezentu;
    }

    public void setId_prezentu(int id_prezentu) {
        this.id_prezentu = id_prezentu;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }




}
