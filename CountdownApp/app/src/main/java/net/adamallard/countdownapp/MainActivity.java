package net.adamallard.countdownapp;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seek;
    TextView text;
    Button timerButton;
    Boolean counterRunning = false;
    CountDownTimer countDownTimer;


    public void resetTimer() {
        text.setText("0:30");
        counterRunning = false;
        timerButton.setText("Start");
        seek.setEnabled(true);
        seek.setProgress(30);
    }

    public void updateTimer(int i) {
        int mins = (int) i / 60;
        int secs = i - mins * 60;
        String secStr = Integer.toString(secs);

        if (secs < 10) {
            secStr = "0" + secStr;
        }

        text.setText(Integer.toString(mins) + ":" + secStr);
    }

    public void buttonPressed(View view) {

        if (!counterRunning) {
            counterRunning = true;
            seek.setEnabled(false);
            timerButton.setText("Stop");

            countDownTimer = new CountDownTimer(seek.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.news_intro);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        } else {
            countDownTimer.cancel();
            resetTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seek = (SeekBar)findViewById(R.id.seekBar);
        text = (TextView)findViewById(R.id.time);
        timerButton = (Button)findViewById(R.id.button);

        seek.setMax(600);
        seek.setProgress(30);

        updateTimer(seek.getProgress());

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
