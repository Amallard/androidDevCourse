package net.adamallard.videodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView vid = (VideoView)findViewById(R.id.videoView);
        vid.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.amara);

        MediaController media = new MediaController(this);
        media.setAnchorView(vid);

        vid.setMediaController(media);

        vid.start();
    }
}
