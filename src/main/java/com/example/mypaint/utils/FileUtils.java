package com.example.mypaint.utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class FileUtils {
    /**
     * Зберігає знімок у вигляді файлу у систему користувача
     * @param file файл, куди зберігається картинка
     * @param image картинка
     */
    public static void savePictureOnFile(File file, WritableImage image){
        try {
            java.awt.image.BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
            String formatName =  "png";
            String fileName = file.getName();
            int dotIndex = fileName.lastIndexOf('.');
            if (dotIndex > 0) {
                formatName = fileName.substring(dotIndex + 1);
            }
            ImageIO.write(bufferedImage, formatName, file);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Trouble with saving image: " + e.getMessage());
        }
    }
}
