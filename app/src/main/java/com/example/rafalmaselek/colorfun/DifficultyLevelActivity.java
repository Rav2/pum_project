package com.example.rafalmaselek.colorfun;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class DifficultyLevelActivity extends Activity
{

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_level);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("USERNAME");
            Log.i("username: ", username);

        }

    }

    public void easy(View view)
    {
        Intent easyGame = new Intent(this, EasyActivity.class);
        startActivity(easyGame);
    }

    public void normal(View view)
    {
        Intent normalGame = new Intent(this, NormalActivity.class);
        normalGame.putExtra("USERNAME",username);
        startActivity(normalGame);
    }

    public void hard(View view)
    {
        Intent hardGame = new Intent(this, HardActivity.class);
        startActivity(hardGame);
    }

}
