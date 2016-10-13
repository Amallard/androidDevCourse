package net.adamallard.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow; 1 = red
    public static int player = 0;

    boolean gameEnd = false;

    // 2 = empty
    int[] state = {2,2,2,2,2,2,2,2,2};

    int[][] winPos = {{0,3,6},{1,4,7},{2,5,8},{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6}};

    public void dropIn(View view) {

        ImageView counter = (ImageView)view;
        int tag = Integer.parseInt(counter.getTag().toString());

        if (state[tag] == 2 && !gameEnd) {
            System.out.println(player);

            state[tag] = player;
            int turn;

            counter.setTranslationY(-1000f);

            if (player == 0) {
                counter.setImageResource(R.drawable.yellow);
                turn = 180;
                player = 1;
                System.out.println(player);
            } else {
                counter.setImageResource(R.drawable.red);
                turn = -180;
                player = 0;
                System.out.println(player);
            }

            counter.animate().translationYBy(1000).rotation(turn).setDuration(500);


            for (int i = 0; i < winPos.length; i++) {
                if (state[winPos[i][0]] != 2) {
                    if (state[winPos[i][0]] == state[winPos[i][1]] && state[winPos[i][1]] == state[winPos[i][2]]) {

                        gameEnd = true;
                        String winner;
                        if (state[winPos[i][0]] == 0) winner = "Yellow";
                        else winner = "Red";

                        TextView text = (TextView)findViewById(R.id.endGameText);
                        text.setText(winner + " has won!");

                        LinearLayout gameEnd = (LinearLayout)findViewById(R.id.endGameView);
                        gameEnd.setVisibility(View.VISIBLE);

                        Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.slide_up);
                        gameEnd.startAnimation(slide_up);
                    } else {
                        boolean gameIsOver = true;
                        for (int counterState : state) {
                            if (counterState == 2) gameIsOver = false;
                        }
                        if (gameIsOver) {
                            TextView text = (TextView)findViewById(R.id.endGameText);
                            text.setText("Tie Game!");

                            LinearLayout gameEnd = (LinearLayout)findViewById(R.id.endGameView);
                            gameEnd.setVisibility(View.VISIBLE);

                            Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(),
                                    R.anim.slide_up);
                            gameEnd.startAnimation(slide_up);
                        }
                    }
                }
            }

        }
    }

    public void playAgain(View view) {
        LinearLayout gameEndView = (LinearLayout)findViewById(R.id.endGameView);
        Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);
        gameEndView.startAnimation(slide_down);
        gameEndView.setVisibility(View.INVISIBLE);

        player = 0;

        for (int i = 0; i < state.length; i++) {
            state[i] = 2;
        }

        GridLayout grid = (GridLayout)findViewById(R.id.grid);
        for (int i = 0; i < grid.getChildCount(); i++) {
            ((ImageView)grid.getChildAt(i)).setImageResource(0);
        }

        gameEnd = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
