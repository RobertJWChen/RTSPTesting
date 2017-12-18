package com.example.robertchen.rtsptesting;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    Button mPlayButton;
    VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlayButton = (Button) findViewById(R.id.btn_play);
        mVideoView = (VideoView) findViewById(R.id.view_video);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov";
                mVideoView.setVideoURI(Uri.parse(url));
                mVideoView.requestFocus();
                mVideoView.start();
                mPlayButton.setEnabled(false);
                mPlayButton.setText("Now Play: "+url);
            }
        });
    }
}
