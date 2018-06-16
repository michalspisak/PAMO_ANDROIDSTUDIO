package com.example.michalspisak.aplikacja.Model;

public class Kategoria {

    private String Nazwa;
    private String Zdjecie;

    public Kategoria() {
    }

    public Kategoria(String nazwa, String zdjecie) {
        Nazwa = nazwa;
        Zdjecie = zdjecie;
    }

    public String getNazwa() {
        return Nazwa;
    }

    public void setNazwa(String nazwa) {
        Nazwa = nazwa;
    }

    public String getZdjecie() {
        return Zdjecie;
    }

    public void setZdjecie(String zdjecie) {
        Zdjecie = zdjecie;
    }
}
