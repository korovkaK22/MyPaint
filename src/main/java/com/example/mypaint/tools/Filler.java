package com.example.mypaint.tools;

import com.example.mypaint.actions.UserActionHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;


public class Filler extends Tool{

    public Filler(UserActionHolder userActionHolder) {
        super(userActionHolder);
    }

    @Override
    public void makeActionOnClicked(Canvas canvas, MouseEvent event, ToolParams toolParams) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Color color = toolParams.getColor();
        gc.setFill(color);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
