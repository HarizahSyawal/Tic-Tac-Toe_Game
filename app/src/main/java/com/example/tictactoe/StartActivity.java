package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Attributes;

public class StartActivity extends AppCompatActivity {
    Button returnButton;
    Button button3;
    RatingBar ratingBar;
    TextView textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        returnButton = findViewById(R.id.button);
        button3=findViewById(R.id.button3);
        textView3=findViewById(R.id.textView3);
        textView3.setText("RATE");
        ratingBar=findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                textView3.setText(String.valueOf(rating));
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Thank you for rating"+ratingBar.getRating(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void button(View view) {
        Intent i = new Intent(StartActivity.this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
