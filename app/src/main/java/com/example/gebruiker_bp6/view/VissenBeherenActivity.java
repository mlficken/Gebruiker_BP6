package com.example.gebruiker_bp6.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gebruiker_bp6.R;
import com.example.gebruiker_bp6.database.DatabaseConnection;
import com.example.gebruiker_bp6.model.Gebruiker;
import com.example.gebruiker_bp6.model.GebruikerAquarium;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class VissenBeherenActivity extends AppCompatActivity {
    private ArrayList<GebruikerAquarium> gebruikerAquariums;
    private ArrayAdapter arrayAdapter;
    private ListView listView;
    private Gebruiker gebruiker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vissen_beheren);

        //Pakt gebruiker van vorige scherm mee
        gebruiker = getIntent().getExtras().getParcelable("gebruiker");

        //Pakt listview van XML file.
        listView = findViewById(R.id.VissenBeheren_Vissen_ListView);

        //Vult listview
        vulLijst();

        //Opent volgende scherm na klikken op item
        listView.setOnItemClickListener((parent, view, position, id) -> {
            GebruikerAquarium ga = (GebruikerAquarium)parent.getItemAtPosition(position);
            Intent i = new Intent(this, VissenInAquariumActivity.class);
            i.putExtra("gebruikerAquarium", ga);
            startActivity(i);
        });
    }

    //Selecteert vissen uit database
    public void vulLijst(){
        RequestParams params = new RequestParams();
        params.put("emailadres", gebruiker.getEmail());

        DatabaseConnection.connect("gebruikeraquariums/readspecific?", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    parseData(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    //Handelt data af en vult de lijst
    public void parseData(JSONArray data) {
        gebruikerAquariums = new ArrayList<>();
        try {
            for (int i=0; i < data.length(); i++)
            {
                JSONObject oneObject = data.getJSONObject(i);

                GebruikerAquarium g = new GebruikerAquarium();
                g.setID(oneObject.getInt("aquariumid"));
                g.setGebruikerEmail(oneObject.getString("gebruikeremail"));

                gebruikerAquariums.add(g);
            }
            arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, gebruikerAquariums);
            listView.setAdapter(arrayAdapter);
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }
}