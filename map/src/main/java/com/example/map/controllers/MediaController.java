package com.example.map.controllers;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class MediaController {
    @FXML private MediaView mediaView;
    private MediaPlayer mediaPlayer;

    public void playAudio() {
        playMedia("src/main/resources/videos/thaba.mp3");
    }

    public void playVideo() {
        playMedia("src/main/resources/videos/thaba.mp4");
    }

    private void playMedia(String filePath) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        Media media = new Media(new File(filePath).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }

    public void stopMedia() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
