package com.example.mypaint.tools;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;

public class Mover extends Tool {
    private int lastX = 0;
    private int lastY = 0;
    private boolean dragging = false;

    public void makeAction(Canvas canvas, MouseEvent event, ToolParams toolParams) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        System.out.println(event.getEventType());
        int currX = (int) event.getX();
        int currY = (int) event.getY();

        int dx = currX - lastX;
        int dy = currY - lastY;

        System.out.printf( "dx: %s, dy: %s\n", dx,dy);

        if (Math.abs(dx) > 120 || Math.abs(dy) > 120){
            lastX = currX;
            lastY = currY;
            return;
        }

        if (Math.abs(dx) > 40 || Math.abs(dy) > 40){
            System.out.println(22222);
            lastX = currX;
            lastY = currY;
            // Створення нового зображення з поточного стану канваса
            WritableImage image = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            canvas.snapshot(null, image);

            // Очищення канваса
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

            // Малювання зображення з новим зміщенням
            gc.drawImage(image, dx > 0? 20 :-20, dy > 0? 20 :-20);
        }
    }


}