package com.example.mypaint;

import com.example.mypaint.configurations.PaintConfiguration;
import com.example.mypaint.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class PaintSceneApplication extends Application {
    private final int WINDOW_HEIGHT = 720;
    private final int WINDOW_WIDTH = 1080;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader mainView = new FXMLLoader(PaintSceneApplication.class.getResource("fxml/main-view.fxml"));
        Scene scene = new Scene(mainView.load(), WINDOW_WIDTH, WINDOW_HEIGHT);
        MainController controller = mainView.getController();
        controller.setEffectsFacade(PaintApplication.getFacade());
        String css = this.getClass().getResource("css/main.css").toExternalForm();
        scene.getStylesheets().add(css);

        String iconPath = this.getClass().getResource("icons/logo.png").toExternalForm();
        Image icon = new Image(iconPath);
        stage.getIcons().add(icon);

        MainController mainController = mainView.getController();
        mainController.setStage(stage);

        stage.setTitle("MyPaint");
        stage.setScene(scene);
        stage.show();
    }

}