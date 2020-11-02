package com.ort.pulsecountdown;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import  com.gusakov.library.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {

PulseCountDown pulseCountDown;
Button startBtn;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pulseCountDown = findViewById(R.id.pulseCountDown);
       iniciar();
    }
    private void iniciar(){
        startBtn = findViewById(R.id.btnStart);
        startBtn.setOnClickListener(v -> pulseCountDown.start(() -> {
            Toast.makeText(MainActivity.this, "Arranca!!!", Toast.LENGTH_SHORT).show();

            textView = findViewById(R.id.textView);
            // Time is in millisecond so 50sec = 50000 I have used
            // countdown Interveal is 1sec = 1000 I have used
            new CountDownTimer(50000, 1000) {
                public void onTick(long millisUntilFinished) {
                    // Used for formatting digit to be in 2 digits only
                    NumberFormat f = new DecimalFormat("00");
                    long hour = (millisUntilFinished / 3600000) % 24;
                    long min = (millisUntilFinished / 60000) % 60;
                    long sec = (millisUntilFinished / 1000) % 60;
                    textView.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                }
                // When the task is over it will print 00:00:00 there
                public void onFinish() {
                    textView.setText("00:00:00");
                }
            }.start();

        }));
    }
}