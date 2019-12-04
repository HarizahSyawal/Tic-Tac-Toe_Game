package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button startButton, settingButton;
    TextView textView;
    EditText p1ediText;
    EditText P2ediText;
    String p1;
    String p2;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startbutton);

        textView= findViewById(R.id.textView2);
        p1ediText = findViewById(R.id.p1editText);
        P2ediText = findViewById(R.id.p2editText2);
        img =findViewById(R.id.imageViewmenu);
        startButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setText("Tic Tac Toe");
                        p1ediText.getText().toString();
                        p1 = p1ediText.getText().toString();
                        p2 = P2ediText.getText().toString();
                        img.setImageResource(R.drawable.tic_tac_toe);

                        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                        intent.putExtra("Player1", p1);
                        intent.putExtra("Player2", p2);
                        startActivity(intent);
                    }
                });
    }

    public void myButton(View view) {
        Intent intent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(intent);


    }
}
