package com.example.gebruiker_bp6.model;

public class OntwikkelaarAquarium {
    String ontwikkelaarTelefoonnummer;
    int ID;

    public OntwikkelaarAquarium(String ontwikkelaarTelefoonnummer, int ID) {
        this.ontwikkelaarTelefoonnummer = ontwikkelaarTelefoonnummer;
        this.ID = ID;
    }

    public OntwikkelaarAquarium() {
    }

    public String getOntwikkelaarTelefoonnummer() {
        return ontwikkelaarTelefoonnummer;
    }

    public void setOntwikkelaarTelefoonnummer(String ontwikkelaarTelefoonnummer) {
        this.ontwikkelaarTelefoonnummer = ontwikkelaarTelefoonnummer;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
