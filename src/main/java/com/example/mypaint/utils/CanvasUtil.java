package com.example.mypaint.utils;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class CanvasUtil {

    public static void fillCanvasWithColor(Canvas canvas, Color color) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }


    public static Canvas getCanvasCopyWithoutEvents(Canvas canvas){
        Canvas copyCanvas = new Canvas(canvas.getWidth(), canvas.getHeight());

        WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        canvas.snapshot(null, writableImage);

        GraphicsContext gc = copyCanvas.getGraphicsContext2D();
        gc.drawImage(writableImage, 0, 0);

        return copyCanvas;
    }

}
