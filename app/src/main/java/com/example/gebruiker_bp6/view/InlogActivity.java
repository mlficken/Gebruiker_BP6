package com.example.gebruiker_bp6.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class InlogActivity extends AppCompatActivity {
    private EditText email, wachtwoord;
    private Button inloggen;
    private Gebruiker ingelogdeGebruiker = new Gebruiker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inlog);

        //Pakt velden uit XML
        vulScherm();

        // Actie wanneer er op knop wordt geklikt
        inloggen.setOnClickListener(v -> {

            ingelogdeGebruiker.setEmail("hans@gmail.com");
            ingelogdeGebruiker.setWw("abcdefg");
            startHomeScreen(v);

            //TODO login activeren, 44-46 weg.
            //login(v);
        });
    }

    //Pakt velden uit XML
    public void vulScherm(){
        email = findViewById(R.id.Inlog_Email_EditText);
        wachtwoord = findViewById(R.id.Inlog_Wachtwoord_EditText);
        inloggen = findViewById(R.id.Inlog_Inloggen_Knop);
    }

    //Controleert login gegevens met database
    public void login(View v){
        RequestParams params = new RequestParams();
        params.put("emailadres", email.getText().toString());
        params.put("wachtwoord", wachtwoord.getText().toString());

        DatabaseConnection.connect("gebruikers/checklogin?", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    Gebruiker gebruiker = new Gebruiker();

                    JSONArray data = response.getJSONArray("data");
                    JSONObject dataGebruiken = (JSONObject) data.get(0);

                    gebruiker.setEmail(dataGebruiken.getString("email"));
                    gebruiker.setWw(dataGebruiken.getString("wachtwoord"));

                    if (gebruiker.getEmail().equals(email.getText().toString()) && gebruiker.getWw().equals(wachtwoord.getText().toString())) {
                        ingelogdeGebruiker = gebruiker;
                        Toast.makeText(getApplicationContext(), "Ingelogd", Toast.LENGTH_LONG).show();
                        startHomeScreen(v);
                    } else {
                        Toast.makeText(getApplicationContext(), "Controleer inloggegevens", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Start volgende scherm en stuurt gebruiker mee
    public void startHomeScreen(View v){
        Intent i = new Intent(v.getContext(), HomeScreenActivity.class);
        i.putExtra("gebruiker", ingelogdeGebruiker);
        startActivity(i);
    }
}