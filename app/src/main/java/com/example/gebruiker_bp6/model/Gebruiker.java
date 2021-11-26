package com.example.gebruiker_bp6.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Gebruiker implements Parcelable {
    String email, ww, naam;

    public Gebruiker(Parcel in) {
        email = in.readString();
        ww = in.readString();
        naam = in.readString();
    }

    public Gebruiker(String email, String ww, String naam) {
        this.email = email;
        this.ww = ww;
        this.naam = naam;
    }

    public static final Creator<Gebruiker> CREATOR = new Creator<Gebruiker>() {
        @Override
        public Gebruiker createFromParcel(Parcel in) {
            return new Gebruiker(in);
        }

        @Override
        public Gebruiker[] newArray(int size) {
            return new Gebruiker[size];
        }
    };

    public Gebruiker() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWw() {
        return ww;
    }

    public void setWw(String ww) {
        this.ww = ww;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public String toString() {
        return "Gebruiker{" +
                "email='" + email + '\'' +
                ", ww='" + ww + '\'' +
                ", naam='" + naam + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(ww);
        dest.writeString(naam);
    }
}
