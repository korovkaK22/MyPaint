package com.example.mypaint.controllers.dialogwindows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;

import java.util.function.BiConsumer;


public class DialogWindowTwoParameters {
    @FXML
    private TextField height;

    @FXML
    private Button submit;

    @FXML
    private TextField width;
    @Setter
    private BiConsumer<Integer, Integer> action;

    @FXML
    private Label errorMessage;


    @FXML
    void submitAction() {
        try {
            int heightValue = Integer.parseInt(height.getText());
            int widthValue = Integer.parseInt(width.getText());

            if (heightValue <= 0 || widthValue <= 0) {
                throw new IllegalArgumentException("Numbers must be greater than 0");
            }

            closeWindow();
            action.accept(heightValue, widthValue);

        } catch (NumberFormatException e) {
            errorMessage.setText("Please, enter valid numbers");
        } catch (IllegalArgumentException e) {
            errorMessage.setText(e.getMessage());
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) errorMessage.getScene().getWindow();
        stage.close();
    }

}
