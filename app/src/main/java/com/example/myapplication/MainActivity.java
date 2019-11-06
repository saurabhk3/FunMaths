package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.GridLayoutAnimationController;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button a,b,c,d,go,playAgain;
    TextView scoreText,timerText,queText,resultText;
    int score=0,noOfQues=0,correctAns;
    String scoreString ;
    List<Integer> answers = new ArrayList<>();
    public void start(View view){
        go.setVisibility(View.INVISIBLE);
        a.setVisibility(View.VISIBLE);
        b.setVisibility(View.VISIBLE);
        c.setVisibility(View.VISIBLE);
        d.setVisibility(View.VISIBLE);
        scoreText.setVisibility(View.VISIBLE);
        timerText.setVisibility(View.VISIBLE);
        queText.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timer));
    }

    public void playAgain(View view){
        playAgain.setVisibility(View.INVISIBLE);
        score = 0;
        noOfQues =0;
        timerText.setText("30s");
        scoreString = score + "/"+ noOfQues;
        scoreText.setText(scoreString);
        newQues();
        resultText.setText("");

        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long l){
                String timeLeft = l/1000 + "s";
                timerText.setText(timeLeft);
            }
            @Override
            public void onFinish(){
                String tmp = "Done! You scored: "+ score;
                resultText.setVisibility(View.VISIBLE);
                resultText.setText(tmp);
                playAgain.setVisibility(View.VISIBLE);
                a.setText("");
                b.setText("");
                c.setText("");
                d.setText("");
                timerText.setText("");
                queText.setText("");
                scoreText.setText("");

            }
        }.start();
    }

    public void checkAnswer(View view){
        if(Integer.toString(correctAns).equals(view.getTag().toString())){
            Toast.makeText(this,"Correct!",Toast.LENGTH_SHORT).show();
            score++;
        }else{
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }
        noOfQues++;
        scoreString = score + "/"+ noOfQues;
        scoreText.setText(scoreString);
        newQues();
    }

    public void newQues(){
        Random rand = new Random();
        int x,y,wrongAns;
        x = rand.nextInt(31);
        y = rand.nextInt(31);
        String queString = x + " + " + y + " =?";
        queText.setText(queString);

        correctAns = rand.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++){
            if(correctAns==i){
                answers.add(x+y);
            }else{
                wrongAns = rand.nextInt(61);
                while(wrongAns == correctAns)
                    wrongAns = rand.nextInt(61);
                answers.add(wrongAns);
            }
        }

        a.setText(Integer.toString(answers.get(0)));
        b.setText(Integer.toString(answers.get(1)));
        c.setText(Integer.toString(answers.get(2)));
        d.setText(Integer.toString(answers.get(3)));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // gameLayout = findViewById(R.id.gameLayout);
        go = findViewById(R.id.go);
        a = findViewById(R.id.button0);
        b = findViewById(R.id.button1);
        c = findViewById(R.id.button3);
        d = findViewById(R.id.button2);
        scoreText = findViewById(R.id.score);
        timerText = findViewById((R.id.timer));
        queText = findViewById(R.id.que);
        resultText = findViewById(R.id.resultText);
        playAgain = findViewById(R.id.playAgain);
        playAgain.setVisibility(View.INVISIBLE);
        Toast.makeText(this, "Click to get started  ", Toast.LENGTH_LONG).show();


    }
}
