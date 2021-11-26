package com.example.gebruiker_bp6.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gebruiker_bp6.R;
import com.example.gebruiker_bp6.database.DatabaseConnection;
import com.example.gebruiker_bp6.model.Gebruiker;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MijnGegevensActivity extends AppCompatActivity {
    private Gebruiker gebruiker;
    private EditText email, naam, wachtwoord;
    private Button opslaan;
    private RequestParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mijn_gegevens);

        gebruiker = getIntent().getExtras().getParcelable("gebruiker");

        vulScherm();

        vulTekstVelden();

        opslaan.setOnClickListener(v ->{
            try {
                updateGebruiker();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    //Pakt velden uit XML
    public void vulScherm(){
        email = findViewById(R.id.MijnGegevens_Email_EditText);
        naam = findViewById(R.id.MijnGegevens_Naam_EditText);
        wachtwoord = findViewById(R.id.MijnGegevens_Wachtwoord_EditText);
        opslaan = findViewById(R.id.MijnGegevens_Opslaan_Knop);
    }

    //Vult tekstvelden met gegevens gebruiker
    public void vulTekstVelden() {
        params = new RequestParams();
        params.put("emailadres", gebruiker.getEmail());

        DatabaseConnection.connect("gebruikers/readspecific?", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Gebruiker g = new Gebruiker();

                    JSONArray data = response.getJSONArray("data");
                    JSONObject dataGebruiken = (JSONObject) data.get(0);

                    g.setEmail(dataGebruiken.getString("email"));
                    g.setNaam(dataGebruiken.getString("naam"));
                    g.setWw(dataGebruiken.getString("wachtwoord"));

                    email.setText(gebruiker.getEmail());
                    naam.setText(gebruiker.getNaam());
                    wachtwoord.setText(gebruiker.getWw());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Update gebruiker
    public void updateGebruiker() throws JSONException {
        RequestParams params = new RequestParams();
        params.put("wachtwoord", wachtwoord.getText().toString());
        params.put("emailadres", email.getText().toString());
        params.put("naam", naam.getText().toString());

        DatabaseConnection.connect("gebruikers/update", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Toast.makeText(getApplicationContext(), "Gegevens Gewijzigd", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), HomeScreenActivity.class);
                i.putExtra("gebruiker", gebruiker);
                startActivity(i);
            }
        });
    }
}