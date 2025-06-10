package com.example.map;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private WebView mapWebView;

    @FXML
    private Button startTourButton;

    @FXML
    private Button infoButton;

    @FXML
    private Button mediaButton;

    @FXML
    private Button quizButton;

    private WebEngine webEngine;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webEngine = mapWebView.getEngine();

        URL mapPage = getClass().getResource("/map.html");
        if (mapPage != null) {
            webEngine.load(mapPage.toExternalForm());
            webEngine.setOnAlert(event -> System.out.println("JS Alert: " + event.getData()));
        } else {
            System.err.println("❌ map.html not found in resources!");
        }

        startTourButton.setOnAction(e -> startTour());
        infoButton.setOnAction(e -> showInfo());
        mediaButton.setOnAction(e -> playMedia());
        quizButton.setOnAction(e -> startQuiz());
    }

    private void startTour() {
        webEngine.executeScript("alert('🎉 Welcome to the Lesotho Tour Guide!')");
    }

    private void showInfo() {
        webEngine.executeScript("alert('ℹ️ This is Thaba-Bosiu, a historical mountain fortress in Lesotho.')");
    }

    private void playMedia() {
        URL audioUrl = getClass().getResource("/assets/audio/thaba.mp3");
        URL videoUrl = getClass().getResource("/assets/video/thaba.mp4");

        if (audioUrl != null) {
            Media audio = new Media(audioUrl.toExternalForm());
            MediaPlayer audioPlayer = new MediaPlayer(audio);
            audioPlayer.play();
        } else {
            System.err.println("❌ Audio file not found at /assets/audio/thaba.mp3");
        }

        if (videoUrl != null) {
            Media video = new Media(videoUrl.toExternalForm());
            MediaPlayer videoPlayer = new MediaPlayer(video);
            videoPlayer.play();
        } else {
            System.err.println("❌ Video file not found at /assets/video/thaba.mp4");
        }
    }

    private void startQuiz() {
        webEngine.executeScript("alert('🧠 Quiz: What is the capital of Lesotho?\\nA) Maseru\\nB) Mafeteng\\nC) Mohale’s Hoek')");
    }
}
