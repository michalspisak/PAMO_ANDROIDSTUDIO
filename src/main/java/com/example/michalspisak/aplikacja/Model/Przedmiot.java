package com.example.michalspisak.aplikacja.Model;

public class Przedmiot {
    private String Nazwa, Zdjecie, Opis, Cena, Znizka, MenuId;

    public Przedmiot() {
    }

    public Przedmiot(String nazwa, String zdjecie, String opis, String cena, String znizka, String menuId) {
        Nazwa = nazwa;
        Zdjecie = zdjecie;
        Opis = opis;
        Cena = cena;
        Znizka = znizka;
        MenuId = menuId;
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

    public String getOpis() {
        return Opis;
    }

    public void setOpis(String opis) {
        Opis = opis;
    }

    public String getCena() {
        return Cena;
    }

    public void setCena(String cena) {
        Cena = cena;
    }

    public String getZnizka() {
        return Znizka;
    }

    public void setZnizka(String znizka) {
        Znizka = znizka;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}
