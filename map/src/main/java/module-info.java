module com.example.map {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.web; // ✅ Add this for WebView (javafx.scene.web)
    requires jdk.jsobject;

    opens com.example.map to javafx.fxml;
    opens com.example.map.controllers to javafx.fxml;
    opens com.example.map.models to javafx.fxml;
    opens com.example.map.utils to javafx.fxml;

    exports com.example.map;
    exports com.example.map.controllers;
    exports com.example.map.models;
    exports com.example.map.utils;
}
