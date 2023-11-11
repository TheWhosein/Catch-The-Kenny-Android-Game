package com.whosein.catchthekenny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button = findViewById(R.id.button);
        Intent intent = getIntent();
        if (intent != null) {
            String value = intent.getStringExtra("Start");
        }
    }

    public void start(View view){
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);

        intent.putExtra("Start", "");

        startActivity(intent);
    }


}