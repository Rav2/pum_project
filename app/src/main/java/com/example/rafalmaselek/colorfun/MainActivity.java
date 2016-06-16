package com.example.rafalmaselek.colorfun;
//komentarz
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void play(View view)
    {
        Intent levelIntent = new Intent(this, DifficultyLevelActivity.class);
        startActivity(levelIntent);
    }

    public void ranks(View view)
    {
        Intent ranksIntent = new Intent(this, RanksActivity.class);
        startActivity(ranksIntent);
    }
}
