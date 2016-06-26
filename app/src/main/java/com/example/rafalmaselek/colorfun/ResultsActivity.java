package com.example.rafalmaselek.colorfun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Monia on 15.06.2016.
 */
public class ResultsActivity extends Activity  {
    private String result = "";
    private String username = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            result = extras.getString("RESULT")+"/"+extras.getString("TOTAL");
            username = extras.getString("USERNAME");

        }

        Log.i("pobieram:  ", result);
        EditText resultEditText = (EditText) findViewById(R.id.resultEditText);
        resultEditText.setText(result);
        Database Ndb = new Database(this, null, null, 0);
        Log.i("Level:  ", extras.getString("LEVEL"));
        Ndb.addResult(username, Integer.parseInt(extras.getString("RESULT")), Integer.parseInt(extras.getString("TOTAL")), extras.getString("LEVEL"));


    }

    public void playAgain(View view){
        Intent levelIntent = new Intent(this, DifficultyLevelActivity.class);
        levelIntent.putExtra("USERNAME", username);
        startActivity(levelIntent);
    }

    public void exit(View view){
        Intent exitIntent = new Intent(this, MainActivity.class);
        exitIntent.putExtra("USERNAME",username);
        startActivity(exitIntent);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this, DifficultyLevelActivity.class);
        startActivity(intent);

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();

            finish();


    }


    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
}
