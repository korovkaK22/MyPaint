package com.example.mypaint.managers;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CanvasMemento {
    List<Canvas> canvases;
    Canvas selectedCanvas;
    double width;
    double height;
}
