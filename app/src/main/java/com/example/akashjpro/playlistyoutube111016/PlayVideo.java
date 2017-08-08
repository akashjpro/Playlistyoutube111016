package com.example.akashjpro.playlistyoutube111016;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayVideo extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    String id = "fa6fZEvArFo";
    YouTubePlayerView youTubePlayerView;
    int REQUEST_VIDEO = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube);
        Intent myIntent = getIntent();
        id = myIntent.getStringExtra("IDVIDEO");
        //Toast.makeText(PlayVideo.this, id, Toast.LENGTH_SHORT).show();

        youTubePlayerView.initialize(MainActivity.API_KEY, PlayVideo.this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        youTubePlayer.cueVideo(id);
        Toast.makeText(this, b?"T":"F", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Video error", Toast.LENGTH_SHORT).show();

        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this, REQUEST_VIDEO);
        }else {
            Toast.makeText(this, "Video error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_VIDEO){
            youTubePlayerView.initialize(MainActivity.API_KEY, this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
