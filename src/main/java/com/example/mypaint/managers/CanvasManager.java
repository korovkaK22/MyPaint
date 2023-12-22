package com.example.mypaint.managers;

import com.example.mypaint.tools.ToolParams;
import com.example.mypaint.tools.Tool;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import lombok.Setter;

public class CanvasManager {
    @Getter
    private ObservableList<Node> canvases;
    private Canvas selectedCanvas;
    @Getter
    @Setter
    private Tool tool;
    @Getter
    private final ToolParams toolParams = new ToolParams();

    @Getter
    @Setter
    private double width = 700;
    @Getter
    @Setter
    private double height = 500;

    public CanvasManager(ObservableList<Node> canvases, Tool tool) {
        this.canvases = canvases;
        this.tool = tool;
    }

    public void makeToolAction(MouseEvent event) {
        tool.makeAction(selectedCanvas, event, toolParams);
    }

    public Canvas getCanvas(int position) {
        return (Canvas) canvases.get(position);
    }

    public void addNewCanvasOnTop(Canvas canvas, int position) {
        canvas.setOnMouseDragged(this::makeToolAction);
        if (canvases.size() == 0) {
            canvases.add(canvas);
        } else {
            canvases.add(position+1, canvas);
        }
        setSelectedCanvas(canvas);
    }

    public void removeSelectedCanvas() {
        int index = canvases.indexOf(selectedCanvas);
        // Видалення поточного selectedCanvas
        canvases.remove(selectedCanvas);

        if (canvases.isEmpty()) {
            selectedCanvas = null; // Немає доступних Canvas
        } else if (index < canvases.size()) {
            // Якщо поточний індекс досі в межах списку після видалення
            selectedCanvas = (Canvas) canvases.get(index);
        } else {
            // Якщо поточний індекс виходить за межі списку (selectedCanvas був останнім), вибираємо попередній
            selectedCanvas = (Canvas) canvases.get(index - 1);
        }
    }

    public void setSelectedCanvas(int position) {
        if (canvases.size() != 0) {
            System.out.println("Канвас поміняно на: " + canvases.get(position)); //============
            selectedCanvas = (Canvas) canvases.get(position);
        }
    }

    public void setSelectedCanvas(Canvas canvas) {
        selectedCanvas = canvas;
    }


    public void addNewCanvasOnTop(Canvas canvas) {
        canvas.setOnMouseDragged(this::makeToolAction);
        canvases.add(canvas);
    }

    public void removeCanvas(int position) {
        canvases.remove(position);
    }

    public void changeCanvasesSize(double width, double height) {
        this.width = width;
        this.height = height;
        //todo зробить зміну розмірів всіх шарів
    }


}
