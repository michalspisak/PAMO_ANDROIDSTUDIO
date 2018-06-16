package com.example.michalspisak.aplikacja.Model;

public class User {
    private String Nazwa;
    private String Haslo;
    private String Telefon;

    public User() {
    }

    public User(String nazwa, String haslo) {
        Nazwa = nazwa;
        Haslo = haslo;

    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String telefon) {
        Telefon = telefon;
    }

    public String getNazwa() {
        return Nazwa;
    }

    public void setNazwa(String nazwa) {
        Nazwa = nazwa;
    }

    public String getHaslo() {
        return Haslo;
    }

    public void setHaslo(String haslo) {
        Haslo = haslo;
    }
}
