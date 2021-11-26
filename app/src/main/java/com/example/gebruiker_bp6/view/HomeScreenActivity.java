package com.example.gebruiker_bp6.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gebruiker_bp6.R;
import com.example.gebruiker_bp6.model.Gebruiker;

public class HomeScreenActivity extends AppCompatActivity {
    private Button aquariums, vissen, gegevens;
    private Gebruiker gebruiker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // Aangemaakte elementen uit view pakken
        aquariums = findViewById(R.id.HomeScreen_Aquarium_Knop);
        vissen = findViewById(R.id.HomeScreen_Vissen_Knop);
        gegevens = findViewById(R.id.HomeScreen_Gegevens_Knop);

        gebruiker = getIntent().getExtras().getParcelable("gebruiker");

        // Actie wanneer er op knop wordt geklikt
        aquariums.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), AlleAquariumsOverzichtActivity.class);
            i.putExtra("gebruiker", gebruiker);
            startActivity(i);
        });

        // Actie wanneer er op knop wordt geklikt
        vissen.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), VissenBeherenActivity.class);
            i.putExtra("gebruiker", gebruiker);
            startActivity(i);
        });

        // Actie wanneer er op knop wordt geklikt
        gegevens.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), MijnGegevensActivity.class);
            i.putExtra("gebruiker", gebruiker);
            startActivity(i);
        });
    }
}