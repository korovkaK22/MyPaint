package com.example.mypaint;

import com.example.mypaint.configurations.PaintConfiguration;
import com.example.mypaint.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class PaintSceneApplication extends Application {
    private int WINDOW_HEIGHT = 720;
    private int WINDOW_WIDTH = 1080;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader mainView = new FXMLLoader(PaintSceneApplication.class.getResource("fxml/main-view.fxml"));
        Scene scene = new Scene(mainView.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
        String css = this.getClass().getResource("css/main.css").toExternalForm();
        scene.getStylesheets().add(css);

        MainController mainController = mainView.getController();
        mainController.setStage(stage);

        stage.setTitle("MyPaint");
        stage.setScene(scene);
        stage.show();
    }

}