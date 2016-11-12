package net.adamallard.gridlayout;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mplay;

    public void buttonPressed(View view) {

        int id = view.getId();
        String strId = view.getResources().getResourceEntryName(id);

        Log.i("info", Integer.toString(id));

        switch (strId) {
            case "button0":
                // applause
                mplay = MediaPlayer.create(this, R.raw.applause);
                //mplay.start();
                break;
            case "button1":
                // crickets
                mplay = MediaPlayer.create(this, R.raw.crickets);
                //mplay.start();
                break;
            case "button2":
                // heli
                mplay = MediaPlayer.create(this, R.raw.helicopter_approach);
                //mplay.start();
                break;
            case "button3":
                // news
                mplay = MediaPlayer.create(this, R.raw.news_intro);
                //mplay.start();
                break;
            case "button4":
                // paino
                mplay = MediaPlayer.create(this, R.raw.piano_broken);
                //mplay.start();
                break;
            case "button5":
                // ufo
                mplay = MediaPlayer.create(this, R.raw.ufo_takeoff);
                //mplay.start();
                break;
        }
        mplay.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
