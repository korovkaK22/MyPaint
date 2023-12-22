package com.example.mypaint.tools;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public abstract class Tool {

    public abstract void makeAction(Canvas canvas, MouseEvent event, ToolParams toolParams);
}
