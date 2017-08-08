package com.example.akashjpro.playlistyoutube111016;

/**
 * Created by Akashjpro on 10/11/2016.
 */

public class Video {
    private String videoID;
    private String title;
    private String channelTitle;
    private String hinh;

    public Video() {
    }

    public Video(String videoID, String title, String channelTitle, String hinh) {
        this.videoID = videoID;
        this.title = title;
        this.channelTitle = channelTitle;
        this.hinh = hinh;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }
}
