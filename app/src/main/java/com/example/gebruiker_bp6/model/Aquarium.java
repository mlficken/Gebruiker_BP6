package com.example.gebruiker_bp6.model;

public class Aquarium {
    int ID;
    String straatHuisnummer, woonplaats;
    double stroomKostenkWh,vermogenLamp,vermogenHitteElement;

    public Aquarium(int ID, String straatHuisnummer, String woonplaats, double stroomKostenkWh, double vermogenLamp, double vermogenHitteElement) {
        this.ID = ID;
        this.straatHuisnummer = straatHuisnummer;
        this.woonplaats = woonplaats;
        this.stroomKostenkWh = stroomKostenkWh;
        this.vermogenLamp = vermogenLamp;
        this.vermogenHitteElement = vermogenHitteElement;
    }

    public Aquarium() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getVermogenLamp() {
        return vermogenLamp;
    }

    public void setVermogenLamp(double vermogenLamp) {
        this.vermogenLamp = vermogenLamp;
    }

    public double getVermogenHitteElement() {
        return vermogenHitteElement;
    }

    public void setVermogenHitteElement(double vermogenHitteElement) {
        this.vermogenHitteElement = vermogenHitteElement;
    }

    public String getStraatHuisnummer() {
        return straatHuisnummer;
    }

    public void setStraatHuisnummer(String straatHuisnummer) {
        this.straatHuisnummer = straatHuisnummer;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public double getStroomKostenkWh() {
        return stroomKostenkWh;
    }

    public void setStroomKostenkWh(double stroomKostenkWh) {
        this.stroomKostenkWh = stroomKostenkWh;
    }

    @Override
    public String toString() {
        return "Aquarium{" +
                "ID=" + ID +
                ", straatHuisnummer='" + straatHuisnummer + '\'' +
                ", woonplaats='" + woonplaats + '\'' +
                ", stroomKostenkWh=" + stroomKostenkWh +
                ", vermogenLamp=" + vermogenLamp +
                ", vermogenHitteElement=" + vermogenHitteElement +
                '}';
    }
}
