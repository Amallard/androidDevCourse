package net.adamallard.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timer;
    int secs = 30;
    TextView score;
    TextView equation;
    TextView status;
    TextView endGameScore;

    Button start;
    Button ans1;
    Button ans2;
    Button ans3;
    Button ans4;

    GridLayout buttons;
    GridLayout top;

    int scorePossible = 0;
    int scoreCorrect = 0;

    Random rand = new Random();
    int a = rand.nextInt(50)+1;
    int b = rand.nextInt(50)+1;

    CountDownTimer countDownTimer;
    Boolean counterRunning = false;



    public void updateScore(int ans) {
        scorePossible++;
        scoreCorrect += ans;
        score.setText(scoreCorrect + "/" + scorePossible);
    }

    public void nextQuestion(int answer) {

        if (answer == 1) {
            status.setText("Correct");
            status.setVisibility(View.VISIBLE);
            updateScore(1);
        } else if (answer == 0){
            status.setText("Wrong");
            status.setVisibility(View.VISIBLE);
            updateScore(0);
        }

        a = rand.nextInt(50)+1;
        b = rand.nextInt(50)+1;
        equation.setText(Integer.toString(a) + " + " + Integer.toString(b));

        int opt1 = rand(a,b);
        int opt2 = rand(a,b);
        int opt3 = rand(a,b);
        int opt4 = rand(a,b);

        while (opt1 == opt2 || opt1 == opt3 || opt1 == opt4) {
            opt1 = rand(a,b);
        }
        while (opt2 == opt1 || opt2 == opt3 || opt2 == opt4) {
            opt2 = rand(a,b);
        }
        while (opt3 == opt2 || opt3 == opt1 || opt3 == opt4) {
            opt3 = rand(a,b);
        }
        while (opt4 == opt2 || opt4 == opt3 || opt4 == opt1) {
            opt4 = rand(a,b);
        }

        ans1.setText(Integer.toString(opt1));
        ans2.setText(Integer.toString(opt2));
        ans3.setText(Integer.toString(opt3));
        ans4.setText(Integer.toString(opt4));


        int ans = rand.nextInt(4)+1;
        switch (ans) {
            case 1:
                ans1.setText(Integer.toString(a + b));
                break;
            case 2:
                ans2.setText(Integer.toString(a + b));
                break;
            case 3:
                ans3.setText(Integer.toString(a + b));
                break;
            case 4:
                ans4.setText(Integer.toString(a + b));
                break;
        }

    }

    public void chose(View view) {
        Button button = (Button)view;
        if (button.getText().toString() == Integer.toString(a+b)) {
            // 1 = correct
            nextQuestion(1);
        } else {
            // 0 = incorrect
            nextQuestion(0);
        }
    }

    public int rand(int a, int b) {
        int val;
        int offset = rand.nextInt(10) + 1;
        int sign = rand.nextInt(1);

        if (sign == 0) {
            val = a + b + offset;
        } else {
            val = a + b - offset;
        }

        return val;
    }

    public void updateTimer(int t) {
        secs--;
        timer.setText(Integer.toString(secs) + "s");
    }

    public void reset() {
        endGameScore.setVisibility(View.VISIBLE);
        endGameScore.setText("Your Score: " + scoreCorrect + "/" + scorePossible);
        double percent = Math.round(((float)scoreCorrect/scorePossible*100)*100.0)/100.0;
        status.setText(Double.toString(percent) + "%");
        start.setVisibility(View.VISIBLE);
        secs = 30;
        scoreCorrect = 0;
        scorePossible = 0;
        buttons.setVisibility(View.INVISIBLE);
    }

    public void startGame(View view) {

        start.setVisibility(View.INVISIBLE);
        buttons.setVisibility(View.VISIBLE);
        top.setVisibility(View.VISIBLE);
        status.setVisibility(View.INVISIBLE);
        score.setText("0/0");
        endGameScore.setVisibility(View.INVISIBLE);

        countDownTimer = new CountDownTimer(30000 + 100, 1000) {

            @Override
            public void onTick(long l) {
                updateTimer((int) l / 1000);
            }

            @Override
            public void onFinish() {
                reset();
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find Views
        timer = (TextView)findViewById(R.id.timer);
        score = (TextView)findViewById(R.id.score);
        equation = (TextView)findViewById(R.id.equation);
        status = (TextView)findViewById(R.id.answerStatus);
        endGameScore = (TextView)findViewById(R.id.endScoreView);
        start = (Button)findViewById(R.id.startButton);
        ans1 = (Button)findViewById(R.id.ans1);
        ans2 = (Button)findViewById(R.id.ans2);
        ans3 = (Button)findViewById(R.id.ans3);
        ans4 = (Button)findViewById(R.id.ans4);

        buttons = (GridLayout)findViewById(R.id.buttons);
        buttons.setVisibility(View.INVISIBLE);
        top = (GridLayout)findViewById(R.id.top);
        top.setVisibility(View.INVISIBLE);

        status.setVisibility(View.INVISIBLE);

        // 2 = first time...
        nextQuestion(2);

    }
}
