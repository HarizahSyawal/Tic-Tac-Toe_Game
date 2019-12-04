package com.example.tictactoe;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    Button returnButton;
    String p1, p2;
    TextView nameP1, namep2;
    Button resetbutton;
    Button [][]buttons = new Button[3][3];
    boolean playerTurn = true;//true - p1 turn, false - p2
    int roundCount = 0;
    int scoreP1 =0, scoreP2 =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        returnButton = findViewById(R.id.button);
        nameP1 = findViewById(R.id.p1btn);
        namep2 = findViewById(R.id.p2btn);
        resetbutton = findViewById(R.id.resetbutton);
        Intent intent = getIntent();
        p1 = intent.getStringExtra("Player1");
        p2 = intent.getStringExtra("Player2");
        nameP1.setText(p1);
        namep2.setText(p2);

        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                String buttonID ="button" + i + j;
                int resID = getResources().getIdentifier(buttonID,
                "id", getPackageName());
                buttons[i][j]=(Button)findViewById(resID);
                buttons[i][j].setOnClickListener(this);

            }
        }

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Main2Activity.this);
                alertDialogBuilder.setTitle("Do you want to Reset the game");
                alertDialogBuilder.setMessage("Choose");
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resetGame();
                    }
                });
                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }

        });
    }

    @Override
    public void onClick(View v) {
        if(!String.valueOf(((Button)v).getText()).matches(""))
        return;
        if(playerTurn){
          ((Button)v).setText("X");
         ((Button)v).setTextColor(Color.RED);
        }
        else{
            ((Button)v).setText(("0"));
            ((Button)v).setTextColor(Color.GREEN);
        }
        roundCount++;

        if(CheckForWinner()){
            if (playerTurn)
                playerWin(1);
            else
                playerWin(2);
        }else if(roundCount == 9){
            draw();
        }
        else{
            playerTurn=!playerTurn;
        }
    }

    private boolean CheckForWinner(){
        String [][] field = new String[3][3];

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                field[i][j] = buttons[i][j].getText().toString();

            }
        }
        //Rows
        for (int i = 0; i < 3; i ++) {
            if (field[i][ 0].equals(field[i][ 1])
                    && field[i][ 0].equals(field[i][ 2])
                    && !field[i][ 0].equals( "" )){
                return true ;
            }
        }

        //Columns

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        //Top left to Bot right
        if (field[ 0][ 0].equals(field[ 1][ 1])
                && field[ 0][0].equals(field[ 2][ 2])
                && !field[ 0][ 0].equals( "" )){
            return true ;
        }

        //Top right to Bot left
        if (field[ 0][ 2].equals(field[ 1][ 1])
                && field[ 0][ 2].equals(field[ 2][ 0])
                && !field[ 0][ 2].equals( "" )){
            return true ;
        }

        return false ;
    }

    private void playerWin( int player) {
        if (player == 1) {
            scoreP1 ++;
            Toast. makeText (this ,"Playe r 1 wins!" , Toast. LENGTH_SHORT ).show();
        } else {
            scoreP2 ++;
            Toast. makeText (this ,"Playe r 2 wins!" , Toast. LENGTH_SHORT ).show();
        }

        updateScore();
        resetBoard();
    }

    private void draw() {
        Toast. makeText (this ,"Draw!" , Toast. LENGTH_SHORT ).show();
        resetBoard();
    }

    private void updateScore() {
        nameP1 .setText( p1 + ":" + scoreP1 );
        namep2 .setText( p2 + ":" + scoreP2 );
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons [i][j].setText( "" );
            }
        }
        roundCount = 0;
        playerTurn = true ;
    }

    private void resetGame() {
        scoreP1 = 0;
        scoreP2 = 0;
        updateScore();
        resetBoard();
        Toast. makeText (getApplicationContext(),
                "Game Reset!" ,
                Toast. LENGTH_SHORT ) .show();
    }


}
