package com.example.gebruiker_bp6.model;


import java.time.LocalTime;
import java.util.Date;

public class Meting {
    int ID;
    Date datum;
    LocalTime tijd;
    double pHWaarde, temperatuurWaarde;
    int lichtWaarde;

    public Meting(int ID, Date datum, LocalTime tijd, double pHWaarde, double temperatuurWaarde, int lichtWaarde) {
        this.ID = ID;
        this.datum = datum;
        this.tijd = tijd;
        this.pHWaarde = pHWaarde;
        this.temperatuurWaarde = temperatuurWaarde;
        this.lichtWaarde = lichtWaarde;
    }

    public Meting() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public LocalTime getTijd() {
        return tijd;
    }

    public void setTijd(LocalTime tijd) {
        this.tijd = tijd;
    }

    public double getpHWaarde() {
        return pHWaarde;
    }

    public void setpHWaarde(double pHWaarde) {
        this.pHWaarde = pHWaarde;
    }

    public double getTemperatuurWaarde() {
        return temperatuurWaarde;
    }

    public void setTemperatuurWaarde(double temperatuurWaarde) {
        this.temperatuurWaarde = temperatuurWaarde;
    }

    public int getLichtWaarde() {
        return lichtWaarde;
    }

    public void setLichtWaarde(int lichtWaarde) {
        this.lichtWaarde = lichtWaarde;
    }

    @Override
    public String toString() {
        return "Meting{" +
                "ID=" + ID +
                ", datum=" + datum +
                ", tijd=" + tijd +
                ", pHWaarde=" + pHWaarde +
                ", temperatuurWaarde=" + temperatuurWaarde +
                ", lichtWaarde=" + lichtWaarde +
                '}';
    }
}
