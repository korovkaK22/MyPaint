package com.example.mypaint.controllers;

import com.example.mypaint.actions.UserActionHolder;
import com.example.mypaint.managers.CanvasManager;
import com.example.mypaint.managers.ListViewManager;
import com.example.mypaint.tools.*;
import com.example.mypaint.utils.CanvasFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;


public class MainControllerInitializer {
    private final UserActionHolder userActionHolder;
    private final MainController controller;
    private final ListViewManager listViewManager;
    private final CanvasManager canvasManager;
    private final MenuButton toolsButton;
    private final ChoiceBox<Integer> sizeChooser;
    private final ColorPicker colorPicker;
    private final MenuItem editRedo;
    private final MenuItem editUndo;

    public MainControllerInitializer(MainController controller, UserActionHolder userActionHolder) {
        this.userActionHolder = userActionHolder;
        this.controller = controller;
        this.listViewManager = controller.getListViewManager();
        this.canvasManager = controller.getCanvasManager();
        this.toolsButton = controller.getToolsButton();
        this.sizeChooser = controller.getSizeChooser();
        this.colorPicker = controller.getColorPicker();
        this.editRedo = controller.getEditRedo();
        this.editUndo = controller.getEditUndo();
        init();
    }

    private void init(){
        userActionHolder.addUserAction();
        initSizeChooser();
        initializeFirstLayer();
        initColorPicker();
        initToolPicker();
        initUndoRedoMenu();
    }

    private void initUndoRedoMenu() {
        editUndo.disableProperty().bind(userActionHolder.undoAvailableProperty().not());
        editRedo.disableProperty().bind(userActionHolder.redoAvailableProperty().not());
    }

    private void initializeFirstLayer(){
        listViewManager.addNewLayerOnTop("Layer 0");
        listViewManager.setSelectedPosition(0);
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
        addToolToButton("Pencil", new Pencil(userActionHolder));
        addToolToButton("Rubber", new Rubber(userActionHolder));
        addToolToButton("Filler", new Filler(userActionHolder));
        Brush brush = new Brush(userActionHolder);
        addToolToButton("Brush", brush );
        canvasManager.setTool(brush);
        toolsButton.setText(toolsButton.getItems().get(3).getText());

    }

    private void addToolToButton(String name, Tool tool){
        MenuItem result = new MenuItem(name);
        result.setOnAction(e -> {canvasManager.setTool(tool);
            toolsButton.setText(result.getText());
        });
        toolsButton.getItems().add(result);
    }
}
