package com.example.mypaint.web;

import com.example.mypaint.exceptions.EffectMakingException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Objects;

@AllArgsConstructor
public class WebCanvasEffects {
    private String serverDomain;
    private String addressRotate;
    private String addressExpand;
    private String addressCrop;

    public WritableImage rotate(WritableImage writableImage){
        return updateCanvasImage(writableImage, addressRotate);
    }

    public WritableImage expand(WritableImage writableImage){
        return updateCanvasImage(writableImage, addressExpand);
    }

    public WritableImage crop(WritableImage writableImage){
        return updateCanvasImage(writableImage, addressCrop);
    }



    private WritableImage updateCanvasImage(WritableImage writableImage, String url) {
        try {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            byte[] imageBytes = baos.toByteArray();
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<byte[]> response = restTemplate.postForEntity("http://"+ serverDomain + url, imageBytes, byte[].class);

            ByteArrayInputStream bais = new ByteArrayInputStream(Objects.requireNonNull(response.getBody()));
            BufferedImage newImage = ImageIO.read(bais);
            return SwingFXUtils.toFXImage(newImage, null);

        } catch (Exception e) {
            throw new EffectMakingException("Can't make effect: ");
        }
    }


}
