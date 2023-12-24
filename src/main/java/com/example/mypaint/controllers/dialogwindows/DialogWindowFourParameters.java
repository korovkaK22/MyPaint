package com.example.mypaint.controllers.dialogwindows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;

import java.util.function.BiConsumer;
import java.util.function.Consumer;


public class DialogWindowFourParameters {
    @FXML
    private Label errorMessage;

    @FXML
    private TextField firstX;

    @FXML
    private TextField firstY;

    @FXML
    private TextField secondX;

    @FXML
    private TextField secondY;

    @FXML
    private Button submit;

    @Setter
    private FourArgsConsumer<Integer, Integer, Integer, Integer> action;

    @FXML
    void submitAction() {
        try {
            int x1 = Integer.parseInt(firstX.getText());
            int y1 = Integer.parseInt(firstY.getText());
            int x2 = Integer.parseInt(secondX.getText());
            int y2 = Integer.parseInt(secondY.getText());

            validateCoordinate(x1);
            validateCoordinate(y1);
            validateCoordinate(x2);
            validateCoordinate(y2);

            closeWindow();
            action.accept(x1, y1, x2, y2);

        } catch (NumberFormatException e) {
            errorMessage.setText("Будь ласка, введіть валідні числа");
        } catch (IllegalArgumentException e) {
            errorMessage.setText(e.getMessage());
        }
    }



    private void validateCoordinate(int coordinate) throws IllegalArgumentException {
        if (coordinate <= 0) {
            throw new IllegalArgumentException("Координати повинні бути більші за 0");
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) errorMessage.getScene().getWindow();
        stage.close();
    }

}
