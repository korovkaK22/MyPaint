package com.example.mypaint.facade;

import com.example.mypaint.web.WebCanvasEffects;
import javafx.scene.image.WritableImage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
public class FacadeImpl implements EffectsFacade{
    WebCanvasEffects effects;

    public WritableImage rotate(WritableImage writableImage){
        return effects.rotate(writableImage);
    }

    public WritableImage resizeImage(WritableImage writableImage, int width, int height){
        return effects.resizeImage(writableImage, width, height);
    }

    public WritableImage changeCanvasSize(WritableImage writableImage, int width, int height){
        return effects.changeCanvasSize(writableImage, width, height);
    }

    public WritableImage cropImage(WritableImage writableImage, int x1, int y1, int x2, int y2){
        return effects.cropImage(writableImage, x1, y1, x2, y2);
    }

    public WritableImage moveImage(WritableImage writableImage, int width, int height){
        return effects.moveImage(writableImage, width, height);
    }

}
