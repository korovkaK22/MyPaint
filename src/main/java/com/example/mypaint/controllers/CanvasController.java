package com.example.mypaint.controllers;

import com.example.mypaint.managers.CanvasManager;
import com.example.mypaint.managers.ListViewManager;
import com.example.mypaint.utils.CanvasUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class CanvasController implements Initializable {
    @Getter @Setter
    private ListViewManager listViewManager;
    @Getter @Setter
    private CanvasManager canvasManager;

    @FXML @Getter
    private StackPane mainStackPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



}
