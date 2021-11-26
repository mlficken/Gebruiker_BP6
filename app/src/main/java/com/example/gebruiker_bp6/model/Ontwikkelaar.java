package com.example.gebruiker_bp6.model;

public class Ontwikkelaar {
    String telefoonnummer, naam;

    public Ontwikkelaar(String telefoonnummer, String naam) {
        this.telefoonnummer = telefoonnummer;
        this.naam = naam;
    }

    public Ontwikkelaar() {
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}
