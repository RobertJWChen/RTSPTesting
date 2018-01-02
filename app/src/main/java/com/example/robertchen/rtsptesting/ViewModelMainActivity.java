package com.example.robertchen.rtsptesting;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.robertchen.rtsptesting.databinding.ActivityMainBinding;

/**
 * Created by Robert on 2017/12/28.
 */

public class ViewModelMainActivity extends BaseObservable {
    private static final String TAG = "ViewModelMainActivity";
    private Activity mActivity;
    private boolean mIsPlaying = false;
    private String mStreamingUrl = "rtsp://localhost:10004/samples/m4e/wwe.m4e"; //defaul streaming url over vpn
    private final VideoView mVideoView;

    public ViewModelMainActivity(Activity mainActivity, VideoView videoView) {
        mActivity = mainActivity;
        mVideoView = videoView;

        //ErrorListener
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.e(TAG, "onError: what = "+what+", extra="+extra);
                switch (what) {
                    case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                        Toast.makeText(mActivity, "Error: try fallback streaming url!", Toast.LENGTH_SHORT).show();
                        mStreamingUrl = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov";//fallback link
                        startPlayStreaming();
                        return true;
                    default:
                        stopPlayStreaming();
                        break;
                }
                return false;
            }
        });
    }

    @Bindable
    public boolean isStreamingPlaying() {
        return mIsPlaying;
    }

    @Bindable
    public String getPlayingStreamUrl() {
        return "NowPlaying:"+mStreamingUrl;
    }

    public void startPlayStreaming() {
        mVideoView.setVideoURI(Uri.parse(mStreamingUrl));
        mVideoView.requestFocus();
        mVideoView.start();
        mIsPlaying = true;
        notifyPropertyChanged(BR.streamingPlaying);
    }

    public void stopPlayStreaming() {
        mVideoView.stopPlayback();
        mVideoView.clearFocus();
        mVideoView.setVideoURI(null);
        mIsPlaying = false;
        notifyPropertyChanged(BR.streamingPlaying);
    }
}
