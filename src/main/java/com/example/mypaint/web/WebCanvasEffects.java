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
import java.io.IOException;
import java.util.Objects;

@AllArgsConstructor
public class WebCanvasEffects {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String serverDomain;
    private final String addressRotate;
    private final String addressResize;
    private final String changeCanvasSize;
    private final String cropImage;
    private final String moveImage;

    /**
     * Повернути зображення на 90 градусів
     * @param writableImage зображення
     * @return перевернуте зображення
     */
    public WritableImage rotate(WritableImage writableImage){
        return updateCanvasImage(writableImage, addressRotate);
    }

    /**
     * Змінити розміри зображення, розтянувши або звуживши його
     * @param writableImage зображення
     * @param width нова ширина
     * @param height нова висота
     * @return змінене зображення
     */
    public WritableImage resizeImage(WritableImage writableImage, int width, int height){
        return updateCanvasImage(writableImage, width, height, addressResize);
    }

    /**
     * Змінити розмір полотна; зображення залишаєтсья тих же розмірів, на новій площі прозорий фон
     * @param writableImage зображення
     * @param width нова ширина
     * @param height нова висота
     * @return змінене зображення
     */
    public WritableImage changeCanvasSize(WritableImage writableImage, int width, int height){
        return updateCanvasImage(writableImage, width, height, changeCanvasSize);
    }

    /**
     * Обрізати зображення за двома точками.
     * @param writableImage зображення
     * @param x1 х-координата першої точки
     * @param y1 y-координата першої точки
     * @param x2 х-координата другої точки
     * @param y2 y-координата другої точки
     * @return обрізане зображення
     */
    public WritableImage cropImage(WritableImage writableImage, int x1, int y1, int x2, int y2){
        return updateCanvasImage(writableImage, x1, y1, x2, y2, cropImage);
    }

    /**
     * здвинути зображення на задані координати; при цьому збільшиться розмір зображення,
     * на місці нової площі буде прозорий фон
     * @param writableImage зображення
     * @param width oX
     * @param height oY
     * @return здвинуте зображення
     */
    public WritableImage moveImage(WritableImage writableImage, int width, int height){
        return updateCanvasImage(writableImage, width, height, moveImage);
    }

    /**
     * Надсилає POST-запит на зміну зображення без аргументів. Зображення передається у тілі запиту
     * @param writableImage зображення
     * @param url адреса запиту
     * @return змінене зображення
     */
    private WritableImage updateCanvasImage(WritableImage writableImage, String url) {
        try {
            ResponseEntity<byte[]> response = restTemplate.postForEntity("http://"+ serverDomain + url,
                    imageToByteArray(writableImage), byte[].class);
            return byteArrayToImage(Objects.requireNonNull(response.getBody()));
        } catch (Exception e) {
            throw new EffectMakingException("Can't make effect: ");
        }
    }

    /**
     * Надсилає POST-запит на зміну зображення з параметрами аргументів у адресні строці.
     * Зображення передається у тілі запиту
     * @param writableImage зображення
     * @param url адреса запиту
     * @param width ширина
     * @param height висота
     * @return змінене зображення
     */
    private WritableImage updateCanvasImage(WritableImage writableImage, int width, int height, String url) {
        try {
            String req = String.format("http://%s%s?width=%d&height=%d", serverDomain, url, width, height);
            ResponseEntity<byte[]> response = restTemplate.postForEntity(req,
                    imageToByteArray(writableImage), byte[].class);
            return byteArrayToImage(Objects.requireNonNull(response.getBody()));
        } catch (Exception e) {
            throw new EffectMakingException("Can't make effect: ", e);
        }
    }

    /**
     * Надсилає POST-запит на зміну зображення з параметрами аргументів у адресні строці.
     * Зображення передається у тілі запиту
     * @param writableImage зображення
     * @param url адреса запиту
     * @param x1 x1
     * @param y1 y1
     * @param x2 x2
     * @param y2 y2
     * @return змінене зображення
     */
    private WritableImage updateCanvasImage(WritableImage writableImage, int x1, int y1, int x2, int y2, String url) {
        try {
            String req = String.format("http://%s%s?x1=%d&y1=%d&x2=%d&y2=%d", serverDomain, url, x1, y1, x2, y2);
            ResponseEntity<byte[]> response = restTemplate.postForEntity(req,
                    imageToByteArray(writableImage), byte[].class);
            return byteArrayToImage(Objects.requireNonNull(response.getBody()));
        } catch (Exception e) {
            throw new EffectMakingException("Can't make effect: ", e);
        }
    }


    /**
     * Перетворює зображення у масив байтів.
     * @param writableImage зображення
     * @return масив байтів
     */
    private byte[] imageToByteArray(WritableImage writableImage) throws IOException {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        return baos.toByteArray();
    }

    /**
     * Перетворює масив байтів у зображення
     * @param array масив байтів
     * @return зображення
     */
    private WritableImage byteArrayToImage(byte[] array) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(Objects.requireNonNull(array));
        BufferedImage newImage = ImageIO.read(bais);
        return SwingFXUtils.toFXImage(newImage, null);
    }

}
