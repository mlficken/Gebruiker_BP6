package com.example.gebruiker_bp6.model;

public class Vis {
    private int aquariumID, aantal;
    private String soort;

    public Vis(int aquariumID, int aantal, String soort) {
        this.aquariumID = aquariumID;
        this.aantal = aantal;
        this.soort = soort;
    }

    public Vis() {
    }

    public int getAquariumID() {
        return aquariumID;
    }

    public void setAquariumID(int aquariumID) {
        this.aquariumID = aquariumID;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    public String getSoort() {
        return soort;
    }

    public void setSoort(String soort) {
        this.soort = soort;
    }

    @Override
    public String toString() {
        return aantal + " " + soort;
    }
}
