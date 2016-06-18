package com.example.rafalmaselek.colorfun;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class LevelRanksActivity extends AppCompatActivity {
    private ListView mainListView ;
    private ArrayAdapter<String> listAdapter ;
    String Level;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_ranks);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Level = extras.getString("LEVEL");

        }

        mainListView = (ListView) findViewById( R.id.results_list);

        String[] planets = new String[]{};
        ArrayList<String> planetList = new ArrayList<String>();
        planetList.addAll( Arrays.asList(planets) );

        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, planetList);

        Result result;
        Database Ndb = new Database(this, null, null, 0);
        Log.i("level1", Level);
        Cursor k = Ndb.getAllResults(Level);

        if(k!=null && k.getCount()>0) {
            int i = 1;
            while (k.moveToNext()) {
                result = Ndb.getResultByID(k.getInt(0), Level);
                listAdapter.add(Integer.toString(i) + ". " + result.getUsername() + "\t\t"
                        + Integer.toString(result.getResult()));
                Log.v("user= ", Integer.toString(result.getNr()) + " *** " + result.getUsername() + " "
                        + Integer.toString(result.getResult()) + " / " + Integer.toString(result.getMax_possible_result()));
                i++;
            }
        }
        else
            listAdapter.add("No results! Be first!");

        mainListView.setAdapter( listAdapter );
    }

}
