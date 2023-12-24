package com.example.mypaint.facade;

import javafx.scene.image.WritableImage;

public interface EffectsFacade {
    public WritableImage rotate(WritableImage writableImage);

    public WritableImage resizeImage(WritableImage writableImage, int width, int height);

    public WritableImage changeCanvasSize(WritableImage writableImage, int width, int height);

    public WritableImage cropImage(WritableImage writableImage, int x1, int y1, int x2, int y2);

    public WritableImage moveImage(WritableImage writableImage, int width, int height);

}
