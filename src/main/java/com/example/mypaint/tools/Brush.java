package com.example.mypaint.tools;

import com.example.mypaint.actions.UserActionHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public class Brush extends Tool{

    public Brush(UserActionHolder userActionHolder) {
        super(userActionHolder);
    }


    @Override
    public void makeActionOnDragged(Canvas canvas, MouseEvent event, ToolParams toolParams) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double size = toolParams.getSize();
        Color color = toolParams.getColor();
        Color transparentColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), 0.5);
        RadialGradient gradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                new Stop(0, color), new Stop(1, transparentColor));
        gc.setFill(gradient);
        double x = event.getX() - size / 2;
        double y = event.getY() - size / 2;
        gc.fillOval(x, y, size, size);
    }
}
