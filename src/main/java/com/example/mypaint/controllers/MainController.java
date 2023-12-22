package com.example.mypaint.controllers;

import com.example.mypaint.managers.ListViewManager;
import com.example.mypaint.managers.CanvasManager;
import com.example.mypaint.tools.*;
import com.example.mypaint.utils.factory.CanvasFactory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import lombok.Getter;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

        @FXML
        private ScrollPane canvasScrollPanel;

        @FXML
        private ColorPicker colorPicker;

        @FXML
        private MenuItem editRedo;

        @FXML
        private MenuItem editUndo;

        @FXML
        private MenuItem effectDarkness;

        @FXML
        private MenuItem effectRotate;

        @FXML
        private MenuItem effectSize;

        @FXML
        private MenuItem fileOpen;

        @FXML
        private MenuItem fileSave;

        @FXML
        private MenuItem fileSaveAs;

        @FXML
        private MenuBar menuBar;

        @FXML
        private ChoiceBox<Integer> sizeChooser;

        @FXML
        private MenuButton toolsButton;

        @FXML
        private LayersController layersFXMLController;

        @FXML
        private CanvasController canvasesFXMLController;

        @Getter
        private ListViewManager listViewManager;
        private CanvasManager canvasManager;


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                listViewManager = new ListViewManager(layersFXMLController.getLayersListView());
                canvasManager = new CanvasManager(canvasesFXMLController.getMainStackPane().getChildren(), new Brush()); //==============

                layersFXMLController.setCanvasManager(canvasManager);
                canvasesFXMLController.setCanvasManager(canvasManager);
                layersFXMLController.setListViewManager(listViewManager);
                canvasesFXMLController.setListViewManager(listViewManager);

                initSizeChooser();
                initializeFirstLayer();
                initColorPicker();
                initToolPicker();
        }



        //========= Класи ініціалізації ========
        private void initializeFirstLayer(){
                listViewManager.addNewLayerOnTop("Шар 0");
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
                MenuItem item1 = new MenuItem("Pencil");
                item1.setOnAction(e -> {canvasManager.setTool(new Pencil());
                        toolsButton.setText(item1.getText());
                });
                MenuItem item2 = new MenuItem("Rubber");
                item2.setOnAction(e -> {canvasManager.setTool(new Rubber());
                        toolsButton.setText(item2.getText());
                });
                MenuItem item3 = new MenuItem("Filler");
                item3.setOnAction(e -> {canvasManager.setTool(new Filler());
                        toolsButton.setText(item3.getText());
                });
                MenuItem item4 = new MenuItem("Mover");
                item4.setOnAction(e -> {canvasManager.setTool(new Mover());
                        toolsButton.setText(item4.getText());
                });
                MenuItem item5 = new MenuItem("Brush");
                item5.setOnAction(e -> {canvasManager.setTool(new Brush());
                        toolsButton.setText(item5.getText());
                });

                toolsButton.getItems().addAll(item1, item2, item3, item4, item5);
                toolsButton.setText(item5.getText());
        }

}

