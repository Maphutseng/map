package com.example.map.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class MediaHandler {

    private MediaPlayer mediaPlayer;

    /**
     * Loads and plays media from a file path.
     *
     * @param filePath Path to the media file (relative or absolute).
     * @param mediaView MediaView where the video should be shown (can be null for audio).
     */
    public void playMedia(String filePath, MediaView mediaView) {
        stop(); // Stop previous media if playing

        try {
            Media media = new Media(new File(filePath).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            if (mediaView != null) {
                mediaView.setMediaPlayer(mediaPlayer);
            }

            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Error playing media: " + e.getMessage());
        }
    }

    /**
     * Stops the currently playing media.
     */
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
    }

    /**
     * Pauses the currently playing media.
     */
    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    /**
     * Resumes the media if it was paused.
     */
    public void resume() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}

