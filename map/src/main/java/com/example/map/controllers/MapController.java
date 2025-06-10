package com.example.map.controllers;

import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MapController {

    @FXML
    private WebView mapView;

    private WebEngine engine;

    @FXML
    public void initialize() {
        try {
            String mapUrl = getClass().getResource("/assets/map.html").toExternalForm();
            engine = mapView.getEngine();
            engine.load(mapUrl);

            // Listen for JavaScript alerts (e.g., quiz feedback)
            engine.setOnAlert(event -> {
                System.out.println("JS Alert: " + event.getData());
            });

            // Expose 'javaConnector' object to JS after page load
            engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
                if (newState == Worker.State.SUCCEEDED) {
                    JSObject window = (JSObject) engine.executeScript("window");
                    window.setMember("javaConnector", this);
                    engine.executeScript("alert('🎉 Welcome to the Lesotho Tour Guide!')");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Called from JS when Start Tour button clicked inside WebView
    public void onStartTourClicked() {
        Platform.runLater(() -> {
            List<String> places = Arrays.asList("Thaba Bosiu", "Maseru", "Sani Pass", "Malealea");
            ChoiceDialog<String> dialog = new ChoiceDialog<>(places.get(0), places);
            dialog.setTitle("Select a Place");
            dialog.setHeaderText("Choose a place to view on the map:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(this::showPlaceOnMap);
        });
    }

    // Updates the map view using JavaScript based on place name
    private void showPlaceOnMap(String place) {
        String jsCommand = "";
        switch (place) {
            case "Thaba Bosiu":
                jsCommand = """
                    if (window.currentMarker) { map.removeLayer(window.currentMarker); }
                    map.setView([-29.31, 27.48], 14);
                    window.currentMarker = L.marker([-29.31, 27.48]).addTo(map);
                    window.currentMarker.bindPopup('<h3>Thaba Bosiu</h3><p>Historic site in Lesotho.</p>').openPopup();
                """;
                break;
            case "Maseru":
                jsCommand = """
                    if (window.currentMarker) { map.removeLayer(window.currentMarker); }
                    map.setView([-29.31, 27.48], 13);
                    window.currentMarker = L.marker([-29.31, 27.48]).addTo(map);
                    window.currentMarker.bindPopup('<h3>Maseru</h3><p>Capital city of Lesotho.</p>').openPopup();
                """;
                break;
            case "Sani Pass":
                jsCommand = """
                    if (window.currentMarker) { map.removeLayer(window.currentMarker); }
                    map.setView([-29.59, 29.12], 13);
                    window.currentMarker = L.marker([-29.59, 29.12]).addTo(map);
                    window.currentMarker.bindPopup('<h3>Sani Pass</h3><p>Mountain pass between Lesotho and South Africa.</p>').openPopup();
                """;
                break;
            case "Malealea":
                jsCommand = """
                    if (window.currentMarker) { map.removeLayer(window.currentMarker); }
                    map.setView([-29.98, 27.47], 13);
                    window.currentMarker = L.marker([-29.98, 27.47]).addTo(map);
                    window.currentMarker.bindPopup('<h3>Malealea</h3><p>Scenic village in Lesotho.</p>').openPopup();
                """;
                break;
        }
        engine.executeScript(jsCommand);
        System.out.println("Map updated to show: " + place);
    }

    @FXML
    public void startTour() {
        System.out.println("Tour Started");
    }

    @FXML
    public void showInfo() {
        System.out.println("Show Info clicked");
        engine.executeScript("window.showSection('info')");
    }

    @FXML
    public void showMedia() {
        System.out.println("Show Media clicked");
        engine.executeScript("window.showSection('media')");
    }

    @FXML
    public void showQuiz() {
        System.out.println("Show Quiz clicked");
        engine.executeScript("window.showSection('quiz')");
    }

    @FXML
    public void closeSection() {
        System.out.println("Close Section clicked");
        engine.executeScript("window.showSection('none')");
    }
}
