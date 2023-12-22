package com.example.mypaint;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PaintApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PaintApplication.class, args);
        Application.launch(PaintSceneApplication.class, args);
    }





}
