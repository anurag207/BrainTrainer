package com.example.anu.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button start;
    TextView timer;
    TextView quest;
    TextView score;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView result;
    TextView again;
    boolean gameActive=true;
    CountDownTimer time;
    ArrayList<Integer> list;
    int a,b,c,ans,correct,wrong;
    public void generate()
    {
        Random rand=new Random();
        int q1=rand.nextInt(16)+5;
        int q2=rand.nextInt(21)+10;
        quest.setText(String.valueOf(q1)+" + "+String.valueOf(q2)+" = ?");
         ans=q1+q2;
        int max=0;
        if(q1>q2)
            max=q1;
        else
            max=q2;
         do {
             a=rand.nextInt(46)+10;
             b=rand.nextInt(46)+10;
             c=rand.nextInt(55-max+1)+max;
         }while ((!(a!=b&&b!=c&&c!=a))||(a==ans||b==ans||c==ans));
        list=new ArrayList<Integer>();
        for(int i=1;i<=4;i++)
            list.add(i);
        Collections.shuffle(list);
        for(int i=0;i<4;i++)
        {
            switch (list.get(i))
            {
                case 1:
                    if(i==0)
                        button1.setText(String.valueOf(a));
                    else if(i==1)
                        button1.setText(String.valueOf(b));
                    else if(i==2)
                        button1.setText(String.valueOf(ans));
                    else
                        button1.setText(String.valueOf(c));
                    break;
                case 2:
                    if(i==0)
                        button2.setText(String.valueOf(a));
                    else if(i==1)
                        button2.setText(String.valueOf(b));
                    else if(i==2)
                        button2.setText(String.valueOf(ans));
                    else
                        button2.setText(String.valueOf(c));
                    break;
                case 3:
                    if(i==0)
                        button3.setText(String.valueOf(a));
                    else if(i==1)
                        button3.setText(String.valueOf(b));
                    else if(i==2)
                        button3.setText(String.valueOf(ans));
                    else
                        button3.setText(String.valueOf(c));
                    break;
                case 4:
                    if(i==0)
                        button4.setText(String.valueOf(a));
                    else if(i==1)
                        button4.setText(String.valueOf(b));
                    else if(i==2)
                        button4.setText(String.valueOf(ans));
                    else
                        button4.setText(String.valueOf(c));
                    break;
            }
        }
    }
    public void start(View view)
    {
        start.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.VISIBLE);
        quest.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);
        time=new CountDownTimer(30000+100,1000)
        {
            public void onTick(long sec)
            {
                timer.setText(Long.toString(sec/1000)+"s");
            }
            public void onFinish()
            {
                timer.setText("0s");
                gameActive=false;
                result.setVisibility(View.VISIBLE);
                result.setText("Game Over!");
                again.setVisibility(View.VISIBLE);
            }
        }.start();
        generate();
    }
    public void option(View view)
    {
        if(gameActive) {
            Button clicked = (Button) view;
            String opt = clicked.getText().toString();
            int opt1 = Integer.parseInt(opt);
            result.setVisibility(View.VISIBLE);
            if (opt1 == ans) {
                result.setText("Correct!");
                correct++;
            } else {
                result.setText("Inorrect!");
                wrong++;

            }
            score.setText(String.valueOf(correct) + "/" + String.valueOf(wrong));
            generate();
        }
    }
    public void playAgain(View view)
    {
        gameActive=true;
        correct=0;
        wrong=0;
        score.setText(String.valueOf(correct) + "/" + String.valueOf(wrong));
        generate();
        time.start();
        again.setVisibility(View.INVISIBLE);
        result.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start=findViewById(R.id.gobutton);
        timer=findViewById(R.id.timer);
        quest=findViewById(R.id.quest);
        score=findViewById(R.id.score);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        result=findViewById(R.id.result);
        again=findViewById(R.id.again);
        correct=0;
        wrong=0;
    }
}
