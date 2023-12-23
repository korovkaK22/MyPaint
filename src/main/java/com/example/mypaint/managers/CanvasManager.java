package com.example.mypaint.managers;

import com.example.mypaint.tools.ToolParams;
import com.example.mypaint.tools.Tool;
import com.example.mypaint.utils.CanvasUtil;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public CanvasManager(ObservableList<Node> canvases) {
        this.canvases = canvases;
    }

    public Canvas getCanvas(int position) {
        return (Canvas) canvases.get(position);
    }

    public void addNewCanvasOnTop(Canvas canvas, int position) {
        initCanvasEvents(canvas);
        if (canvases.size() == 0) {
            canvases.add(canvas);
        } else {
            canvases.add(position + 1, canvas);
        }
        setSelectedCanvas(canvas);
    }


    public void removeSelectedCanvas() {
        int index = canvases.indexOf(selectedCanvas);
        canvases.remove(selectedCanvas);

        if (canvases.isEmpty()) {
            selectedCanvas = null;
        } else if (index < canvases.size()) {
            selectedCanvas = (Canvas) canvases.get(index);
        } else {
            selectedCanvas = (Canvas) canvases.get(index - 1);
        }
    }

    public void setSelectedCanvas(int position) {
        if (canvases.size() != 0) {
            selectedCanvas = (Canvas) canvases.get(position);
        }
    }

    public void setSelectedCanvas(Canvas canvas) {
        selectedCanvas = canvas;
    }

    public Canvas getSelectedCanvas() {
        return selectedCanvas;
    }

    public CanvasMemento getMemento() {
        List<Canvas> list = new ArrayList<>();
        canvases.forEach(e -> {
            Canvas canvas = CanvasUtil.getCanvasCopyWithoutEvents((Canvas) e);
            initCanvasEvents(canvas);
            list.add(canvas);
        });


        return new CanvasMemento(list, selectedCanvas == null ? null : list.get(canvases.indexOf(selectedCanvas)), width, height);
    }

    public void setMemento(CanvasMemento memento) {
        List<Canvas> list = memento.getCanvases();
        canvases.clear();
        canvases.addAll(list);
        setSelectedCanvas(memento.getSelectedCanvas());
        width = memento.width;
        height = memento.height;
    }

    public void changeCanvasesSize(double width, double height) {
        this.width = width;
        this.height = height;
        int position = canvases.indexOf(selectedCanvas);
        List<Canvas> list = new ArrayList<>();
        canvases.forEach(e -> {
            Canvas canvas = CanvasUtil.resizeCanvasWithoutEvents((Canvas) e, width, height);
            initCanvasEvents(canvas);
            list.add(canvas);
        });
        canvases.clear();
        canvases.addAll(list);
        setSelectedCanvas(list.get(position));
    }

    public List<Canvas> getCanvases() {
        List<Canvas> result = new ArrayList<>();
        canvases.forEach(e -> result.add((Canvas) e));
        return result;
    }

    public int canvasNumber() {
        return canvases.size();
    }


    /**
     * Передивляється розміри канвасу, якщо вони більші, ніж у всіх інших, підганяє інші канваси по розміру
     *
     * @param canvas Канвас для перевірки
     * @param toCanvasSize чи зменшувати розмір під зображення
     */
    public void verifySizes(Canvas canvas, boolean toCanvasSize) {
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();
            if (toCanvasSize) {
                changeCanvasesSize(canvasWidth, canvasHeight);
            } else {
                changeCanvasesSize(Math.max(canvasWidth, width), Math.max(canvasHeight, height));
            }
        }

        public void verifySizes(Canvas canvas){
            verifySizes(canvas, false);
        }

        public void initCanvasEvents (Canvas canvas){
            canvas.setOnMouseDragged(e -> tool.makeActionOnDragged(selectedCanvas, e, toolParams));
            canvas.setOnMousePressed(e -> tool.makeActionOnPressed(selectedCanvas, e, toolParams));
            canvas.setOnMouseReleased(e -> tool.makeActionOnReleased(selectedCanvas, e, toolParams));
            canvas.setOnMouseClicked(e -> tool.makeActionOnClicked(selectedCanvas, e, toolParams));
        }

    }
