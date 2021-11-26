package com.example.gebruiker_bp6.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GebruikerAquarium implements Parcelable {
    int ID;
    String gebruikerEmail;

    public GebruikerAquarium(Parcel in) {
        ID = in.readInt();
        gebruikerEmail = in.readString();
    }

    public GebruikerAquarium(int ID, String gebruikerEmail) {
        this.ID = ID;
        this.gebruikerEmail = gebruikerEmail;
    }

    public GebruikerAquarium() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(gebruikerEmail);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GebruikerAquarium> CREATOR = new Creator<GebruikerAquarium>() {
        @Override
        public GebruikerAquarium createFromParcel(Parcel in) {
            return new GebruikerAquarium(in);
        }

        @Override
        public GebruikerAquarium[] newArray(int size) {
            return new GebruikerAquarium[size];
        }
    };

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getGebruikerEmail() {
        return gebruikerEmail;
    }

    public void setGebruikerEmail(String gebruikerEmail) {
        this.gebruikerEmail = gebruikerEmail;
    }

    @Override
    public String toString() {
        return "Aquarium:  " + ID ;
    }
}
