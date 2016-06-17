package com.example.rafalmaselek.colorfun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity
{
    String username = "player";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("USERNAME");
            Log.i("username: ", username);


        }
    }

    public void play(View view)
    {
        Intent registerIntent = new Intent(this, RegisterActivity.class);
        registerIntent.putExtra("USERNAME",username);
        startActivity(registerIntent);
//        Intent levelIntent = new Intent(this, DifficultyLevelActivity.class);
//        startActivity(levelIntent);
    }

    public void ranks(View view)
    {
        Intent ranksIntent = new Intent(this, RanksActivity.class);
        startActivity(ranksIntent);
    }

    public void quit(View view)
    {
        finish();
        System.exit(0);
    }
}
