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
 * Created by Monia on 16.06.2016.
 */
public class RegisterActivity extends Activity {
    private String username = "";
    EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameEditText = (EditText) findViewById(R.id.nameEditText);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("USERNAME");
            Log.i("username: ", username);
            nameEditText.setText(username);


        }

    }

    public void saveData(View view){
        String dataToSave = "";

        username = String.valueOf(nameEditText.getText());
        nameEditText.setTextKeepState(username);
        if ( username.equals("")){
            Log.v("Warning! ", "wrong data");
            // dialog wskazujący na niepoprawnie wpisane dane
            //takich nie możemy wpisać do bazy danych
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("Warning!");
            builder1.setMessage("Enter correct username!");
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
            dataToSave = username ;
            Log.v("DB: ", dataToSave);
            Intent levelIntent = new Intent(this, DifficultyLevelActivity.class);
            levelIntent.putExtra("USERNAME", username);
            startActivity(levelIntent);


        }




        // zaimplementować zachowanie danych do BD
        // dane w postaci: username + result jako string




    }




}
