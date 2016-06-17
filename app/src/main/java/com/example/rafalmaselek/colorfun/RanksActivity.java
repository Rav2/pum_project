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
        Intent normalRanksIntent = new Intent(this, NormalRanksActivity.class);
        startActivity(normalRanksIntent);
    }



}
