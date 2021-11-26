package com.example.gebruiker_bp6.view;

import android.os.Bundle;
import android.text.InputType;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gebruiker_bp6.R;
import com.example.gebruiker_bp6.database.DatabaseConnection;
import com.example.gebruiker_bp6.model.GebruikerAquarium;
import com.example.gebruiker_bp6.model.Vis;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class VissenInAquariumActivity extends AppCompatActivity {
    private EditText vissoort;
    private TextView aantal, aquariumID;
    private Button plus, min, opslaan;
    private int hoeveelheid = 1;
    private GebruikerAquarium ga;
    private ArrayList<Vis> vissenArrayList;
    private ArrayAdapter arrayAdapter;
    private ListView listView;
    private AlertDialog alertDialog;
    private AlertDialog.Builder alert, builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vissen_in_aquarium);

        //Pakt geselecteerd aquarium uit Intent
        ga = getIntent().getExtras().getParcelable("gebruikerAquarium");

        //Vult scherm met onderdelen
        vulScherm();

        //Selecteert vissen uit database en activeert parseData();
        vulLijst();

        //Verhoogt aantal
        plus.setOnClickListener(v -> hogerAantal());

        //Verlaagt aantal
        min.setOnClickListener(v -> lagerAantal());

        //Slaat ingevulde vis(sen) op
        opslaan.setOnClickListener(v -> visOpslaan());

        //Toont Alert bij klikken op item in lijst
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Vis v = (Vis) parent.getItemAtPosition(position);
            keuzeLijst(v);
        });
    }

    //Vult scherm met onderdelen
    public void vulScherm(){
        vissoort = findViewById(R.id.VissenInAquarium_Soort_EditText);
        aantal = findViewById(R.id.VissenInAquarium_Aantal_Hoeveelheid);
        opslaan = findViewById(R.id.VissenInAquarium_Opslaan_Knop);
        plus = findViewById(R.id.VissenInAquarium_Plus_Knop);
        min = findViewById(R.id.VissenInAquarium_Min_Knop);
        listView = findViewById(R.id.VissenInAquarium_Vissen_ListView);

        aquariumID = findViewById(R.id.VissenInAquarium_AquariumID);
        aquariumID.setText("Aquarium " + ga.getID());
    }

    //Selecteert vissen uit database
    public void vulLijst(){
        RequestParams params = new RequestParams();
        params.put("aquariumid", ga.getID());

        DatabaseConnection.connect("vissen/readspecific?", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    parseData(data, ga);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Handelt data af en vult de lijst
    public void parseData(JSONArray data, GebruikerAquarium ga) {
        vissenArrayList = new ArrayList<>();
        try {
            for (int i=0; i < data.length(); i++)
            {
                JSONObject oneObject = data.getJSONObject(i);

                Vis v = new Vis();
                v.setAquariumID(ga.getID());
                v.setAantal(oneObject.getInt("aantal"));
                v.setSoort(oneObject.getString("soort"));

                vissenArrayList.add(v);
            }
            arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, vissenArrayList);
            listView.setAdapter(arrayAdapter);
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }

    //Verhoogt aantal
    public void hogerAantal(){
        hoeveelheid++;
        String text = String.valueOf(hoeveelheid);
        aantal.setText(text);
    }

    //Verlaagt aantal
    public void lagerAantal(){
        if (hoeveelheid > 1){
            hoeveelheid--;
            String text = String.valueOf(hoeveelheid);
            aantal.setText(text);
        }
    }

    //Opent alert om aantal te wijzigen of vissen te verwijderen
    public void keuzeLijst(Vis v){
        alert = new AlertDialog.Builder(this);
        alert.setMessage("Vissen verwijderen of wijzigen?");
        alert.setCancelable(true);

        alert.setPositiveButton("Aantal wijzigen", (dialog, id) -> aantalWijzigenPopUp(v));
        alert.setNegativeButton("Vissen verwijderen", (dialog, id) -> vissenVerwijderenPopUp(v));
        alert.setNeutralButton("Annuleren", (dialog, which) -> dialog.cancel());

        alertDialog = alert.create();
        alertDialog.show();
    }

    //Maakt aantal vissen wijzigen mogelijk
    public void aantalWijzigenPopUp(Vis v){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Aantal vissen");

        EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        builder.setView(input);

        builder.setPositiveButton("Opslaan", (dialog12, which) -> {
            v.setAantal(Integer.parseInt(input.getText().toString()));
            updateVis(v);
        });

        builder.setNegativeButton("Annuleren", (dialog1, which) -> dialog1.cancel());
        builder.show();
    }

    //Maakt vissen verwijderen mogelijk
    public void vissenVerwijderenPopUp(Vis v){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Vissen verwijderen?");

        alert.setPositiveButton("Verwijderen", (dialog, id) -> verwijderVis(v));
        alert.setNegativeButton("Annuleren", (dialog, id) -> dialog.cancel());

        alertDialog = alert.create();
        alertDialog.show();
    }

    //Slaat ingevulde vissen op
    public void visOpslaan(){
        if(vissoort.getText().toString().isEmpty()){
            Toast.makeText(this, "Vul een vissoort in", Toast.LENGTH_LONG).show();
        } else {
            RequestParams params = new RequestParams();
            params.put("aquariumid", ga.getID());
            params.put("soort", vissoort.getText().toString());
            params.put("aantal", aantal.getText().toString());

            DatabaseConnection.connect("vissen/create?", params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Toast.makeText(getApplicationContext(), "Vissen Opgeslagen", Toast.LENGTH_LONG).show();
                    vulLijst();
                }
            });
        }
    }

    //Update aantal vissen in database
    public void updateVis(Vis v){
        RequestParams params = new RequestParams();
        params.put("aquariumid", v.getAquariumID());
        params.put("soort", v.getSoort());
        params.put("aantal", v.getAantal());

        DatabaseConnection.connect("vissen/update?", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                vulLijst();
                Toast.makeText(getApplicationContext(), "Aantal gewijzigd", Toast.LENGTH_LONG).show();
            }
        });
    }

    //Verwijdert vissen uit database
    public void verwijderVis(Vis v){
        RequestParams params = new RequestParams();
        params.put("aquariumid", v.getAquariumID());
        params.put("soort", v.getSoort());
        params.put("aantal", v.getAantal());

        DatabaseConnection.connect("vissen/delete?", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                vulLijst();
                Toast.makeText(getApplicationContext(), "Vis verwijderd", Toast.LENGTH_LONG).show();
            }
        });

    }
}