package com.example.mypaint.tools;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public abstract class Tool {

    public void makeActionOnDragged(Canvas canvas, MouseEvent event, ToolParams toolParams){};

    public void makeActionOnPressed(Canvas canvas, MouseEvent event, ToolParams toolParams){};

    public void makeActionOnReleased(Canvas canvas, MouseEvent event, ToolParams toolParams){};

    public void makeActionOnClicked(Canvas canvas, MouseEvent event, ToolParams toolParams){};

}
