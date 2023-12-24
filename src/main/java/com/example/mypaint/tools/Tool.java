package com.example.mypaint.tools;

import com.example.mypaint.actions.UserActionHolder;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public abstract class Tool {
    protected UserActionHolder userActionHolder;

    public Tool(UserActionHolder userActionHolder) {
        this.userActionHolder = userActionHolder;
    }

    public void makeActionOnDragged(Canvas canvas, MouseEvent event, ToolParams toolParams){}

    public void makeActionOnPressed(Canvas canvas, MouseEvent event, ToolParams toolParams){
        userActionHolder.addUserAction();
    }

    public void makeActionOnReleased(Canvas canvas, MouseEvent event, ToolParams toolParams){
    }

    public void makeActionOnClicked(Canvas canvas, MouseEvent event, ToolParams toolParams){}

}
