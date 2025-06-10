package com.example.map.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class QuizController {
    @FXML private Label questionLabel;
    @FXML private RadioButton option1, option2, option3;
    @FXML private Label feedbackLabel;

    private final String correctAnswer = "Maseru";

    @FXML
    public void initialize() {
        questionLabel.setText("What is the capital city of Lesotho?");
        option1.setText("Maseru");
        option2.setText("Maputsoe");
        option3.setText("Leribe");
    }

    @FXML
    public void checkAnswer() {
        RadioButton selected = (RadioButton) option1.getToggleGroup().getSelectedToggle();
        if (selected == null) {
            feedbackLabel.setText("Please select an answer.");
            return;
        }

        String answer = selected.getText();
        feedbackLabel.setText(answer.equals(correctAnswer) ? "Correct!" : "Incorrect. Try again.");
    }
}
