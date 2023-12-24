package com.example.mypaint;
import com.example.mypaint.facade.FacadeImpl;
import com.example.mypaint.web.WebCanvasEffects;
import javafx.application.Application;
import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PaintApplication {
    @Getter
    private static FacadeImpl facade;


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PaintApplication.class, args);
        facade = context.getBean(FacadeImpl.class);
        Application.launch(PaintSceneApplication.class, args);

    }





}
