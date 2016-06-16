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
            result = extras.getString("RESULT");

        }

        Log.i("pobieram:  ",result);
        EditText resultEditText = (EditText) findViewById(R.id.resultEditText);
        resultEditText.setText(result);

    }

    public void playAgain(View view){
        Intent levelIntent = new Intent(this, DifficultyLevelActivity.class);
        startActivity(levelIntent);
    }

    public void exit(View view){
        Intent exitIntent = new Intent(this, MainActivity.class);
        startActivity(exitIntent);
    }

    public void saveData(View view){
        String dataToSave = "";
        EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
        username = String.valueOf(nameEditText.getText());
        if (username.equals("nazwa użytkownika")|| username.equals("")){
            Log.v("uwaga! ", "niepprawne dane");
            // dialog wskazujący na niepoprawnie wpisane dane
            //takich nie możemy wpisać do bazy danych
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("Uwaga!");
            builder1.setMessage("Proszę podać poprawne dane!");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

//            builder1.setNegativeButton(
//                    "No",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            dialog.cancel();
//                        }
//                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
        else{
            // zapisujemy dane do bazy danych
            dataToSave = username +": "+result;
            Log.v("DB: ", dataToSave);

        }




        // zaimplementować zachowanie danych do BD
        // dane w postaci: username + result jako string


    }




//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//    }
//
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//    }
}
