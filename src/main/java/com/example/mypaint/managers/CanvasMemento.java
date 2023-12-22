package com.example.mypaint.managers;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CanvasMemento {
    ObservableList<Node> canvases;
    Canvas selectedCanvas;
    double width;
    double height;

}
