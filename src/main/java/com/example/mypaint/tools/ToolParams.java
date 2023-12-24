package com.example.mypaint.tools;

import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

/**
 * Клас для збереження налаштувань інструменту
 */
@Getter
@Setter
public class ToolParams {
   private int size = 1;
   private Color color = Color.BLACK;

}
