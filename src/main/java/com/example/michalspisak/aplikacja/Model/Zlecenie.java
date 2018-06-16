package com.example.michalspisak.aplikacja.Model;

import java.util.List;

public class Zlecenie {
    private String phone;
    private String name;
    private String address;
    private String total;
    private List<Zamowienie> przedmioty; // lista produktow (przedmiotow)


    public Zlecenie() {
    }

    public Zlecenie(String phone, String name, String address, String total, List<Zamowienie> przedmioty) {
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.total = total;
        this.przedmioty = przedmioty;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Zamowienie> getPrzedmioty() {
        return przedmioty;
    }

    public void setPrzedmioty(List<Zamowienie> przedmioty) {
        this.przedmioty = przedmioty;
    }
}

