package com.example.mypaint.tools;

import com.example.mypaint.actions.UserActionHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class Pencil extends Tool {
    public Pencil(UserActionHolder userActionHolder) {
        super(userActionHolder);
    }


    @Override
    public void makeActionOnDragged(Canvas canvas, MouseEvent event, ToolParams toolParams) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double size = toolParams.getSize();
        Color color = toolParams.getColor();
        double x = event.getX() - size / 2;
        double y = event.getY() - size / 2;
        gc.setFill(color);
        gc.fillRect(x, y, size, size);
    }
}
