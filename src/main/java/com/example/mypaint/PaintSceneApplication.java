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



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader mainView = new FXMLLoader(PaintSceneApplication.class.getResource("fxml/main-view.fxml"));
        Scene scene = new Scene(mainView.load(), 1080, 720);
        String css = this.getClass().getResource("css/main.css").toExternalForm();
        scene.getStylesheets().add(css);

        MainController mainController = mainView.getController();
        mainController.setStage(stage);

        stage.setTitle("Paint");
        stage.setScene(scene);
        stage.show();
    }

}