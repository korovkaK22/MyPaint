package com.example.mypaint.tools;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class Rubber extends Tool {
    @Override
    public void makeAction(Canvas canvas, MouseEvent event, ToolParams toolParams) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double size = toolParams.getSize();
        double x = event.getX() - size / 2;
        double y = event.getY() - size / 2;
        gc.clearRect(x, y, size, size);
    }
}
