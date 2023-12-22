package com.example.mypaint.controllers;

import com.example.mypaint.managers.CanvasManager;
import com.example.mypaint.managers.ListViewManager;
import com.example.mypaint.tools.*;
import com.example.mypaint.utils.factory.CanvasFactory;
import com.sun.tools.javac.Main;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;


public class MainControllerInitializer {
    private MainController controller;
    private ListViewManager listViewManager;
    private CanvasManager canvasManager;
    private MenuButton toolsButton;
    private ChoiceBox<Integer> sizeChooser;
    private ColorPicker colorPicker;

    public MainControllerInitializer(MainController controller) {
        this.controller = controller;
        this.listViewManager = controller.getListViewManager();
        this.canvasManager = controller.getCanvasManager();
        this.toolsButton = controller.getToolsButton();
        this.sizeChooser = controller.getSizeChooser();
        this.colorPicker = controller.getColorPicker();
        init();
    }

    private void init(){
        initSizeChooser();
        initializeFirstLayer();
        initColorPicker();
        initToolPicker();
    }

    private void initializeFirstLayer(){
        listViewManager.addNewLayerOnTop("Layer 0");
        listViewManager.chooseLayer(0);
        canvasManager.addNewCanvasOnTop(CanvasFactory.createFilledCanvas(canvasManager.getWidth(), canvasManager.getHeight(), Color.WHITE), 0);
        canvasManager.setSelectedCanvas(0);
    }

    private void initSizeChooser(){
        ObservableList<Integer> items = sizeChooser.getItems();
        for (int i = 1; i <= 10; i++) {
            items.add(i);
        }
        sizeChooser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            canvasManager.getToolParams().setSize(newValue * 2);
        });
        sizeChooser.setValue(5);
    }

    private void initColorPicker(){
        colorPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            canvasManager.getToolParams().setColor(newValue);
        });
    }

    private void initToolPicker(){
        addToolToButton("Pencil", new Pencil());
        addToolToButton("Rubber", new Rubber());
        addToolToButton("Filler", new Filler());
        addToolToButton("Brush",  new Brush());
        addToolToButton("1111",  new Mover());

        toolsButton.setText(toolsButton.getItems().get(4).getText());
    }

    private void addToolToButton(String name, Tool tool){
        MenuItem result = new MenuItem(name);
        result.setOnAction(e -> {canvasManager.setTool(tool);
            toolsButton.setText(result.getText());
        });
        toolsButton.getItems().add(result);
    }
}
