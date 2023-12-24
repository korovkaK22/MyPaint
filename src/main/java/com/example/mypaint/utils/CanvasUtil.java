package com.example.mypaint.utils;

import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.List;

public class CanvasUtil {

    /**
     * Отримати копію канвасу з зображенням та розмірами, але без евентів миші
     * @param canvas канвас
     * @return копія канвасу
     */
    public static Canvas getCanvasCopyWithoutEvents(Canvas canvas){
        Canvas copyCanvas = new Canvas(canvas.getWidth(), canvas.getHeight());
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        canvas.snapshot(params, writableImage);

        GraphicsContext gc = copyCanvas.getGraphicsContext2D();
        gc.drawImage(writableImage, 0, 0);

        return copyCanvas;
    }

    /**
     * Створити новий канвас без евентів на мишу, при цьому передавши йому картинку, та змінивши розмір
     * @param canvas канвас
     * @param newWidth нова ширина
     * @param newHeight нова висота
     * @return
     */
    public static Canvas resizeCanvasWithoutEvents(Canvas canvas, double newWidth, double newHeight) {
        WritableImage snapshot = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        canvas.snapshot(params, snapshot);

        Canvas newCanvas = new Canvas(newWidth, newHeight);
        GraphicsContext gc = newCanvas.getGraphicsContext2D();
        gc.drawImage(snapshot, 0, 0);
        return newCanvas;
    }

    /**
     * Отримати фінальний знімок, наклавши всі шари канвасів
     * @param canvases канваси
     * @return знімок
     */
    public static WritableImage getFinaleImage(List<Canvas> canvases){
        if (canvases.isEmpty()) {
            return null;
        }
        int width = (int) canvases.get(0).getWidth();
        int height = (int) canvases.get(0).getHeight();
        WritableImage resultImage = new WritableImage(width, height);
        Canvas tempCanvas = new Canvas(width, height);
        GraphicsContext gc = tempCanvas.getGraphicsContext2D();

        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        for (Canvas canvas : canvases) {
            WritableImage snapshot = getImage(canvas);
            canvas.snapshot(params, snapshot);
            gc.drawImage(snapshot, 0, 0);
        }
        tempCanvas.snapshot(null, resultImage);
        return resultImage;
    }

    /**
     * Отримати знімок з канвасу
     * @param canvas канвас
     * @return знімок
     */
    public static WritableImage getImage(Canvas canvas) {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        return canvas.snapshot(params, null);
    }

    /**
     * Записує зображення в канвас, при цьому змінюючи його розміри до найбільшого
     * @param writableImage зображення
     * @param canvas канвас
     */
    public static void writeImageOnCanvas(WritableImage writableImage ,Canvas canvas){
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        double imageWidth = writableImage.getWidth();
        double imageHeight = writableImage.getHeight();

        if (imageWidth > canvasWidth || imageHeight > canvasHeight) {
            canvasWidth = Math.max(canvasWidth, imageWidth);
            canvasHeight = Math.max(canvasHeight, imageHeight);
            canvas.setWidth(canvasWidth);
            canvas.setHeight(canvasHeight);
        }

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        gc.drawImage(writableImage, 0, 0);
    }


}
