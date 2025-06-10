package com.example.map.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class LocationController {
    @FXML private Label lblTitle;
    @FXML private MediaView mediaView;
    @FXML private Button btnPlayAudio, btnPlayVideo;

    private String audioPath;
    private String videoPath;

    public void setData(String title, String audio, String video) {
        lblTitle.setText(title);
        this.audioPath = audio;
        this.videoPath = video;
    }

    public void playAudio() {
        MediaPlayer player = new MediaPlayer(new Media(getClass().getResource(audioPath).toExternalForm()));
        player.play();
    }

    public void playVideo() {
        MediaPlayer player = new MediaPlayer(new Media(getClass().getResource(videoPath).toExternalForm()));
        mediaView.setMediaPlayer(player);
        player.play();
    }
}

