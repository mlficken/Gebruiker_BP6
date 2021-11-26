package com.example.gebruiker_bp6.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Omgevingstemperatuur {
    int ID;
    double temperatuur;
    LocalDate datum;
    LocalTime tijd;

    public Omgevingstemperatuur(int ID, double temperatuur, LocalDate datum, LocalTime tijd) {
        this.ID = ID;
        this.temperatuur = temperatuur;
        this.datum = datum;
        this.tijd = tijd;
    }


    public Omgevingstemperatuur() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getTemperatuur() {
        return temperatuur;
    }

    public void setTemperatuur(double temperatuur) {
        this.temperatuur = temperatuur;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public LocalTime getTijd() {
        return tijd;
    }

    public void setTijd(LocalTime tijd) {
        this.tijd = tijd;
    }
}
