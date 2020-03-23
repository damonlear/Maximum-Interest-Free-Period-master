package com.damonleexh.ui;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.damonleexh.R;

public class VideoActivity extends AppCompatActivity {

    public static final String interest_free_period = "https://baikevideo.cdn.bcebos.com/media/mda-OguDQzKHv5Z824nu/547cdf68d9c957e80437d0d147e6350a.mp4";
    public static final String statement_date = "https://baikevideo.cdn.bcebos.com/media/mda-OgvGQmeZEwxyMv5u/5dce6d6cd706cead0f103935bd59b8e4.mp4";
    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        String extra = getIntent().getStringExtra("uriString");
        if (!TextUtils.isEmpty(extra)) {
            init(extra);
        } else {
            init(statement_date);
        }
    }

    private void init(String uriString) {
        mVideoView = findViewById(R.id.videoView);
        mVideoView.setVideoURI(Uri.parse(uriString));
        MediaController mediaController = new MediaController(this);
        mVideoView.setMediaController(mediaController);
        mVideoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.pause();
    }
}

