package com.example.rafalmaselek.colorfun;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
            username=extras.getString("USERNAME");

        }

        Log.i("pobieram:  ",result);
        EditText resultEditText = (EditText) findViewById(R.id.resultEditText);
        resultEditText.setText(result);

    }

    public void playAgain(View view){
        Intent levelIntent = new Intent(this, DifficultyLevelActivity.class);
        levelIntent.putExtra("USERNAME",username);
        startActivity(levelIntent);
    }

    public void exit(View view){
        Intent exitIntent = new Intent(this, MainActivity.class);
        startActivity(exitIntent);
    }






    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }
}
