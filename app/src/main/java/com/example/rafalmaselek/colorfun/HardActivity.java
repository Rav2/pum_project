package com.example.rafalmaselek.colorfun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class HardActivity extends Activity
{

    // Intent resultIntent = new Intent(this, ResultsActivity.class);
    // Handler handler;
    int[] colorRefList;
    int[] antiColorRefList;

    protected int numberOfColors;
    String[] colorList;
    String[] antiColorList;


    ArrayList<Integer> currentColors;
    ArrayList<Integer> antiCurrentColors;

    String currentSolution;
    int result;
    int totalRandoms;
    View contentView;
    ProgressBar mProgressBar;
    CountDownTimer mCountDownTimer;
    int timer_i=0;
    String username = "";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("USERNAME");
            Log.i("username: ", username);

        }



        result = 0;
        setContentView(R.layout.activity_hard);
        colorList = new String[]{"beige", "crimson", "salmon", "coral", "khaki", "lime", "aquamarine",
                "olive", "turqoise", "navy", "magenta", "indigo", "chocolate", "maroon", "gold", "cyan", "lemon",
                "chartreuse", "dark green"};
        antiColorList = new String[]{"antiBeige", "antiCrimson", "antiSalmon", "antiCoral", "antiKhaki", "antiLime", "antiAquamarine",
                "antiOlive", "antiTurquoise", "antiNavy", "antiMagenta", "antiIndigo", "antiChocolade", "antiMaroon", "antiGold", "antiCyan",
                "antiLemon", "antiChartreuse", "antiDark_green"};

        numberOfColors = colorList.length;
        colorRefList = new int[]{R.color.beige, R.color.crimson, R.color.salmon, R.color.coral,
                R.color.khaki, R.color.lime, R.color.aquamarine, R.color.olive, R.color.turqoise, R.color.navy, R.color.magenta,
                R.color.indigo, R.color.chocolate, R.color.maroon, R.color.gold, R.color.cyan, R.color.lemon, R.color.chartreuse,
                R.color.dark_green};
        antiColorRefList = new int[]{R.color.antiBeige, R.color.antiCrimson, R.color.antiSalmon, R.color.antiCoral,
                R.color.antiKhaki, R.color.antiLime, R.color.antiAquamarine, R.color.antiOlive, R.color.antiTurqoise, R.color.antiNavy, R.color.antiMagenta,
                R.color.antiIndigo, R.color.antiChocolate, R.color.antiMaroon, R.color.antiGold, R.color.antiCyan, R.color.antiLemon, R.color.antiChartreuse,
                R.color.antiDark_green};
        contentView = this.findViewById(android.R.id.content);
        randomizeColors();
        mProgressBar=(ProgressBar)findViewById(R.id.progressbar);
        mProgressBar.setProgress(timer_i);
        mCountDownTimer=new CountDownTimer(30000,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                //   Log.v("Log_tag", "Tick of Progress" + timer_i + millisUntilFinished);
                timer_i++;
                mProgressBar.setProgress(timer_i);

            }
            @Override
            public void onFinish() {

                // tutaj musimy startować intent wynik i zagraj ponownie
                //Do what you want
                timer_i++;
                mProgressBar.setProgress(timer_i);
                // Log.v("result = ", String.valueOf(result));
                // Log.v("total = ", String.valueOf(totalRandoms));

                // startujemy intent z wynikami
                Intent intent = new Intent(HardActivity.this, ResultsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("RESULT", String.valueOf(result));
                intent.putExtra("TOTAL", String.valueOf(totalRandoms));
                intent.putExtra("USERNAME",username);
                intent.putExtra("LEVEL", "HARD");
                startActivity(intent);
                ;
            }
        };
        mCountDownTimer.start();

    }

    public void nextQuestion(View view)
    {
        TextView t = (TextView)view;
        totalRandoms ++;
        if(currentSolution == t.getText())
            result +=1;
        else {
            if (result > 0)
                result -= 1;
        }
        //  System.out.println("result = "+ result);
        randomizeColors();
    }

    private void randomizeColors()
    {
        Random r = new Random();
        int colorNo;
        currentColors = new ArrayList<Integer>(numberOfColors); //lista kolorow uzywanych w danej chwili na ekranie
        //   antiCurrentColors = new ArrayList<Integer>(numberOfColors);
        for(int ii=0; ii<9; ii++)
        {
            colorNo = (r.nextInt(numberOfColors));
            do
            {
                colorNo = (r.nextInt(numberOfColors));
            }
            while(currentColors.contains(colorNo));
            currentColors.add(colorNo);
            //  antiCurrentColors.add(colorNo);
        }
        Log.v("currenList= ",currentColors.toString());

        int solution = currentColors.get(r.nextInt(4));
        currentSolution = colorList[solution];  //rozwiazaniem jest jeden z pierwszych 4 kolorow
        View square = findViewById(R.id.squareView);
        square.setBackgroundColor(getResources().getColor(colorRefList[solution]));//colorRefList[solution]); //ustawiamy kolor kwadratu
        //ustawiamy napisy na przyciskach
        TextView t1=(TextView)findViewById(R.id.answ1);
        t1.setText(colorList[currentColors.get(0)]);
        TextView t2=(TextView)findViewById(R.id.answ2);
        t2.setText(colorList[currentColors.get(1)]);
        TextView t3=(TextView)findViewById(R.id.answ3);
        t3.setText(colorList[currentColors.get(2)]);
        TextView t4=(TextView)findViewById(R.id.answ4);
        t4.setText(colorList[currentColors.get(3)]);
        //ustawiamy kolory obramowan przyciskow, inne niz napisy
        View bt1bd1 = findViewById(R.id.bt1bdUP);
        View bt1bd2 = findViewById(R.id.bt1bdRI);
        View bt1bd3 = findViewById(R.id.bt1bdLE);
        View bt1bd4 = findViewById(R.id.bt1bdDO);


        bt1bd1.setBackgroundColor(getResources().getColor(colorRefList[currentColors.get(5)]));
        bt1bd2.setBackgroundColor(getResources().getColor(colorRefList[currentColors.get(5)]));
        bt1bd3.setBackgroundColor(getResources().getColor(colorRefList[currentColors.get(5)]));
        bt1bd4.setBackgroundColor(getResources().getColor(colorRefList[currentColors.get(5)]));



        View bt2bd1 = findViewById(R.id.bt2bdUP);
        View bt2bd2 = findViewById(R.id.bt2bdRI);
        View bt2bd3 = findViewById(R.id.bt2bdLE);
        View bt2bd4 = findViewById(R.id.bt2bdDO);
        bt2bd1.setBackgroundColor(getResources().getColor(colorRefList[currentColors.get(6)]));
        bt2bd2.setBackgroundColor(getResources().getColor(colorRefList[currentColors.get(6)]));
        bt2bd3.setBackgroundColor(getResources().getColor(colorRefList[currentColors.get(6)]));
        bt2bd4.setBackgroundColor(getResources().getColor(colorRefList[currentColors.get(6)]));
        View bt3bd1 = findViewById(R.id.bt3bdUP);
        View bt3bd2 = findViewById(R.id.bt3bdRI);
        View bt3bd3 = findViewById(R.id.bt3bdLE);
        View bt3bd4 = findViewById(R.id.bt3bdDO);
        bt3bd1.setBackgroundColor(getResources().getColor(colorRefList[currentColors.get(7)]));
        bt3bd2.setBackgroundColor(getResources().getColor(colorRefList[currentColors.get(7)]));
        bt3bd3.setBackgroundColor(getResources().getColor(colorRefList[currentColors.get(7)]));
        bt3bd4.setBackgroundColor(getResources().getColor(colorRefList[currentColors.get(7)]));
        View bt4bd1 = findViewById(R.id.bt4bdUP);
        View bt4bd2 = findViewById(R.id.bt4bdRI);
        View bt4bd3 = findViewById(R.id.bt4bdLE);
        View bt4bd4 = findViewById(R.id.bt4bdDO);
        bt4bd1.setBackgroundColor(getResources().getColor(colorRefList[currentColors.get(8)]));
        bt4bd2.setBackgroundColor(getResources().getColor(colorRefList[currentColors.get(8)]));
        bt4bd3.setBackgroundColor(getResources().getColor(colorRefList[currentColors.get(8)]));
        bt4bd4.setBackgroundColor(getResources().getColor(colorRefList[currentColors.get(8)]));

        //ustawiamy kolory tekstu na przyciskach, inne niz kolor rozwiazania
        currentColors = new ArrayList<Integer>(4);
        antiCurrentColors = new ArrayList<Integer>(4);
        for(int ii=0; ii<4; ii++)
        {
            do
            {
                colorNo = (r.nextInt(numberOfColors));
            }
            while(colorNo == solution || currentColors.contains((colorRefList[colorNo])));//currentColors.isEmpty() || !currentColors.contains(colorNo));
            currentColors.add(colorRefList[colorNo]);
            antiCurrentColors.add(antiColorRefList[colorNo]);
        }


        t1.setTextColor(getResources().getColor(currentColors.get(0)));
        t1.setBackgroundColor(getResources().getColor(antiCurrentColors.get(0)));

        t2.setTextColor(getResources().getColor(currentColors.get(1)));
        t2.setBackgroundColor(getResources().getColor(antiCurrentColors.get(1)));


        t3.setTextColor(getResources().getColor(currentColors.get(2)));
        t3.setBackgroundColor(getResources().getColor(antiCurrentColors.get(2)));

        t4.setTextColor(getResources().getColor(currentColors.get(3)));
        t4.setBackgroundColor(getResources().getColor(antiCurrentColors.get(3)));



    }


}
