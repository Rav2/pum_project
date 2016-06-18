package com.example.rafalmaselek.colorfun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RanksActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranks);
    }
    public void normalRanks(View view)
    {
        Intent normalRanksIntent = new Intent(this, LevelRanksActivity.class);
        normalRanksIntent.putExtra("LEVEL", "NORMAL");
        startActivity(normalRanksIntent);
    }
    public void easyRanks(View view)
    {
        Intent easyRanksIntent = new Intent(this, LevelRanksActivity.class);
        easyRanksIntent.putExtra("LEVEL", "EASY");
        startActivity(easyRanksIntent);
    }
    public void hardRanks(View view)
    {
        Intent hardRanksIntent = new Intent(this, LevelRanksActivity.class);
        hardRanksIntent.putExtra("LEVEL", "HARD");
        startActivity(hardRanksIntent);
    }



}
