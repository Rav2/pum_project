package com.example.rafalmaselek.colorfun;

import android.content.Intent;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class DifficultyLevelActivity extends Activity
{

    private static final int AUTHORIZING_DIALOG = 2;
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
        easyGame.putExtra("USERNAME", username);
        startActivity(easyGame);
    }

    public void normal(View view)
    {
        Intent normalGame = new Intent(this, NormalActivity.class);
        normalGame.putExtra("USERNAME", username);
        startActivity(normalGame);
    }

    public void hard(View view)
    {
        Intent hardGame = new Intent(this, HardActivity.class);
        hardGame.putExtra("USERNAME", username);
        startActivity(hardGame);
    }

//    class LoginProgressTask extends AsyncTask<String, Integer, Boolean> {
//        @Override
//        protected Boolean doInBackground(String... params) {
//            try {
//                Thread.sleep(4000);  // Do your real work here
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return Boolean.TRUE;   // Return your real result here
//        }
//        @Override
//        protected void onPreExecute() {
//            showDialog(AUTHORIZING_DIALOG);
//        }
//        @Override
//        protected void onPostExecute(Boolean result) {
//            // result is the value returned from doInBackground
//            removeDialog(AUTHORIZING_DIALOG);
//            Intent normalGame = new Intent(this, NormalActivity.class);
//            normalGame.putExtra("USERNAME",username);
//            startActivity(normalGame);w
//        }
//    }


}
