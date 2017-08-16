package com.example.giftlist.giftlist.Data;


public class MyDataGifts {

    // Nie twórzcie tak nigdy zmiennych.
    //    private int id,cena;
    //    private String nazwa,kategoria,opis ,image_link;
    // Tylko normalnie po kolei:
    private int id;

    private int cena;

    private String nazwa;

    private String kategoria;

    private String opis;

    private String image_link;


    public MyDataGifts(int id, String nazwa, String kategoria, int cena, String opis, String image_link) {
        this.id = id;
        this.nazwa = nazwa;
        this.kategoria = kategoria;
        this.cena = cena;
        this.opis = opis;
        this.image_link = image_link;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
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
