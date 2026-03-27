package com.example.hackitall_2025_stripe.model;

public class Firma {
    private String nume;
    private String cui;
    private String caen;

    public Firma(String nume, String cui, String caen) {
        this.nume = nume;
        this.cui = cui;
        this.caen = caen;
    }

    public String getNume() { return nume; }
    public String getCui() { return cui; }
    public String getCaen() { return caen; }

    @Override
    public String toString() {
        return nume + " | " + cui + " | " + caen;
    }
}
