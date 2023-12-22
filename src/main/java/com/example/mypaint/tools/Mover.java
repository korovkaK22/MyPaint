package com.example.mypaint.tools;

import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;

public class Mover extends Tool {


    public void makeActionOnDrag(Canvas canvas, MouseEvent event, ToolParams toolParams) {

        System.out.println(event.getEventType());
    }

    @Override
    public void makeActionOnPressed(Canvas canvas, MouseEvent event, ToolParams toolParams) {

    }

    @Override
    public void makeActionOnReleased(Canvas canvas, MouseEvent event, ToolParams toolParams) {

    }

}