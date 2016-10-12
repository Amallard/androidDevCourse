package net.adamallard.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void fade(View view) {
        ImageView blue = (ImageView)findViewById(R.id.blue);
        ImageView green = (ImageView)findViewById(R.id.green);

        if (blue.isClickable()) {
            blue.animate().alpha(0f).setDuration(2000);
            green.animate().alpha(1f).setDuration(2000);
            blue.setClickable(false);
            green.setClickable(true);
        }
    }

    public void fadeBack(View view) {
        ImageView blue = (ImageView)findViewById(R.id.blue);
        ImageView green = (ImageView)findViewById(R.id.green);

        if (green.isClickable()) {
            blue.animate().alpha(1f).setDuration(2000);
            green.animate().alpha(0f).setDuration(2000);
            green.setClickable(false);
            blue.setClickable(true);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
