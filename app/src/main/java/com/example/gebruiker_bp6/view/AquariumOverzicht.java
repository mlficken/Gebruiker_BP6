package com.example.gebruiker_bp6.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gebruiker_bp6.R;
import com.example.gebruiker_bp6.database.DatabaseConnection;
import com.example.gebruiker_bp6.model.GebruikerAquarium;
import com.example.gebruiker_bp6.model.Meting;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class AquariumOverzicht extends AppCompatActivity {
    private GraphView graph_pH, graphLicht, graphTemp;
    private TextView aquariumID;
    private ArrayList<Meting> metingLijst;
    private GebruikerAquarium ga;
    private LineGraphSeries<DataPoint> series, series2, series3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aquarium_overzicht);

        // Zet aquariumID bovenaan scherm
        ga = getIntent().getExtras().getParcelable("gebruikerAquarium");
        aquariumID = findViewById(R.id.Overzicht_AquariumID);
        aquariumID.setText("Aquarium " + ga.getID());

        maakTabellen();
        vulTabellen();
    }

    public void maakTabellen(){
        graph_pH = findViewById(R.id.Overzicht_pHgraph);
        graphLicht = findViewById(R.id.Overzicht_Lichtgraph);
        graphTemp = findViewById(R.id.Overzicht_Tempgraph);

        graph_pH.setTitle("pH-Waarde");
        graph_pH.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graph_pH.getGridLabelRenderer().setNumHorizontalLabels(3);
        graph_pH.getViewport().setYAxisBoundsManual(false);
        graph_pH.getViewport().setXAxisBoundsManual(false);

        graphLicht.setTitle("Licht Waarde");
        graphLicht.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graphLicht.getGridLabelRenderer().setNumHorizontalLabels(3);
        graphLicht.getViewport().setYAxisBoundsManual(false);
        graphLicht.getViewport().setXAxisBoundsManual(false);

        graphTemp.setTitle("Temperatuur Waarde");
        graphTemp.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graphTemp.getGridLabelRenderer().setNumHorizontalLabels(3);
        graphTemp.getViewport().setYAxisBoundsManual(false);
        graphTemp.getViewport().setXAxisBoundsManual(false);
    }

    public void vulTabellen(){
        RequestParams params = new RequestParams();
        params.put("aquariumid", ga.getID());

        DatabaseConnection.connect("metingen/readspecific?", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                series = new LineGraphSeries<>();
                series2 = new LineGraphSeries<>();
                series3 = new LineGraphSeries<>();
                try {
                    JSONArray data = response.getJSONArray("data");
                    parseData(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                graph_pH.addSeries(series);
                series.setDrawDataPoints(true);
                series.setDataPointsRadius(10);
                series.setThickness(5);

                graphTemp.addSeries(series2);
                series2.setDrawDataPoints(true);
                series2.setDataPointsRadius(10);
                series2.setThickness(5);

                graphLicht.addSeries(series3);
                series3.setDrawDataPoints(true);
                series3.setDataPointsRadius(10);
                series3.setThickness(5);
            }
        });
    }

    public void parseData(JSONArray data) {
        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject oneObject = data.getJSONObject(i);
                Meting m = new Meting();
                m.setpHWaarde(oneObject.getDouble("phwaarde"));
                m.setTemperatuurWaarde(oneObject.getDouble("temperatuur"));
                m.setLichtWaarde(oneObject.getInt("lichtwaarde"));

                // Datum en tijd juist zetten
                String test = oneObject.getString("datum");
                String[] testArray;
                testArray = test.split("T");
                String[] datum;
                datum = testArray[0].split("-");
                String[] tijd;
                String tijds = oneObject.getString("tijd");
                tijd = tijds.split(":");

                Date date = new Date(Integer.parseInt(datum[0]), Integer.parseInt(datum[1]), Integer.parseInt(datum[2]), Integer.parseInt(tijd[0]), Integer.parseInt(tijd[1]));
                m.setDatum(date);

                m.setID(oneObject.getInt("aquariumid"));

                DataPoint point = new DataPoint(date, m.getpHWaarde());
                series.appendData(point, true, 40);
                DataPoint point2 = new DataPoint(date, m.getTemperatuurWaarde());
                series2.appendData(point2, true, 40);
                DataPoint point3 = new DataPoint(date, m.getLichtWaarde());
                series3.appendData(point3, true, 40);
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }
}