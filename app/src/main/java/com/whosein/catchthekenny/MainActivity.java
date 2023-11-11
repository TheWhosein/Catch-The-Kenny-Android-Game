package com.whosein.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timeView, scoreView2;
    int score;
    ImageView imageView, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (intent != null) {
            String value = intent.getStringExtra("Start");
        }

        timeView = findViewById(R.id.textView);
        scoreView2 = findViewById(R.id.textView2);

        imageView = findViewById(R.id.imageView);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);

        imageArray = new ImageView[]{imageView, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8};
        hideImages();

        score = 0;

        new CountDownTimer(10000, 1000){

            @Override
            public void onTick(long l) {
                timeView.setText("Time: "+ l/1000);
            }

            @Override
            public void onFinish() {
                timeView.setText("Time: 0");
                handler.removeCallbacks(runnable);
                for(ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                        intent.putExtra("Stop", "");

                        startActivity(intent);
                    }
                });
                alert.show();
            }
        }.start();

    }

    public void increaseScore(View view){
        score++;
        scoreView2.setText("Score: "+ score);
    }


    public void hideImages(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                for(ImageView image: imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this, 500);

            }
        };
        handler.post(runnable);


    }

}