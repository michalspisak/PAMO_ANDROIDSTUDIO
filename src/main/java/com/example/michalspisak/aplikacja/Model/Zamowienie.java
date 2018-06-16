package com.example.michalspisak.aplikacja.Model;

import java.util.List;

public class Zamowienie {
    private String ProduktId;
    private String ProduktNazwa;
    private String Ilosc;
    private String Cena;
    private String Znizka;

    public Zamowienie(String telefon, String nazwa, String ilosc, String cena, List<Zamowienie> koszyk) {
    }

    public Zamowienie(String produktId, String produktNazwa, String ilosc, String cena, String znizka) {
        ProduktId = produktId;
        ProduktNazwa = produktNazwa;
        Ilosc = ilosc;
        Cena = cena;
        Znizka = znizka;
    }

    public String getProduktId() {
        return ProduktId;
    }

    public void setProduktId(String produktId) {
        ProduktId = produktId;
    }

    public String getProduktNazwa() {
        return ProduktNazwa;
    }

    public void setProduktNazwa(String produktNazwa) {
        ProduktNazwa = produktNazwa;
    }

    public String getIlosc() {
        return Ilosc;
    }

    public void setIlosc(String ilosc) {
        Ilosc = ilosc;
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
}
