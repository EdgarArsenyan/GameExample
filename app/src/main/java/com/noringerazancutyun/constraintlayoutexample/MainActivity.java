package com.noringerazancutyun.constraintlayoutexample;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int leftPoint, rightPoint, milis, bestResult = 50000;
    private int point = 0;
    private TextView summ, bestResultText;
    private boolean running = false;
    private boolean clickable = false;


    Button leftButton, rightButton, startButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leftButton = findViewById(R.id.button);
        rightButton = findViewById(R.id.button2);
        startButton = findViewById(R.id.start_button);
        summ = findViewById(R.id.textView3);
        bestResultText = findViewById(R.id.textView4);

        rightButton.setTextSize(20f);
        leftButton.setTextSize(20f);



        clickButton();
        restart();
    }

    public void randomPoints() {
        Random left = new Random();
        Random right = new Random();
        leftPoint = left.nextInt(50);
        rightPoint = right.nextInt(50);
        leftButton.setText(String.valueOf(leftPoint));
        rightButton.setText(String.valueOf(rightPoint));
    }

    public void clickButton() {

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (point != 10) {

                    if (leftPoint > rightPoint) {
                        ++point;
                        if (point == 10){
                            running =false;
                        }

                        summ.setText(String.valueOf(point));
                        randomPoints();
                    } else if (leftPoint < rightPoint) {
                        if (point > 0) {
                            --point;
                        }
                        summ.setText(String.valueOf(point));
                        randomPoints();
                    } else {
                        randomPoints();

                    }
                } else {
                    clickable = false;
                    v.setClickable(clickable);
                }
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (point != 10) {

                    if (rightPoint > leftPoint) {
                        ++point;
                        if (point == 10){
                            running =false;
                        }
                        summ.setText(String.valueOf(point));
                        randomPoints();
                    } else if (leftPoint > rightPoint) {
                        if (point > 0) {
                            --point;
                        }
                        summ.setText(String.valueOf(point));
                        randomPoints();
                    } else {
                        randomPoints();
                    }
                } else {
                    clickable = false;
                    v.setClickable(clickable);
                }
            }
        });
    }

    public void restart(){
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bestResult > milis){
                    bestResult = milis;
                    bestResultText.setText(toString().valueOf(bestResult));
                }
                randomPoints();
                leftButton.setClickable(true);
                rightButton.setClickable(true);
                runTimer();
                running = true;
                clickable = true;
                point = 0;
            }
        });
    }


    public void runTimer() {

        final TextView time = findViewById(R.id.textView5);
        final Handler handler = new Handler();
        milis = 0;
        handler.post(new Runnable() {
            @Override
            public void run() {
                int second = milis / 10;
                int milliSeconds = milis % 10;
                String text = String.format("%02d:%d", second, milliSeconds);
                time.setText(text);
                if (running) {
                        ++milis;
                }
                handler.postDelayed(this, 100);
            }
        });
    }

}
