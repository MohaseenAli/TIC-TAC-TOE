package com.mohaseenali.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    Button resetButton;

    int activePlayer = 0;//0 = o , 1 = x

    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};//2 = null

    int [] [] winPosition ={{0,1,2}, {3,4,5}, {6,7,8},
                            {0,3,6}, {1,4,7}, {2,5,8},
                            {0,4,8}, {2,4,6}};
    boolean notWin=true;
    public void playerTap(View view)
    {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (gameState[tappedImage] == 2 && notWin)
        {
            resetButton.setVisibility(View.GONE);
            gameState[tappedImage] = activePlayer;
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.o);
                TextView status = findViewById(R.id.StatusBar);
                status.setText("X's Turn's--Tap to play");
                activePlayer = 1;
                }
            else {
                    img.setImageResource(R.drawable.x);
                    TextView status = findViewById(R.id.StatusBar);
                    status.setText("0's Turn's--Tap to play");
                    activePlayer = 0;
                }
        }

        //Check if any players is won
        for (int[] winPosition : winPosition)
        {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                gameState[winPosition[1]] == gameState[winPosition[2]] &&
                gameState[winPosition[0]] != 2)
            {
                    String winnerName;
                    notWin=false;
                    if (gameState[winPosition[0]] == 0) {
                        winnerName = "0's win....";
                    }
                    else {
                        winnerName = "X's win....";
                    }
                    resetButton.setVisibility(View.VISIBLE);
                    TextView status = findViewById(R.id.StatusBar);
                    status.setText(winnerName);
            }
        }
        int j = 0;
        for(int i=0 ; i<9 ; i++)
        {
            if(gameState[i]==2)
            {
                break;
            }
            else
            {
                j++;
            }
            if(j==9)
            {
                TextView status = findViewById(R.id.StatusBar);
                status.setText("Match Draw....");
                resetButton.setVisibility(View.VISIBLE);
            }
        }
    }

    public void gameReset(View view)
    {
        notWin = true;
        activePlayer = 0;
        for(int i=0 ; i<gameState.length ; i++)
        {
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageViewX0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageViewX1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageViewX2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageViewX3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageViewX4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageViewX5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageViewX6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageViewX7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageViewX8)).setImageResource(0);
        resetButton.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resetButton = findViewById(R.id.reset_button);


        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView status = findViewById(R.id.StatusBar);
                status.setText("0's Turn's--Tap to play");;
                gameReset(v);
            }
        });
    }
}