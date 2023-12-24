package com.example.mypaint.utils;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class CanvasFactory {
    /**
     * Створити прозорий канвас заданого розміру
     * @param width ширина
     * @param height висота
     * @return канвас
     */
    public static Canvas createTransparentCanvas(double width, double height) {
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);
        return canvas;
    }

    /**
     * Створити заповнений кольором канвас заданого розміру
     * @param width ширина
     * @param height висота
     * @param color колір
     * @return канвас
     */
    public static Canvas createFilledCanvas(double width, double height, Color color) {
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        return canvas;
    }

    /**
     * Створити канвас, та завантажити в нього картинку. Розмір канвасу
     * буде співпадати розміру картинки
     * @param image картинка
     * @return канвас
     */
    public static Canvas createCanvasWithImage(Image image){
        Canvas canvas = new Canvas(image.getWidth(), image.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, 0, 0);
        return canvas;
    }

}
